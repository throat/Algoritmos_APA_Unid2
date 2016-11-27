
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Robertson Lima -11403755
 */
public class Formiga {

    private int indiceAtual = 0; 
    private int solucao[];
    private double makespan[][];
    public boolean visitado[];

    public Formiga(int tarefas) {
        this.solucao = new int[tarefas];
        this.visitado = new boolean[tarefas];
    }
   // marca nó como visitado e incrementa o indice
    public void visitaNo(int visit) {
        solucao[indiceAtual] = visit;
        visitado[visit] = true;
        indiceAtual++;
    }
    //verifica se o no foi visitado
    public boolean foiVisitado(int no) {
        return visitado[no];
    }

    //seleciona o proximo no escolhido com base tanto aleatoria quanto baseado na trilha.
    public int selecionarProximo(double[][] trilhas, double[][] grafo) {
        int proximo = 0;
        Random random = new Random();
        //obtem um numero aleatorio
        double aletorio = random.nextDouble();
        double melhorOpcao = 0.5;
        //se o numero obtido for menor que 0.8, baseia o escolhido na trilha de feromonio
        //escolhendo o de feromonio de maior intensidade
        if (aletorio < melhorOpcao) {
            double maiorFeromonioAtual = -1;
            for (int i = 0; i < grafo[0].length; i++) {
                double feromonioAtual = trilhas[i][indiceAtual];
                if ((!foiVisitado(i)) && (feromonioAtual > maiorFeromonioAtual)) {
                    proximo = i;
                    maiorFeromonioAtual = feromonioAtual;
                }
            }
            return proximo;
        } else {
            //cria um array com as probabilidades de se escolher 
            //os nos que ainda nao foram visitados
            double prob[] = getProbabilidades(trilhas);
            double r = aletorio;
            double total = 0;
            for (int i = 0; i < grafo[0].length; i++) {
                //quando total chegar a ser maior que o aleatorio, 
                //i será o no escolhido
                total += prob[i];
                if (total >= r) {
                    proximo = i;
                    return proximo;
                }
            }
        }
        return proximo;
    }

    //simplesmente retorna a solução em um array
    //ou seja, a sequencia das tarefas
    public int[] getSolucao() {
        return solucao;
    }

    //funcao que cria um array de probabilidades para a possível
    //selecao de um no. usado pela função selecionarProximo()
    private double[] getProbabilidades(double[][] trilhas) {
        double prob[] = new double[solucao.length];
        double denom = 0.0;
        for (int x = 0; x < trilhas.length; x++) {
            if (!foiVisitado(x)) {
                denom += trilhas[x][indiceAtual];
            }
        }
        for (int j = 0; j < solucao.length; j++) {
            if (foiVisitado(j)) {
                prob[j] = 0.0;
            } else {
                double numerador = trilhas[j][indiceAtual];
                prob[j] = numerador / denom;
            }
        }
        return prob;
    }

    //limpa traços de execução da formiga
    public void limpa() {
        for (int i = 0; i < visitado.length; i++) {
            visitado[i] = false;

        }
    }

    public void setIndiceAtual(int indice) { this.indiceAtual = indice; }

    public int getIndiceAtual() {  return indiceAtual;  }

    public double getMakespan() {  return makespan[makespan.length - 1][makespan[0].length - 1];  }

  

    //funcao que faz o recalculo do makespan de uma solucao temporaria que foi
    //obtida trocando o posicionamento de uma tarefa. o delta representa o indice
    //de menor valor que foi modificado, de modo que a funcao ira recalcular apenas
    //deste indice em diante
    public double[][] calculaMakespanDelta(int[] solTemp, double[][] graph, int delta) {
        int numTarefas = solucao.length;
        int numMaquinas = graph.length;
        double[][] tempoMaquinas = makespan.clone();
        double tempo;

         for (int i = 0; i < numMaquinas; i++) {
            for (int j = delta; j < numTarefas; j++) {
                tempo = graph[i][solucao[j]];
                if (i == 0) { //caso seja a primeira maquina  
                    if (j == 0) { //primeira tarefa da primeira máquina
                        tempoMaquinas[i][j] = tempo; //tempo inicial é zero
                    } else { //segunda tarefa em diante
                        tempoMaquinas[i][j] = tempo + tempoMaquinas[i][j - 1]; //tempo da tarefa anterior + tempo desta tarefa
                    }
                } else //da segunda maquina em diante
                {
                    if (j == 0){//primeira tarefa
                        //tempo inicial é o tempo do fim da  primeira tarefa da maquina anterior
                        tempoMaquinas[i][j] = tempoMaquinas[i-1][j] + tempo; 
                    }
                    //se tarefa nao acabou na maquina anterior
                    else if(tempoMaquinas[i][j - 1] < tempoMaquinas[i - 1][j]) {
                        //começa depois que acaba na maquina anterior
                        tempoMaquinas[i][j] = tempo + tempoMaquinas[i-1][j];
                    } else {
                        //começa imediatamente depois da tarefa anterior
                        tempoMaquinas[i][j] = tempo + tempoMaquinas[i][j-1];
                    }
                }
            }
        }
        //retorna matriz inteira
        return tempoMaquinas;

    }

