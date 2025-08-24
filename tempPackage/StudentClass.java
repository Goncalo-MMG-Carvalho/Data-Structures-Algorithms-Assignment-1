package tempPackage;

import collections.RequestCollection;
import collections.RequestCollectionClass;
import dataStructures.Iterator;

/**
 * Implements the <code>Student</code> interface and extends UserClass
 * 
 * @see Student
 * @see UserClass
 * @author Goncalo Carvalho 61605 gmm.carvalho@gmail.com
 * @author Augusto Mateus 	61626 aj.mateus@campus.fct.unl.pt
 */
public class StudentClass extends UserClass implements Student{

	/**
	 * age of the student
	 */
	private int age;
	/**
	 * Location that the student is staying at/ wants to find a room in
	 */
    private Location location;
    /**
     * Collection of the active requests that the student has 
     * @see RequestCollection
     */
    private RequestCollection requests;
	static final long serialVersionUID= 0L;
	
	/**
     * <code>StudentClass</code> constructor
     * @param studentLogin - login of the student
     * @param studentName - name of the student
     * @param age - age of the student
     * @param location - location of the student
     * @param universityName - name of the university of the student
     */
	public StudentClass(String studentLogin, String studentName, int age, Location location, String universityName) {
		
		super(studentLogin, studentName, universityName);
        this.age = age;
        this.location = location;
        requests = new RequestCollectionClass();
	}

	
	@Override
	public int numberOfRequests() {

		return requests.numberOfRequests();
	}

	@Override
	public boolean hasRequestToRoom(String roomCode) {
		
		Request req = requests.findRequest(roomCode);
		if(req == null)
			return false;
		return true; 
	}

	@Override
	public void addRequest(Request req) {
		
		requests.addRequest(req);
	}

	@Override
	public String getLocationName() {
		return location.getLocationName();
	}

	@Override
	public int getAge() {
		return age;
	}

	@Override
    public String toString() {
        return 	getLogin() + " " + getName() + " " + getAge()+ "\n" 
        	  +	getLocationName() + "\n" 
        	  +	getUniversityName();
    }
	
	@Override
	public Iterator<Request> removeAllRequests() {
		return requests.removeAllRequests();
	}

	@Override
	public void removeRequest(Request req) {
		requests.removeRequest(req);
	}
}
