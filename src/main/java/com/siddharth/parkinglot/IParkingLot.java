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

    public Slot park(Car c);

    public Slot leave(int slotId);

    public int getFreeSlot();

    public Map<Integer, Car> getParkinglotStatus();

    public List<Car> getRegistrationNumbers(String color);

    public Slot getSlotForCar(String registrationNumber);

    public List<Slot>  getSlots(String color);
}
