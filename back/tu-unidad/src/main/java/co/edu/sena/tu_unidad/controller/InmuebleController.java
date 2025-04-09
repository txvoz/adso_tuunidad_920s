package co.edu.sena.tu_unidad.controller;

import co.edu.sena.tu_unidad.dto.DashboardResponseDto;
import co.edu.sena.tu_unidad.dto.InmuebleRequestDto;
import co.edu.sena.tu_unidad.dto.InmuebleResponseDto;
import co.edu.sena.tu_unidad.dto.ServerResponseDataDto;
import co.edu.sena.tu_unidad.service.InmuebleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PutMapping("/{id}")
    public ServerResponseDataDto edit(@PathVariable("id") long id, @RequestBody InmuebleRequestDto request) {

        this.service.edit(request, id);

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

    @GetMapping("/{id}")
    public ServerResponseDataDto findById(@PathVariable("id") long id) {
        InmuebleResponseDto dto = this.service.getById(id);
        return ServerResponseDataDto.builder()
                .status(dto!=null ? 200 : 404)
                .message(dto!=null ? "Consulta exitosa!" : "Recurso no encontrado!")
                .data(dto)
                .build();
    }


    @GetMapping("/dashboard")
    public ServerResponseDataDto getDashboard(){
        return ServerResponseDataDto.builder()
                .status(200)
                .message("Consulta exitosa!")
                .data(this.service.getDashboardData())
                .build();
    }

}
