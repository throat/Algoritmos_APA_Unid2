
/**
 *
 * @author Robertson
 * 
 * CoinChange
 * O objetivo desta classe é calcular de quantas formas distintas pode-se
 * dar um troco N dado que dispoe-se de um número infinito de moedas em um 
 * conjunto S = {moeda1, moeda2, ... , moedaM}.
 */
public class CoinChange {
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
    return table[n][m-1];
}
}
