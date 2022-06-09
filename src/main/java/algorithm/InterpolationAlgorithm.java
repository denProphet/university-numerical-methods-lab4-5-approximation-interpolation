package algorithm;

import java.util.HashMap;
import java.util.Map;

public class InterpolationAlgorithm {

    private double[] xValues;
    private double[] yValues;
    private double intervalA;
    private double intervalB;

    public InterpolationAlgorithm(double[] xValues, double[] yValues, double intervalA, double intervalB) {
        this.xValues = xValues;
        this.yValues = yValues;
        this.intervalA = intervalA;
        this.intervalB = intervalB;
    }

    /**
     * solve algorithm and get result
     * */
    public Map<Double, Double> getResult() {
        
        Map<Double, Double> result = new HashMap<>();
        double step = Math.abs(xValues[0] - xValues[1]);

        for (double i = intervalA; i < intervalB + step; i += step) {
            result.put(i, getLagrangePolynomial(i));
        }
        return result;
    }

    /**
     * solve and get Lagrange Polynomial
     * */
    private double getLagrangePolynomial(double x) {
        double lagrangePol = 0;

        for (int i = 0; i < xValues.length; i++) {
            double basicsPol = 1;
            for (int j = 0; j < xValues.length; j++) {
                if (j != i) {
                    basicsPol *= (x - xValues[j]) /
                            (xValues[i] - xValues[j]);
                }
            }
            lagrangePol += basicsPol * yValues[i];
        }
        return lagrangePol;

    }
}
