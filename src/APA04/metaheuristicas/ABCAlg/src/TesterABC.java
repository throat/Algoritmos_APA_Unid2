
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
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
    private ArrayList<double[][]> procTimes = new ArrayList<>();

    public TesterABC() throws IOException {
        for (int i = 0; i < 10; i++) {
            procTimes.add(loadProblem("teste" + (i + 1) + ".txt"));
        }
        // SOMENTE PARA MOSTRAR IMPLEMENTAÇÃO, TEMPO = 10 S P/ CADA ARQUIVO
        MAX_TIME = 60000;           // time to run each instance of problem
        runtimes = new long[MAX_TIME];
    }
	
	public double getRPD(double melhorMakespan, int i){
		double upper = 0;
		switch(i){
			case 0:	
				upper = 1278;
				break;
			case 1:
				upper = 1582;
				break;
			case 2:
				upper = 2297;
				break;
			case 3:
				upper = 2724;
				break;
			case 4:
				upper = 26189;
				break;
				
			
		}
		return (melhorMakespan - upper) / upper;
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

        //System.out.println("Starting algorithm, best result found will be displayed in 60 seconds");
        int counter = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.println("Teste " + (j+1) + " com arquivo de teste " + (i+1));
                abc = new ArtificialBeeColony(MAX_LENGTH, procTimes.get(i));
                abc.setLimit(trialLimit);
                abc.setMaxEpoch(maxEpoch);
                long testStart = System.currentTimeMillis();
                long startTime = 0;
                long endTime = 0;
                long totalTime = 0;
                while (totalTime < MAX_TIME) {
                    startTime = System.currentTimeMillis();
                    abc.algorithm();
                    endTime = System.currentTimeMillis();
                    totalTime += endTime - startTime;
					System.out.println(getRPD(abc.bestOfAll.getMakespam(), i) + " "+abc.bestOfAll.getMakespam() + " " + totalTime);

                }
            }
        }
       // System.out.println(abc.bestOfAll.getMakespam());
        //System.out.println("With the sequence: ");
//        System.out.print("[ ");
//        for(int i = 0; i < abc.MAX_LENGTH; i++)
//            System.out.print((abc.bestOfAll.getNectar(i)+1) + " ");
//        System.out.print("]");

    }

    public static void main(String args[]) throws IOException {
        TesterABC tester = new TesterABC();

        tester.test(4, 50, 1000);

    }
}
