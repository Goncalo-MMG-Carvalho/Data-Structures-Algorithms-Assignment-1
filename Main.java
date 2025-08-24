import dataStructures.Iterator;
import exceptions.*;
import tempPackage.*;

import java.io.*;
import java.util.*;

/**
 * Main class of the UniBedrooms program 
 * <p>
 * 	this class manages, mostly, the input and output of the program
 * </p>
 * @author Goncalo Carvalho 61605 gmm.carvalho@gmail.com
 * @author Augusto Mateus 	61626 aj.mateus@campus.fct.unl.pt
 *
 */
public class Main {

	/**
	 * Constant output messages, and the name of the database file
	 */
    private static final String
	    MSG_EXIT = "Obrigado. Ate a proxima.",
	
	    MSG_USER_ALREADY_EXISTS = "Utilizador ja existente.",
	    MSG_ROOM_ALREADY_EXISTS = "Quarto existente.",
	    MSG_REQUEST_ALREADY_EXISTS = "Candidatura existente.",
	
	    MSG_STUDENT_REGISTERED = "Registo de estudante executado.",
	    MSG_MANAGER_REGISTERED = "Registo de gerente executado.",
	    MSG_ROOM_REGISTERED = "Registo de quarto executado.",
	    MSG_REQUEST_REGISTERED = "Registo de candidatura executado.",
	
	    MSG_NO_SUCH_STUDENT = "Inexistencia do estudante referido.",
	    MSG_NO_SUCH_MANAGER = "Inexistencia do gerente referido.",
	    MSG_NO_SUCH_ROOM = "Inexistencia do quarto referido.",
	    MSG_NO_SUCH_REQUEST = "Inexistencia da candidatura referida.",
	
	    MSG_NON_AUTHORIZED_OPERATION = "Operacao nao autorizada.",
	
	    MSG_ACTIVE_REQUESTS = "Candidaturas activas.",
	
	    MSG_ROOM_STATUS_UPDATED = "Estado de quarto actualizado.",
	
	    MSG_ROOM_REMOVED = "Remocao de quarto executada.",
	
	    MSG_ROOM_OCUPIED = "Quarto ocupado.",
	
	    MSG_REQUEST_ACCEPTED = "Aceitacao de candidatura executada.",
	
	    MSG_NO_REQUESTS = "Inexistencia de candidaturas.",

	    fileName = "unibedroomsdatabase";

    /**
	 * Enum with the name of the names of the  functions of the program
	 * 
	 * @author Goncalo Carvalho 61605 gmm.carvalho@gmail.com
	 * @author Augusto Mateus 	61626 aj.mateus@campus.fct.unl.pt
	 *
	 */
    private enum Command {
        NEW_STUDENT("IE"), STU_INFO("DE"), NEW_MANAGER("IG"), MAN_INFO("DG"),
        NEW_ROOM("IQ"), ROOM_INFO("DQ"), MODIFY_ROOM("MQ"), REMOVE_ROOM("RQ"),
        NEW_REQUEST("IC"), ACCEPT_REQUEST("AC"), LIST_REQUESTS("LC"),
        //LIST_ALL_ROOMS("LQ"), LIST_LOCATION_FREE("LL"),
        QUIT("XS"), UNKNOWN_COMMAND("");
    	
    	/**
    	 * name of input command
    	 */
        private final String commandInputName;
        
        /**
         * Constructor for <code>Command</code>
         * @param commandInputName - name of the input
         */
        private Command(String commandInputName) {
            this.commandInputName = commandInputName;
        }
        
        /**
         * @return the name of the input Command
         */
        public String getCommandInputName() {
            return commandInputName;
        }
    }

