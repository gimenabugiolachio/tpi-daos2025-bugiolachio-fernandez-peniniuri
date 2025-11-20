package com.tpi.daos.service;

import com.tpi.daos.dto.AsistidoDTO;
import com.tpi.daos.entity.Asistido;
import com.tpi.daos.repository.AsistidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AsistidoServiceImpl implements AsistidoService {
    @Autowired
    private AsistidoRepository asistidoRepository;

    @Override
    public List<Asistido> getAsistidos() {
        return asistidoRepository.findAll();
    }

    @Override
    public Optional<Asistido> getAsistidoById(Long idAsistido) {
        return asistidoRepository.findById(idAsistido);
    }

    @Override
    public void deleteAsistido(Long id) {
        asistidoRepository.deleteById(id);
    }

    @Override
    public void createAsistido(AsistidoDTO asistidoDTO) {
        Asistido asistido = new Asistido();
        asistido.setNombre(asistidoDTO.getNombre());
        asistido.setDni(asistidoDTO.getDni());
        asistido.setDomicilio(asistidoDTO.getDomicilio());
        asistido.setEdadRegistro(asistidoDTO.getEdadRegistro());
        asistido.setFechaNacimiento(asistidoDTO.getFechaNacimiento());
        asistido.setCiudad(asistidoDTO.getCiudad());

        asistidoRepository.save(asistido);
    }

    @Override
    public void updateAsistido(AsistidoDTO asistidoDTO) {
        Optional<Asistido> asistido = asistidoRepository.findByNombre(asistidoDTO.getNombre());

        if(asistido.isPresent()){
            asistido.get().setNombre(asistidoDTO.getNombre());
            asistido.get().setDni(asistidoDTO.getDni());
            asistido.get().setDomicilio(asistidoDTO.getDomicilio());
            asistido.get().setFechaNacimiento(asistidoDTO.getFechaNacimiento());
            asistido.get().setEdadRegistro(asistidoDTO.getEdadRegistro());
            asistido.get().setCiudad(asistidoDTO.getCiudad());
        }
    }

    @Override
    public Optional<Asistido> findByNombre(String nombre) {
        return asistidoRepository.findByNombre(nombre);
    }

    @Override
    public Optional<Asistido> findByDni(String dni) {
        return asistidoRepository.findByDni(dni);
    }
}
