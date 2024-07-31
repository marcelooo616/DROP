package io.drop.drop_api.repository;

import io.drop.drop_api.models.entities.PersonalData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonalDataRepository extends JpaRepository<PersonalData, Integer> {
}
