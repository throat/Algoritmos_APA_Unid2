import java.util.Map;

public class TesteGRASP {
	
	public static void teste1(){
		int[][] Teste = new int[][]{
			new int[]{2,4,6,8,10}, 
			new int[]{3,2,4,5,7},
			new int[]{4,3,1,4,5},
			new int[]{3,4,3,8,4},
			new int[]{3,6,5,7,3}};
			
			GRASP test = new GRASP(5,5,0.5,60000,Teste);
			Map<Integer,Integer> result = test.search();
			System.out.println(result);
	}
	public static void teste2(){
		int[][] Teste = new int[][]{
			new int[]{3, 4, 8, 17, 19}, 
			new int[]{4, 6, 9, 15, 20},
			new int[]{1, 2, 6, 14, 18},
			new int[]{ 4, 6, 9, 11, 17},
			new int[]{2, 5, 13, 14, 17}};
			
			GRASP test = new GRASP(5,5,0.5,60000,Teste);
			Map<Integer,Integer> result = test.search();
			System.out.println(result);
	}
	public static void teste3(){
		int[][] Teste = new int[][]{
			new int[]{7, 22, 29, 42, 44, 52, 77, 90, 95, 98}, 
			new int[]{31, 38, 42, 50, 56, 64, 66, 76, 81, 89},
			new int[]{ 5, 9, 41, 52, 56, 58, 68, 70, 77, 94},
			new int[]{23, 32, 41, 46, 50, 58, 65, 68, 80, 83},
			new int[]{8, 15, 33, 43, 66, 70, 75, 79, 83, 86},
			new int[]{7, 17, 20, 25, 37, 39, 51, 64, 86, 92},
			new int[]{13, 16, 23, 30, 53, 64, 66, 74, 76, 83},
			new int[]{15, 37, 39, 40, 59, 64, 65, 71, 76, 90},
			new int[]{5, 12, 31, 36, 41, 60, 61, 71, 76, 77},
			new int[]{12, 14, 16, 21, 38, 47, 60, 67, 78, 98}};
			
			GRASP test = new GRASP(10,10,0.5,60000,Teste);
			Map<Integer,Integer> result = test.search();
			System.out.println(result);
		
	}
	public static void teste4(){
		int [][] Teste = new int[][]{
			new int[]{50 ,90, 39, 34, 66 ,81 ,27 ,48 ,46 ,68, 48, 92, 78, 84, 93, 39, 43,  1, 65, 87},
			new int[]{78 ,56  ,9 ,43 ,84 ,73 ,66 ,38 ,83 ,57 ,97 ,52 ,77 ,13, 12 , 2 ,65, 93 ,39 , 1},
			new int[]{36 ,43 ,10 ,19 ,55 ,48 ,85, 70, 82, 39, 91, 82, 85, 17,  6, 54, 87, 85,  4, 72},
			new int[]{85, 88 ,60 ,98 , 4 ,99 ,53 ,21 ,33 ,53 ,63 ,18 ,45, 29 ,43 ,41 ,80 , 4, 31 ,19},
			new int[]{ 9 ,92, 98, 44, 51,  8, 31, 15, 47, 31, 80, 83, 20, 84, 69, 49, 93, 39, 13, 88},
			new int[]{75 ,64 ,96 ,95 ,22 ,41 ,26 ,33 ,68  ,9 ,81 ,28 ,61 ,69 ,37 ,57 ,36 ,80 ,96 ,74},
			new int[]{46 ,94 , 6 ,19 ,20 ,51 ,85 ,92 ,43 ,75 ,70 ,70 ,36 ,31 ,76, 63, 89, 46, 25, 88},
			new int[]{73 , 3 ,56 ,73 ,80 ,82 ,36 ,98 ,90, 46, 10 ,46 ,65 ,83 ,75 ,47 ,61 ,28 ,59 ,2},
			new int[]{ 71 ,49 ,36 ,87 , 8 ,25 ,76, 73, 80,  6 , 6 ,33 ,79, 10, 93, 65, 26, 73, 42 ,18},
			new int[]{7 ,40 ,33 ,64 , 5 ,25 ,89 ,95 ,58 ,83 ,28 ,35 ,74 , 5 , 6 , 9 , 3 , 2 ,35 ,41},
			new int[]{49 ,49 ,15 ,18 ,65 ,55 , 1 ,79 ,10 ,37 ,77, 80, 79, 84, 93, 21, 85, 64, 46, 35},
			new int[]{ 3, 53 ,59 , 7 ,65 ,58 ,24, 55 ,26, 40 ,89 ,94 ,51, 74, 54 ,86 ,22, 83, 19, 44},
			new int[]{60 ,88, 15, 26, 11, 16, 55, 59, 81, 53, 92, 23, 55, 79, 13, 89 , 2 ,17 ,97, 41},
			new int[]{ 12, 47 ,46 ,17, 43 ,16 ,91 ,94 ,73 ,89 ,12 ,58 ,25 ,24 ,55 , 1 ,67  ,3  ,1 ,71},
			new int[]{75 ,19, 60, 87, 27, 48, 72 ,88 ,48 ,59 ,74 ,86, 49, 94 ,15, 95, 41, 94, 15, 71},
			new int[]{31 ,61 ,47 ,32 ,34 ,69 ,32 , 1 , 1 ,80 ,19 ,57 ,98 ,37 ,31 ,51 ,66 ,38 ,62 ,72},
			new int[]{70 ,78 ,41,  9 ,47 ,94, 26, 65, 17, 42, 59, 80,  7, 75, 63, 96,  7, 10, 47, 38},
			new int[]{20 ,78 ,38 ,26 ,64 ,62 ,11 ,38 ,68 ,37 ,74 ,9 ,65 ,16 ,38, 85 ,50 ,62 ,39 ,97},
			new int[]{88 ,30, 34, 33, 21,  7 ,94 ,10 ,73 ,85, 82, 62, 99, 67, 61, 10,  4, 70, 31, 49},
			new int[]{9 ,41 ,22 ,34 ,83 ,55  ,3  ,8 ,75 ,30 ,57 ,65 ,89 ,60 ,90 ,84 ,74 ,17  ,2 ,19},
			
		};
	
		GRASP test = new GRASP(20,20,0.5,60000,Teste);
		Map<Integer,Integer> result = test.search();
		System.out.println(result);
	}
	
	 public static void main(String[] args){
		 System.out.println("Teste 1");
		 teste1();
		 System.out.println("Teste 2");
		 teste2();
		 System.out.println("Teste 3");
		 teste3();
		 System.out.println("Teste 4");
		 teste4();
		 
	 }	 
	 

}