    public void setMakespan(double [][]makes){  this.makespan = makes;  }
    
    //calcula o makespan inteiro do zero e atribui à solução presente na formiga
    public void calcMakespan(double[][] graph) {
        int numMaquinas = graph.length;
        int numTarefas = solucao.length;
        double[][] tempoMaquinas = new double[numMaquinas][numTarefas];
        double tempo = 0; //tempo inicial

        for (int i = 0; i < numMaquinas; i++) {
            for (int j = 0; j < numTarefas; j++) {
                tempo = graph[i][solucao[j]];
                if (i == 0) { //caso seja a primeira maquina  
                    if (j == 0) { //primeira tarefa
                        tempoMaquinas[i][j] = tempo;
                    } else { //segunda em diante
                        tempoMaquinas[i][j] = tempo + tempoMaquinas[i][j - 1];
                    }
                } else //da segunda maquina em diante
                {
                    if (j == 0){//primeira tarefa
                        tempoMaquinas[i][j] = tempoMaquinas[i-1][j] + tempo;
                    }
                    else if(tempoMaquinas[i][j - 1] < tempoMaquinas[i - 1][j]) {
                        tempoMaquinas[i][j] = tempo + tempoMaquinas[i-1][j];
                    } else {
                        tempoMaquinas[i][j] = tempo + tempoMaquinas[i][j-1];
                    }
                }
            }
        }
        makespan = tempoMaquinas.clone();      
    }

    public void melhorarSolucao(double[][] graph) {
        calcMakespan(graph);
        double solAtualMakespan = getMakespan();

        int[] solucaoLocal = new int[solucao.length];
        List<Integer> listaTarefas = new ArrayList<Integer>();

        for (int tarefa : solucao) {
            listaTarefas.add(tarefa);
        }

        List<Integer> local = listaTarefas;

        //int indexI = 0;
		int indexI = (solucao.length -1);
        boolean lessMakespan = true;
        //enquanto puder trocar indicies e nao tiver melhorado o makespan
        while (indexI > 0 && lessMakespan) {
            int tarefaI = local.get(indexI);

            local.remove(indexI);

            //int indexJ = 0;
			int indexJ = solucao.length -1;
            //insere tarefaI em todas as posições e calcula makespan
            while (indexJ > 0 && lessMakespan) {
                local.add(indexJ, tarefaI);

                int[] solucaoTemp = new int[solucao.length];
                int t = 0;
                for (int sol : local) {
                    solucaoTemp[t] = sol;
                    t++;
                }
                int delta;
                delta = (tarefaI>indexJ)?tarefaI:indexJ;
                
                double[][] spanTemp = calculaMakespanDelta(solucaoTemp, graph, delta);
                //se makespan obtido na troca de indices for menor que o atual
                if (spanTemp[spanTemp.length-1][solucao.length-1] < solAtualMakespan) {
                    //atual vira o temporario
                    makespan = spanTemp;
                    //flag do loop
                    lessMakespan = false;
                } else {
                    local.remove(indexJ);
                }
                indexJ--;
            }
            //se nao houve melhora, reinsere tarefa na posicao antiga
            if (lessMakespan) {
                local.add(indexI, tarefaI);
            }
            indexI--;
        }

        int k = 0;
        //reconstroi solucao com melhoria
        for (int job : local) {
            solucaoLocal[k] = job;
            k++;
        }
        //atribui nova solucao a formiga
        solucao = solucaoLocal;
    }

    //imprime os indices que representam a ordem de tarefas da solucao
    public String getSolucaoStr() {
        String sol = new String();
        for (int i = 0; i < solucao.length; i++) {
            sol = sol + " " + solucao[i];
        }
        return sol;
    }
}
