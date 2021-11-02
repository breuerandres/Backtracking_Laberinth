import java.util.ArrayList;

public class ResolverLaberintoImplementacion implements ResolverLaberintoInterface {

    private ArrayList<Posicion> mejorCamino;
    private ArrayList<Posicion> actualCamino = new ArrayList<>();
    private int mejorCosto = Integer.MAX_VALUE;
    private int costoActual = 0;

    @Override
    public ArrayList<Posicion> resolverLaberinto(int[][] tabla, int xi, int yi, int xf, int yf) {

        Posicion actual = new Posicion(xi, yi);
        Posicion fin = new Posicion(xf, yf);

        if (actual.x == fin.x && actual.y == fin.y) {
            return actualCamino;
        }

        actualCamino.add(actual);
        costoActual += tabla[xi][yi];
        ArrayList<Posicion> candidatos = arrayCandidatos(actual, actualCamino, tabla);

        if (candidatos.isEmpty() || (costoActual > mejorCosto)) {
            actualCamino.remove(actual);
            costoActual -= tabla[xi][yi];
            return actualCamino;
        }


        for (Posicion p : candidatos) {
            ArrayList<Posicion> resultado = resolverLaberinto(tabla, p.x, p.y, xf, yf);
            if (costoActual < mejorCosto) {
                mejorCosto = costoActual;
                mejorCamino = resultado;


            }

        }


        return actualCamino;
    }

    private ArrayList<Posicion> arrayCandidatos(Posicion pos, ArrayList<Posicion> recorridos, int[][] tabla) {
        ArrayList<Posicion> candidatos = new ArrayList<>();

        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (valido(pos.x + i, pos.y + j, recorridos, tabla)) {
                    candidatos.add(pos);
                }

            }
        }

        return candidatos;
    }

    private boolean valido(int x, int y, ArrayList<Posicion> recorridos, int[][] tabla) {
        if (x != 0 || y != 0) {
            if (x >= 0 && y >= 0 && x <= tabla.length && y <= tabla[0].length) {
                if (!recorridos.contains(new Posicion(x, y))) {
                    if (tabla[x][y] != -1) {
                        return true;
                    }

                }

            }

        }
        return false;
    }
}
