package APA04;


public class EditDistance {
	//o algoritmo mostra com quantas opera��es em String1(inser��o,remo��o e substitui��o) confirma str1 = str2
	    
	static int min(int x,int y,int z) {
	        if (x < y && x <z) return x;
	        if (y < x && y < z) return y;
	        else return z;
	    }
	 
	    
	static int editDistDP(String str1, String str2){
	    	int m = str1.length();
	    	int n = str2.length();
	    	
	        // Cria a tabela com o tamanho [m + 1][n + 1] onde n,m s�o os tamanhos das palavras
	        int dp[][] = new int[m+1][n+1];

	        for (int i=0; i<=m; i++){ //percorre linha
	        
	            for (int j=0; j<=n; j++){//percorre coluna
	            
	                // Se a primeira String � vazia ent�o so resta inserir tudo da segunda String na primeira
	                if (i==0)
	                    dp[i][j] = j;  // requer j opera��es
	      
	                // Funciona de forma inversa do caso acima
	                else if (j==0)
	                    dp[i][j] = i; // requer i opera��es
	      
	                // Se os caracteres forem iguais ent�o nenhuma opera��o � feita
	                else if (str1.charAt(i-1) == str2.charAt(j-1))
	                    dp[i][j] = dp[i-1][j-1];
	      
	                //Se h� caracteres diferentes, ent�o devemos definir a celula como -> (1 + menor vizinho)
	                else
	                    dp[i][j] = 1 + min(dp[i][j-1],  // Insert
	                                       dp[i-1][j],  // Remove
	                                       dp[i-1][j-1]); // Replace
	            }
	        }
	        
	        
	  
	        return dp[m][n];
	    }
	    
}
