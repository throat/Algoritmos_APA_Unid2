package APA04;


/**
 *
 * @author Robertson
 * 
 * CoinChange
 * O objetivo desta classe é calcular de quantas formas distintas pode-se
 * dar um troco N dado que dispoe-se de um número infinito de moedas em um 
 * conjunto S = {moeda1, moeda2, ... , moedaM}.
 * 
 * Exemplo de funcionamento: dado que queremos dar o troco de 5 centavos e
 * dispomos de moedas de 1, 2 e 3, o algoritmo partirá do caso base onde
 * nao temos troco = 0 e temos apenas moedas de 1 e irá calcular quantas
 * formas de dar troco podemos. (No caso, 1 forma para todos os tipos de moeda
 * ja que podemos nao dar nada em todos os casos).
 * Em seguida, incrementa-se o troco e refaz os passos para cada uma das moedas.
 * Neste exemplo, ao chegar no passo onde o troco é 2 e a moeda é 2, o algoritmo
 * aproveita que para troco 2 e moeda 1 já temos uma possibilidade e incrementa
 * o valor quando a moeda é 2.
 * 
 * Exemplo de tabela obtida:
 * i / j	1	2	3    
    0           1       1       1
    1           1	1	1
    2           1       2       2  
    3           1	2	3
    4           1	3	4
    5           1	3	5
			
			
    Valores das moedas			
    INDICES	0	1	2
    S	1	2	4

 */
public class CoinChange {
    public static void print(int[][]a){
        for(int i = 0; i<a.length;i++){
            for(int j = 0; j<a[0].length; j++){
                System.out.print(a[i][j]+ " ");
            }
            System.out.println();
        }
    }
    public static int coinChange( int S[], int n ){
    
    int m = S.length;
    int i, j, x, y;
    if(m==0){
        System.out.println("Sem moedas para troco.");
        return -1;
    }
    if(n<0){
        System.out.println("Entrada Inválida: troco a ser dado não pode ser negativo.");
        return -1;
    }
    // Constroi a tabela 
    int[][] table = new int[n+1][m];
    
    // preenche a tabela quando o troco é 0 com o valor 1 (n = 0)
    // isso significa que quando o troco é 0, a unica solução existente
    // é não dar troco nenhum.
    for (i=0; i<m; i++)
        table[0][i] = 1;
 
    // preenche o resto da tabela de baixo pra cima  
    for (i = 1; i < n+1; i++){
        for (j = 0; j < m; j++){
            // Count of solutions including S[j]
            x = (i-S[j] >= 0)? table[i - S[j]][j]: 0;
 
            // Count of solutions excluding S[j]
            y = (j >= 1)? table[i][j-1]: 0;
 
            // total count
            table[i][j] = x + y;
        }
    }
   // print(table);
    return table[n][m-1];
}
}
