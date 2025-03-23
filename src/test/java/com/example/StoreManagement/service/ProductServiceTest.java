package com.example.StoreManagement.service;

import com.example.StoreManagement.dto.RequestProductDto;
import com.example.StoreManagement.exceptions.ProductNotFoundException;
import com.example.StoreManagement.mapper.ProductMapper;
import com.example.StoreManagement.model.Product;
import com.example.StoreManagement.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository repository;

    @Mock
    private ProductMapper productMapper;

    @InjectMocks
    private ProductService productService;


    @Test
    void shouldAddProduct() {
        //given
        RequestProductDto dto = new RequestProductDto("Laptop",  BigDecimal.valueOf(2000), 1);
        Product mapped = new Product(null, "Laptop", BigDecimal.valueOf(2000), 1, null, null, null);
        Product saved = new Product(1L, "Laptop", BigDecimal.valueOf(2000), 1, null, null, null);
        when(productMapper.toProduct(dto)).thenReturn(mapped);
        when(repository.save(mapped)).thenReturn(saved);

        //when
        Product result = productService.addProduct(dto);

        //then
        assertEquals(1L, result.getId());
        verify(repository).save(mapped);
    }

    @Test
    void shouldReturnProductById() {
        //given
        Product product = new Product(1L, "Smart TV", BigDecimal.valueOf(1000), 10, null, null, null);
        when(repository.findById(1L)).thenReturn(Optional.of(product));

        //when
        Product result = productService.getProductById(1L);

        //then
        assertEquals("Smart TV", result.getName());
        verify(repository).findById(1L);
    }

    @Test
    void shouldThrowWhenProductNotFound() {
        //given
        when(repository.findById(99L)).thenReturn(Optional.empty());

        //when
        //then
        assertThatThrownBy(() -> productService.getProductById(99L))
                .isInstanceOf(ProductNotFoundException.class)
                .hasMessage("Product not found");

        verify(repository).findById(99L);
    }

    @Test
    void shouldGetAllProducts() {
        //given
        List<Product> products = List.of(
                new Product(1L, "IPhone", BigDecimal.valueOf(500), 20, null, null, null));
        when(repository.findAll()).thenReturn(products);

        //when
        List<Product> result = productService.getAll();

        //then
        assertEquals(1, result.size());
        assertEquals("IPhone", result.get(0).getName());
    }

    @Test
    void shouldChangeProductPrice() {
        //given
        Product product = new Product(1L, "Tablet", BigDecimal.valueOf(300), 5, null, null, null);
        when(repository.findById(1L)).thenReturn(Optional.of(product));
        when(repository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        //when
        Product result = productService.changePrice(1L, BigDecimal.valueOf(350));

        //then
        assertEquals( BigDecimal.valueOf(350), result.getPrice());
    }

    @Test
    void shouldChangeProductQuantity() {
        //given
        Product product = new Product(1L, "Monitor", BigDecimal.valueOf(400), 10, null, null, null);
        when(repository.findById(1L)).thenReturn(Optional.of(product));
        when(repository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        //when
        Product result = productService.changeQuantity(1L, 15);

        //then
        assertEquals(15, result.getQuantity());
    }

    @Test
    void shouldRemoveProduct() {
        //given
        Product product = new Product(1L, "Keyboard", BigDecimal.valueOf(100), 7, null, null, null);
        when(repository.findById(1L)).thenReturn(Optional.of(product));

        //when
        productService.removeProduct(1L);

        //then
        verify(repository).delete(product);
    }

}