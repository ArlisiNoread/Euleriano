package euleriano.persistencia;


public class MatrizAdyacencia {

    private int[][] matriz
            = {
                {0, 1, 0, 0, 1},
                {1, 0, 0, 0, 1},
                {0, 0, 0, 1, 1},
                {0, 0, 1, 0, 1},
                {1, 1, 1, 1, 0}
            };

    public int[][] getMatriz() {

        return matriz;
    }

}
