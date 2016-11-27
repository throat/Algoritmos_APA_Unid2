
import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ArtificialBeeColony {

    /*ABC PARAMETERS*/
    public int MAX_LENGTH;
    /*The number of parameters of the problem to be optimized*/
    public int NP;
    /*The number of total bees/colony size. employed + onlookers*/
    public int FOOD_NUMBER;
    /*The number of food sources equals the half of the colony size*/
    public int LIMIT;
    /*A food source which could not be improved through "limit" trials is abandoned by its employed bee*/
    public int MAX_CYCLES_NUMBER;
    /*The number of cycles for foraging {a stopping criteria}*/

    public Random rand;
    public ArrayList<FoodSource> foodSources;
    public ArrayList<FoodSource> solutions;
    public FoodSource bestHoney;
    public int iteraitonsCounter;
    public FoodSource bestOfAll = null;
    private double[][] problemInstance;

    public ArtificialBeeColony(int n, double[][] problemInstance) {

        MAX_LENGTH = problemInstance[0].length;
        NP = 40;
        FOOD_NUMBER = NP / 2;
        LIMIT = 30;
        MAX_CYCLES_NUMBER = 1000;
        bestHoney = null;
        iteraitonsCounter = 0;
        this.problemInstance = problemInstance;
    }

    public boolean algorithm() {
        foodSources = new ArrayList<FoodSource>();
        solutions = new ArrayList<FoodSource>();
        rand = new Random();
        boolean done = false;
        iteraitonsCounter = 0;

        initialize();
        memorizeBestFoodSource();

        while (!done) {
            if (iteraitonsCounter < MAX_CYCLES_NUMBER) {

                employedBeesPhase();
                getFitness();
                calculateProbabilities();
                onlookerBeesPhase();
                memorizeBestFoodSource();
                scoutBeePhase();

                iteraitonsCounter++;
            } else {
                done = true;
            }

        }

        for (FoodSource h : foodSources) {

            solutions.add(h);
            //printSolution(h);

        }

        return done;
    }

    /* Sends the employed bees to optimize the solution
	 *
     */
    public void employedBeesPhase() {
        int neighborBeeIndex = 0;
        FoodSource currentBee = null;
        FoodSource neighborBee = null;

        for (int i = 0; i < FOOD_NUMBER; i++) {
            neighborBeeIndex = getExclusiveRandomNumber(FOOD_NUMBER - 1, i);
            currentBee = foodSources.get(i);
            neighborBee = foodSources.get(neighborBeeIndex);
            sendToWork(currentBee, neighborBee);
        }
    }
 
   public void onlookerBeesPhase() {
        int i = 0;
        int t = 0;
        int neighborBeeIndex = 0;
        FoodSource currentBee = null;
        FoodSource neighborBee = null;

        while (t < FOOD_NUMBER) {
            currentBee = foodSources.get(i);
            if (rand.nextDouble() < currentBee.getSelectionProbability()) {
                t++;
                neighborBeeIndex = getExclusiveRandomNumber(FOOD_NUMBER - 1, i);
                neighborBee = foodSources.get(neighborBeeIndex);
                sendToWork(currentBee, neighborBee);
            }
            i++;
            if (i == FOOD_NUMBER) {
                i = 0;
            }
        }
    }

    public void sendToWork(FoodSource currentBee, FoodSource neighborBee) {
        int newValue = 0;
        int tempValue = 0;
        int tempIndex = 0;
        int prevMakespam = 0;
        int currMakespam = 0;
        int parameterToChange = 0;

        prevMakespam = currentBee.getMakespam();

        //The parameter to be changed is determined randomly
        parameterToChange = getRandomNumber(0, MAX_LENGTH - 1);

        tempValue = currentBee.getNectar(parameterToChange);
        newValue = (int) (tempValue + (tempValue - neighborBee.getNectar(parameterToChange)) * (rand.nextDouble() - 0.5) * 2);

        //trap the value within upper bound and lower bound limits
        if (newValue < 0) {
            newValue = 0;
        }
        if (newValue > MAX_LENGTH - 1) {
            newValue = MAX_LENGTH - 1;
        }

        //get the index of the new value
        tempIndex = currentBee.getIndex(newValue);

        //swap
        currentBee.setNectar(parameterToChange, newValue);
        currentBee.setNectar(tempIndex, tempValue);
        currentBee.computeMakespam(problemInstance);
        currMakespam = currentBee.getMakespam();

        //greedy selection
        if (prevMakespam < currMakespam) {						//No improvement
            currentBee.setNectar(parameterToChange, tempValue);
            currentBee.setNectar(tempIndex, newValue);
            currentBee.computeMakespam(problemInstance);
            currentBee.setTrials(currentBee.getTrials() + 1);
        } else {												//improved solution
            currentBee.setTrials(0);
        }

    }

    public void scoutBeePhase() {
        FoodSource currentBee = null;
        int shuffles = 0;

        for (int i = 0; i < FOOD_NUMBER; i++) {
            currentBee = foodSources.get(i);
            if (currentBee.getTrials() >= LIMIT) {
                //System.out.println("Solution with makepsam " + currentBee.getMakespam() + " was discarded");
                for (int j = 0; j < shuffles; j++) {
                    randomlyArrange(i);
                }
                currentBee.computeMakespam(problemInstance);
                currentBee.setTrials(0);

            }
        }
    }

    public void getFitness() {
        FoodSource thisFood = null;
        double bestMakespam = 0.0;
        double worstMakespam = 0.0;

        worstMakespam = Collections.max(foodSources).getMakespam();

        bestMakespam = worstMakespam - Collections.min(foodSources).getMakespam();

        for (int i = 0; i < FOOD_NUMBER; i++) {
            thisFood = foodSources.get(i);
            thisFood.setFitness((worstMakespam / thisFood.getMakespam()));
        }
    }

    public void calculateProbabilities() {
        FoodSource thisFood = null;
        double maxfit = foodSources.get(0).getFitness();

        for (int i = 1; i < FOOD_NUMBER; i++) {
            thisFood = foodSources.get(i);
            if (thisFood.getFitness() > maxfit) {
                maxfit = thisFood.getFitness();
            }
        }

        for (int j = 0; j < FOOD_NUMBER; j++) {
            thisFood = foodSources.get(j);
            thisFood.setSelectionProbability((0.9 * (thisFood.getFitness() / maxfit)) + 0.1);
        }
    }

    public void initialize() {
        int newFoodIndex = 0;
        int shuffles = 0;

        for (int i = 0; i < FOOD_NUMBER; i++) {
            FoodSource newHoney = new FoodSource(MAX_LENGTH);

            foodSources.add(newHoney);
            newFoodIndex = foodSources.indexOf(newHoney);

            int[] arr = generatesRandomSolutions(MAX_LENGTH);
            for (int j = 0; j < MAX_LENGTH; j++) {
                foodSources.get(i).setNectar(j, arr[j]);
            }

            foodSources.get(newFoodIndex).computeMakespam(problemInstance);
        } // i
    }

    public int getRandomNumber(int low, int high) {
        return (int) Math.round((high - low) * rand.nextDouble() + low);
    }

    public int getExclusiveRandomNumber(int high, int except) {
        boolean done = false;
        int getRand = 0;

        while (!done) {
            getRand = rand.nextInt(high);
            if (getRand != except) {
                done = true;
            }
        }

        return getRand;
    }

    /* Changes the job in a random position
	 *
	 * @param: index of the solution
     */
    public void randomlyArrange(int index) {
        int positionA = getRandomNumber(0, MAX_LENGTH - 1);
        int positionB = getExclusiveRandomNumber(MAX_LENGTH - 1, positionA);
        FoodSource thisHoney = foodSources.get(index);
        int temp = thisHoney.getNectar(positionA);
        thisHoney.setNectar(positionA, thisHoney.getNectar(positionB));
        thisHoney.setNectar(positionB, temp);
    }

    public void memorizeBestFoodSource() {
        if (bestHoney != null && bestHoney.getMakespam() < Collections.min(foodSources).getMakespam()) {
        }
        bestHoney = Collections.min(foodSources);
        if (bestOfAll == null) {
            bestOfAll = bestHoney;
        } else if (bestOfAll.getMakespam() > bestHoney.getMakespam()) {
            try {
                bestOfAll = bestHoney.clone();
                //System.out.println(bestOfAll.getMakespam());
            } catch (CloneNotSupportedException ex) {
                Logger.getLogger(ArtificialBeeColony.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public void printSolution(FoodSource solution) {

        System.out.println("Solution makespam: " + solution.getMakespam());
    }

    public ArrayList<FoodSource> getSolutions() {
        return solutions;
    }

    public int getEpoch() {
        return iteraitonsCounter;
    }

    public void setMaxEpoch(int newMaxEpoch) {
        this.MAX_CYCLES_NUMBER = newMaxEpoch;
    }

    public int getPopSize() {
        return foodSources.size();
    }

    public int getStartSize() {
        return NP;
    }

    public double getFoodNum() {
        return FOOD_NUMBER;
    }

    public int getLimit() {
        return LIMIT;
    }

    public void setLimit(int newLimit) {
        this.LIMIT = newLimit;
    }

    public int getMaxIterations() {
        return MAX_CYCLES_NUMBER;
    }

    // generates a solution
    public int[] generatesRandomSolutions(int jobsNumber) {
        ArrayList<Integer> arr = new ArrayList<Integer>();
        for (int i = 0; i < jobsNumber; i++) {
            arr.add(i);
        }
        Collections.shuffle(arr);
        int[] jobs = new int[jobsNumber];
        for (int i = 0; i < jobsNumber; i++) {
            jobs[i] = arr.get(i);
        }
        return jobs;
    }
}
