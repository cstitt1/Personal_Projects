package hangman;
import java.util.Scanner;
/**
 *
 * @author cstit
 */
public class Hangman {
    /**
     * @param args the command line arguments
     */
    @SuppressWarnings("resource")
	public static void main(String[] args) {
        System.out.println("What word should be used?");
        Scanner in1 = new Scanner(System.in);
        String word=in1.next();
        String gl;
        Scanner in2;
        int times;
        int wlc=word.length();
        int negc;
        if (word.length()<6) {
            negc=word.length();
        } else {
            negc=6;
        }
        while (negc>0 && wlc>0) {
            times=0;
            System.out.println("Guess a letter");
            in2 = new Scanner(System.in);
            gl = in2.next();
            if (word.contains(gl)) {
                for (int a=0; a<word.length(); a++) {
                    if (gl.equals(word.substring(a,a+1))) {
                        times+=1;
                    }
                }
                for (int b=0; b<times; b++) {
                    wlc-=1;
                }
            } else {
                negc-=1;
            }
        }
        if (negc==0) {
            System.out.println("You lose!");
            System.out.println("The word was "+word);
        } else {
            System.out.println("You win!");
            System.out.println("The word was "+word);
        }
    }
}