    /**
     * main method of the UniBedrooms program
     * @param args
     */
    @SuppressWarnings("incomplete-switch") // switch isnt incomplete, QUIT is dealt with in the while condition
    // and readCommand returns UNKNOWN_COMMAND when the command isn't valid, so no need for the default
    public static void main(String[] args) {

        UniBedroomsManager app = load();
        Scanner in = new Scanner(System.in);
        Command comm = readCommand(in);

        while (!comm.equals(Command.QUIT)) {
            switch (comm) {
                case NEW_STUDENT:
                    newStudent(in, app);
                    break;

                case STU_INFO:
                    studentInfo(in, app);
                    break;

                case NEW_MANAGER:
                    newManager(in, app);
                    break;

                case MAN_INFO:
                    managerInfo(in, app);
                    break;

                case NEW_ROOM:
                    newRoom(in, app);
                    break;

                case ROOM_INFO:
                    roomInfo(in, app);
                    break;

                case MODIFY_ROOM:
                    modifyRoomStatus(in, app);
                    break;

                case REMOVE_ROOM:
                    removeRoom(in, app);
                    break;

                case NEW_REQUEST:
                    newRequest(in, app);
                    break;

                case ACCEPT_REQUEST:
                    acceptRequest(in, app);
                    break;

                case LIST_REQUESTS:
                    listRequests(in, app);
                    break;

                //case LIST_ALL_ROOMS:
                // NOTE so para implementar na fase 2
                //listAllRooms(in, app);
                //break;

                //case LIST_LOCATION_FREE:
                // NOTE so para implementar na fase 2
                //listFreeRoomsAtLocation(in, app);
                //break;

                case UNKNOWN_COMMAND:
                    break;
            }
            System.out.println();
            comm = readCommand(in);
        }
        System.out.println(MSG_EXIT);
        System.out.println();
        store(app);
        in.close();
    }

    /**
     * Le o novo comando a executar.
     *
     * @param in - Scanner de onde os dados vao ser lidos.
     */
    private static Command readCommand(Scanner in) {
        //System.out.printf("> "); //prompt
        String input = in.next().toUpperCase();

        for (Command c : Command.values()) {
            if (c.getCommandInputName().equals(input)) {
                return c;
            }
        }
        return Command.UNKNOWN_COMMAND;
    }

    /**
	 * reads information from a Scanner and creates a new <code>Student</code>, with that information
	 * and then,in case of success, prints a message of success <br>
	 * in case of failure, prints a message explaining what failed
	 * @see Student
	 * @see UniBedroomsManager
	 * @param in - Scanner to read from
	 * @param app - UniBedroomsManager where the student will be created and stored
	 */
    private static void newStudent(Scanner in, UniBedroomsManager app) {
        String studentLogin = in.next();
        String studentName = in.nextLine().trim();//\n
        int age = in.nextInt();
        String locationName = in.nextLine().trim(); //\n
        String universityName = in.nextLine();//\n

        //System.out.println();

        try {
            app.createStudent(studentLogin, studentName, age, locationName, universityName);
            System.out.println(MSG_STUDENT_REGISTERED);
        } catch (UserAlreadyExistsException e) {
            System.out.println(MSG_USER_ALREADY_EXISTS);
        }
    }

    /**
	 * reads the <code>login</code> of a <code>Student</code>,
	 * and then,in case of success, prints the information regarding the student<br>
	 * in case of failure, prints a message explaining what failed
	 * @see Student
	 * @see UniBedroomsManager
	 * @param in - Scanner to read from
	 * @param app - UniBedroomsManager where the student is stored
	 */
    private static void studentInfo(Scanner in, UniBedroomsManager app) {
        String studentLogin = in.next();
        in.nextLine();//\n

        //System.out.println();

        try {
            System.out.println(app.getStudentInfo(studentLogin));
        } catch (StudentDoesntExistException e) {
            System.out.println(MSG_NO_SUCH_STUDENT);
        }
    }

    /**
	 * reads information from a Scanner and creates a new <code>Manager</code>, with that information
	 * and then,in case of success, prints a message of success <br>
	 * in case of failure, prints a message explaining what failed
	 * @see Manager
	 * @see UniBedroomsManager
	 * @param in - Scanner to read from
	 * @param app - UniBedroomsManager where the manager will be created and stored
	 */
    private static void newManager(Scanner in, UniBedroomsManager app) {
        String managerLogin = in.next();
        String managerName = in.nextLine().trim();//\n
        String universityName = in.nextLine();//\n

        //System.out.println();

        try {
            app.createManager(managerLogin, managerName, universityName);
            System.out.println(MSG_MANAGER_REGISTERED);
        } catch (UserAlreadyExistsException e) {
            System.out.println(MSG_USER_ALREADY_EXISTS);
        }
    }

    /**
	 * reads the <code>login</code> of a <code>Manager</code>,
	 * and then,in case of success, prints the information regarding the manager<br>
	 * in case of failure, prints a message explaining what failed
	 * @see Manager
	 * @see UniBedroomsManager
	 * @param in - Scanner to read from
	 * @param app - UniBedroomsManager where the manager is stored
	 */
    private static void managerInfo(Scanner in, UniBedroomsManager app) {
        String managerLogin = in.next();
        in.nextLine();//\n

        //System.out.println();

        try {
            System.out.println(app.getManagerInfo(managerLogin));
        } catch (ManagerDoesntExistException e) {
            System.out.println(MSG_NO_SUCH_MANAGER);
        }
    }

