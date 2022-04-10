package com.example.orders.dto;

import lombok.Data;

import javax.validation.constraints.Size;
import java.util.Date;

@Data
public class OrderRequest {

    @Size(min=0,max=10,message = "Size should be not greater than 10")
    private String category;

    private Date date;

    private Long userId;

    private Long productId;
}
