package co.edu.sena.tu_unidad.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "detalle_inmueble_persona")
public class DetalleInmueblePersonaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "tipo_relacion")
    private String tipoRelacion;

    @Column(name = "estado")
    private String estado;

    @ManyToOne
    @JoinColumn(name = "id_detalle_inmueble", nullable = false, updatable = true)
    private DetalleInmuebleEntity detalleInmueble;

    @ManyToOne
    @JoinColumn(name = "id_persona", nullable = false, updatable = true)
    private PersonaEntity persona;
}
