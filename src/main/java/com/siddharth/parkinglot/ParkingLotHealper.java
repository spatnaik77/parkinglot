package com.siddharth.parkinglot;

import com.siddharth.parkinglot.bo.Car;
import com.siddharth.parkinglot.bo.Slot;
import com.siddharth.parkinglot.exception.ParkingNotAvailableException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by sr250345 on 12/8/17.
 */
public class ParkingLotHealper {

    static IParkingLot parkingLot;
    public static void main(String[] args)
    {
        parkingLot = new ParkingLotImpl();

        //file input
        if(args.length > 0)
        {
            String fileName = args[0];
            System.out.println("********************* Going to execute the file input*********************");
            processFileInput(fileName);
        }
        else
        {
            System.out.println("==============================================================================");
            System.out.println("********************* WELCOME TO PARKING LOT CONSOLE *********************");
            System.out.println("==============================================================================");
            printAvailableCommands();

            Scanner scanner = new Scanner(System.in);
            while (true) {
                String commandln = scanner.nextLine();
                processCommand(commandln);
            }
        }
    }
    private static void printAvailableCommands()
    {
        System.out.println("Available commands:" );
        System.out.println("\t help");
        System.out.println("\t create_parking_lot <slots>");
        System.out.println("\t park <RegistrationNumber> <Color>");
        System.out.println("\t leave <slot>");
        System.out.println("\t status");
        System.out.println("\t registration_numbers_for_cars_with_colour <color>");
        System.out.println("\t slot_numbers_for_cars_with_colour <color>");
        System.out.println("\t slot_number_for_registration_number <registrationNumber>");
    }
    private static void create_parking_lot(int nrOfSlots)
    {
        parkingLot.createParkingLot(nrOfSlots);
        System.out.println("Created a parking lot with " + nrOfSlots + " slots");
    }
    private static void park(String registrationNumber, String color)
    {
        try
        {
            int slotId = parkingLot.park(new Car(registrationNumber, color));
            System.out.println("Allocated slot number: " + slotId);
        }
        catch (ParkingNotAvailableException e)
        {
            System.out.println("Sorry, parking lot is full");
        }
    }
    private static void leave(int slotId)
    {
        parkingLot.leave(slotId);
        System.out.println("Slot number " + slotId + " is free");
    }
    private static void status()
    {
        Map<Slot, Car> slotCarMap = parkingLot.getParkinglotStatus();

        System.out.println("Slot No" + "\t" + "Registration No." + "\t" + "Colour");
        for(Map.Entry<Slot, Car> e : slotCarMap.entrySet())
        {
            if(e.getValue() != null)
            {
                System.out.println(e.getKey().getId() + "\t" + e.getValue().getRegistrationNumber() + "\t" + e.getValue().getColor());
            }
        }
    }
    private static void registration_numbers_for_cars_with_colour(String color)
    {
        List<Car> whiteCars = parkingLot.getRegistrationNumbers(color);
        List<String> registrationNumbers = new ArrayList<String>();
        for(Car c : whiteCars)
        {
            registrationNumbers.add(c.getRegistrationNumber());
        }
        StringBuffer sb = new StringBuffer();
        for(int c = 0; c < registrationNumbers.size(); c++)
        {
            sb.append(registrationNumbers.get(c));
            if(c != registrationNumbers.size()-1)
            {
                sb.append(", ");
            }
        }
        System.out.println(sb.toString());
    }
    private static void slot_numbers_for_cars_with_colour(String color)
    {
        List<Slot> whiteSlots =  parkingLot.getSlots(color);
        /*for(Integer slotNum : whiteSlots)
        {
            System.out.print(slotNum + ",");
        }*/
        StringBuffer sb = new StringBuffer();
        for(int c = 0; c < whiteSlots.size(); c++)
        {
            sb.append(whiteSlots.get(c).getId());
            if(c != whiteSlots.size()-1)
            {
                sb.append(", ");
            }
        }
        System.out.println(sb.toString());
    }
    private static void slot_number_for_registration_number(String registrationNumber)
    {
        Slot s = parkingLot.getSlotForCar(registrationNumber);
        if(s == null)
        {
            System.out.println("Not found");
        }
        else
        {
            System.out.println(s.getId());
        }
    }
    private static void processCommand(String commandln)
    {
        String[] commandInput = commandln.split(" ");
        String command = commandInput[0];
        if("help".equals(command))
        {
            printAvailableCommands();
        }
        else if("create_parking_lot".equals(command))
        {
            if(commandInput.length != 2)
            {
                System.out.println("Invalid input");
            }
            else
            {
                int nrOfSlots = Integer.parseInt(commandInput[1]);
                create_parking_lot(nrOfSlots);
            }
        }
        else if("park".equals(command))
        {
            if(commandInput.length != 3)
            {
                System.out.println("Invalid input");
            }
            else
            {
                String registrationNumber = commandInput[1];
                String color = commandInput[2];
                park(registrationNumber, color);
            }
        }
        else if("leave".equals(command))
        {
            if(commandInput.length != 2)
            {
                System.out.println("Invalid input");
            }
            else
            {
                int slotId = Integer.parseInt(commandInput[1]);
                leave(slotId);
            }
        }
        else if("status".equals(command))
        {
            if(commandInput.length != 1)
            {
                System.out.println("Invalid input");
            }
            else
            {
                status();
            }
        }
        else if("registration_numbers_for_cars_with_colour".equals(command))
        {
            if(commandInput.length != 2)
            {
                System.out.println("Invalid input");
            }
            else
            {
                String color = commandInput[1];
                registration_numbers_for_cars_with_colour(color);
            }
        }
        else if("slot_numbers_for_cars_with_colour".equals(command))
        {
            if(commandInput.length != 2)
            {
                System.out.println("Invalid input");
            }
            else
            {
                String color = commandInput[1];
                slot_numbers_for_cars_with_colour(color);
            }
        }
        else if("slot_number_for_registration_number".equals(command))
        {
            if(commandInput.length != 2)
            {
                System.out.println("Invalid input");
            }
            else
            {
                String regNo = commandInput[1];
                slot_number_for_registration_number(regNo);
            }
        }
        else
        {
            System.out.println("Invalid command");
        }

    }
    private static void processFileInput(String fileName)
    {
        BufferedReader br = null;
        FileReader fr = null;
        try {

            //br = new BufferedReader(new FileReader(FILENAME));
            fr = new FileReader(fileName);
            br = new BufferedReader(fr);

            String sCurrentLine;

            while ((sCurrentLine = br.readLine()) != null)
            {
                processCommand(sCurrentLine);
            }

        } catch (IOException e) {

            e.printStackTrace();

        } finally
        {
                try {

                if (br != null)
                    br.close();

                if (fr != null)
                    fr.close();

            } catch (IOException ex) {

                ex.printStackTrace();

            }

        }

    }

}
