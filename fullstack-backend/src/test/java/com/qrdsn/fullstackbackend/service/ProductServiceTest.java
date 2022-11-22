package com.qrdsn.fullstackbackend.service;

import com.qrdsn.fullstackbackend.model.Product;
import com.qrdsn.fullstackbackend.model.dto.ProductDTO;
import com.qrdsn.fullstackbackend.repository.ProductRespository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {


    @Mock
    private ProductRespository productRepository;

    @InjectMocks
    private ProductService productService;

/*    @BeforeEach
    void setup() throws Exception{
        productService = new ProductService(productRepository);

        List<Product> productList = new ArrayList<>();
        Product product = new Product();

        //product 1
        product.setId(Long.valueOf(1));
        product.setName("Golden Delicious");

        productList.add(product);

        //product 2
        product = new Product();
        product.setId(Long.valueOf(2));
        product.setName("Gala");

        //Fill mock repository database
//        productRepository.saveAll(productList);

    }
    */

    @Test
    public void create(){
        ProductDTO product = new ProductDTO();
        product.setId((long)1);
        product.setName("Granny Smith");

        when(productService.create(any(ProductDTO.class))).thenReturn(product);

        ProductDTO savedProduct = productService.create(product);


        assertTrue(savedProduct != null);
        verify(productRepository).save(any(Product.class));

    }


    @Test
    void findAll() {
    }

    @Test
    void findById() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}