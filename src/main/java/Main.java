
import algorithm.ApproximationAlgorithm;
import algorithm.InterpolationAlgorithm;
import exceptions.IllegalDataStructureException;
import exceptions.IllegalIntervalValueException;
import reader.ArrayFileReader;
import utils.ArrayFromCollection;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Дисципліна: чисельні методи. Лабораторна робота 4,5. Яровой Денис
 * Варіант 20
 * <p>
 * Вимоги до проекту:
 * Написати програму для реалізації методів інтерполяції та апроксимації
 * <p>
 * Вхідні дані знаходяться у файлі (файл src/main/resources)
 * <p>
 * Структура файлу з даними:
 * 1 рядок = 1 значення.
 * Дозволено десяткові роздільники "." та ","
 * Дозволено порожні рядки
 *
 * @author Den Yarovoy
 * @version 1.0
 **/

public class Main {
    public static void main(String[] args) throws IOException {

        /**
         *
         * Get x&yValues and interval.txt data from external files using reader&parser
         * check structure
         * solve specific algorithm
         */
        ArrayFileReader arrayFileReader = new ArrayFileReader();
        ArrayFromCollection arrayFromCollection = new ArrayFromCollection();

        try {
            List<Double> xValuesList =
                    arrayFileReader.getDoubleList(new File("src/main/resources/xValues.txt"));
            List<Double> yValuesList =
                    arrayFileReader.getDoubleList(new File("src/main/resources/yValues.txt"));
            List<Double> interval =
                    arrayFileReader.getDoubleList(new File("src/main/resources/interval.txt"));
            double[] xValues = new double[xValuesList.size()];
            double[] yValues = new double[yValuesList.size()];
            xValues = arrayFromCollection.getDoubleArrayFromSpecificList(xValues, xValuesList);
            yValues = arrayFromCollection.getDoubleArrayFromSpecificList(yValues, yValuesList);

            /**
             * check initial values data format
             * */

            if (xValuesList.size() != 5 || yValuesList.size() != 5   ||
                    interval.size() !=2
            ) throw new IllegalDataStructureException();

            /**
             * check interval values
             * */

            if (interval.get(0)>interval.get(1)) throw new IllegalIntervalValueException();

            /**
             * solve algorithm
             * get results
             * show it
             * */

           InterpolationAlgorithm interpolationAlgorithm =
                   new InterpolationAlgorithm(xValues,yValues,interval.get(0),interval.get(1));

            System.out.println("===============Interpolation result===============\n");
            for (Map.Entry<Double, Double> pair: interpolationAlgorithm.getResult().entrySet()) {
                System.out.println("X = "+pair.getKey() + "  F(X) = " + pair.getValue());
            }

            ApproximationAlgorithm approximationAlgorithm = new ApproximationAlgorithm(xValues,yValues);

            System.out.println("===============Approximation result===============\n");
            System.out.println("The polynomial to power of 1: ");
            System.out.println(approximationAlgorithm.getPolynomialStringResult());
            System.out.printf("Standard deviation: %.2f\n",approximationAlgorithm.getPolynomialSumResult(1));

            for (Map.Entry<Double, Double> pair: approximationAlgorithm
                    .getPolynomialValuesResult(1).entrySet()) {
                System.out.println("X = "+pair.getKey() + "  P1(X) = " + String.format("%.3f",pair.getValue()));
            }

            System.out.println("\nThe polynomial to power of 2: ");
            System.out.println(approximationAlgorithm.getPolynomial2StringResult());
            System.out.printf("Standard deviation: %.2f\n",approximationAlgorithm.getPolynomialSumResult(2));
            for (Map.Entry<Double, Double> pair: approximationAlgorithm
                    .getPolynomialValuesResult(2).entrySet()) {
                System.out.println("X = "+pair.getKey() + "  P2(X) = " + String.format("%.3f",pair.getValue()));
            }

        } catch (IllegalDataStructureException e) {
            System.err.println("Wrong initial data structure.Required format:" +
                    " 2 values for interval.txt, 5 values for values data file." +
                    " Every value has to be started from next line ");
        } catch (NumberFormatException e) {
            System.err.println("Illegal file data format. Required format: decimal values. " +
                    "Every value has to be started from next line");
        } catch (IllegalIntervalValueException e){
            System.err.println("Interval A cannot be larger than interval B");
        }
    }
}

