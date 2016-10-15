/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Robertson
 */
public class TesteCoin {
   public static void avalia(int a, int b){
       if(a != b) 
            System.out.println("Erro: resultado encontrado = "+a+" resultado esperado = "+ b);
                    else
            System.out.println("Ok.");
   }
   
   public static void teste1(){
        int[] moedas = new int[]{1, 2, 3};
        int resultado = CoinChange.coinChange(moedas, 5);
        int resultadoEsperado = 5;
        avalia(resultado, resultadoEsperado);    
   }
    public static void teste2(){
        int[] moedas = new int[]{};
        int resultado = CoinChange.coinChange(moedas, 5);
        int resultadoEsperado =-1;
        avalia(resultado, resultadoEsperado);   
   }
    
    public static void teste3(){
        int[] moedas = new int[]{10, 15, 20};
        int resultado = CoinChange.coinChange(moedas, 9);
        int resultadoEsperado =0;
        avalia(resultado, resultadoEsperado);   
   }
      public static void teste4(){
        int[] moedas = new int[]{10, 15, 20};
        int resultado = CoinChange.coinChange(moedas, -9);
        int resultadoEsperado =-1;
        avalia(resultado, resultadoEsperado);   
   }
    public static void main (String[]args){
        System.out.println("Teste de entrada válida:");
        teste1();
        System.out.println("Teste de exceção: sem moedas para troco");
        teste2();
        System.out.println("Teste de exceção: troco menor que a menor moeda");
        teste3();
        System.out.println("Teste de exceção: troco de valor negativo");
        teste4();
    }
}
