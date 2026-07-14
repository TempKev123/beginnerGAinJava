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

    public int[] chromesone1 = generateRandomBinaryArray();
    public int[] chromesone2 = generateRandomBinaryArray();
    public int[] chromesone3 = generateRandomBinaryArray();
    public int[] chromesone4 = generateRandomBinaryArray();
     public static void main(String[] args)
    {System.out.print("KEVIN");

    }
}
