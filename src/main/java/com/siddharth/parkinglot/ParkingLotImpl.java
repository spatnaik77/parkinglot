package com.siddharth.parkinglot;

import com.siddharth.parkinglot.bo.Car;
import com.siddharth.parkinglot.bo.Slot;

import java.util.*;

/**
 * Created by Siddharth on 12/7/17.
 */
public class ParkingLotImpl implements IParkingLot{

    private TreeMap<Integer, Car> slotCarMap;
    private Map<String, List<Car>> colorCarListMap;
    private Map<String, Integer> registrationNumberSlotMap;


    public ParkingLotImpl()
    {
        slotCarMap = new TreeMap<Integer, Car>();

        /*slotCarMap = new TreeMap<Slot, Car>(new Comparator<Slot>() {
            public int compare(Slot o1, Slot o2) {
                return o1.getId() < o2.getId() ? 0: 1;
            }
        });*/

        colorCarListMap = new HashMap<String, List<Car>>();

        registrationNumberSlotMap = new HashMap<String, Integer>();
    }

    public void createParkingLot(int numOfSlots) {

        for(int c = 1; c <= numOfSlots; c++)
        {
            slotCarMap.put(c, null);
        }
    }

    public int park(Car c) {
        int slotId = getFreeSlot();
        if(slotId > 0)
        {
            slotCarMap.put(slotId, c);
            addToColorCarListMap(c);
            addToRegistrationNumberSlotMap(c, slotId);
        }

        //id is -1 when the parkinglot is full
        return slotId;
    }

    public int leave(int slotId)
    {
        Car c = slotCarMap.get(slotId);
        slotCarMap.put(slotId, null);
        removeFromColorCarListMap(c);
        removeFromRegistrationNumberSlotMap(c);
        return slotId;
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

        return colorCarListMap.get(color);
    }

    public int getSlotForCar(String registrationNumber) {
        if(registrationNumberSlotMap.containsKey(registrationNumber))
            return registrationNumberSlotMap.get(registrationNumber);
        else
            return -1;
    }

    public List<Integer> getSlots(String color) {
        List<Car> cars = colorCarListMap.get(color);
        List<Integer> slots = new ArrayList<Integer>();
        for(Car c : cars)
        {
            int slotId = registrationNumberSlotMap.get(c.getRegistrationNumber());
            slots.add(slotId);
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

    private void addToRegistrationNumberSlotMap(Car car, int slot)
    {
        registrationNumberSlotMap.put(car.getRegistrationNumber(), slot);
    }
    private void removeFromRegistrationNumberSlotMap(Car car)
    {
        registrationNumberSlotMap.remove(car.getRegistrationNumber());
    }
}
