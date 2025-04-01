package co.edu.sena.tu_unidad.repository;

import co.edu.sena.tu_unidad.entity.InmuebleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface InmuebleRepository extends JpaRepository<InmuebleEntity, Long>,
        JpaSpecificationExecutor<InmuebleEntity> {
}
