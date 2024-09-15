package io.drop.drop_api.controller;


import io.drop.drop_api.exeption.SenhaInvalidaException;
import io.drop.drop_api.jwt.JwtService;
import io.drop.drop_api.models.dto.CredenciaisDTO;
import io.drop.drop_api.models.dto.TokenDTO;
import io.drop.drop_api.models.dto.UsuariosDTO;
import io.drop.drop_api.models.entities.usuario.Usuarios;
import io.drop.drop_api.repository.UsuarioRepository;
import io.drop.drop_api.service.impl.UsuarioDetailServiceImpl;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/usuario")
public class UsuarioController {


    private final UsuarioRepository usuarioRepository;
    private final UsuarioDetailServiceImpl userDetailService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;


    @PostMapping("/insert")
    @ResponseStatus(HttpStatus.CREATED)
    public Usuarios save(@Valid @RequestBody UsuariosDTO usuarioDTO){
        String encryptedPassword = passwordEncoder.encode(usuarioDTO.getPassword());
        usuarioDTO.setPassword(encryptedPassword);
        System.out.println("Chamando controler");
        return  userDetailService.save(usuarioDTO);
    }

    public static boolean isUserAdmin(Collection<? extends GrantedAuthority> authorities) {
        boolean hasUserRole = false;
        boolean hasAdminRole = false;

        for (GrantedAuthority authority : authorities) {
            if (authority.getAuthority().equals("ROLE_USER")) {
                hasUserRole = true;
            } else if (authority.getAuthority().equals("ROLE_ADMIN")) {
                hasAdminRole = true;
            }
        }

        // Verifica se o usuário tem as duas roles, "ROLE_USER" e "ROLE_ADMIN"
        return hasUserRole && hasAdminRole;
    }

    @PostMapping("/auth")
    public TokenDTO authenticateUser(@RequestBody CredenciaisDTO credenciaisDTO){
        try {

            Usuarios usuario = Usuarios.builder()
                    .email(credenciaisDTO.getEmail())
                    .password(credenciaisDTO.getPassword())
                    .build();

            UserDetails authenticatedUser = userDetailService.autenticar(usuario);

            if (!authenticatedUser.isEnabled()) {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Inactive user. Token cannot be generated.");
            }



            Collection<? extends GrantedAuthority> authorities = authenticatedUser.getAuthorities();
            boolean isAdmin = isUserAdmin(authorities);

            String token = jwtService.gerarToken(usuario);


            return new TokenDTO(usuario.getEmail(),token,isAdmin );

        }catch (UsernameNotFoundException | SenhaInvalidaException e){

            throw  new ResponseStatusException(HttpStatus.UNAUTHORIZED,e.getMessage());

        }

    }

    @GetMapping("/all")
    public List<Usuarios> showAll(){
        return usuarioRepository.findAll(Sort.by("name"));
    }



}
