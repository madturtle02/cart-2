package com.example.orders.controller;

import com.example.orders.dto.OrderRequest;
import com.example.orders.exception.OrderNotFound;
import com.example.orders.model.Order;
import com.example.orders.service.OrderService;
import com.example.orders.utils.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/orders")
    public ResponseEntity createOrder(@RequestBody OrderRequest orderRequest){
        orderService.createOrder(orderRequest);
        return ApiResponse.generateResponse(HttpStatus.OK.value(),"Order created successfully.",null,null);
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity getOrder(@PathVariable("id") Long id){
        Optional<Order> existingOrder= orderService.getOrderById(id);
        if(existingOrder.isPresent()){
            log.info("Order with {} is fetched.",existingOrder.get().getId());
            return ApiResponse.generateResponse(HttpStatus.OK.value(),"Order fetched successfully", existingOrder,null);
        }
        return ApiResponse.generateResponse(HttpStatus.NOT_FOUND.value(),"Order with id "+id+" not found", null,"Order not found.");
    }

    @GetMapping("/orders")
    public ResponseEntity getAll(){
        List<Order> orders= orderService.getAll();
        if(orders.size()>0){
            return ApiResponse.generateResponse(HttpStatus.OK.value(),"Order fetched successfully", orders,null);
        }
        return ApiResponse.generateResponse(HttpStatus.NOT_FOUND.value(),"Orders not found", null,"Order not found.");
    }

    @PutMapping("/orders/{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @Valid @RequestBody OrderRequest orderRequest){
        Optional<Order> existingOrder = orderService.getOrderById(id);
        if(existingOrder.isPresent()){
            log.info("Order with {} is fetched.",existingOrder.get().getId());
            orderService.updateOrder(existingOrder.get(),orderRequest);
            return ApiResponse.generateResponse(HttpStatus.OK.value(), "Order updated successfully.",existingOrder,null);
        }
        return ApiResponse.generateResponse(HttpStatus.OK.value(), "Order not found.",null,"Order not found.");
//        throw new OrderNotFound("Order with id "+id+"not found in our database.");
    }

    @DeleteMapping("/orders/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) throws OrderNotFound{
        Optional<Order> existingOrder = orderService.getOrderById(id);
        if(existingOrder.isPresent()){
            log.info("Order with {} is fetched.",existingOrder.get().getId());
            orderService.deleteOrder(existingOrder.get());
            return ApiResponse.generateResponse(HttpStatus.OK.value(), "Order deleted successfully",null,null);
        }
        return ApiResponse.generateResponse(HttpStatus.OK.value(), "Order not found.",null,null);
//        throw new OrderNotFound("Order with id: "+ id + " not found.");
    }
}
