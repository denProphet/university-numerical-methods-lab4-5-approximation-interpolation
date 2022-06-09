package algorithm;

import java.util.HashMap;
import java.util.Map;

public class ApproximationAlgorithm {
    private double[] xValues;
    private double[] yValues;

    public ApproximationAlgorithm(double[] xValues, double[] yValues) {
        this.xValues = xValues;
        this.yValues = yValues;
    }

    /**
     * solve and get Polynomial values result.
     * Requires polynomial amount (power)
     * */

    public Map<Double, Double> getPolynomialValuesResult(int polynomialAmount) {
        Map<Double, Double> result = new HashMap<>();
        Polynomial polynomial = new Polynomial(xValues, yValues, polynomialAmount);

        for (int i = 0; i < xValues.length; i++) {
            double temp = polynomial.getValue(xValues[i]);
            result.put(xValues[i], temp);
        }
        return result;
    }

    /**
     * solve and get Polynomial Standard deviation (sum) result.
     * Requires polynomial amount (power)
     * */

    public double getPolynomialSumResult(int polynomialAmount) {
        double sumResult = 0.0d;
        Polynomial polynomial = new Polynomial(xValues, yValues, polynomialAmount);

        for (int i = 0; i < xValues.length; i++) {
            double temp = polynomial.getValue(xValues[i]);
            sumResult += Math.pow(temp - yValues[i], 2);
        }
        return sumResult;
    }

    /**
     * solve and get Polynomial to power of 1 (String) result.
     * */

    public String getPolynomialStringResult() {
        Polynomial polynomial = new Polynomial(xValues, yValues, 1);
        return (String.format("%.3f", polynomial.getCoefficients()[1]) +
                "x + " + String.format("%.3f", polynomial.getCoefficients()[0]));
    }

    /**
     * solve and get Polynomial to power of 2 (String) result.
     * */

    public String getPolynomial2StringResult() {
        Polynomial polynomial2 = new Polynomial(xValues, yValues, 2);
        return (String.format("%.3f", polynomial2.getCoefficients()[2]) +
                "x2 + " + String.format("%.3f", polynomial2.getCoefficients()[1]) +
                "x + " + String.format("%.3f", polynomial2.getCoefficients()[0]));

    }
}



