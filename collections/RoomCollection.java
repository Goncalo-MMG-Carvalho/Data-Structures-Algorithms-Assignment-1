package collections;

import tempPackage.*;

import java.io.*;

/**
 * A collection of <code>Room</code> with the necessary methods<br>
 * Extends Serializable </p>
 * A substitute to using the data structures directly, that facilitates the eventual
 * change of data structures needed for phase 2
 * @see Serializable
 * @author Goncalo Carvalho 61605 gmm.carvalho@gmail.com
 * @author Augusto Mateus 	61626 aj.mateus@campus.fct.unl.pt
 *
 */
public interface RoomCollection extends Serializable {

	/**
	 * Makes a new <code>Room</code> and adds it to the Collection
	 * @param roomID - ID of the room to add
	 * @param residenceName - name of the residence of the room to add
	 * @param university - name of the university of the room to add
	 * @param location - <code>Location</code> of the room to add
	 * @param floor - number of the floor of the room to add
	 * @param description - description of the room to add
	 * @param managerLogin - login of the manager managing the room to add
	 */
    void addRooms(String roomID, String residenceName, String university, Location location, int floor, String description, String managerLogin);

    /**
     * Finds the first <code>Room</code> with the code <code>roomId</code>
	 * @param roomID - code of the room to find
	 * @return 
	 * 	Return the <code>Room</code> with code <code>roomId</code><br>
	 *  Or <br> 
	 *  if there is no such room in the collection returns null
	 */
    Room findRoom(String roomID);

    /**
     * Removes <code>room</code> from the Collection,  does nothing if <code>room</code> doesnt exist in the Collection
     * @param room - room to remove
     */
    void removeRoom(Room room);
}
