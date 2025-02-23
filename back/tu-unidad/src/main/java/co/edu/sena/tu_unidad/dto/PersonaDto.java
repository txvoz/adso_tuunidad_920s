package co.edu.sena.tu_unidad.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(Include.NON_NULL)
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
