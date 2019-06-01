import java.util.HashSet;
import java.util.Set;

public class Solution_03 {


    public static void main(String[] args) {
        ValidationResult rst = Solution_03.ValidateSequence("-Gdo-Gdo-Ado-Ado-Udo-Gro-Gro");
        System.out.println(rst);
    }

    private static Set<Character> legalModifierSet = new HashSet<>();
    private static Set<Character> legalBaseSet = new HashSet<>();

    private static Set<Character> legalSugarSet = new HashSet<>();

    private static Set<Character> legalLinkSet = new HashSet<>();
    private static final String[] reasonArray = new String[]{
            "failed in modifier",
            "failed in base",
            "failed in sugar",
            "failed in linkage"
    };

    static {

        legalModifierSet.add('-');
        legalModifierSet.add('m');
        legalModifierSet.add('b');
        legalModifierSet.add('d');

        legalBaseSet.add('A');
        legalBaseSet.add('G');
        legalBaseSet.add('C');
        legalBaseSet.add('T');
        legalBaseSet.add('U');
        legalBaseSet.add('R');
        legalBaseSet.add('Y');
        legalBaseSet.add('S');
        legalBaseSet.add('W');
        legalBaseSet.add('K');
        legalBaseSet.add('M');
        legalBaseSet.add('B');
        legalBaseSet.add('D');
        legalBaseSet.add('H');
        legalBaseSet.add('V');
        legalBaseSet.add('N');

        legalSugarSet.add('d');
        legalSugarSet.add('r');
        legalSugarSet.add('e');
        legalSugarSet.add('m');
        legalSugarSet.add('y');
        legalSugarSet.add('l');
        legalSugarSet.add('k');
        legalSugarSet.add('o');

        legalLinkSet.add('o');
        legalLinkSet.add('s');
    }

    public static ValidationResult ValidateSequence(String sequence) {
        if (sequence == null || sequence.length() == 0) {
            return new ValidationResult(false);
        }
        int i = 0;
        for (; i < sequence.length(); i++) {
            int j = (i + 1) % 4;
            boolean isOK = isValidPosition(i, sequence.charAt(i));
            if (!isOK) {
                return new ValidationResult(false, i, reasonArray[j - 1]);
            }
        }
        i++;
        if (i % 4 == 0) {
            return new ValidationResult(true);
        } else {
            return new ValidationResult(false, i, reasonArray[(i % 4) - 1]);
        }

    }

    private static boolean isValidPosition(int i, char c) {
        int j = (i + 1) % 4;

        boolean isOK = true;
        if (j == 1 && !legalModifierSet.contains(c)) {
            // check modifiers
            isOK = false;
        } else if (j == 2 && !legalBaseSet.contains(c)) {
            // check bases
            isOK = false;
        } else if (j == 3 && !legalSugarSet.contains(c)) {
            // check sugar
            isOK = false;
        } else if (j == 0 && !legalLinkSet.contains(c)) {
            // check linkages
            isOK = false;
        }
        return isOK;
    }
}


class ValidationResult {
    public boolean isValid;
    public int position;
    public String failReason;

    public ValidationResult(boolean isValid) {
        this.isValid = isValid;
        this.position = -1;
        this.failReason = "";
    }

    public ValidationResult(boolean isValid, int position, String failReason) {
        this.isValid = isValid;
        this.position = position;
        this.failReason = failReason;
    }

    @Override
    public String toString() {
        return "ValidationResult{" +
                "isValid = " + isValid +
                ", position = " + position +
                ", failReason = '" + failReason + '\'' +
                '}';
    }
}


