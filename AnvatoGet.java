//pretend I am using the Chrome on an Android phone, otherwise the access will be denied.

import java.net.*;
import java.io.*;

public class AnvatoGet {
    public static void main(String[] args) throws Exception {
        URL anvato = new URL("https://s3.amazonaws.com/anvato-resumes/candidates/2015_11/Yixiao_5637ab30e5bd2");
        HttpURLConnection conn = (HttpURLConnection) anvato.openConnection();
        String self = "Android Chrome";
        conn.addRequestProperty("User-Agent", self);
        conn.addRequestProperty("Referer", "http://www.anvato.com/candidates/Yixiao");
        
        BufferedReader input = new BufferedReader(
                                new InputStreamReader(
                                conn.getInputStream()));
        String line;
        String path = "output.txt";
        File file = new File(path);
        FileOutputStream fos = new FileOutputStream(file);
        BufferedWriter output = new BufferedWriter(new OutputStreamWriter(fos));

        if (file.exists()) {
            while ((line = input.readLine()) != null) {
                output.write(line);
                output.newLine();
                //System.out.println(line);
            }
            output.close();   
            input.close();
        }
    }
}