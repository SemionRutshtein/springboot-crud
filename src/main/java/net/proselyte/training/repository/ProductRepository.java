package net.proselyte.training.repository;

import net.proselyte.springbootdemo.model.User;
import net.proselyte.training.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
