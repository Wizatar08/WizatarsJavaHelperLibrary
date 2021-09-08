package com.wizatar08.helperlibrary.lang;

public class W8Math {

    public static int Ifloor(double number) {
        return (int) Math.floor(number);
    }

    public static int Iceil(double number) {
        return (int) Math.ceil(number);
    }

    public static int highest(int[] numbers) {
        int highest = numbers[0];
        for (int num : numbers) {
            if (num > highest) {
                highest = num;
            }
        }
        return highest;
    }

    public static int lowest(int[] numbers) {
        int lowest = numbers[0];
        for (int num : numbers) {
            if (num < lowest) {
                lowest = num;
            }
        }
        return lowest;
    }
}
