package co.edu.sena.tu_unidad.controller;

import co.edu.sena.tu_unidad.dto.LoginRequestDto;
import co.edu.sena.tu_unidad.dto.LoginResponseDto;
import co.edu.sena.tu_unidad.dto.ServerResponseDataDto;
import co.edu.sena.tu_unidad.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UsuarioService service;

    @PostMapping()
    public ServerResponseDataDto login(@RequestBody LoginRequestDto request){

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        LoginResponseDto responseDto = this.service.login(request);

        return ServerResponseDataDto.builder()
                .message("Success")
                .data(responseDto)
                .status(200)
                .build();
    }
}
