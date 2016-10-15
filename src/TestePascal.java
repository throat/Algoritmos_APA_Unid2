

/**
 *
 * @author Robertson
 */
public class TestePascal {
     public static void avalia(int a, int b){
       if(a != b) 
            System.out.println("Erro: resultado encontrado = "+a+" resultado esperado = "+ b);
                    else
            System.out.println("Ok.");
   }
    public static void teste1(){
        int n = 5, k = 2;
        int esperado = 10;
        int obtido = PascalTriangle.binomialCoeff(n, k);
        System.out.println("O valor do Coeficiente binomial C("+n+","+k+") é :"+obtido);
        avalia(esperado, obtido);
    }
    
     public static void teste2(){
        int n = 5, k = 2;
        int esperado = 0;
        int obtido = PascalTriangle.binomialCoeff(k, n);
        System.out.println("O valor do Coeficiente binomial C("+k+","+n+") é :"+obtido);
        avalia(esperado, obtido);
    }
      public static void teste3(){
        int k = 2;
        int esperado = 1;
        int obtido = PascalTriangle.binomialCoeff(k, k);
        System.out.println("O valor do Coeficiente binomial C("+k+","+k+") é :"+obtido);
        avalia(esperado, obtido);
        
    }
     
    public static void main(String args[]) {
        System.out.println("Teste válido: n > k");
        teste1();  
        System.out.println("Teste Inválido: n < k");
        teste2();   
        System.out.println("Teste válido: n = k");
        teste3();
    }
}
