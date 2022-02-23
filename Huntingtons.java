/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

public class Huntingtons {

    // Returns the maximum number of consecutive repeats of CAG in the DNA string.
    public static int maxRepeats(String dna) {
        int max = 0;
        for (int i = 0; i < dna.length() - 2; i++) {
            if (dna.startsWith("CAG", i)) {
                int locmax = 0;
                for (int j = i; dna.startsWith("CAG", j); j = j + 3) {
                    locmax++;
                    i = j;
                }
                max = Math.max(max, locmax);

            }

        }
        return max;
    }

    // Returns a copy of s, with all whitespace (spaces, tabs, and newlines) removed.
    public static String removeWhitespace(String s) {
        String a = s;
        a = a.replace(" ", "");
        a = a.replace("\n", "");
        a = a.replace("\t", "");
        return a;
    }

    // Returns one of these diagnoses corresponding to the maximum number of repeats:
    // "not human", "normal", "high risk", or "Huntington's".
    public static String diagnose(int maxRepeats) {
        if (maxRepeats <= 9) return "not human";
        else if (maxRepeats <= 35) return "normal";
        else if (maxRepeats <= 39) return "high risk";
        else if (maxRepeats <= 180) return "Huntington's";
        else return "not human";
    }

    // Sample client (see below).
    public static void main(String[] args) {
        String name = args[0];
        In in = new In(name);
        String b = in.readAll();
        b = removeWhitespace(b);
        int max = maxRepeats(b);
        System.out.println("max repeats = " + max);
        System.out.println(diagnose(max));

    }

}
