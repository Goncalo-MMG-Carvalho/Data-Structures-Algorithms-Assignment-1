package tempPackage;

import dataStructures.*;
import exceptions.*;

import java.io.*;

/**
 * An interface representing the UniBedrooms application<br>
 * Extends Serializable
 * <p>
 *  An implementation of this interface must be able to hold all <code>Room</code>s and 
 *  <code>User</code>s that are created by it
 * </p>
 * 
 * @see User
 * @see Room
 * @see Serializable
 * @author Goncalo Carvalho 61605 gmm.carvalho@gmail.com
 * @author Augusto Mateus 	61626 aj.mateus@campus.fct.unl.pt
 *
 */
public interface UniBedroomsManager extends Serializable {

	/**
	 * Creates a <code>Student</code> and stores him
	 * @param studentLogin - login of the student
	 * @param studentName - name of the student
	 * @param age - age of the student
	 * @param locationName - name of the location of the student
	 * @param universityName - name of the university of the student
	 * @throws UserAlreadyExistsException If there already exists a <code>User</code> with
	 * 			the login <code>studentLogin</code>
	 * @see Student
	 * @see User
	 */
    void createStudent(String studentLogin, String studentName, int age, String locationName, 
    		String universityName) throws UserAlreadyExistsException;

    /**
     * @param studentLogin - login of the student
     * @return A string with the information regarding the student
     * @throws StudentDoesntExistException If there doesnt exist a <code>Student</code> with
	 * 			the login <code>studentLogin</code>
     */
    String getStudentInfo(String studentLogin) throws StudentDoesntExistException;

    /**
     * Creates a <code>Manager</code> and stores him
     * @param managerLogin - log in of the manager
     * @param managerName - name of the manager
     * @param universityName - name of the university of the manager
     * @throws UserAlreadyExistsException - If there already exists a <code>User</code> with
	 * 			the login <code>managerLogin</code>
     */
    void createManager(String managerLogin, String managerName, String universityName) 
    		throws UserAlreadyExistsException;

    /**
     * 
     * @param managerLogin - log in of the manager
     * @return A string with the information regarding the manager
     * @throws ManagerDoesntExistException If there doesnt exist a <code>Manager</code> with
	 * 			the login <code>managerLogin</code>
     */
    String getManagerInfo(String managerLogin) throws ManagerDoesntExistException;

    /**
     * Creates <code>Room</code> and stores it
     * @param roomCode - ID/code of the room
     * @param managerLogin - log in of the manager of the room
     * @param redidenceName - name of the residence of the room
     * @param universityName - name of the university of the room
     * @param locationName - name of the location of the room
     * @param floor - floor of the room
     * @param roomDescription - description of the room
     * @throws RoomAlreadyExistsException If a <code>Room</code> with the ID/code
     * 			<code>roomCode</code> already exists
     * @throws ManagerDoesntExistException If there doesnt exist a <code>Manager</code> with
	 * 			the login <code>managerLogin</code>
     * @throws DifferentUniversityExeption If the name of the university of the <code>Manager</code> 
     * 			with the login <code>managerLogin</code> isnt the same as <code>universityName</code>
     */
    void createRoom(String roomCode, String managerLogin, String redidenceName, String universityName,
    		String locationName, int floor, String roomDescription) throws RoomAlreadyExistsException, 
			ManagerDoesntExistException, DifferentUniversityExeption;

    /**
     * @param roomCode - ID/code of the room to get info from
     * @return A string with the information regarding the room
     * @throws RoomDoesntExistException If a <code>Room</code> with the ID/code
     * 			<code>roomCode</code> doesnt exist
     */
    String getRoomInfo(String roomCode) throws RoomDoesntExistException;

