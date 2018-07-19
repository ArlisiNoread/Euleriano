package euleriano.datos;

import java.util.ArrayList;

public class Vertice {
    //Lista con todos los vértices adyacentes

    public String nombre;

    public ArrayList<Vertice> vAdyacentes = new ArrayList<>();

    @Override
    public String toString() {
        return "Vertice{" + "nombre=" + nombre + ", vAdyacentes=" + imprimimeAdyacentes(vAdyacentes) + '}';
    }

    private String imprimimeAdyacentes(ArrayList<Vertice> v) {
        String ret = "";
        for (int x = 0; x < v.size(); x++) {
            ret += v.get(x).nombre + " ";
        }
        return ret;
    }

}
