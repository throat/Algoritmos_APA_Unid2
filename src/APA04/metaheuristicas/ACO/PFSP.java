
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.StringTokenizer;

/*
 * @author Robertson Lima - 11403755
 * 
 * Algoritmo de otimização por colonia de formiga para obter solução de menor
 * makespan para o problema de agendamento de tarefas (Flowshop).
 *
 * O algoritmo cria uma colonia com N formigas e itera durante o tempo especificado
 * de modo que as formigas irão criar soluções aleatoriamente, em seguida irão tentar
 * melhorá-las e por fim, a formiga de melhor solução irá deixar um rasto de feromônio
 * na trilha. A matriz de trilhas tem todos os valores reduzidos a cada iteração do loop
 * de modo que apenas as trilhas criadas com boas rotas (neste caso, melhor tempo de agendamento)
 * terão os valores incrementados.
 *
 * O algoritmo conta com uma função para o cálculo do makespan que reaproveita
 * caso a busca local tenha afetado apenas tarefas que não sejam as primeiras.
 * Ou seja, caso a troca aconteça no meio do vetor, os valores dos indices menores
 * não precisarão ser recalculados.


 */
public class PFSP {
    //parametros de configuracao
    public int n_formigas;
    public double evaporacao;
    public double maxFero;
    public double minFero;
    public double tempoTeste;
    public double lambda; //valor para ser utilizado no calculo do feromonio deixado
    
    //parametros do problema
    private int tarefas;
    private int maquinas;
    private double limiteSuperior;
    private double limiteInferior;
    private double[][] tempoProcessamento;

    //parametros da colonia
    private double[][] trilha = null;
    private Formiga[] colonia = null;
    
    //parametros da solução
    public int[] melhorRota;
    String melhorAgendamento = "";
    public double melhorMakespan = -1.0;

    public PFSP(String problema, String config) throws IOException {
        //carrega configuracoes
        carregarConfiguracoes(config);
        //carrega problema
        carregarProblema(problema);
        //inicia colonia de formigas
        colonia = new Formiga[n_formigas];
        for (int i = 0; i < n_formigas; i++) {
            colonia[i] = new Formiga(tarefas);
        }
        trilha = new double[tarefas][tarefas];

    }
    
