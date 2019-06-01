//2) DNA bases have a "d" sugar in their backbone (3rd letter of the base) RNA bases have an "r".
// When you go to single letter you only show the second letter so lose sugar and modifier information.
public class Solution_02 {

    public static void main(String[] args) {
        System.out.println(Solution_02.reformatSequecne("-Gdo-Gdo-Ado-Ado-Udo-Gro-Gro-Cro-Uro-Uro-Uro-Ur"));
        System.out.println(Solution_02.reformatSequecne("-Gdo-Gdo-Ado-Ado-Udo-Gro-Gro-Cro-Uro-Uro-Uro-Ud"));
        System.out.println(Solution_02.reformatSequecne("-Gd"));
    }

    public static String reformatSequecne(String sequence) {
        if (sequence == null || sequence.length() == 0) {
            return "";
        }
        boolean isDNA = false;
        StringBuilder stringBuilder = new StringBuilder();
        int i = 0;

        while (i < sequence.length()) {

            if (sequence.charAt(i + 2) == 'd') {
                if (!isDNA) {
                    isDNA = true;
                    stringBuilder.append('[');
                }
            } else {
                if (isDNA) {
                    stringBuilder.append(']');
                    isDNA = false;
                }
            }
            stringBuilder.append(sequence.charAt(i + 1));
            i += 4;
        }
        if (isDNA) {
            stringBuilder.append(']');
        }

        return stringBuilder.toString();
    }
}
