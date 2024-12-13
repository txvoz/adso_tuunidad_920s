package co.edu.sena.tu_unidad.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonaDto {

    private Long id;    

    private String nombre;

    private String apellido;

    private String genero;

    private Date fechaNacimiento;

    private String tipoIdentificacion;

    private String identificacion;

    private String telefono;

    private String correo;

    private Long idMunicipio;

    private String direccion;

    private String zip;

}
