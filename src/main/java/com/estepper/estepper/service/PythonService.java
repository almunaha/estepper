package com.estepper.estepper.service;

public interface PythonService {
    String getHello();

    String[] recetasparecidas(String[] want, String[] dontwant);

    String[] recomendacionesglobales();

    String[] recomendacionesindividuales(Integer id);

}
