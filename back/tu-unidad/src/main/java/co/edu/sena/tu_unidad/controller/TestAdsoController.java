package co.edu.sena.tu_unidad.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.sena.tu_unidad.dto.PersonaDto;
import co.edu.sena.tu_unidad.service.PersonaService;

@RestController
@RequestMapping("/test-adso")
public class TestAdsoController {

    @Autowired
    private PersonaService service;


    //Este servicio sirve para hacer un saludo
    @GetMapping()
    public String holaMundoAdso(){

        PersonaDto dto = new PersonaDto();
        dto.setNombre("Testx");
        dto.setApellido("ApellidoA");
        dto.setGenero("F");
        dto.setFechaNacimiento(new Date());
        dto.setTipoIdentificacion("CC");
        dto.setIdentificacion("54333");
        dto.setTelefono("443312");
        dto.setCorreo("prueba@p.com");
        dto.setIdMunicipio(1095L);
        dto.setDireccion("Calle siempre viva 123");
        dto.setZip("190001");

        service.save(dto);

        return "Hola esto es un saludo desde un WS - SpringBoot";
    }

    @GetMapping("/despedir")
    public String despedirAdso(){
        return "Hasta luego gracias por asistir a la sesion editado en linea";
    }

    @GetMapping("/despedir2")
    public String despedirAdso2(){
        return "Bye bye";
    }

}
