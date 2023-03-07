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
import com.estepper.estepper.repository.ParticipanteRepository;
import com.estepper.estepper.repository.GrupoRepository;

@Service
public class ParticipanteServiceImpl implements ParticipanteService{

    @Autowired
    private ParticipanteRepository repo; //inyección de dependencias del participante dao api

    @Autowired
    private GrupoRepository repoG;

    @Override
    public List<Participante> listado(){ 
        return(List<Participante>) repo.findAll();
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
    public void update(Integer edad, Sexo sexo, String fotoParticipante, Grupo grupo, Integer asistencia, Integer idCoor, Double perdidadepeso, Integer sesionescompletas, Integer id){
        repo.update(edad, sexo, fotoParticipante, grupo, asistencia, idCoor, perdidadepeso, sesionescompletas, id);
    }

    @Override
    public List<Participante> listadoGrupo (Grupo grupo){
        return repo.findByGrupo(grupo);
    }

    @Override
    public Page<Participante> paginas(Pageable pageable){
        return(Page<Participante>) repo.findAll(pageable);
    }

    @Override
    public void quitargrupo(Integer id){
        Participante p = repo.findById(id).get();
        Grupo grup = p.getGrupo();
        if(grup.getNumParticipantes() >0) grup.setNumParticipantes(grup.getNumParticipantes()-1);
        repoG.update(grup.getNombre(), grup.getCodigo(), grup.getIdCoordinador(), grup.getNumParticipantes(), grup.getId());
        p.setGrupo(null);
        repo.update(p.edad,p.sexo, p.getFotoParticipante(), null, p.getAsistencia(), p.getIdCoordinador(), p.getPerdidaDePeso(), p.getSesionesCompletas(), id);
    }

    
}
