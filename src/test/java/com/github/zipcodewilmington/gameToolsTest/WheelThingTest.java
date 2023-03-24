package com.github.zipcodewilmington.gameToolsTest;

import com.github.zipcodewilmington.casino.gameTools.WheelThing;
import org.junit.Test;


import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class WheelThingTest {


    @Test
    void testSpinReturnsValidValue() {
        String[] wheelSlots = {"cherry", "lemon", "orange", "plum", "bell", "bar"};
        WheelThing wheel = new WheelThing(wheelSlots);
        Set<String> validValues = new HashSet<>(Arrays.asList(wheelSlots));
        assertTrue(validValues.contains(wheel.spinString(0,wheelSlots.length-1)));
    }

    @Test
    void testEmptyWheel() {
        String[] wheelSlots = {};
        WheelThing wheel = new WheelThing(wheelSlots);

        // spinning an empty wheel should throw an exception
        //assertThrows(IllegalArgumentException.class, wheel.spinString(1,2));
    }
}
