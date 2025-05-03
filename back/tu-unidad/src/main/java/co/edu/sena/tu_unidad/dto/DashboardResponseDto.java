package co.edu.sena.tu_unidad.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DashboardResponseDto {

    private String tipo;

    private Long id;

    private List<DashaboardItemReponseDto> inmuebles;


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class DashaboardItemReponseDto {

        private String nomenclatura;

        private Long id;

        private String estadoInmueble;

        private String estadoDetalle;

        private String propietarioPrincipal;

        private List<String> propietarios;

        private List<String> personasAutorizadas;

        private List<String> personasInactivas;


    }

}
