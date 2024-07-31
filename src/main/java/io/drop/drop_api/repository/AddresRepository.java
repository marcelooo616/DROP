package io.drop.drop_api.repository;

import io.drop.drop_api.models.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddresRepository extends JpaRepository<Address, Integer> {
}
