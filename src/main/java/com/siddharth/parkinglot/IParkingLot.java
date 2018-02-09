package com.siddharth.parkinglot;

import com.siddharth.parkinglot.bo.Car;
import com.siddharth.parkinglot.bo.Slot;
import com.siddharth.parkinglot.exception.ParkingNotAvailableException;

import java.util.List;
import java.util.Map;

/**
 * Created by Siddharth on 12/7/17.
 */

/**
 * Interface for managing the parking lot
 */
public interface IParkingLot {

    /**
     * Creates a parking lot with the given number of slots
     * @param numOfSlots
     */
    public void createParkingLot(int numOfSlots);

    /**
     * Calculates the nearest free slot from entrance and returns the slot number
     * @param c
     * @return  -1 when the parking slot is full
     */
    public int park(Car c) throws ParkingNotAvailableException;

    /**
     * Frees up a slot and returns the slot number
     * @param slotId
     * @return
     */
    public int leave(int slotId);

    /**
     *
     * @return  a Map containing the Slot and corresponding Cars. For the entries where the value (car) is null is considered
     * as free
     */
    public Map<Slot, Car> getParkinglotStatus();

    /**
     * returns list of occupied cars for the given color
     * @param color
     * @return
     */
    public List<Car> getRegistrationNumbers(String color);

    /**
     * Returns the Slot where the car is parked
     * @param registrationNumber
     * @return
     */
    public Slot getSlotForCar(String registrationNumber);

    /**
     * Returns the list of Slots where the cars of the given color is parked
     * @param color
     * @return
     */
    public List<Slot>  getSlots(String color);
}
