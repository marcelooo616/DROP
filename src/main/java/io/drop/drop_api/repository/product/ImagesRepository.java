package io.drop.drop_api.repository.product;

import io.drop.drop_api.models.entities.product.Images;
import io.drop.drop_api.models.entities.product.Skus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ImagesRepository extends JpaRepository<Images, Long> {

    @Query("SELECT s FROM Images s JOIN FETCH s.products p WHERE p.id = :productId")
    List<Images> findAllByProductId(@Param("productId") Long productId);
}
