/*package io.drop.drop_api.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "ProductDetails")
public class ProductDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String material;

    @Column(length = 50)
    private String cor;

    @Column(length = 50)
    private String tamanho;

    @Column(length = 50)
    private String peso;

    @Column(columnDefinition = "TEXT")
    private String beneficios;

    @Column(columnDefinition = "TEXT")
    private String tecnologias;

    @Column(length = 100)
    private String origem;

    @Column(length = 50)
    private String genero;

    @Column(columnDefinition = "TEXT")
    private String marca;

    @Column(columnDefinition = "TEXT")
    private String categoria;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    @OneToOne(mappedBy = "productDetails", cascade = CascadeType.ALL)
    private Products products;
}*/
