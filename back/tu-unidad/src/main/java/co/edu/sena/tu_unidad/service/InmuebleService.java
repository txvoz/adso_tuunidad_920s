package co.edu.sena.tu_unidad.service;

import co.edu.sena.tu_unidad.dto.InmuebleRequestDto;
import co.edu.sena.tu_unidad.dto.InmuebleResponseDto;
import co.edu.sena.tu_unidad.entity.InmuebleEntity;
import co.edu.sena.tu_unidad.entity.TipoInmuebleEntity;
import co.edu.sena.tu_unidad.repository.InmuebleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class InmuebleService {

    @Autowired
    private InmuebleRepository repository;

    @Autowired
    private TipoInmuebleService tipoInmuebleService;

    public List<InmuebleResponseDto> getAll(){

        List<InmuebleEntity> entities = this.repository.findAll();
        List<InmuebleResponseDto> dtos = new ArrayList<>();

        for (InmuebleEntity entity: entities) {
            InmuebleResponseDto dto = InmuebleResponseDto.builder()
                    .id(entity.getId())
                    .nomenclatura(entity.getNomenclatura())
                    .m2(entity.getM2())
                    .tipo(entity.getTipoInmueble().getTitulo())
                    .propietario("-")
                    .build();

            dtos.add(dto);
        }

        return dtos;
    }

    public void save (InmuebleRequestDto dto) {

        InmuebleEntity entity = new InmuebleEntity();
        entity.setNomenclatura(dto.getNomenclatura());
        entity.setM2(dto.getM2());
        TipoInmuebleEntity tipoInmuebleEntity = this.tipoInmuebleService.getById(dto.getIdTipoInmueble());
        entity.setTipoInmueble(tipoInmuebleEntity);

        this.repository.save(entity);

    }

    public void delete(Long id) {
        Optional<InmuebleEntity> optionalInmuebleEntity = this.repository.findById(id);
        optionalInmuebleEntity.ifPresent(inmuebleEntity -> this.repository.delete(inmuebleEntity));
    }


}
