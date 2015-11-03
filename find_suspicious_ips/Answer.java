//Find out IPs that make three or more requests to /account/withdraw 
//with code between 400 and 499 withthin one second.

/*
 * This is the file with your answer, do not rename or move it.
 * Write your code in it, and save it before submitting your answer.
 */
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
/**
 * This class is a container for your answer,
 * and its declaration must be kept unmodified.
 */
public class Answer {
    /**
     * Returns an array of IP addresses that are deemed suspicious.
     *
     * This method declaration must be kept unmodified.
     *
     * @param logPath The full path of a log file. * This is the file with your answer, do not rename or move it.
     * @return An array of IP addresses.
     */
    public static String[] findSuspiciousIps(String logPath) {
        ArrayList<String> list = new ArrayList<String>();
        try {
            File file = new File(logPath);
            if (file.isFile() && file.exists()) {
                InputStreamReader read = new InputStreamReader(
                    new FileInputStream(file));
                BufferedReader bufferedReader = new BufferedReader(read);
                String line = null;
                String preLine = null;
                int count = 1;
                
                while ((line = bufferedReader.readLine()) != null) {
                    if (preLine != null) {
                        int preIP = preLine.indexOf('-');
                        int curIP = line.indexOf('-');
                        String pIP = preLine.substring(0, preIP - 1);
                        String cIP = line.substring(0, curIP - 1);

                        if (pIP.equals(cIP) ) {
                            if (preLine.indexOf("/account/withdraw") >= 0 && 
                                line.indexOf("/account/withdraw") >= 0) {
                                int preCode = preLine.indexOf('"', preLine.indexOf("account"));
                                int curCode = line.indexOf('"', line.indexOf("account"));
                                int pCode = Integer.parseInt(preLine.substring(preCode+2, preCode+5));
                                int cCode = Integer.parseInt(line.substring(curCode+2, curCode+5));
                                if (pCode <= 499 && pCode >= 400 && cCode <= 499 && cCode >= 400) {
                                    count++;
                                }
                            }
                        }
                        else {
                            count = 1;
                        }
                        //add Valid IP into String[]
                        if (count == 3) {
                            list.add(pIP);
                            //System.out.println(pIP);
                        }
                    }
                    preLine = line;
                }
                read.close();
            }
            else {
                System.out.println("Not Found");
            }
        } catch (Exception e) {
            System.out.println("Error");
            e.printStackTrace();
        }
        String[] res = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }

    /**
     * Tests the method abbreviate with the examples given in the question. 
     * This test code is provided only for your convenience.
     */
    public static void main(String[] args) {
        System.out.println("example.log:");
        for (String ip : findSuspiciousIps("example.log"))
        {
            System.out.println(ip);
        }
        System.out.println("bank_logs.log:");
        for (String ip : findSuspiciousIps("bank_logs.log"))
        {
            System.out.println(ip);
        }
    }
}