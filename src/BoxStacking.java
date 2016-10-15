
import java.util.Arrays;
import java.util.Comparator;

/**
 *
 * @author Robertson
 * Esta classe é responsável por calcular a altura maxima que um conjunto de caixas
 * podem ser empilhadas, de modo que a caixa mais em baixo tenha largura e comprimento
 * maiores do que a caixa de cima.
 * Uma caixa pode ser rotacionada para ser colocada em cima de outra, ou seja, 
 * consideramos a caixa C1(altura, comprimento, largura) como 
 * c2(comprimento, altura, largura)
 * O algoritmo consiste em dado um array com as rotações das caixas, para uma posicao
 * i, calculamos a altura maxima dado que a caixa i está no topo.
 * 
 * Dado que o vetor de rotações é ordenado por área da base, podemos verificar 
 * um a um se a caixa[i+1] pode ser colocada sobre [i] e adicionamos o valor de sua altura
 * a altura maxima.
 * no fim, retornamos o resultado com a maior altura.
 */
public class BoxStacking {

    //menor de dois inteiros
    public static int min(int x, int y) {
        return (x < y) ? x : y;
    }

// maior de dois inteiros
    public static int max(int x, int y) {
        return (x > y) ? x : y;
    }

    public static int compare(Caixa a, Caixa b) {
        return (b.comprimento * b.largura) - (a.comprimento * a.largura);
    }

    public static int maxStackHeight(Caixa arr[], int n) {
        /* Create an array of all rotations of given boxes
      For example, for a box {1, 2, 3}, we consider three
      instances{{1, 2, 3}, {2, 1, 3}, {3, 1, 2}} */
        Caixa rot[] = new Caixa[3 * n];
        int index = 0;
        for (int i = 0; i < n; i++) {
            // Caixa original
            rot[index] = arr[i];
            index++;

            // Primeira rotação
            rot[index] = new Caixa(arr[i].largura, max(arr[i].altura, arr[i].comprimento), min(arr[i].altura, arr[i].comprimento));
           
            index++;

            // Segunda rotação
            rot[index] = new Caixa(arr[i].comprimento, max(arr[i].altura, arr[i].largura), min(arr[i].altura, arr[i].largura));
           
            index++;
        }

        
        n = 3 * n;

        /* Ordena vetor ‘rot[]’ em ordem decrescente usando a área da base como critério
        base = comprimento* largura */
        Arrays.sort(rot, new Comparator<Caixa>() {
            @Override
            public int compare(Caixa o1, Caixa o2) {
                return (o2.comprimento * o2.largura) - (o1.comprimento * o1.largura);
            }
        });

        // descomente para imprimir as rotacoes
       /* for (int i = 0; i < n; i++ )
            System.out.println( rot[i].altura+" "+ rot[i].largura+" "+ rot[i].comprimento);
        
      msh[i] –> altura maxima com caixa[i] no topo */
        int msh[] = new int[n];
        for (int i = 0; i < n; i++) {
            msh[i] = rot[i].altura;
        }

        /*calcula altura máxima obtida de baixo pra cima */
        for (int i = 1; i < n; i++) { 
            for (int j = 0; j < i; j++) {
                if (rot[i].largura < rot[j].largura //verifica se dimensoes sao aceitas
                        && rot[i].comprimento < rot[j].comprimento
                        && msh[i] < msh[j] + rot[i].altura) { //verifica se altura aumenta
                    msh[i] = msh[j] + rot[i].altura;
                }
            }
        }

        /* pega o valor da altura maxima */
        int max = -1;
        for (int i = 0; i < n; i++) {
            if (max < msh[i]) {
                max = msh[i];
            }
        }

        return max;
    }
}
