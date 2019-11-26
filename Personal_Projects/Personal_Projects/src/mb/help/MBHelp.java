package mb.help;
import java.util.*;
/**
 *
 * @author cstit
 */
public class MBHelp {
    /**
     * @param args the command line arguments
     */
    @SuppressWarnings("resource")
	public static void main(String[] args) {
        System.out.println("What merit badge?");
        Scanner c1 = new Scanner(System.in);
        String q1 = c1.nextLine();
        if (q1.contains("swimming")) {
            System.out.println("What requirement?");
            Scanner c2 = new Scanner(System.in);
            int q2 = c2.nextInt();
            if (q2==1) {
                System.out.println("Remember why the 8 points are there.");
                System.out.println("The 8 Points: Qualified Supervision, Physical Fitness, Safe Area, Lifeguards, Lookout, Buddy System, Ability Groups, Discipline");
                System.out.println("First Aid: Hypothermia, Dehydration, Sunburn, Heat Exhaustion/Stroke, Muscle Cramps, Spine Injury, Sting/Bite, Cut/Scrape");
            } else if (q2==2 || q2==3) {
                System.out.println("Sidestroke = Lay on side, Scissor Kick, Rowing of arms in front of face");
                System.out.println("Breaststroke = Small Tickle-T, Whip/Push Kick");
                System.out.println("Front Crawl/Trudgen = Rotating arms, Flutter Kick, Rythmic Breathing");
                System.out.println("Back Crawl = Upside-down and backwards Front Cawl/Trudgen");
                System.out.println("Elementary Backstroke = Tickle-T-Touch, Whip Kick, Upside-down and backwards");
            } else if (q2==4) {
                System.out.println("3 Rescue Methods: Throw>Reach>Swim");
                System.out.println("Think about Line Resue Technique");
            } else if (q2==5) {
                System.out.println("Back Float, Survival Float = Up-Breath-Down(Jellyfish)-Up, HELP = Pull knees to chest & hold with preserver, Huddle = group huddle to preserve warmth");
                System.out.println("Swimming and Survival floating cause loss of heat causes hypothermia");
            } else if (q2==6) {
                System.out.println("Review Feet and Head First Surface Dives");
            } else if (q2==7) {
                System.out.println("Review Standing on Sold Surface Dives");
            } else if (q2==8) {
                System.out.println("Benefits of aerobic exercise and why swimming is favored in fitness & therapy");
            } else {
                System.out.println("Not a requirement in Swimming Merit Badge.");
            }
        } else if (q1.contains("management")) {
            System.out.println("Add to log and check date based on chart on front");
        } else if (q1.contains("fitness")) {
            System.out.println("Check log, do activity/activities, add to chart, check date for tests");
        } else {
            System.out.println("Not needed to be done.");
        }
    }
}