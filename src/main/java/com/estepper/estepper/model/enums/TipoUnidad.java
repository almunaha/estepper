package com.estepper.estepper.model.enums;

public enum TipoUnidad {
    GRAMOS("g"), 
    LITROS("l"), 
    MILIGRAMOS("mg"), 
    MILILITROS("ml"), 
    UNIDADES("unidades"), 
    CUCHARADAS("cucharadas"), 
    KILOGRAMOS("kg");
    
    private final String abreviatura;

    private TipoUnidad(String abreviatura) {
        this.abreviatura = abreviatura;
    }

    public String getAbreviatura() {
        return abreviatura;
    }
}
