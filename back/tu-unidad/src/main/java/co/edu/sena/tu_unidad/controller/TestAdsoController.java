package co.edu.sena.tu_unidad.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test-adso")
public class TestAdsoController {


    //Este servicio sirve para hacer un saludo
    @GetMapping()
    public String holaMundoAdso(){
        return "Hola esto es un saludo desde un WS - SpringBoot";
    }

    @GetMapping("/despedir")
    public String despedirAdso(){
        return "Hasta luego gracias por asistir a la sesion editado en linea";
    }

}
