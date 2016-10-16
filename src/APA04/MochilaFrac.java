package APA04;

import java.util.Scanner;

public class MochilaFrac {

	double weight[];
	double benefit[];
	double ratio[];
	double W;


	int getNext(int bLength, double ratio[]) {
		
		double highest = 0;
		int index = -1;
		for (int i = 0; i < bLength; ++i) {
			if (ratio[i] > highest) {
				highest = ratio[i];
				index = i;
			}
		}
		return index;
	}

	double fill(int nItens, double weight[], double benefit[], double W) { //(Numero de itens, peso, beneficios, peso da mochila)
		
		ratio = new double[nItens];
		
		for(int i = 0; i < nItens; i++){
			ratio[i] = benefit[i]/ weight[i];
		}
		
		double cW = 0; // peso atual
		double cB = 0; // beneficio atual

		//System.out.print("\nItems considered: ");
		while (cW < W) {
			int item = getNext(nItens, ratio); // Calcula a pr�xima raz�o
			if (item == -1) {
				// Sem itens
				break;
			}

			//System.out.print((item + 1) + " ");
			if (cW + weight[item] <= W) {
				cW += weight[item];
				cB += benefit[item];
				// Marcado com utilizado pro calculo da raz�o
				ratio[item] = 0;
			} else {
				cB += (ratio[item] * (W - cW));
				cW += (W - cW);
				break; // bolsa cheia
			}
		}
		return cB; //retorna o lucro m�ximo
	}

}