package euleriano.operaciones;

import euleriano.datos.Arista;
import euleriano.datos.Vertice;
import euleriano.persistencia.MatrizAdyacencia;
import java.util.ArrayList;

public class Euleriano {

    private ArrayList<Vertice> verticeBlackList = new ArrayList<>();
    private ArrayList<String> rutas = new ArrayList<>();
    private ArrayList<Arista> aristaBlackList = new ArrayList<>();
    private ArrayList<Vertice> todosLosVertices = new ArrayList<>();

    public ArrayList<String> rutasPosiblesEulerianas(ArrayList<Vertice> grafo) {

        int noAristas = this.numeroDeAristas(grafo);
        this.reiniciarAristaBlackList();
        this.reiniciarRutas();

        for (int x = 0; x < grafo.size(); x++) {
            this.busquedaEuleriana(grafo.get(x), grafo.get(x), "", noAristas);
            this.reiniciarAristaBlackList();
        }

        return this.rutas;
    }

    private void busquedaEuleriana(Vertice base, Vertice ultimo, String ruta, int noAristas) {
        String nombre = base.nombre;
        ArrayList<Vertice> vAdyacentes = base.vAdyacentes;
        ArrayList<Arista> tempAristaBlackList = clonarAristas(this.aristaBlackList);
        for (int x = 0; x < vAdyacentes.size(); x++) {

            if (!this.isAristaOnBlackList(new Arista(base, vAdyacentes.get(x)))) {
                if (vAdyacentes.get(x) == ultimo && (noAristas - 1 == this.aristaBlackList.size())) {
                    this.rutas.add(ruta + " " + nombre + " " + ultimo.nombre);
                    break;

                } else {
                    this.aristaBlackList.add(new Arista(base, vAdyacentes.get(x)));
                    this.busquedaEuleriana(vAdyacentes.get(x), ultimo, ruta + " " + nombre, noAristas);

                }

            }
        this.aristaBlackList = clonarAristas(tempAristaBlackList);

        }

    }

    private ArrayList<Arista> clonarAristas(ArrayList<Arista> listaArista) {
        ArrayList<Arista> ret = new ArrayList<>();

        for (int i = 0; i < listaArista.size(); i++) {
            ret.add(listaArista.get(i));
        }

        return ret;
    }

    public boolean verificadorCircuitoEuleriano(ArrayList<Vertice> grafo) {
        /*
        Una gráfica contiene un circuito euleriano si y sólo si cada uno de sus vértices es de grado par
        Comprobamos esto mediante un ciclo for 
         */

        for (int x = 0; x < grafo.size(); x++) {
            if (grafo.get(x).vAdyacentes.size() % 2 != 0) {
                return false;
            }
        }

        return true;
    }

    public boolean esConexo(ArrayList<Vertice> grafo) {
        //Hago un barrido para comprobar que cada vértice 
        //llegue a otro vértice

        boolean ret = true;

        for (int x = 0; x < grafo.size(); x++) {
            for (int y = 0; y < grafo.size(); y++) {
                if (x != y) {
                    this.reiniciarVerticeBlackList();
                    ret = busquedaVertice(grafo.get(x), grafo.get(y));
                }
            }
        }

        return ret;
    }

    private boolean busquedaVertice(Vertice base, Vertice buscado) {
        boolean ret = false;

        this.verticeBlackList.add(base);

        for (int x = 0; x < base.vAdyacentes.size(); x++) {
            if (!this.isVerticeOnBlackList(base.vAdyacentes.get(x))) {
                if (base.vAdyacentes.get(x) == buscado) {
                    return true;
                } else {
                    if (busquedaVertice(base.vAdyacentes.get(x), buscado)) {
                        return true;
                    }
                }
            }
        }

        return ret;
    }

    private void reiniciarVerticeBlackList() {
        this.verticeBlackList = new ArrayList<>();
    }

    private void reiniciarAristaBlackList() {
        this.aristaBlackList = new ArrayList<>();
    }

    private boolean isVerticeOnBlackList(Vertice v) {

        for (int x = 0; x < this.verticeBlackList.size(); x++) {
            if (this.verticeBlackList.get(x) == v) {
                return true;
            };
        }

        return false;
    }

    private boolean isAristaOnBlackList(Arista a) {

        for (int x = 0; x < this.aristaBlackList.size(); x++) {
            if (this.aristaBlackList.get(x).arista.contains(a.arista.get(0))
                    && this.aristaBlackList.get(x).arista.contains(a.arista.get(1))) {
                return true;
            };
        }

        return false;
    }

    public ArrayList<Vertice> obtenerGrafo() throws Exception {
        ArrayList<Vertice> grafo = new ArrayList<>();
        int[][] matriz = new MatrizAdyacencia().getMatriz();

        int i = matriz.length;
        int j = matriz[0].length;

        if (i != j) {
            throw new Exception("La matríz de adyacencia debe ser cuadrada");
        }

        for (int x = 0; x < i; x++) {
            //Creo los vértices
            Vertice v = new Vertice();
            v.nombre = "V" + x;
            grafo.add(v);

        }

        for (int x = 0; x < i; x++) {

            for (int y = 0; y < j; y++) {
                if (x == y && matriz[x][y] != 0) {
                    throw new Exception("Error en Matriz inicial: Un Vértice no puede ser Vértice de sí mismo, en casilla (" + x + "," + y + ")");
                }

                if (matriz[x][y] != 0 && matriz[x][y] != 1) {
                    throw new Exception("Error en Matriz inicial: Valor incorrecto en casilla (" + x + "," + y + ")");
                }

                if (matriz[x][y] == 1) {
                    grafo.get(x).vAdyacentes.add(grafo.get(y));
                }
            }
        }

        return grafo;
    }

    public int numeroDeVertices(ArrayList<Vertice> grafo) {

        return grafo.size();
    }

    public int numeroDeAristas(ArrayList<Vertice> grafo) {
        int size;
        this.reiniciarVerticeBlackList();
        for (int x = 0; x < grafo.size(); x++) {
            for (int y = 0; y < grafo.get(x).vAdyacentes.size(); y++) {
                if (!this.isAristaOnBlackList(new Arista(grafo.get(x), grafo.get(x).vAdyacentes.get(y)))) {
                    this.aristaBlackList.add(new Arista(grafo.get(x), grafo.get(x).vAdyacentes.get(y)));
                }
            }
        }

        size = this.aristaBlackList.size();
        this.reiniciarAristaBlackList();
        return size;
    }

    private void reiniciarRutas() {
        this.rutas = new ArrayList<>();
    }

}
