import java.util.Objects;

public class Posicion {
    int x;
    int y;
    int valor;

    public Posicion(int x, int y, int valor) {
        this.x = x;
        this.y = y;
        this.valor = valor;
    }

    public Posicion(int x, int y) {
        this.x = x;
        this. y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Posicion posicion = (Posicion) o;
        return x == posicion.x && y == posicion.y && valor == posicion.valor;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, valor);
    }
}
