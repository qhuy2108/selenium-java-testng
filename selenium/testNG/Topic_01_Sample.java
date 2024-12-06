package testNG;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Random;

public class Topic_01_Sample {

    // Unit Test
    @Test
    public void testGetRandomNumber() {
        Topic_01_Sample topic01Sample = new Topic_01_Sample();
        int randomNumber = topic01Sample.getRandomNuber();
        Assert.assertTrue(randomNumber >= 0 && randomNumber < 1000);
    }

    // Component
    private int getRandomNuber() {
        return new Random().nextInt(1000);
    }

}
