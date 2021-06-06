package domain.controllers;

import domain.models.entities.mascotas.Caracteristica;

public class CaracteristicasController {

    public void crearCaracteristica(String descripcion){

        Caracteristica caracteristica = new Caracteristica(descripcion);
    }
}
