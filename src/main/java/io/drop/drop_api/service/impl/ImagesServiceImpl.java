package io.drop.drop_api.service.impl;

import io.drop.drop_api.models.dto.product.ImagesDTO;
import io.drop.drop_api.models.entities.product.Images;
import io.drop.drop_api.repository.product.ImagesRepository;
import io.drop.drop_api.service.ImagesService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ImagesServiceImpl  implements ImagesService {

    @Autowired
    private ImagesRepository imagesRepository;

    public List<ImagesDTO> findAllImagesDTO() {

        //retorna uma lista de images "img"
        List<Images> img = imagesRepository.findAll();


        // cria uma nova instancia do mapper
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.addMappings(new PropertyMap<Images, ImagesDTO>() {

            @Override
            protected void configure(){
                map().setProductId(source.getProducts().getId());
                map().setImageUrl(source.getUrl());

            }
        });

        return img.stream()
                .map(images -> modelMapper.map(images, ImagesDTO.class))
                .collect(Collectors.toList());
    }

}
