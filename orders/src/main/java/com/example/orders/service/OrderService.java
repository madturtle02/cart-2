package com.example.orders.service;

import com.example.orders.dto.OrderRequest;
import com.example.orders.model.Order;
import com.example.orders.repo.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class OrderService {

    @Autowired
    private OrderRepository repo;

    public Order createOrder(OrderRequest orderRequest) {
        Order newOrder = new Order();
        newOrder.setCategory(orderRequest.getCategory());
        newOrder.setDate(orderRequest.getDate());
        newOrder.setUserId(orderRequest.getUserId());
        newOrder.setProductId(orderRequest.getProductId());
        return repo.save(newOrder);
    }

    public Optional<Order> getOrderById(Long id) {
        return repo.findById(id);
    }

    public List<Order> getAll() {
        return repo.findAll();
    }

    public Order updateOrder(Order order, OrderRequest orderRequest) {
        order.setCategory(orderRequest.getCategory());
        order.setDate(orderRequest.getDate());
        order.setUserId(orderRequest.getUserId());
        order.setProductId(orderRequest.getProductId());
        return repo.save(order);
    }

    public void deleteOrder(Order order) {
        repo.delete(order);
    }
}
