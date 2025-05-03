package co.edu.sena.tu_unidad.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "detalle_inmueble")
public class DetalleInmuebleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "estado")
    private String estado;

    @Column(name = "fecha_creacion")
    private Date fechaCreacion;

    @ManyToOne
    @JoinColumn(name = "id_inmueble", nullable = false, updatable = true)
    private InmuebleEntity inmueble;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "detalleInmueble")
    private List<DetalleInmueblePersonaEntity> detalleInmueblePersonas;
}
