import java.util.*;

public class Loader {

    public static final String LETTERS = "ACEHKMOPTXY";
    public static final char[] charList = LETTERS.toCharArray();

    public static ArrayList<String> numbersList = new ArrayList<>();

    public static long startTime = 0;

    public static HashSet<String> hashSet = new HashSet<>();
    public static TreeSet<String> treeSet = new TreeSet<>();

    public static void main(String[] args) {


        for (int i = 0; i < charList.length; i++) {
            for (int j = 0; j <= 9; j++) {
                for (int k = 1; k <= 19700; k++) {
                    String ks = String.format("%05d", k);

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

        treeSet.addAll(numbersList);
        hashSet.addAll(numbersList);

        Scanner scanner = new Scanner(System.in);


        while (true) {
            String input = scanner.nextLine();
            //String input = "F000AA001";

            setStartTime();
            System.out.println("Binary: " + findNumberBinary(input) + " " + duration());
            setStartTime();
            System.out.println("Hash: " + findNumberHash(input) + " " + duration());
            setStartTime();
            System.out.println("Straight: " + findNumberStraight(input) + " " + duration());
            setStartTime();
            System.out.println("Tree: " + findNumberTree(input) + " " + duration());

        }

    }

    public static boolean findNumberStraight(String number) {
        if (numbersList.contains(number)) {
            return true;
        }
        return false;
    }

    public static boolean findNumberBinary(String number) {
        return Collections.binarySearch(numbersList, number) >= 0;      //в действительности отдаёт не -1, а некое отрицательное число
    }

    public static boolean findNumberHash(String number) {
        return hashSet.contains(number);
    }

    public static boolean findNumberTree(String number) {
        return treeSet.contains(number);
    }

    public static void setStartTime() {
        startTime = System.nanoTime();
    }

    public static double duration() {
        long result = System.nanoTime() - startTime;
        return (double)result/1000000;    //millis
    }
}
