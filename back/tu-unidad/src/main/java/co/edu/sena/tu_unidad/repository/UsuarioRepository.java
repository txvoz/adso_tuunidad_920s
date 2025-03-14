package co.edu.sena.tu_unidad.repository;

import co.edu.sena.tu_unidad.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends
        JpaRepository<UsuarioEntity, Long>,
        JpaSpecificationExecutor<UsuarioEntity> {

    Optional<UsuarioEntity> findByLoginAndPassword(String login, String password);
}
