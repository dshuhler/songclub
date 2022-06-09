package com.shuhler.negadelphia.domain.classifier;

import net.bytebuddy.build.ToStringPlugin;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CussClassifierTest {

    private CussClassifier cussClasser = new CussClassifier();

    @Test
    public void noCusses() {
        assertEquals(0, cussClasser.classify("clean as a whistle"));
        assertEquals(0, cussClasser.classify("  s  s s  "));
        assertEquals(0, cussClasser.classify(""));
    }

    @Test
    public void oneCuss() {
        assertEquals(1, cussClasser.classify("fuck"));
        assertEquals(1, cussClasser.classify("Shit"));
        assertEquals(1, cussClasser.classify("ASSHOLE dude"));
    }

    @Test
    public void manyCusses() {
        assertEquals(2, cussClasser.classify("fuck fuck"));
        assertEquals(4, cussClasser.classify("Shit fuck ass asshole"));
        assertEquals(2, cussClasser.classify("I'm a little asshole, a total peice of shit"));
    }
}