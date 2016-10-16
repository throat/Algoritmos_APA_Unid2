package APA04;



/**
 *
 * @author Robertson    11403755
 * O algoritmo da arvore geradora minima de prim recebe um grafo como parametro
 * e retorna um array que representa a arvore geradora minima deste grafo.
 * 
 * Consiste em criar um array booleano para cada vertice, e dado que um vertice
 * é visitado, elimina-o do array (boolean) e pega o próximo cuja aresta é a de
 * menor peso.
 * 
 * Grafo exemplo
 * 
 * 0 ---- 1
 * |      | 
 * |      |
 * 2------3
 * 
 * PESOS: 0 - 1 = 5
 *        0 - 2 = 7
 *        2 - 3 = 1
 *        1 - 3 = 4
 * 
 * O algoritmo inicia no 0 e adiciona como visitado.Em seguida, escolhe o vertice
 * de menor indice e que ainda nao foi visitado. Nesse caso, 1. No array de pais 
 * (parent[]), o indice parent[1] vai receber o valor do 0 (cuja aresta tem o menor valor)
 * e o nó 0 é mercado como visitado. Em seguida, pega-se procura-se pelo proximo indice
 * disponivel no array, que é o 1 e procura pela ligação de menor peso, que é 1 -3. 1 é posicionado
 * no array  parent[3] e 1 é visitado. O proximo indice é 2, e a ligação de menor peso 
 * é com o 3. Logo, parent[3] é colocado 2 e o laço é encerrado.
 * Como a raiz recebe menos -1 (pois ela nao possui um pai), o array retornado é dado por: [-1, 0, 1, 2]
 */
public class PrimMST {
    //retorna o valor mínimo dentre as arestas dos vértices disponíveis
    public static int minKey(int key[], Boolean mstSet[]){ 

        int min = Integer.MAX_VALUE, min_index=-1;
 
        for (int v = 0; v < mstSet.length; v++)
            if (mstSet[v] == false && key[v] < min){ //se aresta ainda nao foi adicionada e a chave é menor que a minima atual
                min = key[v]; //atualiza dados
                min_index = v;
            }
 
        return min_index;
    }
    
    // Imprime arvore geradora minima utilizando array parent[]

    public static void printMST(int parent[], int n, int graph[][]){
        
        System.out.println("Aresta   Peso");
        for (int i = 1; i < graph.length; i++)
            System.out.println(parent[i]+" - "+ i+"    "+ graph[i][parent[i]]);
    }
    
    

    public static int[] primMST(int graph[][]){
        int V = graph.length;
        // array que armazena arvore geradora minima
        int parent[] = new int[V];
 
        // chaves que guardam valor da aresta de menor peso
        int key[] = new int [V];
 
        // representam vertices ainda não incluidos na arvore
        Boolean mstSet[] = new Boolean[V];
 
        // todas as arestas iniciam como infinito
        for (int i = 0; i < V; i++)
        {
            key[i] = Integer.MAX_VALUE;
            mstSet[i] = false;
        }
 
        // Sempre incluir o primeiro vertice.
        key[0] = 0;     // Chave recebe 0
              
        parent[0] = -1; // primeiro nó é sempre raiz da arvore
 
        
        for (int count = 0; count < V-1; count++){
            // pega o vertice de menor custo ainda nao incluido na arvore
            int u = minKey(key, mstSet);
 
            // adiciona o vertice a arvore
            mstSet[u] = true;
 
            // Atualiza apenas quando o vertice é adjacente, considerando
            // apenas vertices não incluidos na arvore
            for (int v = 0; v < V; v++)
 
                // graph[u][v] é diferente de zero para vertices adjacentes
                // mstSet[v] é falso se ainda não foi incluido na arvore
                // atualiza peso da aresta se graph[u][v] for menor que key[v]
                if (graph[u][v]!=0 && mstSet[v] == false && graph[u][v] <  key[v])
                {
                    parent[v]  = u;
                    key[v] = graph[u][v];
                }
        }
        //printMST(parent, V, graph);
        return parent;
    }
}
