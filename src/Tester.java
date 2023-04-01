import java.io.*;

public class Tester {
    public static void main(String[] args) {
        try{
            FileWriter fw = new FileWriter("src/", false);

            PrintWriter pw = new PrintWriter(fw, false);

            pw.flush();

            pw.close();

            fw.close();

        }catch(Exception exception){

            System.out.println("Exception have been caught");

        }
    }
}
