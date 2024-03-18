package models;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import models.Juego;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2024-03-18T17:14:07")
@StaticMetamodel(Categoria.class)
public class Categoria_ { 

    public static volatile SingularAttribute<Categoria, String> imagenCat;
    public static volatile SingularAttribute<Categoria, String> categoria;
    public static volatile ListAttribute<Categoria, Juego> juegoList;
    public static volatile SingularAttribute<Categoria, Integer> idCategoria;

}