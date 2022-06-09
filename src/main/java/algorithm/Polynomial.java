package algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * Used by Approximation algorithm
 * */
public class Polynomial {
    private double[] coefficients;
    private int amount;
    private double[] xValues;
    private double[] yValues;

    public Polynomial(double[] xValues, double[] yValues, int amount) {
        this.amount = amount;
        this.xValues = xValues;
        this.yValues = yValues;
        initialize();
    }

    public double[] getCoefficients() {
        return coefficients;
    }

    private void initialize() {

        int n = amount + 1;
        coefficients = new double[n];
        int count = xValues.length;

        double[][] a = new double[n][n];
        double[] b = new double[n];
        double[] c = new double[2 * n];

        for (int i = 0; i < count; i++) {
            double x = xValues[i];
            double y = yValues[i];

            double f = 1;

            for (int j = 0; j < 2 * n - 1; j++) {
                if (j < n) {
                    b[j] += y;
                    y *= x;
                }
                c[j] += f;
                f *= x;
            }
        }

        for (int i = 0; i < n; i++) {
            int k = i;
            for (int j = 0; j < n; j++) {
                a[i][j] = c[k];
                k++;
            }
        }

        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                a[j][i] /= -a[i][i];
                for (int k = i + 1; k < n; k++) {
                    a[j][k] += a[j][i] * a[i][k];
                }
                b[j] += a[j][i] * b[i];
            }
        }

        coefficients[n - 1] = (b[n - 1] / a[n - 1][n - 1]);

        for (int i = n - 2; i >= 0; i--) {
            double h = b[i];
            for (int j = i + 1; j < n; j++) {
                h -= coefficients[j] * a[i][j];
            }
            coefficients[i] = (h / a[i][i]);
        }
    }

    public double getValue(double xPoint) {
        double s = 0;
        for (int i = amount; i >= 1; i--) {
            s = (s + coefficients[i]) * xPoint;
        }
        return s + coefficients[0];
    }
}



