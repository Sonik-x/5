import java.text.DecimalFormat;
import java.util.Arrays;

public class Loader {
    static final DecimalFormat FORMAT = new DecimalFormat("#.##");

    public static void main(String[] args) {

        double[] tempes = new double[30];

        int healthyAmount = 0;
        for (int i = 0; i < tempes.length; i++) {
            tempes[i] = 32 + (Math.random() * 8);
            System.out.print(FORMAT.format(tempes[i]) + " ");
            if (36.2 < tempes[i] && tempes[i] < 36.9){
                healthyAmount++;
            }
        }
        System.out.println();

        System.out.println("Средняя температура по больнице: " + FORMAT.format(Arrays.stream(tempes).average().getAsDouble()));
        System.out.println("Количество здоровых пациентов: " + healthyAmount);



    }
}
