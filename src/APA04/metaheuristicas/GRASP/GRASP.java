
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class GRASP {

	
	private int numMachines;
	private int numOperations;
	
	private double alpha;
	public int seed;
	
	private int time;
	int rows, columns;
	int lower_bound;
	int upper_bound;
	
	private int[][] operationXmachines;
	
	public GRASP(int numOperations, int numMachines, double alpha, int time, int[][] operationXmachines) {
		super();
		this.numMachines = numMachines;
		this.numOperations = numOperations;
		this.alpha = alpha;
		this.time = time;
		this.operationXmachines = operationXmachines;
		

	}
	
	public Map<Integer, Integer> search(){
		int best = 999999999 ;
		System.out.println("O tempo de execu  o do la o ser : "+(time/60000)+" minuto(s).");
        double tempoAtual =0;
        double tempoInicial, tempoFinal;
		Map<Integer,Integer> candidate = new LinkedHashMap<Integer,Integer>();
		while(time > tempoAtual){
			tempoInicial = System.currentTimeMillis();
			candidate = construct_randomized_greedy_solution(operationXmachines,alpha);
			//candidate = local_search(operationXmachines, candidate);
			int cost = sumJobs(candidate, operationXmachines);
			
			if (best > cost){
				best = cost;
			}
			tempoFinal=System.currentTimeMillis();
			tempoAtual += (tempoFinal - tempoInicial);
			System.out.println(best+ " "+ tempoAtual);
		}
		System.out.println("cost final: " + best);
		System.out.println("A execu  o terminou!");
        System.out.println("Melhor tempo total para realizar tarefas: " + best);
		
		return candidate;
	}
	public Map<Integer,Integer> construct_randomized_greedy_solution(int[][] values, double alpha){
		
		List<Integer>candidate = new ArrayList<Integer>(); 
		Map<Integer,Integer> costs = new LinkedHashMap<Integer,Integer>();
		List<Integer> values_costs = new ArrayList<Integer>(); 
		Map<Integer,Integer> final_costs = new LinkedHashMap<Integer,Integer>();
		
		for(int j =0; j< values.length; j++){
			int minimumCompletionTime = 999999999;
			int maximumCompletionTime = 0;
			
			for(int i = 0; i< values.length; i++){
				int partial_result = 0;
				int SJ = 0;
				if(costs.isEmpty())
					values_costs.add(findCosts(values[i]));
				else
					SJ = sumJobs(costs); //Previous jobs
					partial_result = findCosts(values[i],SJ);
					values_costs.set(i, partial_result);
			}
			for(int i = 0; i < values_costs.size(); i++){
				if(values_costs.get(i) < minimumCompletionTime)
					if(!costs.containsKey(i))
					minimumCompletionTime = values_costs.get(i);
			}
			
			for(int i = 0; i < values_costs.size(); i++){
				if(values_costs.get(i) > maximumCompletionTime)
					if(!costs.containsKey(i))
					maximumCompletionTime = values_costs.get(i);
			}
			
			//System.out.println("Makespam:" + values_costs.toString());
			
			int Range = maximumCompletionTime - minimumCompletionTime;
			double width = Range * alpha;
			//System.out.println("Range: " + Range + " // " + "Width: " + width );
			
			double [] RCL = {minimumCompletionTime,minimumCompletionTime + width};
			//System.out.println("RCL: " + RCL[0] + " " + RCL[1]);
			
			for(int i = 0; i < values_costs.size(); i++){
				if(values_costs.get(i) >= RCL[0] && values_costs.get(i) <= RCL[1]){
					if(!costs.containsKey(i))
						candidate.add(i);
				}
			}
			//RANKS VALUES
			//System.out.println("Candidates: " + candidate );
			for(int i = candidate.size()-1; i>0;i--){
				//System.out.println("V1: " + values_costs.get(candidate.get(i-1)) + " V2: " + values_costs.get(candidate.get(i)) );
				if(values_costs.get(candidate.get(i-1)) > values_costs.get(candidate.get(i))){
					int a = candidate.get(i);
					candidate.set(i, candidate.get(i-1));
					candidate.set(i-1, a);
				}
			}
	
			//System.out.println("Candidates: " + candidate );
			
			//CALCULATES FITNESS
			double [] fitness = new double[candidate.size()]; 
			double R = 0;
			for(int i = 0; i<candidate.size(); i++){
				double result = 1.0 / (candidate.get(i) + 1);
				fitness[i] = result;
				R = R + fitness[i];
			}
			
			
			
			//CALCULATES PROBABILITY
			double [] probability = new double[candidate.size()];
			
			for(int i =0; i < candidate.size(); i++){
				probability[i] = fitness[i]/R; 
			}
			//System.out.println("R: " + R + " " + "Fitness " +Arrays.toString(fitness) + "Prob " + Arrays.toString(probability) );
			double prob_result = Math.random();
			double distance = Math.abs(probability[0] - prob_result);
			int idx = 0;
			for(int d = 1; d < probability.length; d++){
				 double cdistance = Math.abs(probability[d] - prob_result);
				    if(cdistance < distance){
				        idx = d;
				        distance = cdistance;
				    }
			}
			//System.out.println("Number selected: " + candidate.get(idx) + " com distancia: " + distance);
			
			int cost_selected = values[candidate.get(idx)][0];
			costs.put(candidate.get(idx), cost_selected);
			//System.out.println(costs.keySet().toString() + costs.values().toString() + costs.size());
			Integer intObj = new Integer(values[candidate.get(idx)][costs.size()-1]);
			final_costs.put( candidate.get(idx),intObj);
		
			candidate.clear();
		}
	return final_costs;

}
	/*public Map<Integer,Integer>  local_search(int[][] values, Map<Integer,Integer> candidate){
		 
		}*/
	public int sumJobs(Map<Integer,Integer> a){
		int sum = 0;
		for(int f: a.values()){
			sum += f;
			
		}
		return sum;
	}
	
	public int sumJobs(Map<Integer,Integer> a, int[][] values){
		int sum = 0;
		for (int f=0; f < values.length; f++){
			sum +=values[f][0];
		}
		for(int f: a.values()){
			sum += f;
		}
		return sum;
	}
	
	public int findCosts(int[] values){
		int makespam = 0;
		for(int i = 0; i < values.length; i++){
			makespam = makespam +  values[i];
		}
		return makespam;
	}
	public int findCosts(int[] values, int prev){
		int makespam = prev;
		for(int i = 0; i < values.length; i++){
			makespam = makespam +  values[i];
		}
		return makespam;
	}
	
	
	
	/*public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] Teste = new int[][]{
			new int[]{2,4,6,8,10}, 
			new int[]{3,2,4,5,7},
			new int[]{4,3,1,4,5},
			new int[]{3,4,3,8,4},
			new int[]{3,6,5,7,3}};
		
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
	}*/
}
