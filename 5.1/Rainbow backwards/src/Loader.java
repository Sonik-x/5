public class Loader {

    static final String TEXT = "Каждый охотник желает знать, где сидит фазан";


    public static void main(String[] args) {
        String[] rainbow = TEXT.split(",?\\s+");
        int length = rainbow.length;
        String[] wobniar = new String[length];

        for (int i = 0; i < length; i++) {
            wobniar[i] = rainbow[length - 1 - i];
        }
        rainbow = wobniar;
        for (int i = 0; i < length; i++) {
            System.out.println(rainbow[i]);
        }

    }
}
