package co.edu.sena.tu_unidad.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "rol")
public class RolEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rol")
    private List<UsuarioEntity> usuarios;
}
