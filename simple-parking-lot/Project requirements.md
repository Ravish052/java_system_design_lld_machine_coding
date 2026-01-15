> 1. parking lot should have parking lot ID, number of floors
> and number of slots on each floor
>  2. each slot has a type of vehicle . you can decide any type from car, bike, truck. you can also decide slot for each type
> 3. when a vehicle pulls into the parking lot, the app takes the type, registration number, color. aslo additional details can be stored
> 4. any slot selection strategy >> lowest floor >> earliest available slot
> 5. app should return a ticket in form of string. PR123_2_5
> 6. to unpark, a valid ticket is needed.
> 7. should display available slots and available slots for each type.
> 
> 
> we will have separate classes representing our entities.
>   Vehicle and Slot
>   ParkingLot class which contains core business logic
> 
> vehicle Class >
>    type
> registration, color
> 
> Slot class >
>   type, vehicle, ticketId
> 
> ParkingLot>
>   parkingLotId string , slots(List<list>)
> 
> Constructor: ParkingLot(parkingLotId, nfloors, noOfSlotsPerFlr)
> 
> Methods:
>

    parkVehicle(type, regNo, color): takes all the parameters of a vehicle, assigns a slot, and returns the ticket

    unPark(ticketId): takes the ticket id and removes the vehicle from the slot

    getNoOfOpenSlots(type): returns the number of slots for vehicle type

    displayOpenSlots(type): displays all open slots for vehicle type

    displayOccupiedSlots(type): displays all occupied slots for vehicle type

> 