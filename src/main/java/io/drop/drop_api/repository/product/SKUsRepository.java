package io.drop.drop_api.repository.product;

import io.drop.drop_api.models.entities.product.Skus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SKUsRepository extends JpaRepository<Skus, Long> {


    @Query("SELECT s FROM Skus s JOIN FETCH s.products_id p WHERE p.id = :productId")
    List<Skus> findAllByProductId(@Param("productId") Long productId);


}


