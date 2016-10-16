package APA04;

/**
 * @author Emerson Braga - 11318735
 *
 * O algoritmo de Dijkstra recebe como parâmetro uma matriz representando um
 * grafo e um inteiro que representa o vértice inicial. A cada iteração do
 * algoritmo,é buscada a menor distância relativa ao vértice inicial dentre os e
 * a armazena até que todos os possíveis vértices estejam armazenados.
 *
 * Grafo exemplo:
 *
 * 0 ---- 1 
 * | \    | 
 * |    \ | 
 * 2 ---- 3
 *
 * Custo:
 *
 * 0 - 1 = 2
 * 0 - 2 = 4
 * 0 - 3 = 8
 * 2 - 3 = 5
 * 1 - 3 = 2
 *
 *  * Matriz de adjacência:
 *
 * | *   0   1   2   3 | -- > VERTICES 
 * | 0   0   2   4   8 | 
 * | 1   2   0  MAX MAX| 
 * | 2   4  MAX  0   5 | 
 * | 3   8   2   5   0 | 
 *   | 
 *   \/ 
 * VERTICES
 *
 * O algoritmo identificará que, apesar de existir uma rota direta para atingir
 * 3 a partir de 0, o custo através de passando por 1 é menor, logo, a menor
 * distânciad e 0 até 3 será 2 + 2 = 4. O resultado final será [0, 2, 2, 4]
 *
 *
 */
public class Dijkstra {

    public static final int MAX = 99999;

    // método para verificar o menor índice
    private static int min(int dist[], Boolean Q[]) {
        int V = dist.length; // quantidade de vertices
        int min = MAX, minIndex = -1;

        for (int i = 0; i < V; i++) {
            // se Q[i] for true, significa que já foi considerado 
            if (Q[i] == false && dist[i] <= min) {
                min = dist[i];
                minIndex = i;
            }
        }

        return minIndex;
    }

    public static int[] execute(int graph[][], int ini) {
        int V = graph[0].length;
        int dist[] = new int[V];
        Boolean Q[] = new Boolean[V];
        for (int i = 0; i < V; i++) {
            dist[i] = MAX;
            Q[i] = false;
        }
        dist[ini] = 0;
        for (int i = 0; i < V - 1; i++) {
            int u = min(dist, Q);
            Q[u] = true;
            for (int v = 0; v < V; v++) {
                if (!Q[v] && graph[u][v] != 0 && dist[u] != MAX && dist[u] + graph[u][v] < dist[v]) {
                    dist[v] = dist[u] + graph[u][v];
                }
            }
        }
        return dist;
    }

}
