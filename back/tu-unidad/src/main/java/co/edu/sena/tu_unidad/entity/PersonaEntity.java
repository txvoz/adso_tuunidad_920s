package co.edu.sena.tu_unidad.entity;

import java.util.Date;
import java.util.List;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "persona")
public class PersonaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;    

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "genero")
    private String genero;

    @Column(name = "fecha_nacimiento")
    private Date fechaNacimiento;

    @Column(name = "tipo_identificacion")
    private String tipoIdentificacion;

    @Column(name = "identificacion")
    private String identificacion;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "correo")
    private String correo;

    @Column(name = "id_municipio")
    private Long idMunicipio;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "zip")
    private String zip;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "persona")
    private List<UsuarioEntity> usuarios;
}
