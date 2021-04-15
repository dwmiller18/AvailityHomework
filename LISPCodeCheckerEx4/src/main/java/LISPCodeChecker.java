/**
 *
 */
public class LISPCodeChecker {

    /**
     * Constructor for LISPCodeChecker
     */
    public LISPCodeChecker() {
        /* Do Nothing */
    }

    /**
     * isValid(String)
     * @param lisp - A string to be tested
     * @return - boolean value of valid or not
     */
    public boolean isValid(String lisp) {
        boolean result = false;
        String cleanString = "";
        int size = 0;
        int j = 0;

        /* Change to StringBuilder */
        StringBuilder sb = new StringBuilder(lisp);
        size = sb.length();

        /* Remove all non-parenthesis characters */
        for(int i = 0; i < size; i++) {
            if(sb.charAt(j) != '(' && sb.charAt(j) != ')') {
                sb.deleteCharAt(j);
            } else {
                j++;
            }
        }

        /* If uneven number of characters, auto fail */
        if(sb.length() % 2 != 0) {
            return result;
        } else {
            /* Convert back to String */
            cleanString = sb.toString();

            /* Start removing sets of '()' */
            while(cleanString.contains("()")) {
                cleanString = cleanString.replaceAll("\\(\\)", "");
            }

            /* Verify nothing left in string */
            if(cleanString.length() == 0) {
                result = true;
            }
        }

        return result;
    }
}
