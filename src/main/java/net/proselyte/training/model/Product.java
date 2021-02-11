package net.proselyte.training.model;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name="products")
public class Product {

    @Id
    @Column(name = "product_id")
    private long productId;

    @Column(name = "product_name")
    private String productName;

}
