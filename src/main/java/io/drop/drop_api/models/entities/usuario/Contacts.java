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
@Table(name = "Contacts")
public class Contacts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private Usuarios usuarios;

    @Column
    private String sort;

    @Column
    private String data_value;

    @PrePersist
    public void prePersist() {
        sort = (sort == null) ? "" : sort;
        data_value = (data_value == null) ? "" : data_value;
    }

}
