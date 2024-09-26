package io.drop.drop_api.controller.product;


import io.drop.drop_api.controller.CrudRepository;
import io.drop.drop_api.exeption.BusinessRule;
import io.drop.drop_api.models.entities.product.Products;
import io.drop.drop_api.repository.product.ProductsRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductsController implements CrudRepository<Products, Long> {

    ProductsRepository productsRepository;

    public ProductsController(ProductsRepository productRepository) {
        this.productsRepository = productRepository;
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Products save (@RequestBody Products entity){
        System.out.println(
                entity.toString()
        );
        return productsRepository.save(entity);
    }

    @GetMapping("/get/{product_id}")
    @Override
    public Products findById(@PathVariable Long product_id) {
        return productsRepository.findById(product_id)
                .orElseThrow(() -> new BusinessRule(HttpStatus.NOT_FOUND,"Id nao encontrado"));
    }

    @GetMapping("/show/all")
    @Override
    public List<Products> findAll() {
        return productsRepository.findAll();
    }

    @Override
    public void update(Products entity) {
        //Sera implementado depois kk
    }

    @Override
    public void deleteById(Long aLong) {
        //Sera implementado depois kk
    }


}
