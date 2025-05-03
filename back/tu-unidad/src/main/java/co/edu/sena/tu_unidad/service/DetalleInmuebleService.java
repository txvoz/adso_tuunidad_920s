package co.edu.sena.tu_unidad.service;

import co.edu.sena.tu_unidad.entity.DetalleInmuebleEntity;
import co.edu.sena.tu_unidad.entity.InmuebleEntity;
import co.edu.sena.tu_unidad.repository.DetalleInmuebleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetalleInmuebleService {

    @Autowired
    private DetalleInmuebleRepository repository;

    public DetalleInmuebleEntity getLastByInmueble(InmuebleEntity inmueble) {
        return this.repository.findLastByInmueble(inmueble);
    }

    public List<DetalleInmuebleEntity> getAllByInmueble(InmuebleEntity inmueble) {
        return this.repository.findByInmuebleOrderByFechaCreacionDesc(inmueble);
    }

}
