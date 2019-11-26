package loaves.and.fishes.help.desk;
/**
 *
 * @author cstit
 */
import java.util.*;
public class LoavesAndFishesHelpDesk {
    /**
     * @param args the command line arguments
     */
    @SuppressWarnings("resource")
	public static void main(String[] args) {
        System.out.println("How can I help?");
        System.out.println(" ");
        Scanner question=new Scanner(System.in);
        String help=(question.nextLine());
        System.out.println(" ");
        if (help.contains("account")) {
            System.out.println("What seems to be the problem with your account?");
            System.out.println(" ");
            Scanner qc1 = new Scanner(System.in);
            String q1 = qc1.nextLine();
            if (q1.contains("password")) {
                System.out.println("Go to the Sign Up Genius Website Login page and click the reset password link.");
                System.out.println("It will send a link to your email to reset the password. I would suggst then to email the password to yourself");
                System.out.println(" ");
            } else{
                System.out.println("Please email Chris at cstitt421@gmail.com or contact Sign Up Genius for more aid.");
            }
        } else if (help.contains("sign up")) {
            System.out.println("What seems to be the problem with your sign up?");
            System.out.println(" ");
            Scanner qc2 = new Scanner(System.in);
            String q2 = qc2.nextLine();
            if (q2.contains("not there") || q2.contains("missing")) {
                System.out.println("First, click the Archived and Deleted button at the bottom of the Sign Ups Iv'e Created section.");
                System.out.println("Then, hit the Restore button, which looks like the reply button in an email.");
            } else if (q2.contains("reminder")){
                System.out.println("To manually send a reminder email, go into the sign up and click Send Email, and then follow the instructions to send it.");
            } else if (q2.contains("change") || q2.contains("edit")) {
                System.out.println("First, go into the sign up and click the Edit Content button and follow the instructions.");
                System.out.println("Next, go back into the sign up and click the Send Email button to tell your people about the change.");
            } else {
                System.out.println("Please email Chris at cstitt421@gmail.com or contact Sign Up Genius for more aid.");
            }
        } else {
            System.out.println("Please email Chris at cstitt421@gmail.com or contact Sign Up Genius for more aid.");
        }
    }
}