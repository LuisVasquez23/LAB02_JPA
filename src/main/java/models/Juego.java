/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author luisvasquez
 */
@Entity
@Table(catalog = "juego_LEVA_BAML", schema = "")
@NamedQueries({
    @NamedQuery(name = "Juego.findAll", query = "SELECT j FROM Juego j"),
    @NamedQuery(name = "Juego.findByIdJuego", query = "SELECT j FROM Juego j WHERE j.idJuego = :idJuego"),
    @NamedQuery(name = "Juego.findByNomJuego", query = "SELECT j FROM Juego j WHERE j.nomJuego = :nomJuego"),
    @NamedQuery(name = "Juego.findByPrecio", query = "SELECT j FROM Juego j WHERE j.precio = :precio"),
    @NamedQuery(name = "Juego.findByExistencias", query = "SELECT j FROM Juego j WHERE j.existencias = :existencias"),
    @NamedQuery(name = "Juego.findByImagen", query = "SELECT j FROM Juego j WHERE j.imagen = :imagen")})
public class Juego implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer idJuego;
    @Basic(optional = false)
    @Column(nullable = false, length = 75)
    private String nomJuego;
    @Basic(optional = false)
    @Column(nullable = false)
    private float precio;
    @Basic(optional = false)
    @Column(nullable = false)
    private int existencias;
    @Column(length = 200)
    private String imagen;
    @JoinColumn(name = "idCategoria", referencedColumnName = "idCategoria", nullable = false)
    @ManyToOne(optional = false)
    private Categoria idCategoria;

    public Juego() {
    }

    public Juego(Integer idJuego) {
        this.idJuego = idJuego;
    }

    public Juego(Integer idJuego, String nomJuego, float precio, int existencias) {
        this.idJuego = idJuego;
        this.nomJuego = nomJuego;
        this.precio = precio;
        this.existencias = existencias;
    }

    public Integer getIdJuego() {
        return idJuego;
    }

    public void setIdJuego(Integer idJuego) {
        this.idJuego = idJuego;
    }

    public String getNomJuego() {
        return nomJuego;
    }

    public void setNomJuego(String nomJuego) {
        this.nomJuego = nomJuego;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public int getExistencias() {
        return existencias;
    }

    public void setExistencias(int existencias) {
        this.existencias = existencias;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Categoria getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Categoria idCategoria) {
        this.idCategoria = idCategoria;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idJuego != null ? idJuego.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Juego)) {
            return false;
        }
        Juego other = (Juego) object;
        if ((this.idJuego == null && other.idJuego != null) || (this.idJuego != null && !this.idJuego.equals(other.idJuego))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.Juego[ idJuego=" + idJuego + " ]";
    }
    
}
