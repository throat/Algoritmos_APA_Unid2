package APA04;


public class TesteCorGrafo {
	 public static void teste1(){
	 
		 CorGrafo g1 = new CorGrafo(5);
	        g1.addNo(0, 1);
	        g1.addNo(0, 2);
	        g1.addNo(1, 2);
	        g1.addNo(1, 3);
	        g1.addNo(2, 3);
	        g1.addNo(3, 4);
	        System.out.println("Colorindo o Grafo");
	        int[] resultado = g1.greedyColoring();
	        System.out.println("Resultado do Grafo:");
	        for(int i = 0; i< resultado.length; i++){
	        	System.out.println("Cor n� " + i + ": " + resultado[i] + "");
	        }
	        System.out.println();

		 
		 
	 }
	 public static void teste2(){
	        CorGrafo g2 = new CorGrafo(5);
	        g2.addNo(0, 1);
	        g2.addNo(0, 2);
	        g2.addNo(1, 2);
	        g2.addNo(1, 4);
	        g2.addNo(2, 4);
	        g2.addNo(4, 3);
	        int[] resultado = g2.greedyColoring();
	        System.out.println("Resultado do Grafo:");
	        for(int i = 0; i< resultado.length; i++){
	        	System.out.println("Cor n� " + i + ": " + resultado[i] + "");
	        }
	        System.out.println();
	 }
	 
	 public static void teste3(){
	        CorGrafo g3 = new CorGrafo(5);
	        g3.addNo(0, 1);
	        g3.addNo(0, 2);
	        g3.addNo(0, 3);
	        g3.addNo(0, 4);
	        int[] resultado = g3.greedyColoring();
	        System.out.println("Resultado do Grafo:");
	        for(int i = 0; i< resultado.length; i++){
	        	System.out.println("Cor n� " + i + ": " + resultado[i] + "");
	        }
	        System.out.println();
	 }
	 
	 public static void teste4(){
	        CorGrafo g4 = new CorGrafo(4);
	        g4.addNo(0, 0);
	        g4.addNo(0, 0);
	        g4.addNo(0, 0);
	        g4.addNo(0, 0);
	        int[] resultado = g4.greedyColoring();
	        System.out.println("Resultado do Grafo:");
	        for(int i = 0; i< resultado.length; i++){
	        	System.out.println("Cor n� " + i + ": " + resultado[i] + "");
	        }
	        System.out.println();
	 }
	 public static void main(String[] args){
		 teste1();
		 teste2();
		 teste3();
		 teste4();
		 
		 
	 }
}
