package APA04;

 
public class TesteEdit {
		public static void avalia(int a, int b){
			if(a != b) 
	            System.out.println("Erro: resultado encontrado = "+a+" resultado esperado = "+ b);
            else
	            System.out.println("Ok.");
	   }
	   //Classe para teste de entrada comum
	   public static void teste1(){
	        String a = "pao";
	        String b = "paes";
	        int obtido = EditDistance.editDistDP(a, b);
	        int esperado = 2;
	        avalia(obtido, esperado);  
	   }
	    //Segunda String vazia
	    public static void teste2(){
	    	 String a = "pao";
		        String b = "";
		        int obtido = EditDistance.editDistDP(a, b);
		        int esperado = a.length();
		        avalia(obtido, esperado);     
	   }
	    //Primeira String vazia
	      public static void teste3(){
	    	  String a = "";
		        String b = "paes";
		        int obtido = EditDistance.editDistDP(a, b);
		        int esperado = b.length();
		        avalia(obtido, esperado);     
	   }
	    //Strings iguais  
	      public static void teste4(){
	    	  String a = "pao";
		        String b = "pao";
		        int obtido = EditDistance.editDistDP(a, b);
		        int esperado = 0;
		        avalia(obtido, esperado);     
	   }
	    public static void main (String[]args){
	        System.out.println("Teste de entrada v�lida");
	        teste1();
	        System.out.println("Segunda String vazia");
	        teste2();
	        System.out.println("Primeira String vazia");
	        teste3();
	        System.out.println("Strings Iguais");
	        teste4(); 
	    }
	}

