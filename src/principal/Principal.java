package principal;

import euleriano.datos.Vertice;
import euleriano.operaciones.Euleriano;
import java.util.ArrayList;

public class Principal {

    public static void main(String[] args) throws Exception {
        ArrayList<Vertice> grafo = new ArrayList<>();

        ArrayList<String> rutas;
        
        Euleriano operaciones = new Euleriano();

        grafo = operaciones.obtenerGrafo();

        for (int x = 0; x < grafo.size(); x++) {
            System.out.println(grafo.get(x).toString());
        }

        System.out.println("El grafo es conexo: " + operaciones.esConexo(grafo));

        System.out.println("El grafo contiene " + operaciones.numeroDeVertices(grafo) + " vertices");

        System.out.println("El grafo contiene " + operaciones.numeroDeAristas(grafo) + " aristas");

        System.out.println("El grafo contiene circuito euleriano: " + operaciones.verificadorCircuitoEuleriano(grafo));
        
        rutas = operaciones.rutasPosiblesEulerianas(grafo);
        
        System.out.println("Número de rutas: " + rutas.size());
        
        for (int i = 0; i < rutas.size(); i++) {
            System.out.println(rutas.get(i));
        }

    }
}
