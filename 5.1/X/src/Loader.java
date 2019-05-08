public class Loader {
    public static void main(String[] args) {

        var X = createX(25);
        printArreyOfArreys(X);

    }

    public static String[][] createX(int size) {
        String[][] result = new String[size][size];

        /* for (String[] xString : result) {      этот код не работает, потому что (внезапно) для каждой итерации создаётся новая ссылка,
            for (String str : xString) {          и уже ей присваевается следующий объект. То есть присваивать что-то ей бесполезно. грусть.
                str = " ";
            }
        } */

        for (int i = 0; i < size; i++) {     //работает
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

    public static void printArreyOfArreys(Object[][] theArrey) {  //в процессе выяснилось, что для массива примитивов fori быстрее, чем foreach
        for (Object[] line : theArrey) {                          //а для List быстрее foreach
            System.out.println();
            for (Object obj : line) {                             //что из этого быстрее в моём случае не очень понятно, но foreach красивее
                System.out.print(obj);
            }
        }
    }

}
