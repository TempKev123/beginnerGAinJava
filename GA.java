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

    public static int findbiggestIndex(int[] array) {
        int biggestindex = 0;

        for (int i = 1; i < array.length; i++) {
            if (array[i] > array[biggestindex]) {
                biggestindex = i;
            }
        }

        return biggestindex;
    }

    public static boolean ON = true;
    public static int epouch = 0;

    public static void main(String[] args) {
        Random rand = new Random();

        /*
         * int[][] chromosomes = {//initial generate
         * generateRandomArray(),
         * generateRandomArray(),
         * generateRandomArray(),
         * generateRandomArray(),
         * generateRandomArray()
         * };
         */
        int[][] chromosomes = { { 1, 2, 3, 21 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 }, { 13, 14, 15, 16 },
                { 17, 18, 19, 20 } };// for easy testing
        while (ON) {
            epouch++;
            int[] scores = new int[chromosomes.length];
            double divisor = 0;
            for (int i = 0; i < chromosomes.length; i++) {
                int temp = fitness(chromosomes[i]);
                if (temp == 0) {
                    System.out.println("correct sequence:" + Arrays.toString(chromosomes[i]));
                    ON = false;
                    break;
                }
                scores[i] = temp;
                divisor += (1 / temp);
            }
            if (!ON) {
                break;
            }
            double[] percentscores = new double[scores.length];
            for (int i = 0; i < scores.length; i++) {
                percentscores[i] = ((1 / scores[i]) / divisor);
            }
            // ==========================================
            // NEW: RANDOM PERCENTAGE SELECTION (Roulette Wheel)
            // ==========================================

            // Step 1: Spin the wheel (Pick a random number between 0.0 and 1.0)
            double spin = rand.nextDouble();
            double runningSum = 0.0;
            int selectedIndex = 0;

            // Step 2: Cumulative scan to see where the spin lands
            for (int i = 0; i < percentscores.length; i++) {
                runningSum += percentscores[i];
                if (spin <= runningSum) {
                    selectedIndex = i;
                    break;
                }
            }

            System.out.println("Spin landed on: " + spin);
            System.out.println("Selected Chromosome Index: " + selectedIndex);
            System.out.println("Selected Chromosome: " + Arrays.toString(chromosomes[selectedIndex]));


            ON=false;//safty
        }
        System.out.println("epouchs:" + epouch);
    }
}
