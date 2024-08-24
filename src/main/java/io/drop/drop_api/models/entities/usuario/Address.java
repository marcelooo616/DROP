package io.drop.drop_api.models.entities.usuario;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "Address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @OneToOne
    @JoinColumn(name = "user_id")
    @NotEmpty(message = "Campo obrigatorio")
    private Usuarios usuarios;

    @Column
    private String road;

    @Column
    private String city;

    @Column
    private String state;

    @Column
    private String postal_code;
}
