package APA04;


public class SubsetSum {
	
	    // Dado valores n�o negativos, essa fun��o retorna se h� elementos cuja soma d� igual ao parametro sum
	    static boolean isSubsetSum(int set[], int sum){ //Array com os numeros, tamanho do array e soma desejada
	    	
	    	if(sum == 0 && set.equals(0)){
	    		return false;
	    	}
	    	
	    	int n = set.length;
	        //cria-se a matriz de booleanos subset com o tamanho [Soma + 1][Tamanho_set + 1]
	        boolean subset[][] = new boolean[sum+1][n+1];
	      
	        // Caso a soma seja zero, ent�o o resultado � verdadeiro
	        for (int i = 0; i <= n; i++)
	          subset[0][i] = true;
	      
	        // Se a soma n�o for zero, mas o array set estiver vazio entao o resultado � falso 
	        for (int i = 1; i <= sum; i++)
	          subset[i][0] = false;
	      
	         // Fill the subset table in botton up manner
	         for (int i = 1; i <= sum; i++) //percorre linhas
	         {
	           for (int j = 1; j <= n; j++)//percorre coluna
	           {
	             subset[i][j] = subset[i][j-1]; //Atribui o valor do seu vizinho lateral
	             if (i >= set[j-1]) //contador de linhas > valor do array na posi��o j-1
	            	 //valor na posi��o atual ou valor na posi��o [i - valor array][j-1] 
	               subset[i][j] = subset[i][j] ||   subset[i - set[j-1]][j-1]; 
	           }
	         }
	      
	         //retorna a ultima diagonal da matriz
	         //que indicara se h� ou n�o o valor sum atraves da soma de dois elementos
	         return subset[sum][n]; 
	     
	    }
	   

	}

