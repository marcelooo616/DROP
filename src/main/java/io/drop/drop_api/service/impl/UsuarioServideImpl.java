/*package io.drop.drop_api.service.impl;


import io.drop.drop_api.models.dto.UsuariosDTO;
import io.drop.drop_api.models.entities.usuario.Address;
import io.drop.drop_api.models.entities.usuario.Contacts;
import io.drop.drop_api.models.entities.usuario.PersonalData;
import io.drop.drop_api.models.entities.usuario.Usuarios;
import io.drop.drop_api.repository.AddresRepository;
import io.drop.drop_api.repository.ContactsRepository;
import io.drop.drop_api.repository.PersonalDataRepository;
import io.drop.drop_api.repository.UsuarioRepository;
import io.drop.drop_api.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioServideImpl implements UsuarioService {


    private final UsuarioRepository usuarioRepository;
    private final AddresRepository addresRepository;
    private final PersonalDataRepository personalDataRepository;
    private final ContactsRepository contactsRepository;



    @Override
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
}
*/