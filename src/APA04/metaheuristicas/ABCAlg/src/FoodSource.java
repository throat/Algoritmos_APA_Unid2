
public class FoodSource implements Comparable<FoodSource> {

    private int MAX_LENGTH;
    private int nectar[]; 		//solution or placement of sequence
    private int trials;                 //number of attempts of improving
    private int makespam;              //makespam of the nectar solution
    private double fitness;             //quality of nectar
    private double selectionProbability;

    public FoodSource(int size) {
        this.MAX_LENGTH = size;
        this.nectar = new int[MAX_LENGTH];
        this.makespam = 0;
        this.trials = 0;
        this.fitness = 0.0;
        this.selectionProbability = 0.0;
        initNectar();
    }

    @Override
    public int compareTo(FoodSource h) {
        return this.makespam - h.getMakespam();
    }

    public void initNectar() {
        for (int i = 0; i < MAX_LENGTH; i++) {
            nectar[i] = i;
        }
    }

    //obter makespan com solucao intermediaria
    public void computeMakespam(double[][] graph) {
        int machinesNum = graph.length;
        double[] machineTimes = new double[machinesNum];
        double time = 0; //tempo inicial

        for (int i = 0; i < nectar.length; i++) {
            for (int j = 0; j < machinesNum; j++) {
                time = graph[j][nectar[i]];
                if (j == 0) { //caso seja a primeira maquina
                    machineTimes[j] += time;
                } else if (machineTimes[j] > machineTimes[j - 1]) { //se tarefa ja acabou na maquina anterior. começa em seguida
                    machineTimes[j] += time;
                } else { //espera acabar e começa
                    machineTimes[j] = machineTimes[j - 1] + time;
                }
            }
        }

        this.makespam = (int) machineTimes[machinesNum - 1];

    }

    public int getMakespam() {
        return makespam;
    }

    public void setMakespam(int mMakespam) {
        this.makespam = mMakespam;
    }

    public double getSelectionProbability() {
        return selectionProbability;
    }

    public void setSelectionProbability(double mSelectionProbability) {
        this.selectionProbability = mSelectionProbability;
    }

    public double getFitness() {
        return fitness;
    }

    public void setFitness(double mFitness) {
        this.fitness = mFitness;
    }

    public int getNectar(int index) {
        return nectar[index];
    }
    
    public int[] getNectar () {
        return nectar;
    }

    public int getIndex(int value) {
        int k = 0;
        for (; k < MAX_LENGTH; k++) {
            if (nectar[k] == value) {
                break;
            }
        }
        return k;
    }

    public void setNectar(int index, int value) {
        this.nectar[index] = value;
    }
    
    public void setNectar(int[] n) {
        this.nectar = n;
    }

    public int getTrials() {
        return trials;
    }

    public void setTrials(int trials) {
        this.trials = trials;
    }

    public int getMaxLength() {
        return MAX_LENGTH;
    }

    @Override
    protected FoodSource clone() throws CloneNotSupportedException {
        FoodSource fs = new FoodSource(MAX_LENGTH);
        fs.setFitness(this.getFitness());
        fs.setMakespam(this.getMakespam());
        fs.setNectar(this.getNectar());
        fs.setSelectionProbability(this.getSelectionProbability());
        fs.setTrials(this.getTrials());
        return fs;
    }
    
    
}
