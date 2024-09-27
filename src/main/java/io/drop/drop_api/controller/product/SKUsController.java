package io.drop.drop_api.controller.product;


import io.drop.drop_api.controller.CrudRepository;
import io.drop.drop_api.models.entities.product.Skus;
import io.drop.drop_api.repository.product.SKUsRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/SKUs")
public class SKUsController implements CrudRepository<Skus, Long> {


    SKUsRepository skUsRepository;

    public SKUsController(SKUsRepository skUsRepository) {
        this.skUsRepository = skUsRepository;
    }


    @Override
    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Skus save(@RequestBody Skus entity) {
        return skUsRepository.save(entity);
    }

    @Override
    public Skus findById(Long aLong) {
        return null;
    }

    @Override
    @GetMapping("/show/all")
    public List<Skus> findAll() {
        return skUsRepository.findAll();
    }


    @GetMapping("/show/all/{product_id}")
    public List<Skus> findAllByProductId(@PathVariable Long product_id) {
        return skUsRepository.findAllByProductId(product_id);
    }

    @Override
    public void update(Skus entity) {

    }

    @Override
    public void deleteById(Long aLong) {

    }
}
