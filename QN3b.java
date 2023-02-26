// qn4>B
// you are provided certain string and pattern, 
// return true if pattern entirely matches the string otherwise return false.

public class QN3b {
    public static boolean match(String str, String pattern) {
        int strIndex = 0;
        int patternIndex = 0;
        int strLen = str.length();
        int patternLen = pattern.length();
        char wildcard = '@';
        char placeholder = '#';

        while (strIndex < strLen && patternIndex < patternLen) {
            char strChar = str.charAt(strIndex);
            char patternChar = pattern.charAt(patternIndex);

            if (patternChar == wildcard) {
                patternIndex++;
                while (patternIndex < patternLen && pattern.charAt(patternIndex) != wildcard) {
                    patternIndex++;
                }
                if (patternIndex == patternLen) {
                    return true;
                }
                while (strIndex < strLen && str.charAt(strIndex) != pattern.charAt(patternIndex)) {
                    strIndex++;
                }
                if (strIndex == strLen) {
                    return false;
                }
            } else if (patternChar == placeholder) {
                strIndex++;
                patternIndex++;
            } else {
                if (strChar != patternChar) {
                    return false;
                }
                strIndex++;
                patternIndex++;
            }
        }

        if (strIndex == strLen && patternIndex == patternLen) {
            return true;
        }

        return false;
    }

    public static void main(String[] args) {
        String a1 = "tt";
        String p1 = "@";
        System.out.println("Input: " + a1 + ", " + p1);
        System.out.println("Output: " + match(a1, p1));

        String a2 = "ta";
        String p2 = "t";
        System.out.println("Input: " + a2 + ", " + p2);
        System.out.println("Output: " + match(a2, p2));

        String a3 = "ta";
        String p3 = "t#";
        System.out.println("Input: " + a3 + ", " + p3);
        System.out.println("Output: " + match(a3, p3));
    }
}
