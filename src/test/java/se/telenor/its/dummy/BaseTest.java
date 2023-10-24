package se.telenor.its.dummy;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Random;

public class BaseTest {


    @Test
    public void testSmth() {
        Random r = new Random();
        int rand = r.nextInt(10);
        System.out.println(rand);
        Assert.assertEquals(rand % 2, 0);
    }
}
