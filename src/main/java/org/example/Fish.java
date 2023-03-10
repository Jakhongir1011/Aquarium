package org.example;


public class Fish extends Thread {

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



    public static Fish buildFish(Gender gender, String parents) {
        if (FishService.fishList.size() >= 25) {
            System.out.println("Akkvarumga baliqlarga to'ldi");
            System.exit(1);
        }
        Fish fish = new Fish(gender, RandomUtils.getMaxAge(), false,RandomUtils.getDateNumber(), parents, true);
        fish.start(); //Thread  bajaralishini boshaydi JVM thread da run() methodni chiradi
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

            // Programma ketma ketlikda birdaniga javob chiqishini oldini oladi
            try {
                Thread.sleep(2000); // Joriy bajarilayotgan thread ni belgilangan millisekundlar uchun uyqu holatiga keltiradi vaqtincha bajarilishni toxtatadi.
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            this.age++;
            System.out.println(this.fishId + "- baliq " + this.age + " yoshga to'ldi va hali " + (isMarried ? "OILALI" : "BO'YDOQ --->" + this.gender));

            // Baliqni vafot etishi(Max yoshiga yetganda baliq vafot etadi)
            if (this.age == this.maxAge) {
                boolean b = FishService.removeFish(this);
                if (b){
                    System.out.println(this.getFishId() + " baliq "+ this.maxAge + " Umir ko'rdi va baliq vafot etti " + this.isDead);
                    this.stop();
                }
            }

            // Baliqning uchrashib qolishi va nasil kopayishi
            // Birinchisi u tirik bo'lishi kerak
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
