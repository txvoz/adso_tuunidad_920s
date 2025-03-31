package co.edu.sena.tu_unidad.service;

import co.edu.sena.tu_unidad.dto.LogRequestDto;
import co.edu.sena.tu_unidad.dto.LoginRequestDto;
import co.edu.sena.tu_unidad.dto.LoginResponseDto;
import co.edu.sena.tu_unidad.entity.UsuarioEntity;
import co.edu.sena.tu_unidad.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    private String TAG = "usuario-service";

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private LogService logService;


    public LoginResponseDto login(LoginRequestDto request) {

        LoginResponseDto response;

        Optional<UsuarioEntity> optResponse = this.repository
                .findByLoginAndPassword(request.getUsername(), request.getPassword());

        if(optResponse.isPresent()) {
            UsuarioEntity entity =  optResponse.get();

            if(!validateEstado(entity)) {

                logService.save(LogRequestDto.builder()
                        .referencia(TAG + "::login")
                        .data("login::"+request.getUsername()+"-pwd::"+request.getPassword() + "-estado::"+entity.getEstado())
                        .estado("FAIL")
                        .build());

                return LoginResponseDto.builder()
                        .isActive(false)
                        .build();
            }

            response = LoginResponseDto.builder()
                    .id(entity.getId())
                    .usename(entity.getLogin())
                    .nombre(entity.getPersona().getNombre() + " " + entity.getPersona().getApellido())
                    .tipoIdentificacion(entity.getPersona().getTipoIdentificacion())
                    .identificacion(entity.getPersona().getIdentificacion())
                    .email(entity.getPersona().getCorreo())
                    .rol(entity.getRol().getNombre())
                    .isActive(true)
                    .build();

            logService.save(LogRequestDto.builder()
                            .referencia(TAG + "::login")
                            .data("login::"+request.getUsername()+"-pwd::"+request.getPassword()+"-dni::"+entity.getPersona().getIdentificacion())
                            .idUsuario(response.getId())
                            .estado("SUCCESS")
                    .build());

        } else {
            response = LoginResponseDto.builder()
                    .isActive(false)
                    .build();

            logService.save(LogRequestDto.builder()
                    .referencia(TAG + "::login")
                    .data("login::"+request.getUsername()+"-pwd::"+request.getPassword())
                    .estado("FAIL")
                    .build());
        }

        return response;
    }

    boolean validateEstado(UsuarioEntity entity) {
        return entity.getEstado().equals("activo");
    }

}
