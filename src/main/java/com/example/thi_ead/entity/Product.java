package com.example.thi_ead.entity;

import lombok.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
    private  long productID;
    private String productName;
    private String description;
    private Date dateOfManf;
    private double price;

}