package org.example;


public class Fish extends Thread {

    private static int countFishRemove=0;
    private static long counter = 0;
    private long fishId;
    private Gender gender;
    private byte age;
    private byte maxAge;
    private boolean isMarried;
    private int dateNumber; // baliqlarni uchrashib qolishi
    private String parents;
    private boolean isDead;
    {
        fishId = ++counter;
    }

    public Fish(Gender gender, byte maxAge, boolean isMarried, int dateNumber, String parents, boolean isDead) {
        this.age = 0;
        this.gender = gender;
        this.maxAge = maxAge;
        this.isMarried = isMarried;
        this.dateNumber=dateNumber;
        this.parents = parents;
        this.isDead=isDead;
    }

    public long getFishId() {
        return fishId;
    }

    public Gender getGender() {
        return gender;
    }

    public byte getAge() {
        return age;
    }

    public byte getMaxAge() {
        return maxAge;
    }

    public boolean isMarried() {
        return isMarried;
    }

    public void setMarried(boolean married) {
        isMarried = married;
    }

    public int getDateNumber() {
        return dateNumber;
    }

    public boolean isDead() {
        return isDead;
    }


    /**
     *buildFish() -> Fish tug'ilish jarayoni Random orqali aniqlandi
     */
    public static Fish buildFish(Gender gender, String parents) {
        if (FishService.fishList.size() >= 25) {
            System.out.println("Akkvarum baliqlarga to'ldi");
            System.out.println("O'lgan baliqlar soni "+ countFishRemove);
            int fishNumber = FishService.fishList.size()+countFishRemove;
            System.out.println("Akkvarumda shuncha baliq bo'lgan "+ fishNumber);
            System.exit(1);
        }
        Fish fish = new Fish(gender, RandomUtils.getMaxAge(), false,RandomUtils.getDateNumber(), parents, true);
        /**
         * start()->Thread  bajaralishini boshlaydi JVM thread da run() methodni chaqiradi
         */
        fish.start();
        return fish;
    }


    @Override
    public void run() {
        System.out.println(this.getFishId() + " Baliqni MaxAge: " + this.getMaxAge() + " " + this.getGender());

        for (int i = 0; i < this.getMaxAge(); i++) {

            if (i == 0) {
                if (this.parents != null) {
                    System.out.println(this.fishId + " baliq dunyoga keldi " + " va uning ota onasi " + this.parents);
                } else {
                    System.out.println(this.fishId + " akvariumga keldi jinsi " + this.getGender());
                }
            }

            /**
             *  sleep Joriy bajarilayotgan thread ni belgilangan millisekundlar uchun uyqu holatiga keltiradi vaqtincha bajarilishni toxtatadi.
             */
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            this.age++;
            System.out.println(this.fishId + "- baliq " + this.age + " yoshga to'ldi va hali " + (isMarried ? "OILALI" : "BO'YDOQ --->" + this.gender));

            /**
             *  Baliqni vafot etishi(Max yoshiga yetganda baliq vafot etadi).
             */
            if (this.age == this.maxAge) {
                countFishRemove++;
                boolean b = FishService.removeFish(this);
                if (b){
                    System.out.println(this.getFishId() + " baliq "+ this.maxAge + " Umir ko'rdi va baliq vafot etti " + this.isDead);
                    this.stop();
                }
            }

            /**
             *  Baliqlarning uchrashib qolishi va  kopayish jarayoni
             */
            if (!this.isMarried) {
                if (this.isDead){
                    this.dateNumber = RandomUtils.getDateNumber(); //
                    Fish pairFish = FishService.fishDating(this);
                    if (pairFish != null) {
                        System.out.println(this.getFishId() + "- bilan " + pairFish.getFishId() + " turmush qurishidi " +
                                " va bunda " + this.getFishId() + " ni yoshi " + this.age + " edi "
                                + pairFish.getFishId() + " ni yoshi " + pairFish.getAge() + " edi ");
                    }
                }
            }

        }
    }
}
