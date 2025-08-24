package collections;

import dataStructures.*;
import tempPackage.*;

import java.io.*;

/**
 * <p>
 * 	A collection of <code>Request</code> with the necessary methods<br>
 * 	Extends Serializable 
 * </p>
 * A substitute to using the data structures directly, that facilitates the eventual
 * change of data structures needed for phase 2
 * 
 * @see Serializable
 * @author Goncalo Carvalho 61605 gmm.carvalho@gmail.com
 * @author Augusto Mateus 	61626 aj.mateus@campus.fct.unl.pt
 *
 */
public interface RequestCollection extends Serializable {
	
	/**
	 * Adds a <code>Request</code> to the end of the Collection
	 * @param req - <code>Request</code> to add
	 */
	void addRequest(Request req);
    
	/**
	 * Finds the first <code>Request</code> to the <code>Room</code> with code <code>roomId</code>
	 * @param roomID - code of the room
	 * @return 
	 * 	Return a request to the <code>Room</code> with code <code>roomId</code><br>
	 *  Or <br> 
	 *  if there is no such request in the collection returns null
	 */
	Request findRequest(String roomID);

	/**
	 * Removes all the requests in the collection
	 * @return An <code>Iterator</code> with all the requests that were removed
	 */
	Iterator<Request> removeAllRequests();
	
	/**
	 * 
	 * @return The number of requests in the Collection
	 */
	int numberOfRequests();

	/**
	 * 
	 * @return An <code>Iterator</code> with all the requests in the Collection
	 */
	Iterator<Request> iterator();

	/**
	 * Removes a <code>Request</code> from the Collection, does nothing if the request doesnt exist in the Collection
	 * @param req - <code>Request</code> to be removed
	 */
	void removeRequest(Request req);
}
