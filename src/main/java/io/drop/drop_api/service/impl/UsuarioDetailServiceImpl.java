package io.drop.drop_api.service.impl;


import io.drop.drop_api.exeption.SenhaInvalidaException;
import io.drop.drop_api.models.dto.UsuariosDTO;
import io.drop.drop_api.models.entities.usuario.Address;
import io.drop.drop_api.models.entities.usuario.Contacts;
import io.drop.drop_api.models.entities.usuario.PersonalData;
import io.drop.drop_api.models.entities.usuario.Usuarios;
import io.drop.drop_api.exeption.BusinessRule;

import io.drop.drop_api.repository.AddresRepository;
import io.drop.drop_api.repository.ContactsRepository;
import io.drop.drop_api.repository.PersonalDataRepository;
import io.drop.drop_api.repository.UsuarioRepository;


import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class UsuarioDetailServiceImpl implements UserDetailsService {


    @Autowired
    private PasswordEncoder passwordEncoder;

    private final UsuarioRepository usuarioRepository;
    private final AddresRepository addresRepository;
    private final PersonalDataRepository personalDataRepository;
    private final ContactsRepository contactsRepository;

    @Transactional
    public Usuarios save(UsuariosDTO usuarioDTO) {

        Usuarios usuario = new Usuarios();
        usuario.setName(usuarioDTO.getName());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setPassword(usuarioDTO.getPassword());

        usuarioRepository.save(usuario);
        Address address = addresRepository.save(saveAdress(usuario));
        PersonalData personalData = personalDataRepository.save(savePersonalData(usuario));
        Contacts contact = contactsRepository.save(saveContacts(usuario));

        return usuario;
    }

    private Address saveAdress(Usuarios usuario_id ) {
        Address address = new Address();
        address.setCity("");
        address.setRoad("");
        address.setState("");
        address.setPostal_code("");
        address.setUsuarios(usuario_id);
        return address;
    }

    private PersonalData savePersonalData(Usuarios usuario){
        PersonalData personalData = new PersonalData();
        personalData.setCompleted_name(usuario.getName());
        personalData.setRg("");
        personalData.setBirthday("");
        personalData.setGender("");
        personalData.setCpf("");
        personalData.setUsuarios(usuario);
        return personalData;
    }

    private Contacts saveContacts(Usuarios usuario){
        Contacts contact =  new Contacts();
        contact.setData_value(usuario.getEmail());
        contact.setSort("E-mail");
        contact.setUsuarios(usuario);
        return contact;
    }

    public Usuarios update(@PathVariable Integer user_id, @RequestBody Usuarios usuario){
        return usuarioRepository.findById(user_id)
                .map(update_user -> {
                    usuario.setId(update_user.getId());
                    usuarioRepository.save(usuario);
                    return update_user;
                }).orElseThrow(() -> new BusinessRule(HttpStatus.NOT_FOUND, "Update failed, usuario not found or does not exist "));

    }

    public UserDetails autenticar(Usuarios usuario){

        UserDetails user = loadUserByUsername(usuario.getEmail());

        boolean senhasBatem = passwordEncoder.matches(usuario.getPassword(), user.getPassword());
        if(senhasBatem){
            return user;
        }
        throw new SenhaInvalidaException();
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Usuarios usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario not found"));

        String[] roles = usuario.isAdmin()
                ? new String[]{"USER","ADMIN"}
                : new String[]{"USER"};

        System.out.println(Arrays.toString(roles));

        boolean usuarioAtivo = usuario.is_active_user();
        boolean is_adm = usuario.isAdmin();
        return User
                .builder()
                .username(usuario.getEmail())
                .password(usuario.getPassword())
                .accountLocked(usuario.isAdmin())
                .roles(roles)
                .disabled(!usuarioAtivo)
                .build();




    }
}
