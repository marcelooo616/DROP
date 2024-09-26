package io.drop.drop_api.controller.product;


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
@RequiredArgsConstructor
public class ImagesController {

    ImagesRepository imagesRepository;
    ImagesServiceImpl imagesService;

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Images save(@RequestBody Images images){
        return imagesRepository.save(images);
    }

    @GetMapping("/show/all")
    public List<ImagesDTO> showAll(){
        return imagesService.findAllImagesDTO();
    }


}
