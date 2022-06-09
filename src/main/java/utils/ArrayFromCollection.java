package utils;

import java.util.List;

public class ArrayFromCollection {
    public double[] getDoubleArrayFromSpecificList(double[] array, List<Double> collection){
        for (int i = 0; i < collection.size() ; i++) {
            array[i] = collection.get(i);
        }
        return array;
    }
}
