package com.siddharth.parkinglot;

import org.junit.Test;

/**
 * Created by sr250345 on 12/8/17.
 */
public class ParkinglotTest {

    @Test
    public void testParkinglotCreate()
    {
        ParkingLotImpl pkLot = new ParkingLotImpl();
        pkLot.createParkingLot(6);
    }
}
