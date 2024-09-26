package io.drop.drop_api.repository.usuario;

import io.drop.drop_api.models.entities.usuario.PersonalData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonalDataRepository extends JpaRepository<PersonalData, Integer> {
}
