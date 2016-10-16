

/**
 *
 * @author Robertson
 */
public class TesteHuffman {
    public static void teste1(){
         String test = "good dog god fog apple pineapple pie applet";
 
        // assume que todos os carecteres utilizados caberão
        // em uma rray de 256 posições, por simplicidade
        int[] charFreqs = new int[256];
        //le cada caractere e atualiza a frequencia.
        for (char c : test.toCharArray())
            charFreqs[c]++;
 
        // constroi a arvore de huffman
        HuffmanTree tree = Huffman.buildTree(charFreqs);
 
        // imprime resultados
        System.out.println("Char\tPeso\tCodigo de Huffman ");
        Huffman.printCodes(tree, new StringBuffer());
    }
    
    public static void teste2(){
         String test = "a a a b b bbb$#%¨&*@!@#b cccc";
 
        // assume que todos os carecteres utilizados caberão
        // em uma rray de 256 posições, por simplicidade
        int[] charFreqs = new int[256];
        //le cada caractere e atualiza a frequencia.
        for (char c : test.toCharArray())
            charFreqs[c]++;
 
        // constroi a arvore de huffman
        HuffmanTree tree = Huffman.buildTree(charFreqs);
 
        // imprime resultados
        System.out.println("Char\tPeso\tCodigo de Huffman ");
        Huffman.printCodes(tree, new StringBuffer());
    }
     public static void main(String[] args) {
        teste1();
        teste2();
    }
}
