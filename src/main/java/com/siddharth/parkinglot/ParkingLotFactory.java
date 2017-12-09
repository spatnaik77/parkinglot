package com.siddharth.parkinglot;

/**
 * Created by sr250345 on 12/9/17.
 */
public class ParkingLotFactory {

    public static IParkingLot createParkingLot()
    {
        return new ParkingLotImpl();
    }
}
