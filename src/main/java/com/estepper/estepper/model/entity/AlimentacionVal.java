package com.estepper.estepper.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "alimentacionval")
public class AlimentacionVal extends FaseValoracion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private Integer ptosaceite;
    private Integer racaceite;
    private Integer ptosracaceite;
    private Integer racverdura;
    private String aceite;
    private Integer ptosracverdura;
    private Integer racfruta;
    private Integer ptosracfruta;
    private Integer raccarne;
    private Integer ptosraccarne;
    private Integer racmantequilla;
    private Integer ptosracmantequilla;
    private Integer racbebidas;
    private Integer ptosracbebidas;
    private Integer racvino;
    private Integer ptosracvino;
    private Integer raclegumbres;
    private Integer ptosraclegumbres;
    private Integer racpescado;
    private Integer ptosracpescado;
    private Integer racreposteria;
    private Integer ptosracreposteria;
    private Integer racfrutosecos;
    private Integer ptosracfrutosecos;
    private String carneblanca;
    private Integer ptoscarneblanca;
    private Integer racsofrito;
    private Integer ptosracsofrito;
    private Integer puntuacion;
    private String adherencia;

    public AlimentacionVal() {
        super();
    }

    public AlimentacionVal(Integer id, Participante participante, String aceite, Integer ptosaceite, Integer racaceite,
            Integer ptosracaceite, Integer racverdura, Integer ptosracverdura, Integer racfruta, Integer ptosracfruta,
            Integer raccarne, Integer ptosraccarne, Integer racmantequilla, Integer ptosracmantequilla,
            Integer racbebidas, Integer ptosracbebidas, Integer racvino, Integer ptosracvino, Integer raclegumbres,
            Integer ptosraclegumbres, Integer racpescado, Integer ptosracpescado, Integer racreposteria,
            Integer ptosracreposteria, Integer racfrutosecos, Integer ptosracfrutosecos, String carneblanca,
            Integer ptoscarneblanca, Integer racsofrito, Integer ptosracsofrito, Integer puntuacion,
            String adherencia) {
        super(id, participante);
        this.aceite = aceite;
        this.ptosaceite = ptosaceite;
        this.racaceite = racaceite;
        this.ptosracaceite = ptosracaceite;
        this.racverdura = racverdura;
        this.ptosracverdura = ptosracverdura;
        this.racfruta = racfruta;
        this.ptosracfruta = ptosracfruta;
        this.raccarne = raccarne;
        this.ptosraccarne = ptosraccarne;
        this.racmantequilla = racmantequilla;
        this.ptosracmantequilla = ptosracmantequilla;
        this.racbebidas = racbebidas;
        this.ptosracbebidas = ptosracbebidas;
        this.racvino = racvino;
        this.ptosracvino = ptosracvino;
        this.raclegumbres = raclegumbres;
        this.ptosraclegumbres = ptosraclegumbres;
        this.racpescado = racpescado;
        this.ptosracpescado = ptosracpescado;
        this.racreposteria = racreposteria;
        this.ptosracreposteria = ptosracreposteria;
        this.racfrutosecos = racfrutosecos;
        this.ptosracfrutosecos = ptosracfrutosecos;
        this.carneblanca = carneblanca;
        this.ptoscarneblanca = ptoscarneblanca;
        this.racsofrito = racsofrito;
        this.ptosracsofrito = ptosracsofrito;
        this.puntuacion = puntuacion;
        this.adherencia = adherencia;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAceite() {
        return aceite;
    }

    public void setAceite(String aceite) {
        this.aceite = aceite;
    }

    public Integer getPtosaceite() {
        return ptosaceite;
    }

    public void setPtosaceite(Integer ptosaceite) {
        this.ptosaceite = ptosaceite;
    }

    public Integer getRacaceite() {
        return racaceite;
    }

    public void setRacaceite(Integer racaceite) {
        this.racaceite = racaceite;
    }

    public Integer getPtosracaceite() {
        return ptosracaceite;
    }

    public void setPtosracaceite(Integer ptosracaceite) {
        this.ptosracaceite = ptosracaceite;
    }

    public Integer getRacverdura() {
        return racverdura;
    }

    public void setRacverdura(Integer racverdura) {
        this.racverdura = racverdura;
    }

    public Integer getPtosracverdura() {
        return ptosracverdura;
    }

    public void setPtosracverdura(Integer ptosracverdura) {
        this.ptosracverdura = ptosracverdura;
    }

    public Integer getRacfruta() {
        return racfruta;
    }

    public void setRacfruta(Integer racfruta) {
        this.racfruta = racfruta;
    }

    public Integer getPtosracfruta() {
        return ptosracfruta;
    }

    public void setPtosracfruta(Integer ptosracfruta) {
        this.ptosracfruta = ptosracfruta;
    }

    public Integer getRaccarne() {
        return raccarne;
    }

    public void setRaccarne(Integer raccarne) {
        this.raccarne = raccarne;
    }

    public Integer getPtosraccarne() {
        return ptosraccarne;
    }

    public void setPtosraccarne(Integer ptosraccarne) {
        this.ptosraccarne = ptosraccarne;
    }

    public Integer getRacmantequilla() {
        return racmantequilla;
    }

    public void setRacmantequilla(Integer racmantequilla) {
        this.racmantequilla = racmantequilla;
    }

    public Integer getPtosracmantequilla() {
        return ptosracmantequilla;
    }

    public void setPtosracmantequilla(Integer ptosracmantequilla) {
        this.ptosracmantequilla = ptosracmantequilla;
    }

    public Integer getRacbebidas() {
        return racbebidas;
    }

    public void setRacbebidas(Integer racbebidas) {
        this.racbebidas = racbebidas;
    }

    public Integer getPtosracbebidas() {
        return ptosracbebidas;
    }

    public void setPtosracbebidas(Integer ptosracbebidas) {
        this.ptosracbebidas = ptosracbebidas;
    }

    public Integer getRacvino() {
        return racvino;
    }

    public void setRacvino(Integer racvino) {
        this.racvino = racvino;
    }

    public Integer getPtosracvino() {
        return ptosracvino;
    }

    public void setPtosracvino(Integer ptosracvino) {
        this.ptosracvino = ptosracvino;
    }

    public Integer getRaclegumbres() {
        return raclegumbres;
    }

    public void setRaclegumbres(Integer raclegumbres) {
        this.raclegumbres = raclegumbres;
    }

    public Integer getPtosraclegumbres() {
        return ptosraclegumbres;
    }

    public void setPtosraclegumbres(Integer ptosraclegumbres) {
        this.ptosraclegumbres = ptosraclegumbres;
    }

    public Integer getRacpescado() {
        return racpescado;
    }

    public void setRacpescado(Integer racpescado) {
        this.racpescado = racpescado;
    }

    public Integer getPtosracpescado() {
        return ptosracpescado;
    }

    public void setPtosracpescado(Integer ptosracpescado) {
        this.ptosracpescado = ptosracpescado;
    }

    public Integer getRacreposteria() {
        return racreposteria;
    }

    public void setRacreposteria(Integer racreposteria) {
        this.racreposteria = racreposteria;
    }

    public Integer getPtosracreposteria() {
        return ptosracreposteria;
    }

    public void setPtosracreposteria(Integer ptosracreposteria) {
        this.ptosracreposteria = ptosracreposteria;
    }

    public Integer getRacfrutosecos() {
        return racfrutosecos;
    }

    public void setRacfrutosecos(Integer racfrutosecos) {
        this.racfrutosecos = racfrutosecos;
    }

    public Integer getPtosracfrutosecos() {
        return ptosracfrutosecos;
    }

    public void setPtosracfrutosecos(Integer ptosracfrutosecos) {
        this.ptosracfrutosecos = ptosracfrutosecos;
    }

    public String getCarneblanca() {
        return carneblanca;
    }

    public void setCarneblanca(String carneblanca) {
        this.carneblanca = carneblanca;
    }

    public Integer getPtoscarneblanca() {
        return ptoscarneblanca;
    }

    public void setPtoscarneblanca(Integer ptoscarneblanca) {
        this.ptoscarneblanca = ptoscarneblanca;
    }

    public Integer getRacsofrito() {
        return racsofrito;
    }

    public void setRacsofrito(Integer racsofrito) {
        this.racsofrito = racsofrito;
    }

    public Integer getPtosracsofrito() {
        return ptosracsofrito;
    }

    public void setPtosracsofrito(Integer ptosracsofrito) {
        this.ptosracsofrito = ptosracsofrito;
    }

    public Integer getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(Integer puntuacion) {
        this.puntuacion = puntuacion;
    }

    public String getAdherencia() {
        return adherencia;
    }

    public void setAdherencia(String adherencia) {
        this.adherencia = adherencia;
    }

}
