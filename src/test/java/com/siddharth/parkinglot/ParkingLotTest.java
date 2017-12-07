package com.siddharth.parkinglot;

import com.siddharth.parkinglot.bo.Car;
import com.siddharth.parkinglot.bo.Slot;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by sr250345 on 12/7/17.
 */
public class ParkingLotTest {

    static ParkingLot pkLot;
    static int numSlots = 6;

    @BeforeClass
    public static void setup()
    {
        pkLot = new ParkingLot(numSlots);
    }


    @Test
    public void park()
    {
        pkLot.createParkingLot(6);
        System.out.println("Created a parking lot with " + numSlots + " slots");

        Slot s = pkLot.park(new Car("KA-01-HH-1234", "White"));
        assertEquals(1, s.getId());
        System.out.println("Allocated slot number:" + s.getId());

        s = pkLot.park(new Car("KA-01-HH-9999", "White"));
        assertEquals(2, s.getId());
        System.out.println("Allocated slot number:" + s.getId());

        s = pkLot.park(new Car("KA-01-BB-0001", "Black"));
        assertEquals(3, s.getId());
        System.out.println("Allocated slot number:" + s.getId());

        s = pkLot.park(new Car("KA-01-HH-7777", "Red"));
        assertEquals(4, s.getId());
        System.out.println("Allocated slot number:" + s.getId());

        s = pkLot.park(new Car("KA-01-HH-2701", "Blue"));
        assertEquals(5, s.getId());
        System.out.println("Allocated slot number:" + s.getId());

        s = pkLot.park(new Car("KA-01-HH-3141", "Black"));
        assertEquals(6, s.getId());
        System.out.println("Allocated slot number:" + s.getId());
    }

    /*@Test
    public void leave()
    {
        pkLot.leave(4);
        int slotId = pkLot.getFreeSlot();
        assertEquals(4, slotId);

        System.out.println("Slot number " + slotId + " is free");
    }

    @Test
    public void status()
    {
        Map<Slot, Car> slotCarMap = pkLot.getParkinglotStatus();
        assertEquals(5, slotCarMap.size());

        System.out.println("Slot No" + "\t" + "Registration No." + "\t" + "Colour");
        for(Map.Entry<Slot, Car> e : slotCarMap.entrySet())
        {
            System.out.println(e.getKey().getId() + "\t" + e.getValue().getRegistrationNumber() + "\t" + e.getValue().getColor());
        }

        //park KA-01-P-333 White
        Slot s = pkLot.park(new Car("KA-01-P-333", "White"));
        assertNotNull(s);

        //park DL-12-AA-9999 White
        s = pkLot.park(new Car("DL-12-AA-9999", "White"));
        assertEquals(null, s);
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
        List<Slot> whiteSlots =  pkLot.getSlots("White");
        assertEquals(3, whiteSlots.size());
        for(Slot slot : whiteSlots)
        {
            System.out.print(slot.getId() + ",");
        }

        //slot_number_for_registration_number KA-01-HH-3141
        s = pkLot.getSlotForCar("KA-01-HH-3141");
        assertEquals(6, s.getId());

        //slot_number_for_registration_number KA-01-HH-3141
        s = pkLot.getSlotForCar("MH-04-AY-1111");
        assertEquals(null, s.getId());
        System.out.println("Not found");

    }*/


}
