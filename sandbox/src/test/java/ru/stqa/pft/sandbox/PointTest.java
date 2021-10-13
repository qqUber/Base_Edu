package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class PointTest {
    @Test
    public void testLongPoint() {
    Point pp1 = new Point(5.1,5.2);
    Point pp2 = new Point(-1.1,-3.2);

    assert pp1.distance(pp2) != 0;
    Assert.assertTrue(true);
    Assert.assertEquals((pp1.distance(pp2)), 10.44030650891055);
    }
}