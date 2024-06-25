package com.projetosrafaelfemina.calculadoradeimc.utils;

public class NumberUtils {
    public static float calculate(float Weight, float Height) {
        //calculate BMI
        return Weight / (Height * Height);
    }
}
