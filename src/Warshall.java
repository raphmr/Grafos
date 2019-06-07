public class Warshall {

    static void fechamento(boolean ma[][], boolean mf[][]) {
        int i, j, k;

        // inicialização da matriz de fechamento
        for (i = 0; i < ma.length; i++) {
            for (j = 0; j < ma.length; j++) {
                mf[i][j] = ma[i][j];
            }
        }

        // algoritmo de Warshall
        for (k = 0; k < ma.length; k++) {
            for (i = 0; i < ma.length; i++) {
                if (mf[i][k]) {
                    for (j = 0; j < ma.length; j++) {
                        mf[i][j] = mf[i][j] || mf[k][j];
                    }
                }
            }
        }
    }

    static void imprime(boolean m[][]) {
        int i, j;
        for (i = 0; i < m.length; i++) {
            for (j = 0; j < m.length; j++) {
                System.out.print(m[i][j] + "\t");
            }
            System.out.println("");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        // TODO code application logic here
        boolean ma[][] = new boolean[3][3];
        boolean mf[][] = new boolean[3][3];

        ma[0][0] = false;   ma[1][0] = true;    ma[2][0] = true;
        ma[0][1] = true;    ma[1][1] = false;   ma[2][1] = true;
        ma[0][2] = true;    ma[1][2] = true;    ma[2][2] = false;

        imprime(ma);
        fechamento(ma, mf);
        imprime(mf);
    }
}