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

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void setX(int posX) {
        this.x = posX;
    }

    public void setY(int posY) {
        this.y = posY;
    }

    public int getValor() {
        return this.valor;
    }

    public void setValor(int v) {
        this.valor = v;
    }
}
