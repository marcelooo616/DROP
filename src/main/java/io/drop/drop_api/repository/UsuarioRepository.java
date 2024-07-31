package io.drop.drop_api.repository;

import io.drop.drop_api.models.entities.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuarios, Integer> {
    Optional<Usuarios> findByEmail(String email);
}
