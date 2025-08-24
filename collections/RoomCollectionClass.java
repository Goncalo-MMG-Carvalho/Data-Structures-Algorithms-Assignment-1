package collections;

import dataStructures.*;
import tempPackage.*;

/**
 * Implements the <code>RoomCollection</code> interface
 * @see RoomCollection
 * @author Goncalo Carvalho 61605 gmm.carvalho@gmail.com
 * @author Augusto Mateus 	61626 aj.mateus@campus.fct.unl.pt
 *
 */
public class RoomCollectionClass implements RoomCollection {
	
	/**
	 * data structure that holds the rooms
	 * @see dataStructures.List
	 */
    private final List<Room> rooms;

    static final long serialVersionUID= 0L;

    /**
     * <code>RoomCollectionClass</code> constructor
     */
    public RoomCollectionClass() {
        rooms = new DoubleList<>();
    }

    @Override
    public void addRooms(String roomID, String residenceName, String university,
    		Location location, int floor, String description, String managerLogin) {
        rooms.addLast(new RoomClass(roomID, residenceName, managerLogin, university, location, floor, description));
    }

    @Override
    public Room findRoom(String roomID) {

        Iterator<Room> roomIterator = rooms.iterator();

        while (roomIterator.hasNext()) {
            Room room = roomIterator.next();
            if (room.getRoomID().equalsIgnoreCase(roomID))
                return room;
        }

        return null;
    }

    @Override
    public void removeRoom(Room room) {
        rooms.remove(room);
    }
}
