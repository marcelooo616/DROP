package io.drop.drop_api.repository.product;

import io.drop.drop_api.models.entities.product.Products;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsRepository extends JpaRepository<Products, Long> {
}
