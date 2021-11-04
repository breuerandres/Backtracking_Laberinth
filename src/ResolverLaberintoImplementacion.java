import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ResolverLaberintoImplementacion implements ResolverLaberintoInterface {

    private final List<Posicion> posicionesAdyacentes = Arrays.asList(new Posicion(1, 1), new Posicion(1, 0), new Posicion(0, 1),
            new Posicion(-1, -1), new Posicion(-1, 0), new Posicion(0, -1),
            new Posicion(1, -1), new Posicion(-1, 1));

    @Override
    public ArrayList<Posicion> resolverLaberinto(int[][] tabla, int xi, int yi, int xf, int yf) {
        return busqueda(tabla, xi, yi, xf, yf, 0, Integer.MAX_VALUE, new ArrayList<Posicion>());
    }

    private ArrayList<Posicion> busqueda(int[][] tabla, int inicioX, int inicioY, int finX, int finY, int acumulado, int max, ArrayList<Posicion> recorridos) {
        Posicion actual = new Posicion(inicioX, inicioY, tabla[inicioX][inicioY]);
        Posicion fin = new Posicion(finX, finY, tabla[finX][finY]);

        recorridos.add(actual);

        if (actual.equals(fin)) {
            return recorridos;
        }

        if (acumulado + actual.valor > max) {
            return new ArrayList<>();
        }

        ArrayList<Posicion> caminoEncontrado = new ArrayList<>();

        acumulado = acumulado + actual.valor;

        ArrayList<Posicion> adyacentes = obtenerAdyacentes(actual, recorridos, tabla, fin);

        for (Posicion adyacente : adyacentes) {
            ArrayList<Posicion> resultado = busqueda(tabla, adyacente.x, adyacente.y, finX, finY, acumulado, max, new ArrayList<>(recorridos));
            if (resultado.size() == 0) continue;
            int valorResultado = resultado.stream().reduce(0, (subtotal, posicion) -> subtotal + posicion.valor, Integer::sum);

            if (valorResultado < max) {
                max = valorResultado;
                caminoEncontrado = resultado;
            }
        }
        return caminoEncontrado;
    }

    private ArrayList<Posicion> obtenerAdyacentes(Posicion pos, ArrayList<Posicion> recorridos, int[][] tabla, Posicion finalPos) {
        ArrayList<Posicion> candidatos = new ArrayList<>();

        if (pos.equals(finalPos)) {
            return candidatos;
        }
        for (Posicion p : posicionesAdyacentes) {
            int xAdyacente = pos.x + p.x;
            int yAdyacente = pos.y + p.y;

            if (valido(xAdyacente, yAdyacente, recorridos, tabla)) {
                candidatos.add(new Posicion(xAdyacente, yAdyacente, tabla[xAdyacente][yAdyacente]));
            }
        }

        return candidatos;
    }

    private boolean valido(int x, int y, ArrayList<Posicion> recorridos, int[][] tabla) {
        try {
            int valor = tabla[x][y];

            return !recorridos.stream().anyMatch(new Posicion(x, y, valor)::equals) && valor != -1;
        } catch (ArrayIndexOutOfBoundsException error) {
            return false;
        }
    }
}
