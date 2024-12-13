package co.edu.sena.tu_unidad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import co.edu.sena.tu_unidad.entity.PersonaEntity;

@Repository
public interface PersonaRepository extends
        JpaRepository<PersonaEntity, Long>,
        JpaSpecificationExecutor<PersonaEntity>{

}
