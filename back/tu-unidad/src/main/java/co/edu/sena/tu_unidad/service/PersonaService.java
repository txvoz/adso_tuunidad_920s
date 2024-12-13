package co.edu.sena.tu_unidad.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.sena.tu_unidad.dto.PersonaDto;
import co.edu.sena.tu_unidad.entity.PersonaEntity;
import co.edu.sena.tu_unidad.repository.PersonaRepository;

@Service
public class PersonaService {

    @Autowired
    private PersonaRepository repository;

    public void save(PersonaDto dto){

        PersonaEntity entity = new PersonaEntity();
        entity.setNombre(dto.getNombre());
        entity.setApellido(dto.getApellido());
        entity.setGenero(dto.getGenero());
        entity.setFechaNacimiento(dto.getFechaNacimiento());
        entity.setTipoIdentificacion(dto.getTipoIdentificacion());
        entity.setIdentificacion(dto.getIdentificacion());
        entity.setTelefono(dto.getTelefono());
        entity.setCorreo(dto.getCorreo());
        entity.setIdMunicipio(dto.getIdMunicipio());
        entity.setDireccion(dto.getDireccion());
        entity.setZip(dto.getZip());

        repository.save(entity);

    }

    

}
