package io.drop.drop_api.models.entities.usuario;

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
@Table(name = "PersonalData")
public class PersonalData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private Usuarios usuarios;

    @Column
    private String completed_name;

    @Column
    private String birthday;

    @Column
    private String cpf;

    @Column
    private String rg;

    @Column
    private String gender;

}
