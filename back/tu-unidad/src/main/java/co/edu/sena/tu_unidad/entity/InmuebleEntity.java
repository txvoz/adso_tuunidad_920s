package co.edu.sena.tu_unidad.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "inmueble")
public class InmuebleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nomenclatura")
    private String nomenclatura;

    @Column(name = "m2")
    private String m2;

    @ManyToOne
    @JoinColumn(name = "id_tipo_inmueble", nullable = false, updatable = true)
    private TipoInmuebleEntity tipoInmueble;
}