    /**
	 * reads information from a Scanner and creates a new <code>Room</code>, with that information
	 * and then,in case of success, prints a message of success <br>
	 * in case of failure, prints a message explaining what failed
	 * @see Room
	 * @see UniBedroomsManager
	 * @param in - Scanner to read from
	 * @param app - UniBedroomsManager where the room will be created and stored
	 */
    private static void newRoom(Scanner in, UniBedroomsManager app) {
        String roomCode = in.next();
        String managerLogin = in.nextLine().trim();//\n
        String residenceName = in.nextLine();//\n
        String universityName = in.nextLine();//\n
        String locationName = in.nextLine();//\n
        int floor = in.nextInt();
        in.nextLine();//\n
        String roomDescription = in.nextLine().trim();//\n

        //System.out.println();

        try {
            app.createRoom(roomCode, managerLogin, residenceName, universityName, locationName, floor, roomDescription);
            System.out.println(MSG_ROOM_REGISTERED);
        } catch (RoomAlreadyExistsException e) {
            System.out.println(MSG_ROOM_ALREADY_EXISTS);
        } catch (ManagerDoesntExistException e) {
            System.out.println(MSG_NO_SUCH_MANAGER);
        } catch (DifferentUniversityExeption e) {
            System.out.println(MSG_NON_AUTHORIZED_OPERATION);
        }
    }

    /**
	 * reads the <code>code</code> of a <code>Room</code>
	 * and then,in case of success, prints the information regarding the room<br>
	 * in case of failure, prints a message explaining what failed
	 * @see Room
	 * @see UniBedroomsManager
	 * @param in - Scanner to read from
	 * @param app - UniBedroomsManager where the room stored
	 */
    private static void roomInfo(Scanner in, UniBedroomsManager app) {
        String roomCode = in.next();
        in.nextLine();//\n

        //System.out.println();

        try {
            System.out.println(app.getRoomInfo(roomCode));
        } catch (RoomDoesntExistException e) {
            System.out.println(MSG_NO_SUCH_ROOM);
        }
    }

    /**
	 * reads the <code>code</code> of a <code>Room</code>
	 * and then,in case of success, changes the status of the room and prints a message of success<br>
	 * in case of failure, prints a message explaining what failed
	 * @see Room
	 * @see Room.FREE
	 * @see Room.OCUPIED
	 * @see UniBedroomsManager
	 * @param in - Scanner to read from
	 * @param app - UniBedroomsManager where the room is stored
	 */
    private static void modifyRoomStatus(Scanner in, UniBedroomsManager app) {
        String roomCode = in.next();
        String managerLogin = in.next();
        in.nextLine();//\n
        String roomStatus = in.next();
        in.nextLine();//\n

        //System.out.println();

        try {
            app.changeRoomState(roomCode, managerLogin, roomStatus);
            System.out.println(MSG_ROOM_STATUS_UPDATED);
        } catch (RoomDoesntExistException e) {
            System.out.println(MSG_NO_SUCH_ROOM);
        } catch (DifferentManagerException e) {
            System.out.println(MSG_NON_AUTHORIZED_OPERATION);
        } catch (ActiveRequestsException e) {
            System.out.println(MSG_ACTIVE_REQUESTS);
        }
    }

    /**
	 * reads the <code>code</code> of a <code>Room</code>
	 * and then,in case of success, removes the room from the <code>UniBedroomsManager</code>
	 * and prints a message of success<br>
	 * in case of failure, prints a message explaining what failed
	 * @see Room
	 * @see UniBedroomsManager
	 * @param in - Scanner to read from
	 * @param app - UniBedroomsManager where the room is stored
	 */
    private static void removeRoom(Scanner in, UniBedroomsManager app) {
        String roomCode = in.next();
        String managerLogin = in.next();
        in.nextLine();

        //System.out.println();

        try {
            app.removeRoom(roomCode, managerLogin);
            System.out.println(MSG_ROOM_REMOVED);
        } catch (RoomDoesntExistException e) {
            System.out.println(MSG_NO_SUCH_ROOM);
        } catch (DifferentManagerException e) {
            System.out.println(MSG_NON_AUTHORIZED_OPERATION);
        } catch (ActiveRequestsException e) {
            System.out.println(MSG_ACTIVE_REQUESTS);
        }
    }

