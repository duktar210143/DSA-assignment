// b)
// Given an array of even numbers sorted in ascending order and an integer k,
// Find the k^th missing even number from provided array


public class QN8b{
    public static int findKthMissingEvenNumber(int[] a, int k) {
        int missingCount = 0;
        int lastEven = -2; // initialized to -2 to account for the first missing even number (2)
        for (int i = 0; i < a.length; i++) {
            int missingNums = (a[i] - lastEven) / 2 - 1;
            if (missingCount + missingNums >= k) {
                return lastEven + (k - missingCount) * 2;
            }
            missingCount += missingNums;
            lastEven = a[i];
        }
        return lastEven + (k - missingCount) * 2;
    }
    
    
    public static void main(String[] args) {
        int[] arr = {0, 2, 6, 18, 22};
        int k = 6;
        int missingEven = findKthMissingEvenNumber(arr, k);
        System.out.println("The " + k + "-th missing even number is: " + missingEven);
    }
        
}
