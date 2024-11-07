package ru.netology.productMenager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.productMenager.AlreadyExistsException;
import ru.netology.productMenager.NotFoundException;
import ru.netology.productMenager.Product;
import ru.netology.repository.ShopRepository;

public class ProductRepositoryTest {

    ShopRepository repo = new ShopRepository();

    Product product1 = new Product(1, "Хлеб", 50);
    Product product2 = new Product(2, "Молоко", 100);
    Product product3 = new Product(3, "Яйца", 150);


    @BeforeEach
    public void setup() {
        repo.save(product1);
        repo.save(product2);
        repo.save(product3);
    }


    @Test
    public void shouldAddProduct() {
        Product newProduct = new Product(4, "Что-то", 300);
        repo.add(newProduct);

        Product[] expected = {product1, product2, product3, newProduct};
        Product[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldExceptionRemoveById() {

        Assertions.assertThrows(NotFoundException.class, () -> {
            repo.removeById(15);
        });
    }

    @Test
    public void shouldSaveProduct() {
        Product[] expected = {product1, product2, product3};
        Product[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldExceptionSaveProduct() {

        Assertions.assertThrows(AlreadyExistsException.class, () -> {
            repo.save(product2);
        });
    }
}
