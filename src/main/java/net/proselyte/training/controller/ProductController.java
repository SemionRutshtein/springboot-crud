package net.proselyte.training.controller;

import net.proselyte.springbootdemo.model.User;
import net.proselyte.training.model.Product;
import net.proselyte.training.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public String findAll(Model model) {
        List<Product> products = productService.showAll();
        model.addAttribute("products",products);
        return "product-list";
    }

    @GetMapping("/product-create")
    public String createProductForm(Product product) {
        return "product-create";
    }

    @PostMapping("/product-create")
    public String createProduct(Product product) {
        productService.saveProduct(product);
        return "redirect:/products";
    }


    @GetMapping("product-delete/{productId}")
    public String deleteProduct(@PathVariable("productId") Long productId) {
        productService.deleteProduct(productId);
        return "redirect:/products";
    }


    @GetMapping("/product-update/{productId}")
    public String updateProductForm(@PathVariable("productId") Long productId, Model model) {
        Product product = productService.findById(productId);
        model.addAttribute(product);
        return "/product-update";
    }

    @PostMapping("/product-update")
    public String updateProduct(Product product) {
        productService.saveProduct(product);
        return "redirect:/products";
    }

}
