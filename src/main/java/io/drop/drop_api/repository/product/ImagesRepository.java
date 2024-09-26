package io.drop.drop_api.repository.product;

import io.drop.drop_api.models.entities.product.Images;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImagesRepository extends JpaRepository<Images, Integer> {
}
