package com.estepper.estepper.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.RealVector;
import org.apache.commons.math3.linear.BlockRealMatrix;
import java.util.*;

import com.estepper.estepper.model.entity.Alimentacion;
import com.estepper.estepper.model.entity.AlimentosConsumidos;
import com.estepper.estepper.model.entity.Participante;
import com.estepper.estepper.model.entity.Receta;
import com.estepper.estepper.repository.AlimentacionRepository;
import com.estepper.estepper.repository.AlimentosConsumidosRepository;
import com.estepper.estepper.repository.RecetaRepository;

@Service
public class AlimentacionServiceImpl implements AlimentacionService{

    @Autowired
    private AlimentacionRepository repoA;

    @Autowired
    private AlimentosConsumidosRepository repoAC;

    @Autowired
    private RecetaRepository repoR;

    @Override
    public void deleteByParticipante(Participante p) {
        repoAC.deleteAllByParticipante(p);
    }

    @Override
    public List<Alimentacion> getAlimentos() {
        return repoA.findAll();
    }

    @Override
    public List<AlimentosConsumidos> getAlimentosCon(Participante p) {
        return repoAC.findByParticipante(p);
    }

    @Override
    public void saveAlCon(AlimentosConsumidos al) {
        repoAC.save(al);
    }

    @Override
    public void saveAlimento(Alimentacion al) {
        repoA.save(al);
    }

    @Override
    public void deleteAlCon(Integer id) {
        repoAC.findById(id).get().setAlimento(null);
        repoAC.deleteById(id);
    }

    @Override
    public List<Receta> getRecetas() {
        return repoR.findAll();
    }

    @Override
    public void updateReceta(Receta receta) {
        repoR.save(receta);
    }

    @Override
    public void borraralconSem(Participante p) {
       List<AlimentosConsumidos> lista = repoAC.getWeek(p);
       for(int i = 0; i < lista.size(); i++) deleteAlCon(lista.get(i).getId());
    }

    @Override
    public Receta getRecetasById(Integer id) {
       return repoR.findById(id).get();
    }

    public double cosineSimilarity(Alimentacion a, Alimentacion b) {
        RealVector va = new ArrayRealVector(new double[]{a.getProteinas(), a.getSal(), a.getFibra_alimentaria(), a.getGrasas_saturadas(), a.getHidratos_de_carbono()});
        RealVector vb = new ArrayRealVector(new double[]{b.getProteinas(), b.getSal(), b.getFibra_alimentaria(), b.getGrasas_saturadas(), b.getHidratos_de_carbono()});
        return (va.dotProduct(vb)) / (va.getNorm() * vb.getNorm());
    }

    @Override
    public List<Receta> recetasParecidas(String[] want, String[] dontwant) {
        List<Receta> recetas = getRecetas();
        List<Alimentacion> alimentos = getAlimentos();
        
        // 1. APLICAR EL ALGORITMO DE COSINE_SIMILARITY PARA HACER UN ARRAY CON LOS ALIMENTOS Y SU SIMILITUD EN LOS ATRIBUTOS ALIMENTO.PROTEINAS Y ALIMENTO.SAL
        int numAlimentos = alimentos.size();
        BlockRealMatrix similarityMatrix = new BlockRealMatrix(numAlimentos, numAlimentos);
        for (int i = 0; i < numAlimentos; i++) {
            Alimentacion alimentoi = alimentos.get(i);
            for (int j = i+1; j < numAlimentos; j++) {
                Alimentacion alimentoj = alimentos.get(j);
                double sim = cosineSimilarity(alimentoi, alimentoj);
                similarityMatrix.setEntry(i, j, sim);
                similarityMatrix.setEntry(j, i, sim);
            }
        }
        
        // 2. HACER UNA LISTA DE ALIMENTOS_SIMILARES CON LOS ALIMENTOS.ID QUE TENGAN UNA SIMILARIDAD MAYOR A 0.3 LOS WANT (QUE ES UNA LISTA DE IDS)
        Set<Integer> wantSet = new HashSet<Integer>();
        for (String id : want) {
            wantSet.add(Integer.parseInt(id));
        }
        List<Integer> alimentosSimilares = new ArrayList<Integer>();
        for (int i = 0; i < numAlimentos; i++) {
            if (wantSet.contains(alimentos.get(i).getId())) {
                alimentosSimilares.add(i);
                continue;
            }
            boolean similar = false;
            for (int j : alimentosSimilares) {
                if (similarityMatrix.getEntry(i, j) > 0.3) {
                    similar = true;
                    break;
                }
            }
            if (similar) {
                alimentosSimilares.add(i);
            }
        }

        // 3. HACER UNA LISTA CON LAS RECETAS QUE TENGAN ESOS ALIMENTOS_SIMILARES BUSCANDO EN RECETAS.INGREDIENTES.ID
        List<Receta> recetasSimilares = new ArrayList<Receta>();
        for (Receta receta : recetas) {
            boolean found = false;
            for (Alimentacion ingrediente : receta.getIngredientes()) {
                if (alimentosSimilares.contains(ingrediente.getId())) {
                    found = true;
                    break;
                }
            }
            if (found) {
                recetasSimilares.add(receta);
            }
        }

        // 4. HACER UNA LISTA CON LAS RECETAS QUE TENGAN LOS INGREDIENTES DE WANT Y DONTWANT
        List<Receta> recetasWantDontWant = new ArrayList<Receta>();
        for (Receta receta : recetasSimilares) {
            boolean wantFound = false;
            boolean dontWantFound = false;
            for (Alimentacion ingrediente : receta.getIngredientes()) {
                if (Arrays.asList(want).contains(String.valueOf(ingrediente.getId()))) {
                    wantFound = true;
                }
                if (Arrays.asList(dontwant).contains(String.valueOf(ingrediente.getId()))) {
                    dontWantFound = true;
                }
            }
            if (wantFound && !dontWantFound) {
                recetasWantDontWant.add(receta);
            }
        }

        // 5. HACER UNA LISTA CON LAS RECETAS QUE ESTÁN EN LA LISTA 4. PERO NO EN LA LISTA 5.
        List<Receta> recetasFinales = new ArrayList<Receta>();
        for (Receta receta : recetasWantDontWant) {
            if (!recetasSimilares.contains(receta)) {
                recetasFinales.add(receta);
            }
        }
        return recetasFinales;

    }

    
}
