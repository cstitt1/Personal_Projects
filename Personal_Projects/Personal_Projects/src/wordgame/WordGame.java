package wordgame;
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * Program to print a random list of words with different phonetic starts.
 * @author cstit
 */
public class WordGame {
	/**
	 * Returns the next prefix to be tested
	 * @return The next prefix to be tested
	 */
    public static String ToNext() {
        File SL = new File("C:\\Users\\cstit\\Desktop\\PrefixList.txt");
        Scanner pres = null;
        try {
            pres = new Scanner(SL);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(WordGame.class.getName()).log(Level.SEVERE, null, ex);
        }
        String pl= pres.nextLine();
        return pl;
    }
    /**
     * Tests the prefix against the entire list of words.
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        File WL = new File("C:\\Users\\cstit\\Desktop\\WordList.txt");
        if (!(WL.exists())) {
            System.out.println("This file does not exist.");
        } else {
            System.out.println("The file does exist!");
            String preuse;
            int[] ac = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
            for (int i=1; i<=(26*26); i++) {
                preuse = WordGame.ToNext();
                Scanner wds = null;
                try {
                    wds = new Scanner(WL);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(WordGame.class.getName()).log(Level.SEVERE, null, ex);
                }
                String pl = wds.nextLine();
                while (wds.hasNextLine()==true) {
                    if (pl.substring(0,2).equals(preuse) && pl.length()<6) {
                        switch (preuse.charAt(0)) {
                            case 'a':
                                ac[0]++;
                                break;
                            case 'b':
                                ac[1]++;
                                break;
                            case 'c':
                                ac[2]++;
                                break;
                            case 'd':
                                ac[3]++;
                                break;   
                            case 'e':
                                ac[4]++;
                                break;
                            case 'f':
                                ac[5]++;
                                break;
                            case 'g':
                                ac[6]++;
                                break;
                            case 'h':
                                ac[7]++;
                                break;
                            case 'i':
                                ac[8]++;
                                break;
                            case 'j':
                                ac[9]++;
                                break;
                            case 'k':
                                ac[10]++;
                                break;
                            case 'l':
                                ac[11]++;
                                break;
                            case 'm':
                                ac[12]++;
                                break;
                            case 'n':
                                ac[13]++;
                                break;
                            case 'o':
                                ac[14]++;
                                break;
                            case 'p':
                                ac[15]++;
                                break;
                            case 'q':
                                ac[16]++;
                                break;
                            case 'r':
                                ac[17]++;
                                break;
                            case 's':
                                ac[18]++;
                                break;
                            case 't':
                                ac[19]++;
                                break;
                            case 'u':
                                ac[20]++;
                                break;
                            case 'v':
                                ac[21]++;
                                break;
                            case 'w':
                                ac[22]++;
                                break;
                            case 'x':
                                ac[23]++;
                                break;
                            case 'y': 
                                ac[24]++;
                                break;
                            case 'z':
                                ac[25]++;
                                break;
                            default:
                                break;
                        }
                    }
                    pl = wds.nextLine();
                }
                //Call ToNext method and check each line for prefix
                //Make array of words with prefix and then use random int to choose words
                //Make final array of words that are the final list
            }
            String[] awords = new String[ac[0]];
            String[] bwords = new String[ac[1]];
            String[] cwords = new String[ac[2]];
            String[] dwords = new String[ac[3]];
            String[] ewords = new String[ac[4]];
            String[] fwords = new String[ac[5]];
            String[] gwords = new String[ac[6]];
            String[] hwords = new String[ac[7]];
            String[] iwords = new String[ac[8]];
            String[] jwords = new String[ac[9]];
            String[] kwords = new String[ac[10]];
            String[] lwords = new String[ac[11]];
            String[] mwords = new String[ac[12]];
            String[] nwords = new String[ac[13]];
            String[] owords = new String[ac[14]];
            String[] pwords = new String[ac[15]];
            String[] qwords = new String[ac[16]];
            String[] rwords = new String[ac[17]];
            String[] swords = new String[ac[18]];
            String[] twords = new String[ac[19]];
            String[] uwords = new String[ac[20]];
            String[] vwords = new String[ac[21]];
            String[] wwords = new String[ac[22]];
            String[] xwords = new String[ac[23]];
            String[] ywords = new String[ac[24]];
            String[] zwords = new String[ac[25]];
            for (int i=0; i<ac.length; i++) {
                ac[i]=0;
            }
            for (int i=1; i<=(26*26); i++) {
                preuse = WordGame.ToNext();
                Scanner wds = null;
                try {
                    wds = new Scanner(WL);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(WordGame.class.getName()).log(Level.SEVERE, null, ex);
                }
                String pl = wds.nextLine();
                while (wds.hasNextLine()==true) {
                    if (pl.substring(0,2).equals(preuse) && pl.length()<6) {
                        switch (preuse.charAt(0)) {
                            case 'a':
                                awords[ac[0]]=pl;
                                ac[0]++;
                                break;
                            case 'b':
                                bwords[ac[1]]=pl;
                                ac[1]++;
                                break;
                            case 'c':
                                cwords[ac[2]]=pl;
                                ac[2]++;
                                break;
                            case 'd':
                                dwords[ac[3]]=pl;
                                ac[3]++;
                                break;   
                            case 'e':
                                ewords[ac[4]]=pl;
                                ac[4]++;
                                break;
                            case 'f':
                                fwords[ac[5]]=pl;
                                ac[5]++;
                                break;
                            case 'g':
                                gwords[ac[6]]=pl;
                                ac[6]++;
                                break;
                            case 'h':
                                hwords[ac[7]]=pl;
                                ac[7]++;
                                break;
                            case 'i':
                                iwords[ac[8]]=pl;
                                ac[8]++;
                                break;
                            case 'j':
                                jwords[ac[9]]=pl;
                                ac[9]++;
                                break;
                            case 'k':
                                kwords[ac[10]]=pl;
                                ac[10]++;
                                break;
                            case 'l':
                                lwords[ac[11]]=pl;
                                ac[11]++;
                                break;
                            case 'm':
                                mwords[ac[12]]=pl;
                                ac[12]++;
                                break;
                            case 'n':
                                nwords[ac[13]]=pl;
                                ac[13]++;
                                break;
                            case 'o':
                                owords[ac[14]]=pl;
                                ac[14]++;
                                break;
                            case 'p':
                                pwords[ac[15]]=pl;
                                ac[15]++;
                                break;
                            case 'q':
                                qwords[ac[16]]=pl;
                                ac[16]++;
                                break;
                            case 'r':
                                rwords[ac[17]]=pl;
                                ac[17]++;
                                break;
                            case 's':
                                swords[ac[18]]=pl;
                                ac[18]++;
                                break;
                            case 't':
                                twords[ac[19]]=pl;
                                ac[19]++;
                                break;
                            case 'u':
                                uwords[ac[20]]=pl;
                                ac[20]++;
                                break;
                            case 'v':
                                vwords[ac[21]]=pl;
                                ac[21]++;
                                break;
                            case 'w':
                                wwords[ac[22]]=pl;
                                ac[22]++;
                                break;
                            case 'x':
                                xwords[ac[23]]=pl;
                                ac[23]++;
                                break;
                            case 'y':
                                ywords[ac[24]]=pl;
                                ac[24]++;
                                break;
                            case 'z':
                                zwords[ac[25]]=pl;
                                ac[25]++;
                                break;
                            default:
                                break;
                        }
                    }
                    pl = wds.nextLine();
                }
                //Call ToNext method and check each line for prefix
                //Make array of words with prefix and then use random int to choose words
                //Make final array of words that are the final list
            }
            String[] res = new String[26];
            for (int i=0; i<26; i++) {
                int tv;
                switch (i) {
                    case 0:
                        tv = (int)(Math.random() * ((awords.length)-1));
                        res[i] = awords[tv];
                        break;
                    case 1:
                        tv = (int)(Math.random() * ((bwords.length)-1));
                        res[i] = bwords[tv];
                        break;
                    case 2:
                        tv = (int)(Math.random() * ((cwords.length)-1));
                        res[i] = cwords[tv];
                        break;
                    case 3:
                        tv = (int)(Math.random() * ((dwords.length)-1));
                        res[i] = dwords[tv];
                        break;
                    case 4:
                        tv = (int)(Math.random() * ((ewords.length)-1));
                        res[i] = ewords[tv];
                        break;
                    case 5:
                        tv = (int)(Math.random() * ((fwords.length)-1));
                        res[i] = fwords[tv];
                        break;
                    case 6:
                        tv = (int)(Math.random() * ((gwords.length)-1));
                        res[i] = gwords[tv];
                        break;
                    case 7:
                        tv = (int)(Math.random() * ((hwords.length)-1));
                        res[i] = hwords[tv];
                        break;
                    case 8:
                        tv = (int)(Math.random() * ((iwords.length)-1));
                        res[i] = iwords[tv];
                        break;
                    case 9:
                        tv = (int)(Math.random() * ((jwords.length)-1));
                        res[i] = jwords[tv];
                        break;
                    case 10:
                        tv = (int)(Math.random() * ((kwords.length)-1));
                        res[i] = kwords[tv];
                        break;
                    case 11:
                        tv = (int)(Math.random() * ((lwords.length)-1));
                        res[i] = lwords[tv];
                        break;
                    case 12:
                        tv = (int)(Math.random() * ((mwords.length)-1));
                        res[i] = mwords[tv];
                        break;
                    case 13:
                        tv = (int)(Math.random() * ((nwords.length)-1));
                        res[i] = nwords[tv];
                        break;
                    case 14:
                        tv = (int)(Math.random() * ((owords.length)-1));
                        res[i] = owords[tv];
                        break;
                    case 15:
                        tv = (int)(Math.random() * ((pwords.length)-1));
                        res[i] = pwords[tv];
                        break;
                    case 16:
                        tv = (int)(Math.random() * ((qwords.length)-1));
                        res[i] = qwords[tv];
                        break;
                    case 17:
                        tv = (int)(Math.random() * ((rwords.length)-1));
                        res[i] = rwords[tv];
                        break;
                    case 18:
                        tv = (int)(Math.random() * ((swords.length)-1));
                        res[i] = swords[tv];
                        break;
                    case 19:
                        tv = (int)(Math.random() * ((twords.length)-1));
                        res[i] = twords[tv];
                        break;
                    case 20:
                        tv = (int)(Math.random() * ((uwords.length)-1));
                        res[i] = uwords[tv];
                        break;
                    case 21:
                        tv = (int)(Math.random() * ((vwords.length)-1));
                        res[i] = vwords[tv];
                        break;
                    case 22:
                        tv = (int)(Math.random() * ((wwords.length)-1));
                        res[i] = wwords[tv];
                        break;
                    case 23:
                        tv = (int)(Math.random() * ((xwords.length)-1));
                        res[i] = xwords[tv];
                        break;
                    case 24:
                        tv = (int)(Math.random() * ((ywords.length)-1));
                        res[i] = ywords[tv];
                        break;
                    case 25:
                        tv = (int)(Math.random() * ((zwords.length)-1));
                        res[i] = zwords[tv];
                        break;
                    default:
                        break;
                }
            }
            System.out.println("Here are the words:");
            for (int i=0; i<26; i++) {
                System.out.println(res[i]);
            }
        }
    }
}