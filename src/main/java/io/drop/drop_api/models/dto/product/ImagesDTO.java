package io.drop.drop_api.models.dto.product;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImagesDTO {

    private String imageUrl;
    private Long productId;
}