    public void carregarFlowshop(String dir){
        File arquivo = new File(dir);
        try {
            FileReader fr = new FileReader(arquivo); //faz a leitura do arquivo
            String linha;
            BufferedReader br = new BufferedReader(fr);
            StringTokenizer st;
            while ((linha = br.readLine()) != null) {
                st = new StringTokenizer(linha);
                if (linha.startsWith("number")) {
                    linha = br.readLine();
                    st = new StringTokenizer(linha);

                    this.tarefas = Integer.parseInt(st.nextElement().toString());
                    this.maquinas = Integer.parseInt(st.nextElement().toString());
                    Double.parseDouble(st.nextElement().toString());
                    this.limiteSuperior = Double.parseDouble(st.nextElement().toString());
                    this.limiteInferior = Double.parseDouble(st.nextElement().toString());
                    System.out.println("Indices inseridos.");

                } else if (linha.startsWith("processing")) {
                    double[][] novo = new double[maquinas][tarefas];
                    linha = br.readLine();
                    for (int i = 0; i < maquinas; i++) {
                        st = new StringTokenizer(linha);
                        for (int j = 0; j < tarefas; j++) {
                            novo[i][j] = Double.parseDouble(st.nextElement().toString());
                        }
                        linha = br.readLine();

                    }
                    this.tempoProcessamento = novo;
                }
            }
            br.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
     public void carregarOpenshop(String dir){
        File arquivo = new File(dir);
        try {
            FileReader fr = new FileReader(arquivo); //faz a leitura do arquivo
            String linha;
            BufferedReader br = new BufferedReader(fr);
            StringTokenizer st;
            while ((linha = br.readLine()) != null) {
                st = new StringTokenizer(linha);
                if (linha.startsWith("Nb")) {
                    linha = br.readLine();
                    st = new StringTokenizer(linha);

                    this.tarefas = Integer.parseInt(st.nextElement().toString());
                    this.maquinas = Integer.parseInt(st.nextElement().toString());
                    Double.parseDouble(st.nextElement().toString());
                    Double.parseDouble(st.nextElement().toString());
                    this.limiteSuperior = Double.parseDouble(st.nextElement().toString());
                    this.limiteInferior = Double.parseDouble(st.nextElement().toString());
                    System.out.println("Indices inseridos.");

                } else if (linha.startsWith("Times")) {
                    double[][] novo = new double[maquinas][tarefas];
                    linha = br.readLine();
                    for (int i = 0; i < maquinas; i++) {
                        st = new StringTokenizer(linha);
                        for (int j = 0; j < tarefas; j++) {
                            novo[i][j] = Double.parseDouble(st.nextElement().toString());
                        }
                        linha = br.readLine();

                    }
                    this.tempoProcessamento = novo;
                }
            }
            br.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public void carregarProblema(String dir) throws IOException {
        File arquivo = new File(dir);
        try {
            FileReader fr = new FileReader(arquivo); //faz a leitura do arquivo
            String linha;
            BufferedReader br = new BufferedReader(fr);
            StringTokenizer st;
            linha = br.readLine();
            if(linha != null){
                st = new StringTokenizer(linha);
                if (linha.startsWith("number")) {
                   
                    carregarFlowshop(dir);

                } else {
                  
                    carregarOpenshop(dir);
                    }
                linha = null;   
                }
            
            br.close();;;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void carregarConfiguracoes(String dir) throws IOException {
        File arquivo = new File(dir);
        try {
            FileReader fr = new FileReader(arquivo); //faz a leitura do arquivo
            String linha;
            BufferedReader br = new BufferedReader(fr);
            StringTokenizer st;
            while ((linha = br.readLine()) != null) {
                st = new StringTokenizer(linha);
                switch (st.nextElement().toString()) {
                    case "n_formigas":
                        this.n_formigas = Integer.parseInt(st.nextElement().toString());
                        break;
                    case "evaporacao":
                        this.evaporacao = Double.parseDouble(st.nextElement().toString());
                        break;
                    case "maxFero":
                        this.maxFero = Double.parseDouble(st.nextElement().toString());
                        break;
                    case "minFero":
                        this.minFero = Double.parseDouble(st.nextElement().toString());
                        break;
                    case "tempoTeste":
                        this.tempoTeste = Double.parseDouble(st.nextElement().toString());
                        break;
                    case "lambda":
                        this.lambda = Double.parseDouble(st.nextElement().toString());
                        break;
                }
            }
            br.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

  

    public int[] resolver() {
        System.out.println("Espalhando feromônio por todo o grafo:");
        double feroInicial = maxFero;
        for (int i = 0; i < tarefas; i++) {
            for (int j = 0; j < tarefas; j++) {
                trilha[i][j] = feroInicial;
            }
        }
        System.out.println("Iniciando iterações!");
        double tempo = 60000*tempoTeste;
        System.out.println("O tempo de execução do laço será: "+(tempoTeste/60000)+" minuto(s).");
        double tempoAtual = 0;
        double tempoInicial, tempoFinal;
        while (tempo > tempoAtual) { 
            tempoInicial = System.currentTimeMillis();
            limpaSolucoes(); //limpa
            construirSolucoes();
            atualizaTrilhas();
            atualizarSolucao();
            tempoFinal=System.currentTimeMillis();
            tempoAtual += (tempoFinal - tempoInicial);
            System.out.println("Melhor makespan obtido: " +melhorMakespan);
        }
        System.out.println("A execução terminou!");
         System.out.println("Tempo calculando soluções: "+ tempoAtual/60000+" min.");
        System.out.println("Melhor tempo total para realizar tarefas: " + melhorMakespan);
        System.out.println("Limite superior(upper bound): "+ limiteSuperior);
        System.out.println("Limite inferior(lower bound): "+ limiteInferior);
        System.out.println("Melhor ordem de agendamento: " + melhorAgendamento);
        return melhorRota.clone();
    }

    public void construirSolucoes() {
        for (Formiga formiga : colonia) {
            while (formiga.getIndiceAtual() < tarefas) {
                int nextNode = formiga.selecionarProximo(trilha, tempoProcessamento);
                formiga.visitaNo(nextNode);
            }
            formiga.calcMakespan(tempoProcessamento);
            formiga.melhorarSolucao(tempoProcessamento);       
        }
    }


    private void atualizarSolucao() {
        Formiga melhor = colonia[0];
        double tempMake;
        for (Formiga temp : colonia) {
            tempMake = temp.getMakespan( );
            if (tempMake < melhor.getMakespan()) {
                melhor = temp;
            }
        }
            if ((melhorRota == null) || (melhorMakespan > melhor.getMakespan())) {
                melhorRota = melhor.getSolucao().clone();
                melhorMakespan = melhor.getMakespan();
                melhorAgendamento = melhor.getSolucaoStr();
            }
        
    }

    private void atualizaTrilhas() {
        //primeiramente atualiza as trilhas reduzindo o valor de evaporação do feromonio
        for (int i = 0; i < tarefas; i++) {
            for (int j = 0; j < tarefas; j++) {
                double novo = trilha[i][j] - evaporacao;
                if (novo >= minFero) {
                    trilha[i][j] = novo;
                } else {
                    trilha[i][j] = minFero;
                }
            }
        }
        Formiga melhor = colonia[0];
        double tempMake;
        for (Formiga temp : colonia) {
            tempMake = temp.getMakespan();
            if (tempMake < melhor.getMakespan()) {
                melhor = temp;
            }
        }
        // contribuição é dada pela melhor formiga, que tem o menor valor de makespan
        double contribuicao = lambda / melhor.getMakespan();
        for (int i = 0; i < tarefas; i++) {           
            double novo = trilha[melhor.getSolucao()[i]][i] + contribuicao;
            if (novo <= maxFero) {
                trilha[melhor.getSolucao()[i]][i] = novo;
            } else {
                trilha[melhor.getSolucao()[i]][i] = maxFero;
            }
        }
    }


    private void limpaSolucoes() {
        for (Formiga formiga : colonia) {
            formiga.setIndiceAtual(0);
            formiga.limpa();
        }
    }
    

    

    public static void main(String[] args) throws IOException {
       // String entrada = "testemanual.txt";
        String diretorio = System.getProperty("user.dir");
        String config = diretorio + "\\entrada\\configuracoes.txt";
        String problema = diretorio + "\\entrada\\" + args[0];

        System.out.println("Colonia de Formigas para Redução de tempo em agendamento de tarefas:");
        System.out.println("=============================");

        PFSP p = new PFSP(problema, config);

        long inicio = System.nanoTime();
        p.resolver();
			long fim = System.nanoTime();
			System.out.println("Solucao final encontrada em: : " + new Date());
			System.out.println("Duração total (em min): "
					+ ((double) (fim - inicio) / 60000000000.0));

    }
}
