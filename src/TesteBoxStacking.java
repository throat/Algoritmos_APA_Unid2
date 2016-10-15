/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Robertson
 */
public class TesteBoxStacking {
    public static void print(Caixa[] a){
        for(int x = 0; x< a.length;x++)
            System.out.println(a[x].altura);
    }
    public static void avalia(int a, int b){
       if(a != b) 
            System.out.println("Erro: resultado encontrado = "+a+" resultado esperado = "+ b);
                    else
            System.out.println("Ok.");
   }
    
    public static void teste1(){
        Caixa caixas[] = new Caixa[]{
            new Caixa(5,5,5),
            new Caixa(5,5,5),
            new Caixa(5,5,5),
            new Caixa(5,5,5)};
  
        int esperado = 5;
        int obtido = BoxStacking.maxStackHeight(caixas, caixas.length);
        avalia(obtido, esperado);
    }
    
     public static void teste2(){
        Caixa caixas[] = new Caixa[]{
            new Caixa(40,30,20),
            //new Caixa(35,25,15),
           // new Caixa(25,15,13),
            new Caixa(15,12,10)};
    
        int esperado = 85;
        int obtido = BoxStacking.maxStackHeight(caixas, caixas.length);
        avalia(obtido, esperado);
    }
     public static void teste3(){
        Caixa caixas[] = new Caixa[]{
            //new Caixa(40,30,20),
            //new Caixa(35,25,15),
           // new Caixa(25,15,13),
            new Caixa(20,30,40)};
    
        int esperado = 40;
        int obtido = BoxStacking.maxStackHeight(caixas, caixas.length);
        avalia(obtido, esperado);
    }
     public static void main(String args[]) {
        System.out.println("Teste válido: 4 caixas cubicas de lado 5. Não é possivel empilhar.");
        teste1();  
        System.out.println("Teste válido: 2 caixas com dimensõe c1(40,30,20) e c2(15,12,10).");
        System.out.println("Solução utiliza 2 caixas c1 e 2 caixas c2 com altura maxima 85");
        teste2();  
         System.out.println("Teste válido: 1 caixas com dimensão c1(40,30,20)");
        System.out.println("Solução utiliza 1 caixa c1 de altura 40, sendo impossivel com as dimensoes 30x20 empilhar outra");
        teste3();  
        
    }
}
