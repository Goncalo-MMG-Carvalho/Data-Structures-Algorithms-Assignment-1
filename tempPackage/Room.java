package tempPackage;

import dataStructures.Iterator;

import java.io.*;

/**
 * <p>
 * 	An interface representing a university room<br>
 * 	Extends Serializable
 * </p>
 * <p>An implementation of this interface must be able to hold all the active requests
 *  to a rooom</p>
 *  @see Serializable
 * @author Goncalo Carvalho 61605 gmm.carvalho@gmail.com
 * @author Augusto Mateus 	61626 aj.mateus@campus.fct.unl.pt
 *
 */
public interface Room extends Serializable {
	
	/**
	 * constant of state of the room
	 */
	public static final String OCUPIED = "ocupado";
	/**
	 * constant of state of the room
	 */
	public static final String FREE = "livre";
	
	/**
	 * @return the login of the manager in charge of the room
	 */
	String getManagerLogin();

	/**
	 * @return 	true - if there is any active request to the room<br>
	 * 			false - otherwise
	 */
	boolean hasRequests();

	/**
	 * Changes the state of the room to <code>roomState</code>, if <code>roomState</code>
	 * isnt a valid state, the state doesnt change
	 * @param roomState - state to change into
	 */
	void changeState(String roomState);

	/**
	 * @return 	true - if the room is unocupied
	 * 			false - otherwise
	 */
	boolean isFree();

	/**
	 * adds a request to the room to the <code>Request</code> collection
	 * @param req - request to add
	 */
	void addRequest(Request req);
 
	/**
	 * @return The ID/code of the room
	 */
	String getRoomID();

	/**
	 * A string with the state of the room
	 * @return	Room.FREE - if isFree()<br>
	 * 			Room.OCUPIED - otherwise
	 */
	String getState();

	/**
	 * @return The description of the room
	 */
	String getRoomDescription();

	/**
	 * @return The name of the university
	 */
	String getUniversityName();

	/**
	 * @return the name of the residence
	 */
	String getResidenceName();

	/**
	 * @return the name of the location
	 */
	String getLocationName();

	/**
	 * @return the number of the floor of the room
	 */
	int getFloor();

	/**
	 * changes the state of the room to ocupied
	 */
	void occupyRoom();

	/**
	 * Removes all the requests to the room
	 * @return An <code>Iterator</code> with all the request removed
	 * @see Iterator
	 */
	Iterator<Request> removeAllRequests();

	/**
	 * @return An <code>Iterator</code> with all the request to the room
	 * @see Iterator
	 */
	Iterator<Request> listRoomRequests();

	/**
	 * Remove a request from the requests to the room
	 * @param req - request to remove
	 */
	void removeRequest(Request req);
}
