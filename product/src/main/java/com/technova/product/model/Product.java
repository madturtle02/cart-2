package com.technova.product.model;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name="product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String category;

    @Column
    private Double unitPrice;

    @Column
    private int quantityAvailable;

}
