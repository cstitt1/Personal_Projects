package mock.trial.reader;
/**
 *
 * @author cstit
 */
import java.util.*;
import java.io.*;
public class MockTrialReader {
    /**
     * @param args the command line arguments
     */
    @SuppressWarnings("resource")
	public static void main(String[] args) {
        try {
            System.out.println("What is the part?");
            Scanner choice = new Scanner(System.in);
            String area = (choice.nextLine());
            System.out.println("What is the query");
            Scanner sc = new Scanner(System.in);
            String part = sc.nextLine();
            File MTW = new File("C:\\Users\\cstit\\Desktop\\MockTrial"+area+".txt");
            if (MTW.exists()) {
                System.out.println("");
                System.out.println(part+" in "+MTW.getName()+":");
                System.out.println("");
            } else {
                System.out.println("Error--File Does Not Exist");
            }
            Scanner mtwsc = new Scanner(MTW);
            int ln=1;
            while (mtwsc.hasNextLine()) {
                String comp = (mtwsc.nextLine());
                if (comp.contains(part)) {
                    System.out.println(comp);
                    System.out.println("Line Number " + ln);
                    //Include what area the line and query are in using if (ln>=#) {s.o.pln("Area: ")} and else if's for moore areas.
                    System.out.println("");
                }
                ln+=1;
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("Error--File Not Found");
        }
    }
}