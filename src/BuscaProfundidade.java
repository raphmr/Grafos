import java.util.Scanner;

public class BuscaProfundidade {

    public static class Vertice {

        int num;
        Vertice prox;
    }

    public static class ListaAdjacente {

        public Vertice listaV;
    }

    public static class Queue {

        int numV;
        Queue prox;
    }
    static Queue pilha = null;
    static Scanner in = new Scanner(System.in);
    static int marcado[];
    static ListaAdjacente adj[];

    public static void push(int n) {
        Queue novo = new Queue();
        novo.numV = n;
        novo.prox = pilha;
        pilha = novo;
    }

    public static void pop(int v) {
        if (pilha.numV == v) {
            pilha = pilha.prox;
        }
    }

    static void buscaprof(ListaAdjacente adj[], int tam, int v) {
        Vertice vert;
        int w;

        marcado[v] = 1;
        push(v);
        for (int i = 1; i <= tam; i++) {
            vert = adj[v].listaV;
            while (vert != null) {
                w = vert.num;
                if (marcado[w] != 1) {
                    System.out.println(" " + w);
                    buscaprof(adj, tam, w);
                }
                vert = vert.prox;
            }
        }
        pop(v);
    }

    static void mostrar_adj(ListaAdjacente adj[], int tam) {
        Vertice v;
        for (int i = 1; i < tam; i++) {
            v = adj[i].listaV;
            System.out.println("Entrada " + 1 + " ");
            while (v != null) {
                System.out.println("(" + i + "," + v.num + ") " + " ");
                v = v.prox;
            }
        }
    }
    public static void main(String[] args) {
        // TODO code application logic here
        Vertice novo;

        int tVertices, org, dest, op, num, tipo;

        System.out.println("Tipo do grado (1-não orientado, 2-orientado)");
        tipo = in.nextInt();

        System.out.println("Digite número de vértices do grafo:");
        tVertices = in.nextInt();

        adj = new ListaAdjacente[tVertices + 1];
        marcado = new int[tVertices + 1];

        for (int i = 1; i <= tVertices; i++) {
            adj[i] = new ListaAdjacente();
            marcado[i] = 0;
        }

        System.out.println("Arestas do grafo: VérticesOrigem (-1 para parar):");
        org = in.nextInt();

        System.out.println("Arestas do grafo: VérticeDestino (-1 para parar):");
        dest = in.nextInt();

        while (org != -1 && dest != -1) {
            novo = new Vertice();
            novo.num = dest;
            novo.prox = adj[org].listaV;
            adj[org].listaV = novo;

            if (tipo == 1) {
                novo = new Vertice();
                novo.num = org;
                novo.prox = adj[dest].listaV;
                adj[dest].listaV = novo;
            }

            System.out.println("Arestas do grafo: VérticeOrigem (-1 para parar):");
            org = in.nextInt();
            System.out.println("Arestas do grafo: VérticeDestino (-1 para parar):");
            dest = in.nextInt();
        }

        do {
            System.out.println("1 - Buscar em profundidade");
            System.out.println("2 - Mostar lista de adjacencias");
            System.out.println("3 - Sair");
            System.out.print("Digite seu opção: ");
            op = in.nextInt();
            switch (op) {
                case 1:
                    System.out.println("Digite um vértice de partida da busca:");
                    num = in.nextInt();
                    System.out.println(" " + num);
                    buscaprof(adj, tVertices, num);
                    for (int i = 1; i <= tVertices; i++) {
                        marcado[i] = 0;
                    }
                    break;
                case 2:
                    mostrar_adj(adj, tVertices);
                    break;
            }
        } while (op != 3);
    }
}

