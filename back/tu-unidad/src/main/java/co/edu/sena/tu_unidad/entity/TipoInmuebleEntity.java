package co.edu.sena.tu_unidad.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "tipo_inmueble")
public class TipoInmuebleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "titulo")
    private String titulo;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoInmueble")
    private List<InmuebleEntity> inmuebles;

}
