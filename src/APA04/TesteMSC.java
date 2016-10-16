package APA04;


public class TesteMSC {
	   public static void avalia(String a, String b){
	       if(!a.equals(b)) 
	            System.out.println("Erro: resultado encontrado = "+a+" resultado esperado = "+ b);
	                    else
	            System.out.println("Ok.");
	   }
	   //Classe para teste de entrada v�lida
	   public static void teste1(){
	        String a = "ABCDGH";
	        String b = "AEDFHR";
	        String obtido = MSC.lcs(a, b);
	        String esperado = "ADH";
	        avalia(obtido, esperado);  
	   }
	 //Classe para teste de entrada v�lida
	    public static void teste2(){
	        String a = "AGGTAB";
	        String b = "GXTXAYB";
	        String obtido = MSC.lcs(a, b);
	        String esperado = "GTAB";
	        avalia(obtido, esperado);   
	   }
	    //Segunda String vazia
	    public static void teste3(){
	    	String a = "AGGTAB";
	        String b = "";
	        String obtido = MSC.lcs(a, b);
	        String esperado = "";
	        avalia(obtido, esperado);    
	   }
	    //Primeira String vazia
	      public static void teste4(){
	    	  String a = "";
		        String b = "GXTXAYB";
		        String obtido = MSC.lcs(a, b);
		        String esperado = "";
		        avalia(obtido, esperado);   
	   }
	    //Strings iguais  
	      public static void teste5(){
	    	  String a = "XXTABC";
		        String b ="XXTABC";
		        String obtido = MSC.lcs(a, b);
		        String esperado = "XXTABC";
		        avalia(obtido, esperado);   
	   }
	   /* public static void main (String[]args){
	        System.out.println("Teste de entrada v�lida");
	        teste1();
	        System.out.println("Teste de entrada v�lida");
	        teste2();
	        System.out.println("Segunda String vazia");
	        teste3();
	        System.out.println("Primeira String vazia");
	        teste4();
	        System.out.println("Strings Iguais");
	        teste5(); 
	    }*/
	}
