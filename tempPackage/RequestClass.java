package tempPackage;

/**
 * Implements the <code>Request</code> interface
 * 
 * @see Request
 * @author Goncalo Carvalho 61605 gmm.carvalho@gmail.com
 * @author Augusto Mateus 	61626 aj.mateus@campus.fct.unl.pt
 */
public class RequestClass implements Request {

	/**
	 * student that made the request
	 * @see Student
	 */
	public Student student;
	/**
	 * ID of the room the request was made to
	 */
    public String roomID;

	static final long serialVersionUID= 0L;

	/**
     * <code>RequestClass</code> constructor
     * @param student - student that made the request
     * @param roomID - Id of the room
     */
    public RequestClass(Student student, String roomID) {
        this.student = student;
        this.roomID = roomID;
    }

	@Override
    public String getStudentLogin() {
        return student.getLogin();
    }
    
	@Override
    public String getRoomID() {
        return roomID;
    }
	
	public String getStudentName() {
		return student.getName();
	}
	
	public String getUniversityName() {
		return student.getUniversityName();
	}

	public String toString() {
		return String.format("%s %s %s", getStudentLogin(), getStudentName(), getUniversityName());
	}
	
	/*protected Student getStudent() {
		return student;
	}*/
}
