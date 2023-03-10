package org.example;

import java.util.HashSet;
import java.util.Set;

public class FishService {

    static Set<Fish> fishList = new HashSet<>();

    /**
     * Fishlarni tug'ilishini qilib beradigan method
     * @param parents Ota ona si kimligi
     */
    public static void birthFish(String parents) {
        System.out.println("\n Baliqlar tug'ildi !");
        int maleFishNumber = RandomUtils.getFishNumber();
        int femaleFishNumber = RandomUtils.getFishNumber();
        System.out.println(maleFishNumber + " ta erkaka baliq paydo boldi " + Gender.MALE);
        System.out.println(femaleFishNumber + " ta ayol baliq paydo bo'ldi " + Gender.FEMALE);
        for (int i = 0; i < maleFishNumber + femaleFishNumber; i++) {
            if (i < maleFishNumber) {
                Fish fish = Fish.buildFish(Gender.MALE, parents);
                fishList.add(fish);
            } else {
                Fish fish = Fish.buildFish(Gender.FEMALE, parents);
                fishList.add(fish);
            }
        }
        System.out.println(fishList.size() + " ta jami baliqlar soni ");
    }

    /**
     *
     * @param currentFish Baliqlar uchrashib qolishi
     * @return baliqni juftini qaytaradi(Fish)
     */
    public static synchronized Fish fishDating(Fish currentFish) {
        int dateNumber = currentFish.getDateNumber();
        Gender gender = currentFish.getGender();
        Fish pairFish = fishList.stream().filter((fish) -> {
            if (!fish.isMarried()) {
                if (!fish.getGender().equals(gender) && fish.getDateNumber() == dateNumber && fish.isDead() && currentFish.isDead()) {
                    fish.setMarried(true);
                    currentFish.setMarried(true);
                    return true;
                }
            }
            return false;
        }).findFirst().orElse(null);
        if (pairFish != null) {
            System.out.println("\n ---------------------------");
            birthFish(currentFish.getFishId() + " va " + pairFish.getFishId() + " baliqlar ");
        }
        return pairFish;
    }

    /**
     * @param removeFish Umrini yashagan baliqni o'chirish methodi
     * @return true or false
     */
    public  static synchronized boolean removeFish(Fish removeFish){
        return fishList.remove(removeFish);
    }

}
