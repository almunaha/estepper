package com.estepper.estepper.service;

import com.estepper.estepper.model.entity.FaseValoracion;
import java.util.List;

public interface FaseValoracionService {
    public List<FaseValoracion> faseValoracion(Integer id);
    public void crearFormularios(Integer id);
    public void updateExploracion(String primeravez, Integer peso, Integer talla, Integer cmcintura, Integer edad, Integer imc, Integer id);
}
