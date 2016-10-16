package APA04;


public class TesteMochilaFrac {
	 public static void teste1(){
		MochilaFrac MF = new MochilaFrac();
		MF.weight = new double[] {10,20,30};
		MF.benefit = new double[] {60,100,120};
		MF.W = 50;
		int num_itens = MF.weight.length;
		
		double resultado = MF.fill(num_itens, MF.weight, MF.benefit, MF.W);
		
		System.out.println("Lucro m�ximo: " + resultado + "\n");
	}
	 
	 public static void teste2(){
		 
		 MochilaFrac MF = new MochilaFrac();
			MF.weight = new double[] {20,40,10,35,40};
			MF.benefit = new double[] {4,3,1,5,2};
			MF.W = 50;
			int num_itens = MF.weight.length;
			
			double resultado = MF.fill(num_itens, MF.weight, MF.benefit, MF.W);
			
			System.out.println("Lucro m�ximo: " + resultado + "\n");
		}
	 
	 public static void teste3(){
		 
		 MochilaFrac MF = new MochilaFrac();
			MF.weight = new double[] {};
			MF.benefit = new double[] {};
			MF.W = 50;
			int num_itens = MF.weight.length;
			
			double resultado = MF.fill(num_itens, MF.weight, MF.benefit, MF.W);
			
			System.out.println("Lucro m�ximo: " + resultado + "\n");
		}
	 
	 public static void teste4(){
		 
		 MochilaFrac MF = new MochilaFrac();
		 	MF.weight = new double[] {20,40,10,35,40};
			MF.benefit = new double[] {4,3,1,5,2};
			MF.W = 0;
			int num_itens = MF.weight.length;
			
			double resultado = MF.fill(num_itens, MF.weight, MF.benefit, MF.W);
			
			System.out.println("Lucro m�ximo: " + resultado + "\n");
		}
	 public static void main(String[] args){
		 teste1();
		 teste2();
		 teste3();
		 teste4();
	 }
}
