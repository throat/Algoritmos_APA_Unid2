
import java.util.Arrays;
import java.util.Comparator;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Robertson
 */
public class BoxStacking {

    //estrutura de dados caixa contendo 3 valoes inteiros
    public static int min(int x, int y) {
        return (x < y) ? x : y;
    }

// A utility function to get maximum of two intgers
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
            // Copy the original box
            rot[index] = arr[i];
            index++;

            // First rotation of box
            rot[index] = new Caixa(arr[i].largura, max(arr[i].altura, arr[i].comprimento), min(arr[i].altura, arr[i].comprimento));
            //rot[index].altura = arr[i].largura;
            //rot[index].comprimento = max(arr[i].altura, arr[i].comprimento);
            // rot[index].largura = min(arr[i].altura, arr[i].comprimento);
            index++;

            // Second rotation of box
            rot[index] = new Caixa(arr[i].comprimento, max(arr[i].altura, arr[i].largura), min(arr[i].altura, arr[i].largura));
            //  rot[index].altura = arr[i].comprimento;
            // rot[index].comprimento = max(arr[i].altura, arr[i].largura);
            // rot[index].largura = min(arr[i].altura, arr[i].largura);
            index++;
        }

        // Now the number of boxes is 3n
        n = 3 * n;

        /* Sort the array ‘rot[]’ in decreasing order, using library
      function for quick sort */
        Arrays.sort(rot, new Comparator<Caixa>() {
            @Override
            public int compare(Caixa o1, Caixa o2) {
                return (o2.comprimento * o2.largura) - (o1.comprimento * o1.largura);
            }
        });

        // Uncomment following two lines to print all rotations
        for (int i = 0; i < n; i++ )
            System.out.println( rot[i].altura+" "+ rot[i].largura+" "+ rot[i].comprimento);
        /* Initialize msh values for all indexes 
      msh[i] –> Maximum possible Stack Height with box i on top */
        int msh[] = new int[n];
        for (int i = 0; i < n; i++) {
            msh[i] = rot[i].altura;
        }

        /* Compute optimized msh values in bottom up manner */
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (rot[i].largura < rot[j].largura
                        && rot[i].comprimento < rot[j].comprimento
                        && msh[i] < msh[j] + rot[i].altura) {
                    msh[i] = msh[j] + rot[i].altura;
                }
            }
        }

        /* Pick maximum of all msh values */
        int max = -1;
        for (int i = 0; i < n; i++) {
            if (max < msh[i]) {
                max = msh[i];
            }
        }

        return max;
    }
}
