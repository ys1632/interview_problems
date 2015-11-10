/*
 * This is the file with your answer, do not rename or move it.
 * Write your code in it, and save it before submitting your answer.
 */

/**
 * This class is a container for your answer,
 * and its declaration must be kept unmodified.
 */
public class AbbrName {
    /**
     * Returns an abbreviated version of a full name. All names except the 
     * last are abbreviated with one letter.
     *
     * This method declaration must be kept unmodified.
     *
     * @param abbreviateName the full name of the person
     * @return the abbreviated version of the full name
     */
    public static String abbreviateName(String name) {
        // write your code here ...
        String res = "";
        int firstPos = name.indexOf(' ');
        if (firstPos < 0) return name;
        String firstName = name.substring(0, firstPos);
        res += firstName;

        int pre = firstPos + 1;
        int spacePos = name.indexOf(' ', pre);
        while (spacePos > 0) {
            String middle = name.substring(pre, spacePos);
            res += " " + String.valueOf(middle.charAt(0)) + ".";
            pre = spacePos + 1;
            spacePos = name.indexOf(' ', pre);
        }

        res += " " + name.substring(pre);
        return res;
    }

    /**
     * Tests the method abbreviate with the examples given in the question. 
     * This test code is provided only for your convenience.
     */
    public static void main(String[] args) {
        System.out.print(Answer.abbreviateName("John Smith") + "\n");
        System.out.print(Answer.abbreviateName("Anna Maria Simpson") + "\n");
        System.out.print(Answer.abbreviateName("Bob Alan Faria Stewart") + "\n");
    }
}
