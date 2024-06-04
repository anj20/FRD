package com.lcwd.user.service.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "Products")
public class Products {

    @Id
    @Column(name = "Id")
    private  String id;
    @Column(name = "Name")
    private  String name;
    @Column(name = "Price")
    private  double price;
    @Column(name = "Description")
    private  String description;

}
