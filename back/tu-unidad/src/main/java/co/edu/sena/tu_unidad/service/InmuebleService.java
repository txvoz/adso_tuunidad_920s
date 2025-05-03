package co.edu.sena.tu_unidad.service;

import co.edu.sena.tu_unidad.dto.DashboardResponseDto;
import co.edu.sena.tu_unidad.dto.InmuebleRequestDto;
import co.edu.sena.tu_unidad.dto.InmuebleResponseDto;
import co.edu.sena.tu_unidad.entity.DetalleInmuebleEntity;
import co.edu.sena.tu_unidad.entity.DetalleInmueblePersonaEntity;
import co.edu.sena.tu_unidad.entity.InmuebleEntity;
import co.edu.sena.tu_unidad.entity.TipoInmuebleEntity;
import co.edu.sena.tu_unidad.repository.InmuebleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class InmuebleService {

    @Autowired
    private InmuebleRepository repository;

    private static final String ESTADO_EN_PROCESO = "EN_PROCESO";

    private static final String ESTADO_ACTIVO = "ACTIVO";

    private static final String ESTADO_INACTIVO = "INACTIVO";

    @Autowired
    private TipoInmuebleService tipoInmuebleService;

    @Autowired
    private DetalleInmuebleService detalleInmuebleService;

    public List<InmuebleResponseDto> getAll(){

        List<InmuebleEntity> entities = this.repository.findAll();
        List<InmuebleResponseDto> dtos = new ArrayList<>();

        for (InmuebleEntity entity: entities) {

            DetalleInmuebleEntity detalleInmueble = this.detalleInmuebleService.getLastByInmueble(entity);

            InmuebleResponseDto dto = InmuebleResponseDto.builder()
                    .id(entity.getId())
                    .nomenclatura(entity.getNomenclatura())
                    .m2(entity.getM2())
                    .tipo(entity.getTipoInmueble().getTitulo())
                    .estado(entity.getEstado())
                    .propietario(detalleInmueble == null ? "Sin propietario" : "Pendiente")
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
                    .estado(optInmueble.get().getEstado())
                    .build();
        }

        return null;

    }

    public void save (InmuebleRequestDto dto) {

        InmuebleEntity entity = new InmuebleEntity();
        entity.setNomenclatura(dto.getNomenclatura());
        entity.setM2(dto.getM2());
        entity.setEstado(ESTADO_EN_PROCESO);
        TipoInmuebleEntity tipoInmuebleEntity = this.tipoInmuebleService.getById(dto.getIdTipoInmueble());
        entity.setTipoInmueble(tipoInmuebleEntity);

        this.repository.save(entity);

    }

    public void edit (InmuebleRequestDto dto, Long id) {
        InmuebleEntity entity = this.repository.findById(id).get();

        entity.setNomenclatura(dto.getNomenclatura());
        entity.setM2(dto.getM2());

        if(!dto.getEstado().isEmpty()) {
            entity.setEstado(dto.getEstado());
        }

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

            DetalleInmuebleEntity detalleInmueble = this.detalleInmuebleService.getLastByInmueble(entity);
            DetalleInmueblePersonaEntity propietario = null;
            List<String> autorizados = new ArrayList<>();
            List<String> inactivos = new ArrayList<>();
            List<String> propietarios = new ArrayList<>();
            String estadoDetalle = ESTADO_INACTIVO;
            if(detalleInmueble != null && detalleInmueble.getDetalleInmueblePersonas() != null && !detalleInmueble.getDetalleInmueblePersonas().isEmpty()) {

                estadoDetalle = detalleInmueble.getEstado().toUpperCase();

                Optional<DetalleInmueblePersonaEntity>
                        optDPersona = detalleInmueble
                        .getDetalleInmueblePersonas()
                        .stream()
                        .filter(dpersona -> dpersona.getTipoRelacion().equals("propietario") && dpersona.getEstado().equals("activo"))
                        .findFirst();

                if(optDPersona.isPresent()) {
                    propietario = optDPersona.get();
                }

                autorizados = detalleInmueble
                        .getDetalleInmueblePersonas()
                        .stream()
                        .filter(dpersona -> !dpersona.getTipoRelacion().equals("propietario") && dpersona.getEstado().equals("activo"))
                        .map(dp -> dp.getTipoRelacion() + " : " + dp.getPersona().getIdentificacion() + " - " + dp.getPersona().getNombre() + " " + dp.getPersona().getApellido())
                        .sorted()
                        .toList();

                propietarios = detalleInmueble
                        .getDetalleInmueblePersonas()
                        .stream()
                        .filter(dpersona -> dpersona.getTipoRelacion().equals("propietario") && dpersona.getEstado().equals("activo"))
                        .map(dp -> dp.getPersona().getIdentificacion() + " - " + dp.getPersona().getNombre() + " " + dp.getPersona().getApellido())
                        .sorted()
                        .toList();

                inactivos = detalleInmueble
                        .getDetalleInmueblePersonas()
                        .stream()
                        .filter(dpersona -> !dpersona.getEstado().equals("activo"))
                        .map(dp -> dp.getTipoRelacion() + " : " + dp.getPersona().getIdentificacion() + " - " + dp.getPersona().getNombre() + " " + dp.getPersona().getApellido())
                        .sorted()
                        .toList();

            }

            value.getInmuebles().add(
                    DashboardResponseDto.DashaboardItemReponseDto.builder()
                    .id(entity.getId())
                    .nomenclatura(entity.getNomenclatura())
                    .propietarioPrincipal(propietario == null ? "Sin propietario" : propietario.getPersona().getIdentificacion() + " - " + propietario.getPersona().getNombre() + " " + propietario.getPersona().getApellido())
                    .personasAutorizadas(autorizados)
                    .propietarios(propietarios)
                    .personasInactivas(inactivos)
                    .estadoInmueble(entity.getEstado())
                            .estadoDetalle(estadoDetalle)
                    .build()
            );
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
