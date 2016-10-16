package APA04;


/**
 *
 * @author Robertson
 * Função utilizada para retornar o valor de um coeficiente binomial.
 * Um coeficiente binomial é a relacao entre dois numeros naturais n e p,
 * com n>=p, cujo resultado é n!/(p! (n-p)!).
 * 
 * O triangulo de pascal consiste em armazenar resultados de coeficientes
 * binomiais em uma matriz, de modo que coeficientes com mesmo numerador (n)
 * sejam armazenados na mesma linha, e denominador (p) na mesma coluna.
 * 
 * Observamos que o coeficiente binomial onde n e p são iguais é dado por
 * n!/p!0!, cujo resultado é sempre 1. (n! e p! se cancelam, e 0! é 1).
 * Finalmente, o coeficiente binomial de n e p, com n e p distintos é 
 * dado pelo valor do resultado do coeficiente binomial de n-1 e p-1 somados ao
 * coeficiente de n-1 e p.
 * 
 */
public class PascalTriangle {
    public static int binomialCoeff(int n, int k) {
    int C[][] = new int[n+1][k+1];
    int i, j;
     
        // Calcula o coeficiente binomial
    for (i = 0; i <= n; i++){
        for (j = 0; j <= min(i, k); j++) {
            // se for primeiro elemento da linha ou ultimo, será 1 pois n e p
            //são iguais
            if (j == 0 || j == i)
                C[i][j] = 1;     
            // caso contrário, utiliza os valores ja armazenados na linha anterior
            else
                C[i][j] = C[i-1][j-1] + C[i-1][j];
          }
     }
      
    return C[n][k];
    }
 
    // Funcao auxiliar que retorna o menor de dois inteiros
    static int min(int a, int b) {
        return (a<b)? a: b; 
    }
 
}

