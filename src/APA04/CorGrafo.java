package APA04;

//Esse algoritmo implementa uma estrutura de grafo e depois colore n�s nao-adjacentes com o menor num de cores
import java.util.*;
import java.util.LinkedList;
 
class CorGrafo //Gera a estrutura do grafo
{
    private int V;   // No. de vertices
    private LinkedList<Integer> adj[]; //Lista de vizinhos
 
    //Constructor
    CorGrafo(int v) // v = numero de n�s
    {
        V = v;
        adj = new LinkedList[v]; //instancia a lista
        for (int i=0; i<v; ++i)
            adj[i] = new LinkedList(); //lista de vizinhos do n� i
    }
 
    //Adiciona um n� e seu vizinho
    void addNo(int v,int w)
    {
        adj[v].add(w);
        adj[w].add(v); 
    }
 
    //define as cores e mostra a cor definida ao final
    int[] greedyColoring()
    {
        int result[] = new int[V]; //Array de resultado
 
        // Cor para o primeiro v�rtice/N�
        result[0]  = 0;
 
        // Define os demais v�rtices como "sem cor"
        for (int u = 1; u < V; u++)
            result[u] = -1;  // "Sem cor"
 
        //Vetor temporario que indica se o vetor adjacente est� usando a cor, se True a cor est� em uso.
        boolean available[] = new boolean[V];
        for (int cr = 0; cr < V; cr++)
            available[cr] = false;
 
        //Definir cores para os demais v�rtices/n�s
        for (int u = 1; u < V; u++)
        {
            //Define as cores como indisponiveis
            Iterator<Integer> it = adj[u].iterator() ;
            while (it.hasNext())
            {
                int i = it.next();
                if (result[i] != -1)
                    available[result[i]] = true;
            }
 
            // Encontrar a primeira cor dispon�vel
            int cr;
            for (cr = 0; cr < V; cr++)
                if (available[cr] == false) //Checa qual posi��o nao tem cor utilizada
                    break; //Se sim quebra o loop
 
            result[u] = cr; //Define a cor ao n�
 
            // Reseta os valores de cor disponivel
            it = adj[u].iterator() ;
            while (it.hasNext())
            {
                int i = it.next();
                if (result[i] != -1) //so reseta para valores que tem cor definida
                    available[result[i]] = false;
            }
        }
        return result; //retorna cores dos Grafos
    }

}