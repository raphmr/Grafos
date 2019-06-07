import java.util.Scanner;

public class BuscaLargura {

    public static class vertice {

        int num;
        vertice prox;
    }

    public static class listaadj {

        public vertice listav;
    }

    public static class fila {

        int numv;
        fila prox;
    }

    public static void inserir(int n) {
        fila novo = new fila();
        novo.numv = n;
        novo.prox = null;
        if (inicio == null) {
            inicio = fim = novo;
        } else {
            fim.prox = novo;
            fim = novo;
        }
    }

    public static int remover() {
        if (inicio != null) {
            int vert;
            if (inicio == fim) {
                fim = null;
            }
            vert = inicio.numv;
            inicio = inicio.prox;
            return vert;
        }
        System.out.println("lista vazia");
        return 0;
    }
    static fila inicio = null, fim = null;
    static Scanner in = new Scanner(System.in);
    static int marcado[];
    static int dist[];
    static listaadj adj[];

    public static void main(String[] args) {
        // TODO code application logic here
        vertice novo;

        int tam, org, dest, op, num = 0, tipo, flag = 0;
        String menu;

        System.out.print("Tipo do grafo (1 - não orientado, 2 - orientado): ");
        tipo = in.nextInt();

        System.out.print("Digite número de vértices do grafo: ");
        tam = in.nextInt();

        adj = new listaadj[tam + 1];
        marcado = new int[tam + 1];
        dist = new int[tam + 1];

        for (int i = 1; i <= tam; i++) {
            adj[i] = new listaadj();
            marcado[i] = 0;
        }

        System.out.print("Arestas do grafo: VérticesOrigem (-1 para para): ");
        org = in.nextInt();
        System.out.print("Arestas do grafo: VérticesDestino (-1 para parar): ");
        dest = in.nextInt();
        while (org != -1 && dest != -1) {
            novo = new vertice();
            novo.num = dest;
            novo.prox = adj[org].listav;
            adj[org].listav = novo;

            if (tipo == 1) {
                novo = new vertice();
                novo.num = org;
                novo.prox = adj[dest].listav;
                adj[dest].listav = novo;
            }
            System.out.print("Arestas do grafo: VérticeOrigem (-1 para parar): ");
            org = in.nextInt();
            System.out.print("Arestas do grafo: VérticeDestino (-1 para parar): ");
            dest = in.nextInt();
        }
        do {
            menu = "\n1 - Busca em largura"
                    + "\n2 - Mostar lista de adjacencias"
                    + "\n3 - Menor distância a partir do vértice de origem"
                    + "\n4 - Sair"
                    + "\n Digite sua opção: ";
            System.out.print(menu);
            op = in.nextInt();

            switch (op) {
                case 1:
                    System.out.print("Digite um vertice de origem da busca: ");
                    num = in.nextInt();

                    for (int i = 1; i <= tam; i++) {
                        marcado[i] = 0;
                        dist[i] = 0;
                    }
                    buscalargura(adj, tam, num);
                    flag = 1;
                    break;
                case 2:
                    mostrar_adj(adj, tam);
                    break;
                case 3:
                    if (flag == 0) {
                        System.out.println("É necessário realizar a busca primeiro");
                    } else {
                        mostrar_dist(tam, num);
                    }
                    break;
            }
        } while (op != 4);
    }

    static void buscalargura(listaadj adj[], int tam, int v) {
        vertice listavert;
        int w;
        int vertice;
        marcado[v] = 1;
        dist[v] = 0;
        inserir(v);
        while (inicio != null) {
            vertice = remover();
            for (int i = 1; i <= tam; i++) {
                listavert = adj[vertice].listav;
                while (listavert != null) {
                    w = listavert.num;
                    if (marcado[w] == 0) {
                        marcado[w] = 1;
                        dist[w] = dist[vertice] + 1;
                        inserir(w);
                    }
                    listavert = listavert.prox;
                }
            }
        }
    }

    static void mostrar_adj(listaadj adj[], int tam) {
        vertice v;
        for (int i = 1; i <= tam; i++) {
            v = adj[i].listav;
            System.out.println("Entrada " + i + " ");
            while (v != null) {
                System.out.println("(" + i + "," + v.num + ")" + " ");
                v = v.prox;
            }
        }
    }

    static void mostrar_dist(int tam, int or) {
        System.out.println("Distância da origem " + or + " para os demais vértices\n");
        for (int i = 1; i <= tam; i++) {
            System.out.println("" + i + "-" + dist[i]);
        }
    }
}