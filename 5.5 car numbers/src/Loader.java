import java.util.ArrayList;

public class Loader {

    public static final String LETTERS = "ACEHKMOPTXY";
    public static final char[] charList = LETTERS.toCharArray();

    public static ArrayList<String> numbersList = new ArrayList<>();


    public static void main(String[] args) {

        for (int i = 0; i < charList.length; i++) {
            for (int j = 0; j <= 9; j++) {
                for (int k = 1; k <= 197; k++) {
                    String ks = ((Integer)k).toString();
                    while (ks.length() < 3){
                        ks = "0" + ks;
                    }

                    StringBuilder builder = new StringBuilder();
                    builder.append(charList[i]);
                    builder.append(j);
                    builder.append(j);
                    builder.append(j);
                    builder.append(charList[i]);
                    builder.append(charList[i]);
                    builder.append(ks);
                    numbersList.add(builder.toString());
                }
            }
        }



    }

    public static boolean findNumberStraight(String number){

        return false;
    }

    public static boolean findNumberBinary(String number){

        return false;
    }

    public static boolean findNumberHash(String number){

        return false;
    }

    public static boolean findNumberTree(String number){

        return false;
    }
}
