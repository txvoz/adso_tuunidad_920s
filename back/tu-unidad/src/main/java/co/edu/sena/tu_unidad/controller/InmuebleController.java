package co.edu.sena.tu_unidad.controller;

import co.edu.sena.tu_unidad.dto.InmuebleRequestDto;
import co.edu.sena.tu_unidad.dto.ServerResponseDataDto;
import co.edu.sena.tu_unidad.service.InmuebleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/inmueble")
public class InmuebleController {

    @Autowired
    private InmuebleService service;

    @PostMapping()
    public ServerResponseDataDto save(@RequestBody InmuebleRequestDto request) {

        this.service.save(request);

        return ServerResponseDataDto.builder()
                .status(200)
                .message("Registro exitoso!")
                .data(true)
                .build();
    }


    @GetMapping()
    public ServerResponseDataDto getAll(){
        return ServerResponseDataDto.builder()
                .status(200)
                .message("Consulta exitosa!")
                .data(this.service.getAll())
                .build();
    }

    @DeleteMapping("/{id}")
    public ServerResponseDataDto deleteById(@PathVariable("id") long id) {
        this.service.delete(id);
        return ServerResponseDataDto.builder()
                .status(200)
                .message("Consulta exitosa!")
                .data(true)
                .build();
    }

}
