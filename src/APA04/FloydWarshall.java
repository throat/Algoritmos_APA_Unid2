package APA04;

/**
 * @author Emerson Braga - 11318735
 *
 * O algoritmo de Floyd-Warshall recebe como entrada um grafo não direcionado,
 * pois será representado por uma matriz de adjacência, i. é, uma matriz cujas
 * linhas e colunas representam vértices e a posição na mesma representa a
 * distância entre os mesmos. Cada iteração em K do algoritmo garante a melhor
 * distância entre um vértice e outro passando por K outros vértices. No final
 * de sua execução, temos uma matriz com os caminhos otimizados, na qual podemos
 * verificar a menor distância de um vértice a todos os demais.
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
 * Matriz de adjacência:
 *
 *  * Matriz de adjacência:
 *
 * | *   0   1   2   3 | -- > VERTICES 
 * | 0   0   2   4   8 | 
 * | 1   2   0  MAX  2 | 
 * | 2   4  MAX  0   5 | 
 * | 3   8   2   5   0 | 
 *   | 
 *   \/ 
 * VERTICES
 *
 *
 */
class FloydWarshall {

    final static int MAX = 99999;

    public static int[][] execute(int graph[][]) {
        int N = graph[0].length;
        int dist[][] = new int[N][N];
        int i, j, k;

        for (i = 0; i < N; i++) {
            for (j = 0; j < N; j++) {
                dist[i][j] = graph[i][j];
            }
        }
        // laço mais externo garante menor caminho passando por K vértices
        for (k = 0; k < N; k++) {
            // itera sobre cada vértice
            for (i = 0; i < N; i++) {
                // verifica se a distância passando por vértices adjacentes não
                // é menor do que a distância direta
                for (j = 0; j < N; j++) {
                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }
        return dist;
    }

}
