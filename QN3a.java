// qn3< a
// You are given an even length array; 
// divide it in half and return possible minimum product difference of any two subarrays.

import java.util.Arrays;

public class QN3a {
    public static int minProductDiff(int[] nums) {
        int n = nums.length;
        int mid = n / 2;
        Arrays.sort(nums);
        int minDiff = Integer.MAX_VALUE;
        for (int i = 0; i < mid; i++) {
            int diff = nums[i] * nums[n-1-i] - nums[mid-1-i] * nums[mid+i];
            minDiff = Math.min(minDiff, Math.abs(diff));
        }
        for (int i = mid; i < n; i++) {
            int diff = nums[i] * nums[n-1-i] - nums[i-mid] * nums[n-1-i+mid];
            minDiff = Math.min(minDiff, Math.abs(diff));
        }
        return minDiff;
    }
    

    public static void main(String[] args) {
        int[] nums = {5,2,4,11};
        int minProductDiff = minProductDiff(nums);
        System.out.println("Minimum product difference: " + minProductDiff);
    }
}
