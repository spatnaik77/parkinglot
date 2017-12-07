package com.siddharth.parkinglot;

import com.siddharth.parkinglot.bo.Car;
import com.siddharth.parkinglot.bo.Slot;

import java.util.*;

/**
 * Created by sr250345 on 12/7/17.
 */
public class ParkingLot implements IParkingLot{

    private TreeMap<Integer, Car> slotCarMap;
    private Map<String, List<Car>> colorCarListMap;
    private Map<String, Slot> registrationNumberSlotMap;

    int maxSize;

    public ParkingLot(int maxSize)
    {
        this.maxSize = maxSize;

        slotCarMap = new TreeMap<Integer, Car>();

        /*slotCarMap = new TreeMap<Slot, Car>(new Comparator<Slot>() {
            public int compare(Slot o1, Slot o2) {
                return o1.getId() < o2.getId() ? 0: 1;
            }
        });*/

        colorCarListMap = new HashMap<String, List<Car>>();

        registrationNumberSlotMap = new HashMap<String, Slot>();
    }

    public void createParkingLot(int numOfSlots) {

        for(int c = 1; c <= numOfSlots; c++)
        {
            slotCarMap.put(c, null);
        }
    }

    public Slot park(Car c) {
        int position = getFreeSlot();
        Slot s = new Slot(position);
        slotCarMap.put( position, c);
        return s;
    }

    public Slot leave(int slotId) {
        return null;
    }

    public int getFreeSlot()
    {
        int c = 1;
        boolean found = false;
        for(Map.Entry<Integer, Car> e : slotCarMap.entrySet())
        {
            if(e.getValue() == null)
            {
                found = true;
                break;
            }
            else {
                c++;
            }
        }
        if(found)
        {
            return c;
        }
        else
        {
            return -1;
        }
    }

    public Map<Integer, Car> getParkinglotStatus()
    {
        return this.slotCarMap;
    }

    public List<Car> getRegistrationNumbers(String color) {
        return null;
    }

    public Slot getSlotForCar(String registrationNumber) {
        return null;
    }

    public List<Slot> getSlots(String color) {
        return null;
    }
}
