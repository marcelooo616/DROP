package io.drop.drop_api.controller.product;

import io.drop.drop_api.controller.CrudRepository;
import io.drop.drop_api.exeption.BusinessRule;
import io.drop.drop_api.models.entities.product.Category;
import io.drop.drop_api.repository.product.CategoryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoyController implements CrudRepository<Category, Long> {


    CategoryRepository categoryRepository;

    public CategoyController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    @Override
    public Category save(@RequestBody Category entity) {
        System.out.println(entity.toString());
        return categoryRepository.save(entity);
    }

    @GetMapping("/get/{category_id}")
    @Override
    public Category findById(@PathVariable Long category_id) {
        return categoryRepository.findById(category_id)
                .orElseThrow(() -> new BusinessRule(HttpStatus.NOT_FOUND,"Id nao encontrado"));
    }

    @GetMapping("/show/all")
    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public void update(Category entity) {

    }

    @Override
    public void deleteById(Long aLong) {

    }
}
