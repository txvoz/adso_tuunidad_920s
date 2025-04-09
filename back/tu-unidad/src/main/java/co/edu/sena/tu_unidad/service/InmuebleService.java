package co.edu.sena.tu_unidad.service;

import co.edu.sena.tu_unidad.dto.DashboardResponseDto;
import co.edu.sena.tu_unidad.dto.InmuebleRequestDto;
import co.edu.sena.tu_unidad.dto.InmuebleResponseDto;
import co.edu.sena.tu_unidad.entity.InmuebleEntity;
import co.edu.sena.tu_unidad.entity.TipoInmuebleEntity;
import co.edu.sena.tu_unidad.repository.InmuebleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


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

    public InmuebleResponseDto getById(Long id) {

        Optional<InmuebleEntity> optInmueble =  this.repository.findById(id);
        if(optInmueble.isPresent()) {
            return InmuebleResponseDto
                    .builder()
                    .id(optInmueble.get().getId())
                    .nomenclatura(optInmueble.get().getNomenclatura())
                    .m2(optInmueble.get().getM2())
                    .tipo(optInmueble.get().getTipoInmueble().getTitulo())
                    .idTipo(optInmueble.get().getTipoInmueble().getId())
                    .build();
        }

        return null;

    }

    public void save (InmuebleRequestDto dto) {

        InmuebleEntity entity = new InmuebleEntity();
        entity.setNomenclatura(dto.getNomenclatura());
        entity.setM2(dto.getM2());
        TipoInmuebleEntity tipoInmuebleEntity = this.tipoInmuebleService.getById(dto.getIdTipoInmueble());
        entity.setTipoInmueble(tipoInmuebleEntity);

        this.repository.save(entity);

    }

    public void edit (InmuebleRequestDto dto, Long id) {
        InmuebleEntity entity = this.repository.findById(id).get();

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


    public List<DashboardResponseDto> getDashboardData(){
        HashMap<String, DashboardResponseDto> map = new HashMap<>();
        List<DashboardResponseDto> response = new ArrayList<>();
        List<InmuebleEntity> entities = this.repository.findAll();

        for (InmuebleEntity entity: entities) {
            String tipo = entity.getTipoInmueble().getTitulo();
            DashboardResponseDto value = map.get(tipo);
            if(value == null) {
                value = new DashboardResponseDto();
                value.setTipo(tipo);
                value.setId(entity.getTipoInmueble().getId());
                value.setInmuebles(new ArrayList<>());
                map.put(tipo, value);
                response.add(value);
            }
            value.getInmuebles().add(DashboardResponseDto.DashaboardItemReponseDto.builder()
                    .id(entity.getId())
                    .nomenclatura(entity.getNomenclatura())
                    .build());
        }

        for (DashboardResponseDto dto : response) {
            dto.getInmuebles()
                    .sort(
                            Comparator.comparing(DashboardResponseDto.DashaboardItemReponseDto::getNomenclatura)
                    );
        }

        return response;
    }

}
