package io.drop.drop_api.models.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotEmpty;

import java.time.LocalDateTime;
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
    private String name;

    @Column
    @NotEmpty(message = "Campo obrigatorio")
    private String password;

    @Column
    private boolean is_active_user;

    @Column
    private LocalDateTime data_criacao;

    @Column
    private LocalDateTime data_atualizacao;

    @Column
    private boolean isAdmin;

    @PrePersist
    public void prePersist() {
        is_active_user = true;
        isAdmin = true;
        data_criacao = LocalDateTime.now();
        data_atualizacao = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        data_atualizacao = LocalDateTime.now();
    }








}
