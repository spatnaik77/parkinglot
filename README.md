# Problem Statement
I own a multi-storey parking lot that can hold up to 'n' cars at any given point in time. Each slot is given a number starting at 1 increasing with increasing distance from the entry point in steps of one. I want to create an automated ticketing system that allows my customers to use my parking lot without human intervention.

When a car enters my parking lot, I want to have a ticket issued to the driver. The ticket issuing process includes us documenting the registration number (number plate) and the colour of the car and allocating an available parking slot to the car before actually handing over a ticket to the driver (we assume that our customers are nice enough to always park in the slots allocated to them). The customer should be allocated a parking slot which is nearest to the entry. At the exit the customer returns the ticket which then marks the slot they were using as being available.

# APIs to be exposed
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

