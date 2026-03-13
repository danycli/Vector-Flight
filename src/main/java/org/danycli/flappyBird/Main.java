package org.danycli.flappyBird;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Random num = new Random();
        int n = num.nextInt(50,101);
        // int n = (int)(Math.random()*((100-50)+1));
        System.out.println(n);
    }
}
