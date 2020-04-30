package com.bertonisolutions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HandTest {

    @Test
    void setHand() {
        Hand a = new Hand("TDJDQDKDAD");
        Hand b = new Hand("TDJDQDKDAD");
        assertEquals(0, a.compareTo(b));
        b.setHand("3D6D7DTDQD");
        assertEquals(1, a.compareTo(b));
        a.setHand("3D6D7DTDQD");
        assertEquals(0, a.compareTo(b));
    }
}