// Question 8
// a)
// Given 2D matrix of 1 and 0s. Using stack, find maximum area of square made by 0s.


public class QN8a {
    public static int maxSquareArea(int[][] matrix) {
        int m = matrix.length;
        if (m == 0) return 0;
        int n = matrix[0].length;
        if (n == 0) return 0;
        
        int[][] dp = new int[m][n];
        int maxArea = 0;
        
        // initialize first row and column of dp matrix
        for (int i = 0; i < m; i++) {
            dp[i][0] = matrix[i][0];
            maxArea = Math.max(maxArea, dp[i][0]);
        }
        for (int j = 0; j < n; j++) {
            dp[0][j] = matrix[0][j];
            maxArea = Math.max(maxArea, dp[0][j]);
        }
        
        // fill in the rest of the dp matrix using dynamic programming
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 1) {
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = Math.min(dp[i-1][j-1], Math.min(dp[i-1][j], dp[i][j-1])) + 1;
                    maxArea = Math.max(maxArea, dp[i][j] * dp[i][j]);
                }
            }
        }
        
        return maxArea;
    }
    
    public static void main(String[] args) {
        int[][] matrix = {
            {1, 0, 1, 0, 0},
            {0, 1, 1, 1, 1},
            {0, 0, 0, 0, 1},
            {0, 0, 0, 1, 1},
            {0, 1, 0, 1, 1}
        };
        int maxArea = maxSquareArea(matrix);
        System.out.println("Maximum square area: " + maxArea);
    }
}
