package co.edu.sena.tu_unidad.service;

import co.edu.sena.tu_unidad.dto.LoginRequestDto;
import co.edu.sena.tu_unidad.dto.LoginResponseDto;
import co.edu.sena.tu_unidad.entity.UsuarioEntity;
import co.edu.sena.tu_unidad.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public LoginResponseDto login(LoginRequestDto request) {

        LoginResponseDto response;

        Optional<UsuarioEntity> optResponse = this.repository
                .findByLoginAndPassword(request.getUsername(), request.getPassword());

        if(optResponse.isPresent()) {
            UsuarioEntity entity =  optResponse.get();
            response = LoginResponseDto.builder()
                    .id(entity.getId())
                    .rol(entity.getIdRol())
                    .isActive(true)
                    .build();
        } else {
            response = LoginResponseDto.builder()
                    .isActive(false)
                    .build();
        }

        return response;
    }
}
