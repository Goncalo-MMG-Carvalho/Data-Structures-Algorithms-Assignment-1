package tempPackage;

import collections.*;
import dataStructures.*;

/**
 * Implements the <code>Room</code> interface
 * 
 * @see Room
 * @author Goncalo Carvalho 61605 gmm.carvalho@gmail.com
 * @author Augusto Mateus 	61626 aj.mateus@campus.fct.unl.pt
 */
public class RoomClass implements Room{
	
	/**
	 * true if the room is unocupied<br>
	 * false otherwise
	 */
	private boolean isFree;
	/**
	 * ID/code of the room
	 */
	private String roomID;
	/**
	 * name of the residence
	 */
	private String residenceName;
	/**
	 * login of the manager
	 */
	private String managerLogin;
	/**
	 * name of the university
	 */
	private String universityName;
	/**
	 * location of the room
	 */
	private Location location;
	/**
	 * number of the floor of the room 
	 */
	private int floor;
	/**
	 * description of the room
	 */
	private String roomDescription;
	/**
	 * Collection that holds the active requests to this room
	 */
	private RequestCollection requests;
	static final long serialVersionUID= 0L;
	
	/**
	 * <code>RoomClass</code> constructor
	 * @param roomCode - code/ID of the room
	 * @param residenceName - name of the residence of the room
	 * @param managerLogin - login of the manager in charge of the room
	 * @param universityName - name of the university of the room
	 * @param location - location of the room
	 * @param floor - floor of the room
	 * @param roomDescription - description of the room
	 */
	public RoomClass(String roomCode, String residenceName, String managerLogin,
			String universityName, Location location, int floor, String roomDescription) {
		
		isFree = true;
		this.roomID = roomCode;
		this.residenceName = residenceName;
		this.managerLogin = managerLogin;
		this.universityName = universityName;
		this.location = location;
		this.floor = floor;
		this.roomDescription = roomDescription;
		requests = new RequestCollectionClass();
	}

	@Override
	public String getManagerLogin() {
		
		return managerLogin;
	}

	@Override
	public boolean hasRequests() {
		
		return requests.numberOfRequests() > 0;
	}

	@Override
	public void changeState(String roomStatus) {
		
		if(roomStatus.equals(Room.OCUPIED))
			isFree = false;
		else
			if(roomStatus.equals(Room.FREE))
				isFree = true;
	}

	@Override
	public boolean isFree() {
		return isFree;
	}

	@Override
	public String getState() {
		if(isFree())
			return Room.FREE;
		return Room.OCUPIED;
	}
	
	@Override
	public void addRequest(Request req) {
		requests.addRequest(req);
		
	}

	@Override
	public String getRoomID() {
		return roomID;
	}

	@Override
	public String getResidenceName() {
		return residenceName;
	}
	
	@Override
	public String getUniversityName() {
		return universityName;
	}

	@Override
	public String getRoomDescription() {
		return roomDescription;
	}
	
	@Override
	public String getLocationName() {
		return location.getLocationName();
	}
	
	@Override
	public int getFloor() {
		return floor;
	}
	
	@Override
    public boolean equals(Object o) {
		
		if(!(o instanceof Room))
			return false;
		
		Room other = (Room) o;
		
        return getRoomID().equalsIgnoreCase(other.getRoomID());
    }

	@Override
	public String toString() {
		return String.format("%s %s\n"+"%s\n"+"%s\n"+"%d\n"+"%s\n"+"%s", 
				getRoomID(), getResidenceName(), getUniversityName(), getLocationName(),
				getFloor(), getRoomDescription(), getState());
	}
	
	@Override
	public void occupyRoom() {
		isFree = false;
	}
	
	@Override
	public Iterator<Request> removeAllRequests() {

		return requests.removeAllRequests();
	}
	
	@Override
	public Iterator<Request> listRoomRequests() {
		
		return requests.iterator();
	}

	
	@Override
	public void removeRequest(Request req) {
		
		requests.removeRequest(req);	
	}

}
