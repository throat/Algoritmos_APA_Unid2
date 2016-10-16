package APA04;


public class TesteSubsetSum {
	     public static void avalia(boolean a, boolean b){
	       if(a != b) 
	            System.out.println("Erro: resultado encontrado = "+a+" resultado esperado = "+ b);
	                    else
	            System.out.println("Ok.");
	   }
	    //Sequencia valida
	    public static void teste1(){
	    	int[] sequencia = {18,70,13,41,89};
	    	int soma = 83;
	    	boolean esperado = true;
	    	boolean obtido = SubsetSum.isSubsetSum(sequencia, soma);
	    	avalia(obtido, esperado);
	    }
	    //N�o ir� encontrar valores
	    public static void teste2(){
	    	int[] sequencia = {82,9,60,7,66,1,84,10};
	    	int soma = 12;
	    	boolean esperado = false;
	    	boolean obtido = SubsetSum.isSubsetSum(sequencia, soma);
	    	avalia(obtido, esperado);
	    }
	    //Conjunto vazio
	    public static void teste3(){
	    	int[] sequencia = {};
	    	int soma = 12;
	    	boolean esperado = false;
	    	boolean obtido = SubsetSum.isSubsetSum(sequencia, soma);
	    	avalia(obtido, esperado);
	        
	    }
	    //Soma igual a zero
	    public static void teste4(){
	    	int[] sequencia = {82,9,60,7,66,1,84,10};
	    	int soma = 0;
	    	boolean esperado = false;
	    	boolean obtido = SubsetSum.isSubsetSum(sequencia, soma);
	    	avalia(obtido, esperado);
	    }
	     
	   /* public static void main(String args[]) {
	        System.out.println("Teste v�lido: Existe a soma para 83");
	        teste1();  
	        System.out.println("Teste Inv�lido:N�o h� a soma para 12");
	        teste2();   
	        System.out.println("Conjunto vazio de elementos");
	        teste3();
	        System.out.println("Soma igual a zero");
	        teste4();
	    }*/
	}