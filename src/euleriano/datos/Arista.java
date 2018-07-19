package euleriano.datos;

import java.util.ArrayList;


public class Arista {

    public ArrayList<Vertice> arista = new ArrayList<>();

    public Arista(Vertice a, Vertice b) {
        arista.add(a);
        arista.add(b);
    }

}
