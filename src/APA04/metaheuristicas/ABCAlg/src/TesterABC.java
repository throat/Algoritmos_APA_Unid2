import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

/* TesterABC.java
 *
 */

public class TesterABC {
    ArtificialBeeColony abc;
    int MAX_TIME;
    int MAX_LENGTH;
    long[] runtimes;
    private int jobs;
    private int machines;
    private double inicialSeed;
    private double upperBound;
    private double lowerBound;
    private double[][] procTime;
    
    // test
    private double [][] pt1;
    private double [][] pt2;
    private double [][] pt3;
    private double [][] pt4;
    private double [][] pt5;

    public TesterABC() throws IOException {
        pt1 = loadProblem("teste1.txt");
        pt2 = loadProblem("teste2.txt");
        pt3 = loadProblem("teste3.txt");
        pt4 = loadProblem("teste4.txt");
        pt5 = loadProblem("teste5.txt");
        // SOMENTE PARA MOSTRAR IMPLEMENTAÇÃO, TEMPO = 10 S P/ CADA ARQUIVO
        MAX_TIME = 10000;           // time to run each instance of problem
        runtimes = new long[MAX_TIME];
    }
    
    public double[][] loadProblem(String dir) throws IOException {
        File arquivo = new File(dir);
        double[][] newInstance = null;
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

                    this.jobs = Integer.parseInt(st.nextElement().toString());
                    this.machines = Integer.parseInt(st.nextElement().toString());
                    this.inicialSeed = Double.parseDouble(st.nextElement().toString());
                    this.upperBound = Double.parseDouble(st.nextElement().toString());
                    this.lowerBound = Double.parseDouble(st.nextElement().toString());

                } else if (linha.startsWith("processing")) {
                    newInstance = new double[machines][jobs];
                    linha = br.readLine();
                    // st = new StringTokenizer(linha);
                    for (int i = 0; i < machines; i++) {
                        st = new StringTokenizer(linha);
                        for (int j = 0; j < jobs; j++) {
                            newInstance[i][j] = Double.parseDouble(st.nextElement().toString());
                        }
                        linha = br.readLine();

                    }
                  
                }
            }
            br.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return newInstance;
    }

    public void test(int maxLength, int trialLimit, int maxEpoch) {
        MAX_LENGTH = maxLength;
        for(int k = 0; k < 5; k++){
            if(k == 0)
                abc = new ArtificialBeeColony(MAX_LENGTH, pt1);                                      //instantiate and define abc here
            if(k == 1)
                    abc = new ArtificialBeeColony(MAX_LENGTH, pt2);
            if(k == 2)
                    abc = new ArtificialBeeColony(MAX_LENGTH, pt3);
            if(k == 3)
                    abc = new ArtificialBeeColony(MAX_LENGTH, pt4);
            if(k == 4)
                    abc = new ArtificialBeeColony(MAX_LENGTH, pt5);
        abc.setLimit(trialLimit);
        abc.setMaxEpoch(maxEpoch);
        long testStart = System.currentTimeMillis();
        String filepath = "ABC-N"+MAX_LENGTH+"-"+trialLimit+"-"+maxEpoch+".txt";
        long startTime = 0;
        long endTime = 0;
        long totalTime = 0;
        int fail = 0;
        int success = 0;
        
        System.out.println("Starting algorithm, best result found will be displayed in 10 seconds");
        while(totalTime < MAX_TIME ) {                                            
            startTime = System.currentTimeMillis();
            abc.algorithm();
            endTime = System.currentTimeMillis();
            totalTime += endTime - startTime;
        }
        System.out.println("The best makespam was: " + abc.bestHoney.getMakespam());
        System.out.println("With the sequence: ");
        System.out.print("[ ");
        for(int i = 0; i < abc.MAX_LENGTH; i++)
            System.out.print((abc.bestHoney.getNectar(i)+1) + " ");
        System.out.print("]");
        }
    }

    public static void main(String args[]) throws IOException {
        TesterABC tester = new TesterABC();

        tester.test(4, 50, 1000);
        
    }
}
