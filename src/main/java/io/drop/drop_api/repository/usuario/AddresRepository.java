package io.drop.drop_api.repository.usuario;

import io.drop.drop_api.models.entities.usuario.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddresRepository extends JpaRepository<Address, Integer> {
}
