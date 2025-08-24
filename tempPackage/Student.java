package tempPackage;

import dataStructures.Iterator;

import java.io.*;

/**
 * An interface, representing a student, that extends the interface User<br>
 * Extends Serializable
 * <p>An implementation of this interface must be able to hold all the active requests
 *  to a rooom</p>
 * @see User
 * @see Serializable
 * @author Goncalo Carvalho 61605 gmm.carvalho@gmail.com
 * @author Augusto Mateus 	61626 aj.mateus@campus.fct.unl.pt
 *
 */
public interface Student extends User, Serializable{

	/**
	 * @return Returns the number of active <code>Request</code> the student has
	 */
	public int numberOfRequests();

	/**
	 * Checks if the student has an active request to a room with the code <code>room</code>
	 * @param roomCode - code of the room to check
	 * @return 	true - if there the student has an active request to a room with the code <code>room</code><br>
	 * 			false - otherwise
	 */
	public boolean hasRequestToRoom(String roomCode);

	/**
	 * Adds a request to the Collection held by the student of his own requests
	 * @param req - <code>Request</code> to add
	 */
	public void addRequest(Request req);

	/**
	 * 
	 * @return The age of the student
	 */
	int getAge();

	/**
	 * Removes all the requests the student has
	 * @return An <code>Iterator</code> with all the requests removed
	 * @see Iterator
	 */
	public Iterator<Request> removeAllRequests();

	/**
	 * @return The name of the location
	 */
	String getLocationName();

	/**
	 * Removes <code>req</code> from the requests the student has active 
	 * @param req - <code>Request</code> to remove
	 */
	void removeRequest(Request req);
}
