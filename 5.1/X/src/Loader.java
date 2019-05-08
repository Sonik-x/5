public class Loader {
    public static void main(String[] args) {

        var X = createX(25);

        for (int i = 0; i < X.length; i++) {
            System.out.println();
            for (int j = 0; j < X[i].length; j++) {
                System.out.print(X[i][j]);
            }
        }

    }

    public static String[][] createX(int size) {
        String[][] result = new String[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                result[i][j] = " ";
            }
        }

        for (int i = 0; i < size; i++) {
            result[i][i] = "X";
            result[i][size - 1 - i] = "X";
        }

        return result;
    }

}
