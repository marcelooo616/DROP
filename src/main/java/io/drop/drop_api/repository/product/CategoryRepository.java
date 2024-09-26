package io.drop.drop_api.repository.product;

import io.drop.drop_api.models.entities.product.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
