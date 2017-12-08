package com.siddharth.parkinglot;

import com.siddharth.parkinglot.bo.Car;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Siddharth on 12/7/17.
 */
public class ParkingLotIntegrationTest {

    static ParkingLotImpl pkLot;
    static int numSlots = 6;

    @BeforeClass
    public static void setup()
    {
        pkLot = new ParkingLotImpl();
    }


    @Test
    public void park()
    {
        pkLot.createParkingLot(6);
        System.out.println("Created a parking lot with " + numSlots + " slots");

        int slotId = pkLot.park(new Car("KA-01-HH-1234", "White"));
        assertEquals(1, slotId);
        System.out.println("Allocated slot number:" + slotId);

        slotId = pkLot.park(new Car("KA-01-HH-9999", "White"));
        assertEquals(2, slotId);
        System.out.println("Allocated slot number:" + slotId);

        slotId = pkLot.park(new Car("KA-01-BB-0001", "Black"));
        assertEquals(3, slotId);
        System.out.println("Allocated slot number:" + slotId);

        slotId = pkLot.park(new Car("KA-01-HH-7777", "Red"));
        assertEquals(4, slotId);
        System.out.println("Allocated slot number:" + slotId);

        slotId = pkLot.park(new Car("KA-01-HH-2701", "Blue"));
        assertEquals(5,slotId);
        System.out.println("Allocated slot number:" + slotId);

        slotId = pkLot.park(new Car("KA-01-HH-3141", "Black"));
        assertEquals(6, slotId);
        System.out.println("Allocated slot number:" + slotId);
    }

    @Test
    public void status()
    {
        pkLot.leave(4);
        int slotId = pkLot.getFreeSlot();
        assertEquals(4, slotId);

        System.out.println("Slot number " + slotId + " is free");

        Map<Integer, Car> slotCarMap = pkLot.getParkinglotStatus();

        System.out.println("Slot No" + "\t" + "Registration No." + "\t" + "Colour");
        for(Map.Entry<Integer, Car> e : slotCarMap.entrySet())
        {
            if(e.getValue() != null)
                System.out.println(e.getKey()+ "\t" + e.getValue().getRegistrationNumber() + "\t" + e.getValue().getColor());
        }

        //park KA-01-P-333 White
        pkLot.park(new Car("KA-01-P-333", "White"));
        slotCarMap = pkLot.getParkinglotStatus();
        assertEquals(6, slotCarMap.size());

        //park DL-12-AA-9999 White
        slotId = pkLot.park(new Car("DL-12-AA-9999", "White"));
        assertEquals(-1, slotId);
        System.out.println("Sorry, parking lot is full");

        List<Car> whiteCars = pkLot.getRegistrationNumbers("White");
        assertEquals(3, whiteCars.size());
        List<String> registrationNumbers = new ArrayList<String>();
        for(Car c : whiteCars)
        {
            registrationNumbers.add(c.getRegistrationNumber());
        }
        assertEquals(true, registrationNumbers.contains("KA-01-HH-1234"));
        assertEquals(true, registrationNumbers.contains("KA-01-HH-9999"));
        assertEquals(true, registrationNumbers.contains("KA-01-P-333"));

        for(String regNum : registrationNumbers)
        {
            System.out.print(regNum + ",");
        }

        //slot_numbers_for_cars_with_colour White
        List<Integer> whiteSlots =  pkLot.getSlots("White");
        assertEquals(3, whiteSlots.size());
        for(Integer slotNum : whiteSlots)
        {
            System.out.print(slotNum + ",");
        }

        //slot_number_for_registration_number KA-01-HH-3141
        assertEquals(6, pkLot.getSlotForCar("KA-01-HH-3141"));

        //slot_number_for_registration_number KA-01-HH-3141
        Integer s = pkLot.getSlotForCar("MH-04-AY-1111");
        assertEquals(-1, s.intValue());

        System.out.println("Not found");

    }


}
