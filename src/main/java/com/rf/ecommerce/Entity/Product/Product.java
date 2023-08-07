package com.rf.ecommerce.Entity.Product;

import com.rf.ecommerce.Entity.Admin.Admin;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Table
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Size(min = 2,max = 255)
    private String title;
    private String description;
    @ManyToOne
    @JoinColumn(name = "sahibi")
    private Admin admin;
    @Lob
    private String image;
    @NotNull
    private String Category;
    @NotNull
    private int price;
}
