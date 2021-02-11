package net.proselyte.training.service;

import net.proselyte.training.model.Product;
import net.proselyte.training.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    public Product findById(Long product_id) {
      return productRepository.getOne(product_id);
    }

    public List<Product> showAll() {
        return productRepository.findAll();
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProduct(Long product_id) {
        productRepository.deleteById(product_id);
    }
}
