package co.edu.sena.tu_unidad.repository;

import co.edu.sena.tu_unidad.entity.TipoInmuebleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoInmuebleRepository extends JpaRepository<TipoInmuebleEntity, Long>,
        JpaSpecificationExecutor<TipoInmuebleEntity> {
}
