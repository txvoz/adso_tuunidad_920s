package co.edu.sena.tu_unidad.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "usuario")
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "estado")
    private String estado;

    /*@Column(name = "id_persona")
    private String idPersona;*/

    /*
    @Column(name = "id_rol")
    private Long idRol;*/

    @ManyToOne
    @JoinColumn(name = "id_rol", nullable = false, updatable = true)
    private RolEntity rol;

    @ManyToOne
    @JoinColumn(name = "id_persona", nullable = false, updatable = true)
    private PersonaEntity persona;

}
