package io.drop.drop_api.controller.product;


import io.drop.drop_api.controller.CrudRepository;
import io.drop.drop_api.models.dto.product.ImagesDTO;
import io.drop.drop_api.models.entities.product.Images;
import io.drop.drop_api.repository.product.ImagesRepository;
import io.drop.drop_api.service.impl.ImagesServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/image")
public class ImagesController implements CrudRepository<Images, Long> {

    ImagesRepository imagesRepository;
    ImagesServiceImpl imagesService;

    public ImagesController(ImagesRepository imagesRepository, ImagesServiceImpl imagesService) {
        this.imagesRepository = imagesRepository;
        this.imagesService = imagesService;
    }


    @Override
    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Images save(@RequestBody Images entity) {
        return imagesRepository.save(entity);
    }

    @Override
    public Images findById(Long aLong) {
        //implementação posterior
        return null;
    }

    @Override
    @GetMapping("/show/all")
    public List<Images> findAll() {
        return imagesRepository.findAll();
    }


    @GetMapping("/show/all/{product_id}")
    public List<Images> findAllByProductId(@PathVariable Long product_id) {
        return imagesRepository.findAllByProductId(product_id);
    }

    @Override
    public void update(Images entity) {
        //implementação posterior
    }

    @Override
    public void deleteById(Long aLong) {
        //implementação posterior
    }
}
