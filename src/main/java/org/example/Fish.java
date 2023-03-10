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

    public Fish() {
    }

    public Fish(Gender gender, byte maxAge, boolean isMarried, String parents, boolean isDead) {
        this.age = 0;
        this.gender = gender;
        this.maxAge = maxAge;
        this.isMarried = isMarried;
        this.parents = parents;
        this.isDead=isDead;
    }

//    public Fish(Gender gender, byte maxAge, boolean isMarried, int dateNumber) {
//        this.age = 0;
//        this.gender = gender;
//        this.maxAge = maxAge;
//        this.isMarried = isMarried;
//        this.dateNumber = dateNumber;
//    }

    public long getFishId() {
        return fishId;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public byte getAge() {
        return age;
    }

    public void setAge(byte age) {
        this.age = age;
    }

    public byte getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(byte maxAge) {
        this.maxAge = maxAge;
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

    public void setDateNumber(int dateNumber) {
        this.dateNumber = dateNumber;
    }

    public String getParents() {
        return parents;
    }

    public void setParents(String parents) {
        this.parents = parents;
    }

    public boolean isDead() {
        return isDead;
    }

    public static Fish buildFish(Gender gender, String parents) {
        if (FishService.fishList.size() >= 15) {
            System.out.println("Akkvarumga baliqlarga to'ldi");
            System.exit(1);
        }
        Fish fish = new Fish(gender, RandomUtils.getMaxAge(), false, parents, true);
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
                this.isDead=false;
                System.out.println(this.getFishId() + " baliq "+ this.maxAge + " Umir ko'rdi va baliq vafot etti " + this.isDead);
               }

            }

        }

        // Baliqning uchrashib qolishi va nasil kopayishi mumkin
        // Birinchisi u tirik bo'lishi kerak
//            if (this.age >= 18 && this.age <= 30 && !this.isMarried) {
//                this.dateNumber = RandomUtils.getDateNumber();
//                Fish pairFish = FishService.fishDating(this);
//                if (pairFish != null) {
//                    System.out.println(this.getFishId() + "- bilan " + pairFish.getFishId() + " turmush qurishidi " +
//                                       " va bunda " + this.getFishId() + " ni yoshi " + this.age + " edi "
//                                       + pairFish.getFishId() + " ni yoshi " + pairFish.getAge() + " edi ");
//                }
//            }
//
//
//
//        }
//        this.isDead=true;
    }

