package com.siddharth.parkinglot;

import com.siddharth.parkinglot.bo.Car;
import com.siddharth.parkinglot.bo.Slot;
import com.siddharth.parkinglot.exception.ParkingNotAvailableException;

import java.util.*;

/**
 * Created by Siddharth on 12/7/17.
 */
public class ParkingLotImpl implements IParkingLot{

    private Map<Slot, Car> slotCarMap;
    private Map<String, List<Car>> colorCarListMap;
    private Map<String, Slot> registrationNumberSlotMap;


    ParkingLotImpl()
    {
        slotCarMap = new HashMap<Slot, Car>();

        colorCarListMap = new HashMap<String, List<Car>>();

        registrationNumberSlotMap = new HashMap<String, Slot>();
    }

    public void createParkingLot(int numOfSlots) {

        for(int c = 1; c <= numOfSlots; c++)
        {
            slotCarMap.put(new Slot(c), null);
        }
    }

    public int park(Car c) throws ParkingNotAvailableException {
        //Get the free slot nearest to entrance
        int slotId = getFreeSlot();
        if(slotId > 0)
        {
            Slot s = new Slot(slotId);
            slotCarMap.put(s, c);
            addToColorCarListMap(c);
            addToRegistrationNumberSlotMap(c, s);
        }
        else
        {
            throw new ParkingNotAvailableException("Parking full");
        }

        //id is -1 when the parkinglot is full
        return slotId;
    }

    public int leave(int slotId)
    {
        Car c = slotCarMap.get(new Slot(slotId));
        slotCarMap.put(new Slot(slotId), null);
        removeFromColorCarListMap(c);
        removeFromRegistrationNumberSlotMap(c);
        return slotId;
    }

    public int getFreeSlot()
    {
        int retVal = -1;
        for(Map.Entry<Slot, Car> e : slotCarMap.entrySet())
        {
            if(e.getValue() == null)
            {
                retVal = e.getKey().getId();
                break;
            }
        }
        return retVal;

    }

    public Map<Slot, Car> getParkinglotStatus()
    {
        return this.slotCarMap;
    }

    public List<Car> getRegistrationNumbers(String color) {

        return colorCarListMap.get(color);
    }

    public Slot getSlotForCar(String registrationNumber) {
        if(registrationNumberSlotMap.containsKey(registrationNumber))
            return registrationNumberSlotMap.get(registrationNumber);
        else
            return null;
    }

    public List<Slot> getSlots(String color) {
        List<Car> cars = colorCarListMap.get(color);
        List<Slot> slots = new ArrayList<Slot>();
        for(Car c : cars)
        {
            Slot slot = registrationNumberSlotMap.get(c.getRegistrationNumber());
            slots.add(slot);
        }
        return slots;
    }

    private void addToColorCarListMap(Car car)
    {
        String color = car.getColor();
        List<Car> cars = colorCarListMap.get(color);
        if(cars == null)
        {
            cars = new ArrayList<Car>();
            cars.add(car);
            colorCarListMap.put(color, cars);
        }
        else
        {
            cars.add(car);
        }
    }
    private void removeFromColorCarListMap(Car car)
    {
        String color = car.getColor();
        List<Car> cars = colorCarListMap.get(color);
        if(cars == null)
        {

        }
        else
        {
            cars.remove(car);
        }
    }

    private void addToRegistrationNumberSlotMap(Car car, Slot s)
    {
        registrationNumberSlotMap.put(car.getRegistrationNumber(), s);
    }

    private void removeFromRegistrationNumberSlotMap(Car car)
    {
        registrationNumberSlotMap.remove(car.getRegistrationNumber());
    }
}
