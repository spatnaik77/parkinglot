package com.siddharth.parkinglot;

import com.siddharth.parkinglot.bo.Car;
import com.siddharth.parkinglot.bo.Slot;

import java.util.List;
import java.util.Map;

/**
 * Created by Siddharth on 12/7/17.
 */
public interface IParkingLot {

    public void createParkingLot(int numOfSlots);

    public int park(Car c);

    public int leave(int slotId);

    public int getFreeSlot();

    public Map<Integer, Car> getParkinglotStatus();

    public List<Car> getRegistrationNumbers(String color);

    public int getSlotForCar(String registrationNumber);

    public List<Integer>  getSlots(String color);
}
