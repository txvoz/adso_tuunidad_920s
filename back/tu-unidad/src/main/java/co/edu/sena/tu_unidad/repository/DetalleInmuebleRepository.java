package co.edu.sena.tu_unidad.repository;

import co.edu.sena.tu_unidad.entity.DetalleInmuebleEntity;
import co.edu.sena.tu_unidad.entity.InmuebleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetalleInmuebleRepository extends JpaRepository<DetalleInmuebleEntity, Long>,
        JpaSpecificationExecutor<DetalleInmuebleEntity> {


    @Query("SELECT d FROM DetalleInmuebleEntity as d WHERE d.inmueble = :inmueble AND d.estado = 'activo' ORDER BY d.fechaCreacion DESC LIMIT 1")
    DetalleInmuebleEntity findLastByInmueble(InmuebleEntity inmueble);

    List<DetalleInmuebleEntity> findByInmuebleOrderByFechaCreacionDesc(InmuebleEntity inmueble);

}
