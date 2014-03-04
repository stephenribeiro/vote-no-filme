package br.com.bluesoft.modelo;

import java.util.Arrays;
import java.util.List;

/**
 * Representa uma combinação de dois filmes a e b
 * 
 * @author stephen.ribeiro
 */
public class CombinacaoFilme {

    private Filme a;

    private Filme b;

    /**
     * Construtor
     * 
     * @param a filme a
     * @param b filme b
     */
    public CombinacaoFilme(Filme a, Filme b) {
        this.a = a;
        this.b = b;
    }

    /**
     * Devolve os filmes numa lista
     * 
     * @return
     */
    public List<Filme> getAsList() {
        return Arrays.asList(this.a, this.b);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((a == null) ? 0 : a.hashCode());
        result = prime * result + ((b == null) ? 0 : b.hashCode());
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
        CombinacaoFilme other = (CombinacaoFilme) obj;
        if (a == null) {
            if (other.a != null)
                return false;
        } else if (!a.equals(other.a) && !a.equals(other.b))
            return false;
        if (b == null) {
            if (other.b != null)
                return false;
        } else if (!b.equals(other.b) && !b.equals(other.a))
            return false;
        return true;
    }

}
