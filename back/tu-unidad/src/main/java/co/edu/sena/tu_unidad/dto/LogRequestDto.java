package co.edu.sena.tu_unidad.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LogRequestDto {

    private String referencia;

    private String data;

    private String estado;

    private Long idUsuario;

}
