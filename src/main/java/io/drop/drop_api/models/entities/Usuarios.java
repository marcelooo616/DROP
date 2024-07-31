package io.drop.drop_api.models.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotEmpty;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "Usuarios")
public class Usuarios {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column()
    private Integer id;

    @Column
    private String email;

    @Column
    @NotEmpty(message = "Campo obrigatorio")
    private String senha;

    @Column
    private boolean is_active_user;

    @Column
    private Date data_criacao;

    @Column
    private Date data_atualizacao;

    @PrePersist
    public void prePersist() {
        is_active_user = true;
    }

}
