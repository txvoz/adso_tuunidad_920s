package co.edu.sena.tu_unidad.service;

import co.edu.sena.tu_unidad.dto.LogRequestDto;
import co.edu.sena.tu_unidad.entity.LogEntity;
import co.edu.sena.tu_unidad.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class LogService {

    @Autowired
    private LogRepository repository;

    public void save(LogRequestDto log) {

        LogEntity entity = new LogEntity();
        entity.setReferencia(log.getReferencia());
        entity.setData(log.getData());
        entity.setEstado(log.getEstado());
        entity.setFecha(new Date());
        entity.setIdUsuario(log.getIdUsuario());

        repository.save(entity);

    }


}
