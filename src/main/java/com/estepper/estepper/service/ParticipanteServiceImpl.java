package com.estepper.estepper.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import com.estepper.estepper.model.entity.Participante;
import com.estepper.estepper.model.enums.Sexo;
import com.estepper.estepper.model.entity.Coordinador;
import com.estepper.estepper.model.entity.Grupo;
import com.estepper.estepper.model.enums.Estado;

import com.estepper.estepper.repository.ParticipanteRepository;
import com.estepper.estepper.repository.CoordinadorRepository;
import com.estepper.estepper.repository.GrupoRepository;

@Service
public class ParticipanteServiceImpl implements ParticipanteService {

    @Autowired
    private ParticipanteRepository repo; // inyección de dependencias del participante dao api

    @Autowired
    private GrupoRepository repoG;

    @Autowired
    private CoordinadorRepository repoCoord;

    @Override
    public List<Participante> listado(Integer idCoordinador, Estado estadoCuenta) {
        return (List<Participante>) repo.findByIdCoordinadonOrEstado(repoCoord.findById(idCoordinador).get(),
                estadoCuenta);
    }

    @Override
    public Optional<Participante> findById(Integer id) {
        return repo.findById(id);
    }

    @Override
    public Participante getParticipante(Integer id) {
        return repo.findById(id).get();
    }

    @Override
    public void update(Integer edad, Sexo sexo, String fotoUsuario, Grupo grupo, Integer asistencia,
            Coordinador coordinador, Double perdidadepeso, Integer sesionescompletas, Double perdcmcintura,
            Integer id) {
        repo.update(edad, sexo, fotoUsuario, grupo, asistencia, coordinador, perdidadepeso, sesionescompletas,
                perdcmcintura, id);
    }

    @Override
    public List<Participante> listadoGrupo(Grupo grupo) {
        return repo.findByGrupo(grupo);
    }

    @Override
    public Page<Participante> paginas(Pageable pageable, Coordinador coordinador, Estado estadoCuenta) {
        return (Page<Participante>) repo.findByIdCoordinadonOrEstado(pageable, coordinador, estadoCuenta);
    }

    @Override
    public void quitargrupo(Integer id) {
        Participante p = repo.findById(id).get();
        Grupo grup = p.getGrupo();
        if (grup.getNumParticipantes() > 0)
            grup.setNumParticipantes(grup.getNumParticipantes() - 1);
        repoG.update(grup.getNombre(), grup.getCodigo(), grup.getCoordinador(), grup.getNumParticipantes(),
                grup.getId());
        p.setGrupo(null);
        repo.update(p.getEdad(), p.getSexo(), p.getFotoUsuario(), null, p.getAsistencia(), p.getCoordinador(),
                p.getPerdidaDePeso(), p.getSesionesCompletas(), p.getPerdidacmcintura(), id);
    }

    @Override
    public void save(Participante participante) {
        repo.save(participante);
    }

    @Override
    public void borrarCoordinador(Coordinador coordinador) {
        List<Participante> lista = repo.findByCoordinador(coordinador);
        for(int i = 0; i < lista.size(); i++){
            Participante este = lista.get(i);
            repo.update(este.getEdad(), este.getSexo(), este.getFotoUsuario(), este.getGrupo(), este.getAsistencia(), null, este.getPerdidaDePeso(), este.getSesionesCompletas(), este.getPerdidacmcintura(), este.getId());
        }
    }

}
