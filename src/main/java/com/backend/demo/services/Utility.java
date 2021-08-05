package com.backend.demo.services;

public class Utility {

    public static void GenerateArray(double[] array, double min, double max) {
        double delta = (max - min) / (double)array.length;
        for(int i = 0; i < array.length; i++)
        {
            array[i] = min + (delta * i);
        }
    }
}
