package org.example;

import java.util.Random;

public class RandomUtils {

    public static byte getMaxAge() {
        byte b = (byte) new Random().nextInt(20);
        if (b<10){
            b=10;
            return b;
        }
        return b;
    }

    public static int getFishNumber(){
        int i = new Random().nextInt(3);
        if (i<=0){
            i=1;
            return i;
        }
        return i;
    }

        public static int getDateNumber(){
        return new Random().nextInt(10);
    }

}
