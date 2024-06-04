package com.lcwd.user.service.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "Orders")
public class Orders {
    @Id
    @Column(name="OrderId")
    private String orderId;
    @Column(name="User_id")
    private String userId;
    @Column(name="Product_Id")
    private String productId;
    @Column(name="Order")
    private  int order;
    @Column(name="Quantity")
    private  int quantity;
    @Column(name="TotalPrice")
    private  double totalPrice;
    @Column(name="OrderDate")
    private Date orderDate;
    @Transient
    private Products products;
}