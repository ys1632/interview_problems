/*
 * This is the file with your answer, do not rename or move it.
 * Write your code in it, and save it before submitting your answer.
 */

public class standardizePhoneNum
{

    /**
    * Normalize a given phone number. 
    *
    * This method declaration must be kept unmodified.
    *
    * @param String phoneNumber: A string containing a phone number.
    * @return String with the normalized phone number if the input phone is 
    *         valid, or '' otherwise.
    */
    public static String standardizePhoneNumber( String phoneNumber)
    {
        // Write your implementation here
        String res = "";
        int count = 0;
        for (int i = 0; i < phoneNumber.length(); i++) {
            char c = phoneNumber.charAt(i);
            if (c >= 48 && c <= 57) {
                count++;
                res += String.valueOf(c);
                if (res.length() == 3 || res.length() == 7)
                res += "-";
            }
            if (count > 10) return "";
        }

        if (count != 10) return "";
        return res;
    }

    // This tests your code with the examples given in the question, 
    // and is provided only for your convenience.
    public static void main(String[] args)
    {
        String[] phoneNumbers = {"(650) 555-1234", "65.0555.1234",
                                 "65/05/5512/34", "(650) 555-1234 x111",
                                 "(650) 555-123"};
        for (String phoneNumber : phoneNumbers)
        {
            System.out.println(standardizePhoneNumber(phoneNumber));
        }
    }
}
