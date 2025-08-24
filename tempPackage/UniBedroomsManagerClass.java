package tempPackage;

import dataStructures.*;
import collections.*;
import exceptions.*;

/**
 * Implements the <code>UniBedroomsManager</code> interface
 * @see UniBedroomsManager
 * @author Goncalo Carvalho 61605 gmm.carvalho@gmail.com
 * @author Augusto Mateus 	61626 aj.mateus@campus.fct.unl.pt
 *
 */
public class UniBedroomsManagerClass implements UniBedroomsManager {
	
	/**
	 * Collection that holds the <code>User</code>s
	 */
	private final UserCollection users;
	/**
	 * Collection that holds the <code>Room</code>s
	 */
	private final RoomCollection rooms;

	static final long serialVersionUID= 0L;
	
	/**
	 * <code>UniBedroomsManagerClass</code> constructor
	 * @see UniBedroomsManagerClass
	 */
 	public UniBedroomsManagerClass() {

		users = new UserCollectionClass();
		rooms = new RoomCollectionClass();
    }
	
	@Override
	public void createStudent(String studentLogin, String studentName, int age,
			String locationName, String universityName) throws UserAlreadyExistsException {
		
		Location location = new LocationClass(locationName);

		users.addStudent(studentLogin, studentName, age, location, universityName);
	}

	@Override
	public String getStudentInfo(String studentLogin) 
			throws StudentDoesntExistException {
		
		return users.findStudent(studentLogin).toString();
	}

	@Override
	public void createManager(String managerLogin, String managerName, String universityName) 
			throws UserAlreadyExistsException {

		users.addManager(managerLogin, managerName, universityName);
	}

	@Override
	public String getManagerInfo(String managerLogin) throws ManagerDoesntExistException {
		
		return users.findManager(managerLogin).toString();
	}

	@Override
	public void createRoom(String roomCode, String managerLogin, String residenceName, String universityName,
    		String locationName, int floor, String roomDescription) throws RoomAlreadyExistsException, 
			ManagerDoesntExistException, DifferentUniversityExeption {
		
		Location location = new LocationClass(locationName);
		
		Room room = rooms.findRoom(roomCode);
		if(room != null)
			throw new RoomAlreadyExistsException();
		
		Manager manager = users.findManager(managerLogin);
		
		if(!manager.getUniversityName().equals(universityName)) 
			throw new DifferentUniversityExeption();
		
		rooms.addRooms(roomCode, residenceName, universityName, location, floor, roomDescription, managerLogin);
	}

	@Override
	public String getRoomInfo(String roomCode) throws RoomDoesntExistException {
		
		Room room = rooms.findRoom(roomCode);
		
		if(room == null)
			throw new RoomDoesntExistException();
		
		return room.toString();
	}

	@Override
	public void changeRoomState(String roomCode, String managerLogin, String roomStatus) 
			throws DifferentManagerException, RoomDoesntExistException, ActiveRequestsException {
		
		Room room = rooms.findRoom(roomCode);
		
		if(room == null)
			throw new RoomDoesntExistException();
		
		if(!room.getManagerLogin().equals(managerLogin))
			throw new DifferentManagerException();
		
		if(roomStatus.equals(Room.OCUPIED) && room.hasRequests())
			throw new ActiveRequestsException();
		
		room.changeState(roomStatus);
	}

	@Override
	public void removeRoom(String roomCode, String managerLogin) 
			throws RoomDoesntExistException, DifferentManagerException, ActiveRequestsException {
		
		Room room = rooms.findRoom(roomCode);
		
		if(room == null)
			throw new RoomDoesntExistException();
		
		if(!room.getManagerLogin().equals(managerLogin))
			throw new DifferentManagerException();
		
		if(room.hasRequests())
			throw new ActiveRequestsException();
		
		rooms.removeRoom(room);
	}

	@Override
	public void createRequest(String studentLogin, String roomCode) 
			throws StudentDoesntExistException, ExceededNumberOfRequestsException,
			RoomDoesntExistException, RoomIsOcupiedException, RequestAlreadyExistsException {
		
		
		
		Student student = users.findStudent(studentLogin);
		
		if(student.numberOfRequests() >= 10) //check if student has too many requests
			throw new ExceededNumberOfRequestsException();
		
		Request req = new RequestClass(student, roomCode); //Create a request
		
		Room room = rooms.findRoom(roomCode);
		
		if(room == null)
			throw new RoomDoesntExistException();
		
		if(!room.isFree()) // if the room is free to use
			throw new RoomIsOcupiedException();
		
		if(student.hasRequestToRoom(roomCode)) // if the student already made a request to room roomCode
			throw new RequestAlreadyExistsException();
		
		student.addRequest(req); 	// add the request to the student
		room.addRequest(req);	// add the request to the room
	}

	@Override
	public void acceptRequest(String roomID, String managerLogin, String studentLogin) 
			throws RoomDoesntExistException, StudentDoesntExistException, DifferentManagerException, RequestDoesntExistException  {
		
		Room room = rooms.findRoom(roomID);
		if(room == null)
			throw new RoomDoesntExistException();
		
		if(!room.getManagerLogin().equals(managerLogin))
			throw new DifferentManagerException();
        
		Student student = users.findStudent(studentLogin);
        
		if(!student.hasRequestToRoom(roomID))
			throw new RequestDoesntExistException();

        room.occupyRoom();
        Iterator<Request> requestIt = student.removeAllRequests(); // remover as candidaturas deste estudante aos varios quartos
        while(requestIt.hasNext()) { 	// remover as candidatura tb dos quartos
        	
        	Request req = requestIt.next();
        	Room tmpRoom = rooms.findRoom(req.getRoomID());
        	tmpRoom.removeRequest(req);
        }
        
        requestIt = room.removeAllRequests(); 	// remover todas as candidatura a este quarto
        while(requestIt.hasNext()) { 			// remover as candidatura tb dos estudantes
        	
        	Request req = requestIt.next();
        	student = users.findStudent(req.getStudentLogin());
        	student.removeRequest(req);
        }
        
	}

	@Override
	public Iterator<Request> listRoomRequests(String roomCode, String managerLogin) 
			throws RoomDoesntExistException, DifferentManagerException, NoRequestsException {
		
        Room room = rooms.findRoom(roomCode);
        if(room == null)
			throw new RoomDoesntExistException();
		
		if(!room.getManagerLogin().equals(managerLogin))
			throw new DifferentManagerException();
        
		Iterator<Request> it = room.listRoomRequests();
		if(!it.hasNext())
			throw new NoRequestsException();
		
        return it;
	}

}
