package org.uncc.service.orderService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.uncc.service.dto.InventoryResponse;
import org.uncc.service.dto.OrderLineItemsDto;
import org.uncc.service.dto.OrderRequest;
import org.uncc.service.model.Order;
import org.uncc.service.model.OrderLineItems;
import org.uncc.service.repository.OrderRepository;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    private final WebClient webClient;

    public void placeOrder(OrderRequest orderRequest){
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        List<OrderLineItems> orderLineItemsList =  orderRequest.getOrderLineItemsDtoList()
                .stream()
                .map(this::mapToDto).toList();
        order.setOrderLineItems(orderLineItemsList);
        List<String> listOfSkus = order.getOrderLineItems().stream().map(OrderLineItems::getSkuCode).toList();
        // call inventory Service
        InventoryResponse result[]  = webClient.get().uri("http://localhost:8082/api/inventory",
                        uriBuilder -> uriBuilder.queryParam("skuCode",listOfSkus).build())
                .retrieve().bodyToMono(InventoryResponse[].class).block();  // "http://localhost:8082/api/inventory?skuCode=ipone12&skuCode=android"  ,block used for Synchronous communication
        boolean inStock = Arrays.stream(result).allMatch(InventoryResponse::isInStock);
        if(inStock){
            orderRepository.save(order);
        }else {
            throw new IllegalStateException("Product not in inventory");
        }
    }

    private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto){
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
        return orderLineItems;
    }


}
