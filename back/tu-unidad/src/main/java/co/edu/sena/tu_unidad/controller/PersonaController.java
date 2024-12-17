package co.edu.sena.tu_unidad.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.sena.tu_unidad.dto.PersonaDto;
import co.edu.sena.tu_unidad.service.PersonaService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import co.edu.sena.tu_unidad.dto.ServerResponseDataDto;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/persona")
public class PersonaController {

    @Autowired
    private PersonaService service;

    @PostMapping()
    public ServerResponseDataDto create(@RequestBody PersonaDto request){

        service.save(request);

        return ServerResponseDataDto.builder()
            .message("Registro exitoso!")
            .status(HttpStatus.OK.value())
            .data(null)
            .build();
    }


    @GetMapping()
    public ServerResponseDataDto listAll() {

        List<PersonaDto> dtos = this.service.getAll();

        return ServerResponseDataDto.builder()
            .message("Consulta Exitosa!")
            .status(HttpStatus.OK.value())
            .data(dtos)
            .build();
    }

    @GetMapping("/{id}")
    public ServerResponseDataDto getById(@PathVariable("id") Long id) { 

        PersonaDto dto = this.service.getById(id);

        return ServerResponseDataDto.builder()
            .message(dto != null ? "Registro encontrado!" : "Registro no encontrado")
            .status(dto != null ? HttpStatus.OK.value() : HttpStatus.NOT_FOUND.value())
            .data(dto)
            .build();
    }


    @DeleteMapping("/{id}")
    public ServerResponseDataDto deleteById(@PathVariable("id") Long id) { 

        this.service.delete(id);

        return ServerResponseDataDto
            .builder()
            .message("Registro eliminado")
            .status( HttpStatus.OK.value())
            .build();
    }


    @PutMapping("/{id}")
    public ServerResponseDataDto update(
        @PathVariable("id") Long id, 
        @RequestBody PersonaDto request) {

        request.setId(id);
        request = this.service.update(request);

        return ServerResponseDataDto
            .builder()
            .message(request != null ? "Registro actualizado" : "El registro no se pudo actualizar")
            .status( request != null ? HttpStatus.OK.value() : HttpStatus.BAD_REQUEST.value())
            .data(request)
            .build();

    }


}