    /**
     * Changes the state of a room
     * @param roomCode - ID/code of the room to change
     * @param managerLogin - login of the manager of the room to change
     * @param roomStatus - new state of the room to change
     * @throws DifferentManagerException If the <code>Manager</code> with the login
     * 			<code>managerLogin</code> isnt the manager of the <code>Room</code> with 
     * 			the ID/code <code>roomCode</code>
     * @throws RoomDoesntExistException If a <code>Room</code> with the ID/code
     * 			<code>roomCode</code> doesnt exist
     * @throws ActiveRequestsException If the room has active <code>Request</code>s to it
     */
    void changeRoomState(String roomCode, String managerLogin, String roomStatus) 
    		throws DifferentManagerException, RoomDoesntExistException, ActiveRequestsException;

    /**
     * Removes a <code>Room</code>
     * @param roomCode - ID/code of the room to remove 
     * @param managerLogin - login of the manager of the room
     * @throws RoomDoesntExistException  If a <code>Room</code> with the ID/code
     * 			<code>roomCode</code> doesnt exist
     * @throws DifferentManagerException If the <code>Manager</code> with the login
     * 			<code>managerLogin</code> isnt the manager of the <code>Room</code> with 
     * 			the ID/code <code>roomCode</code>
     * @throws ActiveRequestsException If the room has active <code>Request</code>s to it
     */
    void removeRoom(String roomCode, String managerLogin) 
    		throws RoomDoesntExistException, DifferentManagerException, ActiveRequestsException;

    /**
     * Creates <code>Request</code> and stores it in the room the request was made to
     * and stores in the student that made the request
     * @param studentLogin - login of the student that makes the request
     * @param roomCode - ID/code of the room the request was made too
     * @throws StudentDoesntExistException If there doesnt exist a <code>Student</code> with
	 * 			the login <code>studentLogin</code>
     * @throws ExceededNumberOfRequestsException If the student that made the request already has
     * 			10 active requests
     * @throws RoomDoesntExistException If a <code>Room</code> with the ID/code
     * 			<code>roomCode</code> doesnt exist
     * @throws RoomIsOcupiedException If the room is already ocupied/ isnt free
     * @throws RequestAlreadyExistsException If the student already has made a request to
     * 			the room
     */
    void createRequest(String studentLogin, String roomCode) 
    		throws StudentDoesntExistException, ExceededNumberOfRequestsException, 
    		RoomDoesntExistException, RoomIsOcupiedException, RequestAlreadyExistsException;

    /**
     * Accepts a <code>Request</code> to a <code>Room</code>
     * @param roomID - ID/code of the room
     * @param managerLogin - login of the manager of the room
     * @param studentLogin - login of the student that made the request
     * @throws StudentDoesntExistException If there doesnt exist a <code>Student</code> with
	 * 			the login <code>studentLogin</code>
     * @throws RoomDoesntExistException If a <code>Room</code> with the ID/code
     * 			<code>roomCode</code> doesnt exist
     * @throws DifferentManagerException If the <code>Manager</code> with the login
     * 			<code>managerLogin</code> isnt the manager of the <code>Room</code> with 
     * 			the ID/code <code>roomCode</code>
     * @throws RequestDoesntExistException If the <code>Student</code> with
	 * 			the login <code>studentLogin</code> didnt made a request to the 
	 * 			<code>Room</code> with the ID/code <code>roomCode</code>
     */
    void acceptRequest(String roomID, String managerLogin, String studentLogin) 
    		throws StudentDoesntExistException, RoomDoesntExistException, DifferentManagerException, RequestDoesntExistException;

    /**
     * @param roomCode - ID/code of the room
     * @param managerLogin - login of the manager of the room
     * @return Returns an <code>Iterator</code> with all the active requests to the room
     * @throws RoomDoesntExistException If a <code>Room</code> with the ID/code
     * 			<code>roomCode</code> doesnt exist
     * @throws DifferentManagerException If the <code>Manager</code> with the login
     * 			<code>managerLogin</code> isnt the manager of the <code>Room</code> with 
     * 			the ID/code <code>roomCode</code>
     * @throws NoRequestsException If the <code>Room</code> with the ID/code 
     * 			<code>roomCode</code> doesnt have any active <code>Request</code>s to it
     */
    Iterator<Request> listRoomRequests(String roomCode, String managerLogin) throws RoomDoesntExistException, DifferentManagerException, NoRequestsException;
}
