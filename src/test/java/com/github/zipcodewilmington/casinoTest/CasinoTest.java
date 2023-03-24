package com.github.zipcodewilmington.casinoTest;

import com.github.zipcodewilmington.Casino;
import com.github.zipcodewilmington.casino.CasinoAccountManager;
import org.junit.Assert;
import org.junit.Test;

public class CasinoTest {
    @Test
    public void testConstructor(){
        // Given
        Casino casino = new Casino();

        // When
        // Then
        Assert.assertNotNull(casino.getAccountManager());
    }

    @Test
    public void testSetAccountManager(){
        // Given
        Casino casino = new Casino();
        CasinoAccountManager expected = new CasinoAccountManager();

        // When
        casino.setAccountManager(expected);
        // Then
        Assert.assertEquals(expected,casino.getAccountManager());
    }
}
