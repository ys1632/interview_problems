/*
 * This is the file with your answer, do not rename or move it.
 * Write your code in it, and save it before submitting your answer.
 */

/*
* Write the code for your answer in this class.
*/
public class ValidSocket
{
    /**
    * Determine if the provided string is a valid socket address.
    *
    * This method declaration must be kept unmodified.
    *
    * @param address A string with a socket address, eg, 
    *                '127.12.23.43:5000', to be checked for validity.
    * @return boolean indicating wether the provided string is a valid
    *                  socket address
    */
    public static boolean isValidSocket(String address)
    {
        // Write your implementation here
        int len = address.length();
        //check port number
        int pos = address.indexOf(':');
        if (pos < 0) return false;
        String portS = address.substring(pos+1);
        int portI = Integer.parseInt(portS);
        if (portI < 1 || portI > 65535) return false;
        //check IP
        int cnt = 0;
        int pre = 0;
        int posDot = address.indexOf('.') ;
        while (posDot >= 0) {
            String ipS = address.substring(pre, posDot);
            int ipI = Integer.parseInt(ipS);
            if (ipI < 0 || ipI > 255) return false;
            pre = posDot + 1;
            if (cnt != 2) {
                posDot = address.indexOf('.', pre);
            }
            else {
                posDot = pos;
            }
            cnt++;
        }

        if (cnt != 4) return false;
        return true;

    }

    /*
    * This tests your code with the examples given in the question,
    * and is provided only for your conveinience.
    */
    public static void main(String[] args)
    {
        String[] addresses = {"127.12.23.43:5000", "127.A:-12"};
        for (String address : addresses)
        {
            System.out.println(isValidSocket(address));
        }
    }
}
