package com.lcwd.order.entities;

import lombok.*;
import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @Column(name = "OrderID")
    private String orderId;
    @Column(name = "UserID")
    private String userId;
    @Column(name = "ProductID")
    private String productId;
    @Column(name = "quantity")
    private  int quantity;
    @Column(name = "total_price")
    private  double totalPrice;
    @Column(name = "order_date")
    private Date orderDate;

}
