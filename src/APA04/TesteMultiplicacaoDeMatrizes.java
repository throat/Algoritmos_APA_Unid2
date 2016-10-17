/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package APA04;

/**
 *
 * @author Emerson
 */
public class TesteMultiplicacaoDeMatrizes {
    
    public static void teste1() {
        // teste com uma única matriz
        int[] input1 = {1,1};
        int output1 = MultiplicacaoDeMatrizes.execute(input1, 1, input1.length - 1);
        System.out.println("Menor número de operações: " + output1);
    }
    
    public static void teste2() {
        // todas do mesmo tamanho, resultado esperado: 3 * 10^3 = 3 000
        int[] input2 = {10,10,10,10,10};
        int output2 = MultiplicacaoDeMatrizes.execute(input2, 1, input2.length - 1);
        System.out.println("Menor número de operações: " + output2);
    }
    
    public static void teste3() {
        // teste com matriz 40x20, 20x30, 30x10, 10x30
        int[] input3 = {40,20,30,10,30};
        int output3 = MultiplicacaoDeMatrizes.execute(input3, 1, input3.length - 1);
        System.out.println("Menor número de operações: " + output3);
    }
    
    public static void main (String[] args) {
        System.out.println("Teste válido: melhor forma de multiplicar: ");
        teste1();
        System.out.println("Teste válido: multiplicação de 4 matrizes 10x10");
        teste2();
        System.out.println("Teste válido: melhor ordem (A(BC))D = 26000 ");
        teste3();
    }
    
}
