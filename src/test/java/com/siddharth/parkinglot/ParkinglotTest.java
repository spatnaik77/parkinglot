package com.siddharth.parkinglot;

import com.siddharth.parkinglot.bo.Car;
import com.siddharth.parkinglot.bo.Slot;
import com.siddharth.parkinglot.exception.ParkingNotAvailableException;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by sr250345 on 12/8/17.
 */
public class ParkinglotTest {

    @Test
    public void testParkLeave()
    {
        ParkingLotImpl pkLot = new ParkingLotImpl();
        pkLot.createParkingLot(10);

        for(int c = 1; c <= 10; c++)
        {
            try
            {
                pkLot.park(new Car("KA-10-ME-" + c, "White"));

            } catch (ParkingNotAvailableException e)
            {
                e.printStackTrace();
            }
        }

        //Now try to park 11th car and it should say parking full
        try
        {
            pkLot.park(new Car("KA-10-ME-" + 11, "White"));

        } catch (Exception e)
        {
            assertTrue(e instanceof ParkingNotAvailableException);

        }
        //Leave at 1,3,5 slots
        pkLot.leave(5);
        pkLot.leave(1);
        pkLot.leave(3);

        //Now park 3 cars
        try {
            int slotId = slotId = pkLot.park(new Car("KA-10-ME-" + 1, "White"));
            assertEquals(1, slotId);

            slotId = pkLot.park(new Car("KA-10-ME-" + 3, "White"));
            assertEquals(3, slotId);

            slotId = pkLot.park(new Car("KA-10-ME-" + 5, "White"));
            assertEquals(5, slotId);
        }
        catch (ParkingNotAvailableException e)
        {
           e.printStackTrace();

        }
    }

    @Test
    public void testGetCarsByColor()
    {
        ParkingLotImpl pkLot = new ParkingLotImpl();
        pkLot.createParkingLot(15);

        for(int c = 1; c <= 10; c++)
        {
            try
            {
                pkLot.park(new Car("KA-10-ME-" + c, "White"));
            }
            catch (ParkingNotAvailableException e)
            {
                e.printStackTrace();

            }
        }
        for(int c = 11; c <= 15; c++)
        {
            try
            {
                pkLot.park(new Car("KA-10-ME-" + c, "Black"));
            }
            catch (ParkingNotAvailableException e)
            {
                e.printStackTrace();

            }
        }
        List<Car> whiteCars = pkLot.getRegistrationNumbers("White");
        assertEquals(10, whiteCars.size());

        List<Car> blackCars = pkLot.getRegistrationNumbers("Black");
        assertEquals(5, blackCars.size());
    }

    @Test
    public void testGetSlotForCar()
    {
        ParkingLotImpl pkLot = new ParkingLotImpl();
        pkLot.createParkingLot(10);

        for(int c = 1; c <= 10; c++)
        {
            try
            {
                pkLot.park(new Car("KA-10-ME-" + c, "White"));
            }
            catch (ParkingNotAvailableException e)
            {
                e.printStackTrace();

            }
        }

        assertEquals(1, pkLot.getSlotForCar("KA-10-ME-1").getId());
        assertEquals(5, pkLot.getSlotForCar("KA-10-ME-5").getId());
        assertEquals(10, pkLot.getSlotForCar("KA-10-ME-10").getId());
    }

    @Test
    public void testGetSlots()
    {
        ParkingLotImpl pkLot = new ParkingLotImpl();
        pkLot.createParkingLot(5);

        for(int c = 1; c <= 5; c++)
        {
            try
            {
                pkLot.park(new Car("KA-10-ME-" + c, "White"));
            }
            catch (ParkingNotAvailableException e)
            {
                e.printStackTrace();

            }
        }
        List<Slot> whiteSlots =  pkLot.getSlots("White");
        assertEquals(5, whiteSlots.size());

        assertTrue(whiteSlots.contains(new Slot(1)));
        assertTrue(whiteSlots.contains(new Slot(2)));
        assertTrue(whiteSlots.contains(new Slot(3)));
        assertTrue(whiteSlots.contains(new Slot(4)));
        assertTrue(whiteSlots.contains(new Slot(5)));

        assertFalse(whiteSlots.contains(new Slot(10)));
    }

}
