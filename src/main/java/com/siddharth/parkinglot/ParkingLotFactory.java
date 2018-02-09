package com.siddharth.parkinglot;

/**
 * Created by Siddharth on 12/9/17.
 */
public class ParkingLotFactory {

    public static IParkingLot createParkingLot()
    {
        return new ParkingLotImpl();
    }
}
