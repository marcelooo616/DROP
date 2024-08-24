package io.drop.drop_api.service;


import io.drop.drop_api.models.dto.UsuariosDTO;
import io.drop.drop_api.models.entities.usuario.Usuarios;

public interface UsuarioService {
    Usuarios save(UsuariosDTO UsuariosDTO);
}

