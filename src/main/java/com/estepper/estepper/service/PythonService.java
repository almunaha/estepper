package com.estepper.estepper.service;

public interface PythonService {
    String getHello();
    String[] recetasparecidas(String[] want, String[] dontwant);
    String[] recomendacionesglobales();
    String[] recomendacionesindividuales(Integer id);

    /*NUTRIENTES*/
    //String[] recomendarAlimentos(Float fibra, Float grasasSaturadas, Float hidratosCarbono, Float proteinas, Float sales, Float icdr);

    
}
