package net.proselyte.training.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import net.proselyte.training.model.Product;
import net.proselyte.training.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {ProductController.class})
@ExtendWith(SpringExtension.class)
public class ProductControllerTest {
    @Autowired
    private ProductController productController;

    @MockBean
    private ProductService productService;

    @Test
    public void testDeleteProduct() throws Exception {
        doNothing().when(this.productService).deleteProduct((Long) any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/product-delete/{productId}", 1L);
        MockMvcBuilders.standaloneSetup(this.productController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("redirect:/products"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/products"));
    }

    @Test
    public void testFindAll() throws Exception {
        when(this.productService.showAll()).thenReturn(new ArrayList<Product>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/products");
        MockMvcBuilders.standaloneSetup(this.productController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(1))
                .andExpect(MockMvcResultMatchers.model().attributeExists("products"))
                .andExpect(MockMvcResultMatchers.view().name("product-list"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("product-list"));
    }

    @Test
    public void testFindAll2() throws Exception {
        when(this.productService.showAll()).thenReturn(new ArrayList<Product>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/products");
        getResult.contentType("Not all who wander are lost");
        MockMvcBuilders.standaloneSetup(this.productController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(1))
                .andExpect(MockMvcResultMatchers.model().attributeExists("products"))
                .andExpect(MockMvcResultMatchers.view().name("product-list"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("product-list"));
    }

    @Test
    public void testUpdateProductForm() throws Exception {
        Product product = new Product();
        product.setProductName("Product Name");
        product.setProductId(123L);
        when(this.productService.findById((Long) any())).thenReturn(product);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/product-update/{productId}", 1L);
        MockMvcBuilders.standaloneSetup(this.productController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(1))
                .andExpect(MockMvcResultMatchers.model().attributeExists("product"))
                .andExpect(MockMvcResultMatchers.view().name("/product-update"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("/product-update"));
    }
}

