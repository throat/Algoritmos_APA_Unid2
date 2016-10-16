package APA04;

/**
 * @author Emerson Braga - 11318735
 *
 * Spanning trees são árvores que conectam todos os vértices. O algoritmo de
 * Kruskal calcula a menor dessas árvores para um determinado grafo. Para isso,
 * é necessário utilizar uma estrutura de dados que garanta ordem baseada num
 * determinado critério. No caso, utilizamos Maps com comparações baseadas no
 * peso das arestas. O algoritmo ordena todos os vértices em ordem decrescente,
 * checa se o menor deles forma um cíclo e, se o fizer, descarta o mesmo, então
 * repete o processo até que todos os vértices tenham sido visitados.
 *
 * Grafo exemplo:
 *
 * 0 ---- 1 | \ | | \ | 2 ---- 3
 *
 * Custo:
 *
 * 0 - 1 = 2
 * 0 - 2 = 4
 * 0 - 3 = 8
 * 2 - 3 = 5
 * 1 - 3 = 2
 *
 * O algoritmo identificará
 *
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Kruskal {

    // para se utilizar Maps em Java, é necessário implementar a interface Comparable
    public static class Edge implements Comparable<Edge> {

        int from, to;
        int weight;

        Edge(int f, int t, int w) {
            from = f;
            to = t;
            weight = w;
        }

        @Override
        public int compareTo(Edge e) {
            return weight < e.weight ? -1 : (weight > e.weight ? 1 : 0);
        }

        @Override
        public String toString() {
            return "[" + from + ", " + to + "]";
        }
    }

    private static Map<Integer, Integer> parents;
    private static Map<Integer, Integer> ranks;

    // método de inicialização
    public static void initialize(int[] universe) {
        parents = new HashMap<>();
        ranks = new HashMap<>();
        for (int x : universe) {
            parents.put(x, x);
            ranks.put(x, 1);
        }
    }

    // faz a busc no hash map
    public static int FindSet(int item) {
        int parent = parents.get(item);
        if (parent == item) {
            return item;
        } else {
            return FindSet(parent);
        }
    }

    // método para tratar os cíclos no grafo
    public static void Union(int setA, int setB) {
        int pA, pB;
        while ((pA = parents.get(setA)) != setA) {
            setA = pA;
        }
        while ((pB = parents.get(setB)) != setB) {
            setB = pB;
        }

        int first = ranks.get(setA), sec = ranks.get(setB);
        if (first > sec) {
            parents.put(setB, setA);
            update(setB);
        } else if (sec > first) {
            parents.put(setA, setB);
            update(setA);
        } else {
            parents.put(setB, setA);
            update(setB);
        }
    }

    // atualiza os ranks
    public static void update(int current) {
        int currentDepth = ranks.get(current);
        int currentsParent = parents.get(current);
        int parentsDepth = ranks.get(currentsParent);
        if (!(currentDepth < parentsDepth || currentsParent == current)) {
            ranks.put(currentsParent, currentDepth + 1);
            update(currentsParent);
        }
    }

    public static ArrayList<Edge> execute(int[] vertices, Edge[] edges) {

        ArrayList<Edge> mst = new ArrayList<>();
        initialize(vertices);
        Arrays.sort(edges);
        for (Edge edge : edges) {
            if (FindSet(edge.from) != FindSet(edge.to)) {
                mst.add(edge);
                Union(edge.from, edge.to);
            }
        }
        return mst;
    }
}
