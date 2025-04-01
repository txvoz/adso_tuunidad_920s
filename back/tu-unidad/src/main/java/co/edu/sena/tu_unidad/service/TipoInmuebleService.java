package co.edu.sena.tu_unidad.service;


import co.edu.sena.tu_unidad.dto.TipoInmuebleResponseDto;
import co.edu.sena.tu_unidad.entity.TipoInmuebleEntity;
import co.edu.sena.tu_unidad.repository.TipoInmuebleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TipoInmuebleService {

    @Autowired
    private TipoInmuebleRepository repository;

    public List<TipoInmuebleResponseDto> getAll() {
        List<TipoInmuebleEntity> entities = this.repository.findAll();
        List<TipoInmuebleResponseDto> dtos = new ArrayList<>();

        for (TipoInmuebleEntity entity: entities) {
            TipoInmuebleResponseDto dto = TipoInmuebleResponseDto.builder()
                    .id(entity.getId())
                    .titulo(entity.getTitulo())
                    .build();
            dtos.add(dto);
        }
        return dtos;
    }

    public TipoInmuebleEntity getById(Long id) {
        Optional<TipoInmuebleEntity> optTipoInmueble = this.repository.findById(id);
        if(optTipoInmueble.isPresent()) return optTipoInmueble.get();
        return null;
    }

}
