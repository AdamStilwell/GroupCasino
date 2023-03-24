package com.github.zipcodewilmington.casino.gameTools;

import java.util.Random;

public class WheelThing {
    private String[] wheelSlots;

    private Random random;

    public WheelThing(String[] wheelSlots) {
        this.wheelSlots = wheelSlots;
        random = new Random();
    }
    public WheelThing() {
        random = new Random();
    }
    public Integer spin(int min, int max) {
        int result = random.nextInt((max - min) + 1) + min;
        return result;
    }

    public String spinString(int min, int max) {
        int result = random.nextInt((max - min) + 1) + min;
        return wheelSlots[result-1];
    }
}

//public class WheelThing {
//    //    public int numberGenerator(int lowerBound, int upperBound){
////        Random rand = new Random();
////        return rand.nextInt(upperBound-lowerBound)+lowerBound;
////    }
//    private String[] wheelSlots;
//    private Random random;
//
//    public void Wheel(String[]wheel){
//        this.wheelSlots=wheel;
//        this.random=new Random();
//    }
//    public String spin(){
//        int index=random.nextInt(wheelSlots.length);
//        return wheelSlots[index];
//    }
//}
////public class WheelThing{
////    private Random random;
////
////    public Wheel(){
////        random= new Random();
////    }
////
////    public int spin(int min, int max){
////        int result= random.nextInt((max-min)+1)+min;
////        return result;
////    }


