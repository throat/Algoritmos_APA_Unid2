package APA04;


public class MSC {
	//Essa fun��o tem como objetivo retornar a maior sequencia de letras em comum entre as duas palavras.
	public static String lcs(String a, String b) {
	    int[][] lengths = new int[a.length()+1][b.length()+1];
	    //Tamanho do array -----> [length + 1][length + 1]
	    //Inicializa a primeira linha e coluna com 0
	 
	    for (int i = 0; i < a.length(); i++)
	        for (int j = 0; j < b.length(); j++)
	            if (a.charAt(i) == b.charAt(j)) //checa se os caracteres das duas strings s�o iguais
	                lengths[i+1][j+1] = lengths[i][j] + 1; //Letras coincidem (Soma a posi��o na diagonal + 1)
	            else 
	                lengths[i+1][j+1] =
	                    Math.max(lengths[i+1][j], lengths[i][j+1]); //Letras n�o coincidem (max entre os vizinhos)
	 
	    // recupera a string de subsequ�ncia
	    StringBuffer sb = new StringBuffer(); //Cria um BufferString para armazenar as palavras
	    for (int x = a.length(), y = b.length(); //percorre a tabela a partir do final para achar as letras
	         x != 0 && y != 0; ) {
	        if (lengths[x][y] == lengths[x-1][y]) //valor na tabela igual ao seu antecessor vertical
	            x--; //"Salta" uma linha
	        else if (lengths[x][y] == lengths[x][y-1]) //valor na tabela igual ao seu antecessor horizontal
	            y--; //"Salta" uma coluna
	        else {
	            assert a.charAt(x-1) == b.charAt(y-1);
	            sb.append(a.charAt(x-1)); //adiciona no buffer a ultima letra encontrada da sequencia
	            x--;
	            y--;
	        }
	    }
	    return sb.reverse().toString(); //retorna a string em ordem
	}
	
	public static void main(String args[]){
		String resultado = MSC.lcs("SHAZAM", "alakazam");
		
		System.out.println(resultado);
	}
	
}