    /**
	 * reads information from a Scanner and creates a new <code>Request</code>, with that information.
	 * <br> And then, in case of success, prints a message of success
	 * <br> In case of failure, prints a message explaining what failed
	 * @see Request
	 * @see UniBedroomsManager
	 * @param in - Scanner to read from
	 * @param app - UniBedroomsManager where the Request will be created and stored
	 */
    private static void newRequest(Scanner in, UniBedroomsManager app) {
        String studentLogin = in.next();
        String roomCode = in.next();
        in.nextLine();

        //System.out.println();

        try {
            app.createRequest(studentLogin, roomCode);
            System.out.println(MSG_REQUEST_REGISTERED);
        } catch (StudentDoesntExistException e) {
            System.out.println(MSG_NO_SUCH_STUDENT);
        } catch (ExceededNumberOfRequestsException e) {
            System.out.println(MSG_NON_AUTHORIZED_OPERATION);
        } catch (RoomDoesntExistException e) {
            System.out.println(MSG_NO_SUCH_ROOM);
        } catch (RoomIsOcupiedException e) {
            System.out.println(MSG_ROOM_OCUPIED);
        } catch (RequestAlreadyExistsException e) {
            System.out.println(MSG_REQUEST_ALREADY_EXISTS);
        }
    }

    /**
	 * reads information from a Scanner and accepts a <code>Request</code> to a room, with that information.
	 * <br> And then, in case of success, prints a message of success
	 * <br> In case of failure, prints a message explaining what failed
	 * @see Room
 	 * @see Request
	 * @see UniBedroomsManager
	 * @param in - Scanner to read from
	 * @param app - UniBedroomsManager where the Request is stored
	 */
    private static void acceptRequest(Scanner in, UniBedroomsManager app) {
        String roomCode = in.next();
        String managerLogin = in.next();
        String studentLogin = in.next();
        in.nextLine();

        //System.out.println();

        try {
            app.acceptRequest(roomCode, managerLogin, studentLogin);
            System.out.println(MSG_REQUEST_ACCEPTED);
        } catch (StudentDoesntExistException | RequestDoesntExistException e) {
            System.out.println(MSG_NO_SUCH_REQUEST);
        } catch (RoomDoesntExistException e) {
            System.out.println(MSG_NO_SUCH_ROOM);
        } catch (DifferentManagerException e) {
            System.out.println(MSG_NON_AUTHORIZED_OPERATION);
        }
    }

    /**
	 * reads information from a Scanner.
	 * <br> And then, in case of success, print all the requests to the room, from the info read
	 * <br> In case of failure, prints a message explaining what failed
	 * @param in
	 * @param app
	 */
    private static void listRequests(Scanner in, UniBedroomsManager app) {
        String roomCode = in.next();
        String managerLogin = in.next();
        in.nextLine();

        //System.out.println();

        try {
            Iterator<Request> it = app.listRoomRequests(roomCode, managerLogin);

            while (it.hasNext())
                System.out.println(it.next());
        } catch (RoomDoesntExistException e) {
            System.out.println(MSG_NO_SUCH_ROOM);
        } catch (DifferentManagerException e) {
            System.out.println(MSG_NON_AUTHORIZED_OPERATION);
        } catch (NoRequestsException e) {
            System.out.println(MSG_NO_REQUESTS);
        }
    }

    /**
     * Stores all the data of the program, to a file that will work as a database
     */
    private static void store(UniBedroomsManager app) {

        try {
            ObjectOutputStream file = new ObjectOutputStream(new FileOutputStream(fileName));
            file.writeObject(app);
            file.flush();
            file.close();
        } catch (IOException e) {

        }
    }

    /**
     * read all the data of the program, from a file that works as a database
     * @return  <p>
     * 				In case of success, returns a UniBedroomsManager with all the data in the file
     * 			</p>
     *			In case of failure, returns a new UniBedroomsManager()
     */			
    private static UniBedroomsManager load() {
        UniBedroomsManager app;
        try {
            ObjectInputStream file = new ObjectInputStream(new FileInputStream(fileName));
            app = (UniBedroomsManager) file.readObject();
            file.close();
            return app;
        } catch (IOException e) {
            return new UniBedroomsManagerClass();
        } catch (ClassNotFoundException e) {
            return new UniBedroomsManagerClass();
        }
    }



	
	/*
	NOTE so para implementar na fase 2
	private static void listAllRooms(Scanner in) {
		in.nextLine();
		
		// TO DO Acabar 
	}
	
	private static void listFreeRoomsAtLocation(Scanner in) {
		String locationName = in.nextLine().trim();
		
		// TO DO Acabar
		
	}
	*/
}
