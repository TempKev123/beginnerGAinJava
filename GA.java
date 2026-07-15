import java.util.Random;

public class GA {
    public static int[] generateRandomBinaryArray() {
        int[] binaryArray = new int[4];
        Random random = new Random();
        for (int i = 0; i < binaryArray.length; i++) {
            binaryArray[i] = random.nextInt(2); // Generates 0 or 1
        }
        return binaryArray;
    }

    public static int decodeBinary(int[] bits){
        int result = 0;
        for (int bit : bits) {
            result = (result << 1) | bit;
        }
        return result;
    }
    public static int fitness(int[] bits){
        return decodeBinary(bits);//repleace with fitness algorithim
    }

    public static int[] findTwoLargest(int[] array) {
    int largest = Integer.MIN_VALUE;
    int secondLargest = Integer.MIN_VALUE;
    int largestIndex = -1;
    int secondLargestIndex = -1;

    for (int i = 0; i < array.length; i++) {
        if (array[i] > largest) {
            secondLargest = largest;
            secondLargestIndex = largestIndex;

            largest = array[i];
            largestIndex = i;
        } else if (array[i] > secondLargest) {
            secondLargest = array[i];
            secondLargestIndex = i;
        }
    }

    return new int[]{largest, largestIndex, secondLargest, secondLargestIndex};
}

    public int[][] chromesomes = {generateRandomBinaryArray(),generateRandomBinaryArray(),generateRandomBinaryArray(),generateRandomBinaryArray()};
    public int[] scores = {decodeBinary(chromesomes[0]),decodeBinary(chromesomes[1]),decodeBinary(chromesomes[2]),decodeBinary(chromesomes[3])};

    public static void main(String[] args) {

    }
}
