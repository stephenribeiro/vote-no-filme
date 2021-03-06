package br.com.bluesoft.modelo;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Representa um voto
 * 
 * @author stephen.ribeiro
 */
@Entity
public class Voto implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Usuario usuario;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Filme filme;

    /**
     * Construtor
     */
    public Voto() {
    }

    /**
     * Construtor
     * 
     * @param usuario que fez o voto
     * @param filme do voto
     */
    public Voto(Usuario usuario, Filme filme) {
        this.usuario = usuario;
        this.filme = filme;
    }

    /**
     * @return the usuario
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * @return the filme
     */
    public Filme getFilme() {
        return filme;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((filme == null) ? 0 : filme.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Voto other = (Voto) obj;
        if (filme == null) {
            if (other.filme != null)
                return false;
        } else if (!filme.equals(other.filme))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (usuario == null) {
            if (other.usuario != null)
                return false;
        } else if (!usuario.equals(other.usuario))
            return false;
        return true;
    }

}
