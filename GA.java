import java.util.Arrays;
import java.util.Random;

public class GA {
    public static int[] generateRandomArray() {
        int[] binaryArray = new int[4];
        Random random = new Random();
        for (int i = 0; i < binaryArray.length; i++) {
            binaryArray[i] = random.nextInt(31); // Generates four under 30 numbers
        }
        return binaryArray;
    }

    public static int fitness(int[] bits) {
        int x = bits[0] + (2 * bits[1]) + (3 * bits[2]) + (4 * bits[3]); // should equal 30
        if (x > 30) {
            return x - 30;
        }
        return 30 - x;
    }

    public static int[] selectParent(int[][] chromosomes, double[] percentscores, Random rand) {
        double spin = rand.nextDouble();
        double runningSum = 0.0;
        for (int i = 0; i < percentscores.length; i++) {
            runningSum += percentscores[i];
            if (spin <= runningSum) {
                return chromosomes[i];
            }
        }
        return chromosomes[chromosomes.length - 1]; // Fallback in case of 0.999999
    }

    public static boolean ON = true;
    public static int epouch = 0;

    public static void main(String[] args) {
        Random rand = new Random();

        int[][] chromosomes = new int[5][4];//base chromosomes
        for (int i = 0; i < chromosomes.length; i++) {
            chromosomes[i] = generateRandomArray();
        }

        while (ON) {
            epouch++;
            double[] scores = new double[chromosomes.length];
            double divisor = 0;
            boolean foundSolution = false;

            for (int i = 0; i < chromosomes.length; i++) {
                double temp = fitness(chromosomes[i]);
                if (temp == 0) {
                    System.out.println("Correct sequence found: " + Arrays.toString(chromosomes[i]));
                    foundSolution = true;
                    ON = false; // Stop the loop
                    break;
                }
                scores[i] = temp;
                divisor += (1.0 / temp);
            }

            if (foundSolution) {
                break;//stop calclations after finding the solution
            }

            double[] percentscores = new double[scores.length];
            for (int i = 0; i < scores.length; i++) {
                percentscores[i] = ((1.0 / scores[i]) / divisor);
            }

            int[][] nextGeneration = new int[chromosomes.length][4];

            for (int i = 0; i < chromosomes.length; i++) {
                int[] parent1 = selectParent(chromosomes, percentscores, rand);
                int[] parent2 = selectParent(chromosomes, percentscores, rand);

                //Singlepoint split down the middle crossover
                int[] child = new int[4];
                child[0] = parent1[0];
                child[1] = parent1[1];
                child[2] = parent2[2];
                child[3] = parent2[3];

                if (rand.nextDouble() < 0.15) {//15% chance of mutation
                    int mutateGene = rand.nextInt(4);
                    int tweak = rand.nextBoolean() ? 1 : -1;
                    child[mutateGene] = Math.max(0, child[mutateGene] + tweak); //Make not negative
                }

                nextGeneration[i] = child;
            }

            chromosomes = nextGeneration;

        }
        System.out.println("Total Epochs: " + epouch);
    }
}