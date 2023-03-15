package com.estepper.estepper.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estepper.estepper.model.enums.Estado;
import com.estepper.estepper.model.enums.Sexo;

import com.estepper.estepper.model.entity.FaseValoracion;
import com.estepper.estepper.model.entity.Findrisc;
import com.estepper.estepper.model.entity.Participante;
import com.estepper.estepper.model.entity.Exploracion;
import com.estepper.estepper.model.entity.Antecedentes;
import com.estepper.estepper.model.entity.Clasificacion;
import com.estepper.estepper.model.entity.AlimentacionVal;
import com.estepper.estepper.model.entity.ActividadFisica;

import com.estepper.estepper.repository.FaseValoracionRepository;
import com.estepper.estepper.repository.ExploracionRepository;
import com.estepper.estepper.repository.FindriscRepository;
import com.estepper.estepper.repository.ParticipanteRepository;
import com.estepper.estepper.repository.UsuarioRepository;
import com.estepper.estepper.repository.ClasificacionRepository;
import com.estepper.estepper.repository.AntecedentesRepository;
import com.estepper.estepper.repository.AlimentacionValRepository;
import com.estepper.estepper.repository.ActividadFisicaRepository;


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

    @Autowired
    private ClasificacionRepository repoC;

    @Autowired
    private AntecedentesRepository repoA;

    @Autowired
    private AlimentacionValRepository repoAl;

    @Autowired
    private ActividadFisicaRepository repoAF;

    @Override
    public List<FaseValoracion> faseValoracion(Participante participante) {
        return repoV.findByParticipante(participante);
    }

    @Override
    public void crearFormularios(Participante participante){
        //EXPLORACIÓN
        Exploracion exploracion = new Exploracion(0, participante, Sexo.MASCULINO, "no", 0, 0, 0, 0, 0);
        repoE.save(exploracion);
        //FINDRISC
        Findrisc findrisc = new Findrisc(0, participante, 0, 0, 0, 0, 0, 0, 0, 0, 0, "Bajo");
        repoF.save(findrisc);
    }

    @Override
    public void crearFormulariosNuevos(Participante participante){
        //COMPROBAR QUE NO ESTÉN CREADOS YA
        if(repoC.findByParticipante(participante) == null){
            //CLASIFICACION
            Clasificacion clasificacion = new Clasificacion(0, participante, "no", 0, 0, 0, 0, 0, 0, 0, "no", 0, "no", "", "no", "", "no" );
            repoC.save(clasificacion);
            //ANTECEDENTES
            Antecedentes antecedentes = new Antecedentes(0, participante, "no", "no", "no", "no", "no", "no", "no", "no", "", "no", "no", 0, 0);
            repoA.save(antecedentes);
            //ALIMENTACION
            AlimentacionVal alimentacion = new AlimentacionVal(0, participante, "no", 0, 0, 0, 0, 0 , 0, 0, 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,"no",0,0,0,0,"baja");
            repoAl.save(alimentacion);
            //ACTIVIDAD FISICA
            ActividadFisica actfisica = new ActividadFisica(0, participante, 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,"moderada");
            repoAF.save(actfisica);
        }
    }

    @Override
    public void updateExploracion(String primeravez, Sexo sexo, Integer peso, Integer talla, Integer cmcintura, Integer edad,
            Integer imc, Participante participante) {
        repoE.updateExploracion(primeravez, sexo, peso, talla, cmcintura, edad, imc, participante);
        
    }

    @Override
    public void updateFindrisc(Participante participante,Integer puntosedad, Integer puntosimc, Integer puntoscmcintura, Integer ptosactfisica,
    Integer ptosfrecfruta, Integer ptosmedicacion, Integer ptosglucosa, Integer ptosdiabetes, Integer puntuacion,
    String escalarriesgo){
        repoF.updateFindrisc(participante, puntosedad, puntosimc, puntoscmcintura, ptosactfisica, ptosfrecfruta, ptosmedicacion, ptosglucosa, ptosdiabetes, puntuacion, escalarriesgo);
    }
    
    @Override
    public void updateClasificacion(Clasificacion clasificacion, Participante participante){
        repoC.updateClasificacion(clasificacion.getAnaliticahecha(), clasificacion.getGlucemia(), clasificacion.getColesterol(), clasificacion.getLdl(), clasificacion.getSog(), clasificacion.getHdl(), clasificacion.getTrigliceridos(), clasificacion.getHbA1c(), clasificacion.getPediranalitica(), clasificacion.getClasificacionusuario(), clasificacion.getMontesa(), clasificacion.getMotivomontesa(), clasificacion.getTaller(), clasificacion.getMotivotaller(), clasificacion.getActividadfisica(), participante);
    }

    @Override
    public void updateAntecedentes(Antecedentes antecedentes, Participante participante){
        repoA.updateAntecedentes(antecedentes.getHta(),antecedentes.getTiroides(),antecedentes.getPatmental(), antecedentes.getDislipemias(), antecedentes.getPatmuscesq(), antecedentes.getMedicacion(), antecedentes.getEcv(), antecedentes.getPatsensorial(), antecedentes.getEspecificar(), antecedentes.getFuma(), antecedentes.getDejardefumar(), antecedentes.getTasistolica(), antecedentes.getTadiastolica(), participante);
    }

    @Override
    public Exploracion findByParticipante(Participante participante){
        return repoE.findByParticipante(participante);

    }

    @Override
    public void actualizarFindrisc(Exploracion exploracion, Findrisc findrisc){
        //PUNTOS EDAD
        if (exploracion.getEdad() < 45) { findrisc.setPuntosedad(0);}
        else if (exploracion.getEdad() >= 45 && exploracion.getEdad() < 54) {findrisc.setPuntosedad(2);}
        else if (exploracion.getEdad() >= 55 && exploracion.getEdad() < 64){findrisc.setPuntosedad(3);}
        else findrisc.setPuntosedad(4);

        //PUNTOS IMC
        if(exploracion.getImc() < 25){findrisc.setPuntosimc(0);}
        else if (exploracion.getImc() >= 25 && exploracion.getImc() < 30){findrisc.setPuntosimc(1);}
        else findrisc.setPuntosimc(3);

        //PUNTOS CMCINTURA
        if(exploracion.getSexo().equals(Sexo.FEMENINO)){
            if(exploracion.getCmcintura() < 80){findrisc.setPuntoscmcintura(0);}
            else if (exploracion.getCmcintura() >= 80 && exploracion.getCmcintura() < 88){findrisc.setPuntoscmcintura(3);}
            else findrisc.setPuntoscmcintura(4);
        } else{
            if(exploracion.getCmcintura() < 94){findrisc.setPuntoscmcintura(0);}
            else if (exploracion.getCmcintura() >= 94 && exploracion.getCmcintura() < 102){findrisc.setPuntoscmcintura(3);}
            else findrisc.setPuntoscmcintura(102);
        }

        repoF.updateFindrisc(findrisc.getParticipante(), findrisc.getPuntosedad(), findrisc.getPuntosimc(), findrisc.getPuntoscmcintura(), findrisc.getPtosactfisica(), findrisc.getPtosfrecfruta(), findrisc.getPtosmedicacion(), findrisc.getPtosglucosa(), findrisc.getPtosdiabetes(), findrisc.getPuntuacion(), findrisc.getEscalarriesgo());
    }

    @Override
    public void activarcuenta(Exploracion exploracion, Findrisc findrisc, Integer id, Integer idCoor){
        Participante usuario = repoP.findById(id).get();
        usuario.setEstadoCuenta(Estado.ALTA);
        usuario.setEdad(exploracion.getEdad());
        usuario.setSexo(exploracion.getSexo());
        usuario.setIdCoordinador(idCoor);
        usuario.setAsistencia(0);
        usuario.setSesionesCompletas(0);
        usuario.setPerdidaDePeso(0.0);

        repoP.update(usuario.getEdad(), usuario.getSexo(), usuario.getFotoParticipante(), usuario.getGrupo(), usuario.getAsistencia(), idCoor, usuario.getPerdidaDePeso(), usuario.getSesionesCompletas(), id);
        repoU.update(usuario.getNickname(), usuario.getEmail(), usuario.getContrasenia(), usuario.getEstadoCuenta(), id);
    }

    @Override
    public void eliminarcuenta(Participante participante){
        repoE.deleteByParticipante(participante);
        repoF.deleteByParticipante(participante);
        repoC.deleteByParticipante(participante);
        repoA.deleteByParticipante(participante);
        repoAl.deleteByParticipante(participante);
        repoAF.deleteByParticipante(participante);
        repoP.delete(participante.getId());
    }

    @Override
    public void updateAlimentacionVal(AlimentacionVal alimentacion, Participante participante) {
        repoAl.updateAlimentacionVal(alimentacion.getAceite(), alimentacion.getPtosaceite(), alimentacion.getRacaceite(), alimentacion.getPtosaceite(), alimentacion.getRacverdura(), alimentacion.getPtosracverdura(), alimentacion.getRacfruta(), alimentacion.getPtosracfruta(), alimentacion.getRaccarne(), alimentacion.getPtosraccarne(), alimentacion.getRacmantequilla(), alimentacion.getPtosracmantequilla(), alimentacion.getRacbebidas(), alimentacion.getPtosracbebidas(), alimentacion.getRacvino(), alimentacion.getPtosracvino(), alimentacion.getRaclegumbres(), alimentacion.getPtosraclegumbres(), alimentacion.getRacpescado(), alimentacion.getPtosracpescado(), alimentacion.getRacreposteria(), alimentacion.getPtosracreposteria(), alimentacion.getRacfrutosecos(), alimentacion.getPtosracfrutosecos(), alimentacion.getCarneblanca(), alimentacion.getPtoscarneblanca(), alimentacion.getRacsofrito(), alimentacion.getPtosracsofrito(), alimentacion.getPuntuacion(), alimentacion.getAdherencia(), participante);
    }

    @Override
    public void updateActividadFisica(ActividadFisica actfisica, Participante participante) {
        repoAF.updateActividadFisica(actfisica.getVecesafv(), actfisica.getHoraafv(), actfisica.getMinafv(), actfisica.getMetsafv(), actfisica.getVecesafm(), actfisica.getHoraafm(), actfisica.getMinafm(), actfisica.getMetsafm(), actfisica.getVecescaminar(), actfisica.getHoracaminar(), actfisica.getMincaminar(), actfisica.getMetscaminar(), actfisica.getHorasentado(), actfisica.getMinsentado(), actfisica.getMetstotales(), actfisica.getClasificacion(), participante);
        
    }
}
