package org.example;

import java.util.Random;

public class RandomUtils {

    public static byte getMaxAge() {
        return (byte) new Random().nextInt(100);
    }

    public static int getDateNumber(){
        return new Random().nextInt(10);
    }

    public static int getFishNumber(){
        return new Random().nextInt(3);
    } // savol shuni [1:3] oralig'ida qilib berish uchun nima qilamiz

//    public static int getAquariumSize(){
//        return new Random().nextInt(30);
//    }
}
