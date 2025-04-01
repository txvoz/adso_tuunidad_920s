package co.edu.sena.tu_unidad.controller;

import co.edu.sena.tu_unidad.dto.ServerResponseDataDto;
import co.edu.sena.tu_unidad.service.TipoInmuebleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/tipo-inmueble")
public class TipoInmuebleController {

    @Autowired
    private TipoInmuebleService service;

    @GetMapping()
    public ServerResponseDataDto listAll() {
        return ServerResponseDataDto.builder()
                .status(200)
                .message("Consulta exitosa!")
                .data(this.service.getAll())
                .build();
    }

}
