package io.drop.drop_api.controller;


import io.drop.drop_api.models.dto.UsuariosDTO;
import io.drop.drop_api.models.entities.Usuarios;
import io.drop.drop_api.service.impl.UsuarioServideImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/usuario")
public class UsuarioController {


    private final UsuarioServideImpl usuarioServide;

    @PostMapping("/insert")
    @ResponseStatus(HttpStatus.CREATED)
    public Usuarios save(@Valid @RequestBody UsuariosDTO usuarioDTO){
        return  usuarioServide.save(usuarioDTO);
    }
}
