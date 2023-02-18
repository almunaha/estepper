package com.estepper.estepper.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estepper.estepper.model.entity.FaseValoracion;
import com.estepper.estepper.model.entity.Findrisc;
import com.estepper.estepper.model.entity.Participante;
import com.estepper.estepper.model.enums.Estado;
import com.estepper.estepper.model.enums.Sexo;
import com.estepper.estepper.model.entity.Exploracion;
import com.estepper.estepper.repository.FaseValoracionRepository;
import com.estepper.estepper.repository.ExploracionRepository;
import com.estepper.estepper.repository.FindriscRepository;
import com.estepper.estepper.repository.ParticipanteRepository;
import com.estepper.estepper.repository.UsuarioRepository;

@Service
public class FaseValoracionServiceImpl implements FaseValoracionService {

    @Autowired
    private FaseValoracionRepository repoV;

    @Autowired
    private ExploracionRepository repoE;

    @Autowired
    private FindriscRepository repoF;

    @Autowired
    private ParticipanteRepository repoP;

    @Autowired
    private UsuarioRepository repoU;

    @Override
    public List<FaseValoracion> faseValoracion(Integer id) {
        return repoV.findByIdParticipante(id);
    }

    @Override
    public void crearFormularios(Integer id){
        //EXPLORACIÓN
        Exploracion exploracion = new Exploracion(0, id, Sexo.MASCULINO, "no", 0, 0, 0, 0, 0);
        repoE.save(exploracion);
        //FINDRISC
        Findrisc findrisc = new Findrisc(0, id, 0, 0, 0, 0, 0, 0, 0, 0, 0, "Bajo");
        repoF.save(findrisc);
    }

    @Override
    public void crearFormulariosNuevos(Integer id){
        //COMPROBAR QUE NO ESTÉN CREADOS YA
        //CLASIFICACION
        //ANTECEDENTES
        //ALIMENTACION
        //ACTIVIDAD FISICA
    }

    @Override
    public void updateExploracion(String primeravez, Sexo sexo, Integer peso, Integer talla, Integer cmcintura, Integer edad,
            Integer imc, Integer id) {
        repoE.updateExploracion(primeravez, sexo, peso, talla, cmcintura, edad, imc, id);
        
    }

    @Override
    public void updateFindrisc(Integer id,Integer puntosedad, Integer puntosimc, Integer puntoscmcintura, Integer ptosactfisica,
    Integer ptosfrecfruta, Integer ptosmedicacion, Integer ptosglucosa, Integer ptosdiabetes, Integer puntuacion,
    String escalarriesgo){
        repoF.updateFindrisc(id, puntosedad, puntosimc, puntoscmcintura, ptosactfisica, ptosfrecfruta, ptosmedicacion, ptosglucosa, ptosdiabetes, puntuacion, escalarriesgo);
    }
    
    @Override
    public Exploracion findByIdParticipante(Integer id){
        return repoE.findByIdParticipante(id);

    }

    @Override
    public void actualizarFindrisc(Exploracion exploracion, Findrisc findrisc){
        //PUNTOS EDAD
        if (exploracion.edad < 45) { findrisc.setPuntosedad(0);}
        else if (exploracion.edad >= 45 && exploracion.edad < 54) {findrisc.setPuntosedad(2);}
        else if (exploracion.edad >= 55 && exploracion.edad < 64){findrisc.setPuntosedad(3);}
        else findrisc.setPuntosedad(4);

        //PUNTOS IMC
        if(exploracion.imc < 25){findrisc.setPuntosimc(0);}
        else if (exploracion.imc >= 25 && exploracion.imc < 30){findrisc.setPuntosimc(1);}
        else findrisc.setPuntosimc(3);

        //PUNTOS CMCINTURA
        if(exploracion.sexo.equals(Sexo.FEMENINO)){
            if(exploracion.cmcintura < 80){findrisc.setPuntoscmcintura(0);}
            else if (exploracion.cmcintura >= 80 && exploracion.cmcintura < 88){findrisc.setPuntoscmcintura(3);}
            else findrisc.setPuntoscmcintura(4);
        } else{
            if(exploracion.cmcintura < 94){findrisc.setPuntoscmcintura(0);}
            else if (exploracion.cmcintura >= 94 && exploracion.cmcintura < 102){findrisc.setPuntoscmcintura(3);}
            else findrisc.setPuntoscmcintura(102);
        }

        repoF.updateFindrisc(findrisc.idParticipante, findrisc.puntosedad, findrisc.puntosimc, findrisc.puntoscmcintura, findrisc.ptosactfisica, findrisc.ptosfrecfruta, findrisc.ptosmedicacion, findrisc.ptosglucosa, findrisc.ptosdiabetes, findrisc.puntuacion, findrisc.escalarriesgo);
    }

    @Override
    public void activarcuenta(Exploracion exploracion, Findrisc findrisc, Integer id, Integer idCoor){
        Participante usuario = repoP.findById(id).get();
        usuario.setEstadoCuenta(Estado.ALTA);
        usuario.setEdad(exploracion.edad);
        usuario.setSexo(exploracion.sexo);
        usuario.setIdCoordinador(idCoor);
        usuario.setAsistencia(0);
        usuario.setSesionesCompletas(0);
        usuario.setPerdidaDePeso(0);

        repoP.update(usuario.edad, usuario.sexo, usuario.getFotoParticipante(), usuario.getGrupo(), usuario.getAsistencia(), idCoor, usuario.getPerdidaDePeso(), usuario.getSesionesCompletas(), id);
        repoU.update(usuario.nickname, usuario.email, usuario.contrasenia, usuario.getEstadoCuenta(), id);
    }

    @Override
    public void eliminarcuenta(Integer id){
        repoE.deleteByIdParticipante(id);
        repoF.deleteByIdParticipante(id);
        repoP.deleteById(id);
    }
}
