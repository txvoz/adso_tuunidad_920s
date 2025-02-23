package co.edu.sena.tu_unidad.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public List<PersonaDto> getAll(){

        List<PersonaDto> dtos = new ArrayList<>();
        List<PersonaEntity> entities = repository.findAll();

        for (PersonaEntity entity : entities) {
            PersonaDto dto = new PersonaDto();

            dto.setId(entity.getId());
            dto.setNombre(entity.getNombre());
            dto.setApellido(entity.getApellido());
            dto.setGenero(entity.getGenero());
            dto.setFechaNacimiento(entity.getFechaNacimiento());
            dto.setTipoIdentificacion(entity.getTipoIdentificacion());
            dto.setIdentificacion(entity.getIdentificacion());
            dto.setTelefono(entity.getTelefono());
            dto.setCorreo(entity.getCorreo());
            dto.setIdMunicipio(entity.getIdMunicipio());
            dto.setDireccion(entity.getDireccion());
            dto.setZip(entity.getZip());

            dtos.add(dto);
        }

        return dtos;
    }

    public PersonaDto getById(Long id) {

        Optional<PersonaEntity> optionalPersona  = this.repository.findById(id);

        if(optionalPersona.isPresent()) {
            PersonaEntity entity = optionalPersona.get();
            PersonaDto dto = new PersonaDto();
            dto.setId(entity.getId());
            dto.setNombre(entity.getNombre());
            dto.setApellido(entity.getApellido());
            dto.setGenero(entity.getGenero());
            dto.setFechaNacimiento(entity.getFechaNacimiento());
            dto.setTipoIdentificacion(entity.getTipoIdentificacion());
            dto.setIdentificacion(entity.getIdentificacion());
            dto.setTelefono(entity.getTelefono());
            dto.setCorreo(entity.getCorreo());
            dto.setIdMunicipio(entity.getIdMunicipio());
            dto.setDireccion(entity.getDireccion());
            dto.setZip(entity.getZip());
            return dto;
        }


        return null;
    }

    public void delete(Long id) {
        this.repository.deleteById(id);
    }


    public PersonaDto update(PersonaDto newData) {

        Optional<PersonaEntity> optionalPersona  = this.repository.findById(newData.getId());

        if(optionalPersona.isPresent()) {
            PersonaEntity entity = optionalPersona.get();

            entity.setId(newData.getId());
            entity.setNombre(newData.getNombre());
            entity.setApellido(newData.getApellido());
            entity.setGenero(newData.getGenero());
            entity.setFechaNacimiento(newData.getFechaNacimiento());
            entity.setTipoIdentificacion(newData.getTipoIdentificacion());
            entity.setIdentificacion(newData.getIdentificacion());
            entity.setTelefono(newData.getTelefono());
            entity.setCorreo(newData.getCorreo());
            entity.setIdMunicipio(newData.getIdMunicipio());
            entity.setDireccion(newData.getDireccion());
            entity.setZip(newData.getZip());

            this.repository.save(entity);

            return newData;
        }

        return null;
    }

    

}
