package br.com.bluesoft.modelo;

import java.util.Arrays;
import java.util.List;

public class CombinacaoFilme {

    private Filme a;

    private Filme b;

    public CombinacaoFilme(Filme a, Filme b) {
        this.a = a;
        this.b = b;
    }

    public List<Filme> getAsList() {
        return Arrays.asList(this.a, this.b);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((a == null) ? 0 : a.hashCode());
        result = prime * result + ((b == null) ? 0 : b.hashCode());
        return result;
    }

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
