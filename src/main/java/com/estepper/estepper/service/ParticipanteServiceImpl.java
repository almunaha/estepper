package com.estepper.estepper.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import com.estepper.estepper.model.entity.Participante;
import com.estepper.estepper.model.enums.Sexo;
import com.estepper.estepper.model.entity.Grupo;
import com.estepper.estepper.model.enums.Estado;

import com.estepper.estepper.repository.ParticipanteRepository;
import com.estepper.estepper.repository.GrupoRepository;

@Service
public class ParticipanteServiceImpl implements ParticipanteService{

    @Autowired
    private ParticipanteRepository repo; //inyección de dependencias del participante dao api

    @Autowired
    private GrupoRepository repoG;

    @Override
    public List<Participante> listado(Integer idCoordinador, Estado estadoCuenta){ 
        return(List<Participante>) repo.findByIdCoordinadonOrEstado(idCoordinador, estadoCuenta);
    }

    @Override
    public Optional<Participante> findById(Integer id) {
        return repo.findById(id);
    }
    @Override
    public Participante getParticipante(Integer id){
        return repo.findById(id).get();
    }   

    @Override
    public void update(Integer edad, Sexo sexo, String fotoUsuario, Grupo grupo, Integer asistencia, Integer idCoor, Integer idAdmin, Double perdidadepeso, Integer sesionescompletas, Double perdcmcintura, Integer id){
        repo.update(edad, sexo, fotoUsuario, grupo, asistencia, idCoor, idAdmin, perdidadepeso, sesionescompletas, perdcmcintura, id);
    }

    @Override
    public List<Participante> listadoGrupo (Grupo grupo){
        return repo.findByGrupo(grupo);
    }

    @Override
    public Page<Participante> paginas(Pageable pageable, Integer idCoordinador, Estado estadoCuenta){
        return(Page<Participante>) repo.findByIdCoordinadonOrEstado(pageable, idCoordinador, estadoCuenta);
    }

    @Override
    public void quitargrupo(Integer id){
        Participante p = repo.findById(id).get();
        Grupo grup = p.getGrupo();
        if(grup.getNumParticipantes() >0) grup.setNumParticipantes(grup.getNumParticipantes()-1);
        repoG.update(grup.getNombre(), grup.getCodigo(), grup.getIdCoordinador(), grup.getNumParticipantes(), grup.getId());
        p.setGrupo(null);
        repo.update(p.getEdad(),p.getSexo(), p.getFotoUsuario(), null, p.getAsistencia(), p.getIdCoordinador(),p.getIdAdministrador(), p.getPerdidaDePeso(), p.getSesionesCompletas(), p.getPerdidacmcintura(), id);
    }

    
    @Override
    public void save(Participante participante) {
        repo.save(participante);
    }
    
}
