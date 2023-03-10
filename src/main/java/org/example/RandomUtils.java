package org.example;

import java.util.Random;

public class RandomUtils {

    public static byte getMaxAge() {
        byte b = (byte) new Random().nextInt(15);
        if (b<5){
            b=5;
            return b;
        }
        return b;
    }

    public static int getFishNumber(){
        int i = new Random().nextInt(3);
        if (i<1){
            i=1;
            return i;
        }
        return i;
    }

        public static int getDateNumber(){
            int i = new Random().nextInt(10);
            if (i<5){
                i=5;
                return 5;
            }
            return i;
    }

}
