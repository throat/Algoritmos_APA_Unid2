
/**
 *
 * @author Robertson
 *
 * A compressão de dados de Huffman utiliza codigo binário utilizando menos bits
 * para caracteres mais utilizados, economizando espaço.
 *
 * Funciona da seguinte maneira: inicialmente passa-se como parametro um array
 * com as frequencias dos caracteres. O algoritmo adiciona essas frequencias como
 * folhas (HuffmanLeaf). Em seguida, são pegas duas folhas, com as menores frequencias
 * e cria-se um nó (HuffmanNode). A frequencia do nó é a soma das frequencias e as
 * antigas folhas viram filhos deste nó. O algoritmo itera até não existir mais 
 * referencia pra folhas. No fim, retornará uma árvore.
 * 
 * Para imprimir os códigos, uma função percorre a arvore, e para cada nó
 * percorrido a esquerda adiciona 0, e a direita 1. Os nós de maior frequencia,
 * por serem  inseridos por ultimo, ficaram proximos a raiz da árvore e consequentemente
 * os códigos (caminho percorrido até a folha) será menor.
 */
import java.util.*;

abstract class HuffmanTree implements Comparable<HuffmanTree> {

    public final int frequency; // frequencia da arvore

    public HuffmanTree(int freq) {
        frequency = freq;
    }

    // compara as frequencias
    public int compareTo(HuffmanTree tree) {
        return frequency - tree.frequency;
    }
}

class HuffmanLeaf extends HuffmanTree {

    public final char value; // caractere representado pela folha

    public HuffmanLeaf(int freq, char val) {
        super(freq);
        value = val;
    }
}

class HuffmanNode extends HuffmanTree {

    public final HuffmanTree left, right; // subarvores

    public HuffmanNode(HuffmanTree l, HuffmanTree r) {
        super(l.frequency + r.frequency);
        left = l;
        right = r;
    }
}

public class Huffman {

    public static HuffmanTree buildTree(int[] charFreqs) {
        //fila de prioridade
        PriorityQueue<HuffmanTree> trees = new PriorityQueue<HuffmanTree>();
        // inicialmente temos uma floresta de folhas
        // uma para cada caractere não-nulo
        for (int i = 0; i < charFreqs.length; i++) {
            if (charFreqs[i] > 0) {
                trees.offer(new HuffmanLeaf(charFreqs[i], (char) i));
            }
        }

        assert trees.size() > 0; //caso negativo, lança assertionerror().
        // itera até não ter mais folhas
        while (trees.size() > 1) {
            // duas arvores com a menor prioridade
            HuffmanTree a = trees.poll(); //´poll() remove
            HuffmanTree b = trees.poll();

            // coloca em um novo nó e reinsere na fila
            trees.offer(new HuffmanNode(a, b)); //offer() insere
        }
        return trees.poll();
    }

    public static void printCodes(HuffmanTree tree, StringBuffer prefix) {
        assert tree != null;
        if (tree instanceof HuffmanLeaf) {
            HuffmanLeaf leaf = (HuffmanLeaf) tree;
            // imprime caractere, frequencia e codigo para a folha
            System.out.println(leaf.value + "\t" + leaf.frequency + "\t" + prefix);

        } else if (tree instanceof HuffmanNode) {
            HuffmanNode node = (HuffmanNode) tree;

            // percorre a esquerda
            prefix.append('0');
            printCodes(node.left, prefix);
            prefix.deleteCharAt(prefix.length() - 1);

            // percorre a direita
            prefix.append('1');
            printCodes(node.right, prefix);
            prefix.deleteCharAt(prefix.length() - 1);
        }
    }

}
