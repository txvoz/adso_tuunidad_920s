package co.edu.sena.tu_unidad.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test-adso")
public class TestAdsoController {


    @GetMapping()
    public String holaMundoAdso(){
        return "Hola esto es un saludo desde un WS - SpringBoot";
    }

}
