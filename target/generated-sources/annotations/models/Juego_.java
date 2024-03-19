package models;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import models.Categoria;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2024-03-18T21:50:30")
@StaticMetamodel(Juego.class)
public class Juego_ { 

    public static volatile SingularAttribute<Juego, Integer> existencias;
    public static volatile SingularAttribute<Juego, Float> precio;
    public static volatile SingularAttribute<Juego, String> nomJuego;
    public static volatile SingularAttribute<Juego, String> claficacion;
    public static volatile SingularAttribute<Juego, String> imagen;
    public static volatile SingularAttribute<Juego, Integer> idJuego;
    public static volatile SingularAttribute<Juego, Categoria> idCategoria;

}