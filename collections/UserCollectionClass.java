package collections;

import dataStructures.*;
import exceptions.*;
import tempPackage.*;

/**
 * Implements the <code>UserCollection</code> interface
 * @see UserCollection
 * @author Goncalo Carvalho 61605 gmm.carvalho@gmail.com
 * @author Augusto Mateus 	61626 aj.mateus@campus.fct.unl.pt
 *
 */
public class UserCollectionClass implements UserCollection {

	/**
	 * data structure that holds the students in the Collection
	 * @see dataStructures.List
	 */
    private final List<Student> students;
    /**
	 * data structure that holds the managers in the Collection
	 * @see dataStructures.List
	 */
    private final List<Manager> managers;

    static final long serialVersionUID= 0L;

    /**
     * <code>UserCollectionClass</code> constructor
     */
    public UserCollectionClass() {
        students = new DoubleList<>();
        managers = new DoubleList<>();
    }

    @Override
    public void addStudent(String login, String name, int age, Location location, String university) throws UserAlreadyExistsException {
    	
    	 if (findUser(students, login) == null && findUser(managers, login) == null)
             students.addLast(new StudentClass(login, name, age, location, university));
         else
         	throw new UserAlreadyExistsException();
    }

    @Override
    public Student findStudent(String studentLogin) throws StudentDoesntExistException {
        
    	Student student = findUser(students, studentLogin);
        
        if(student == null)
        	throw new StudentDoesntExistException();
        
        return student;
    }

    @Override
    public void addManager(String login, String name, String university) throws UserAlreadyExistsException {
    	
    	if (findUser(managers, login) == null && findUser(students, login) == null)
            managers.addLast(new ManagerClass(login, name, university));
        else
        	throw new UserAlreadyExistsException();
    }

    @Override
    public Manager findManager(String managerLogin) throws ManagerDoesntExistException {

    	Manager manager = findUser(managers, managerLogin);
    	
    	if(manager == null)
    		throw new ManagerDoesntExistException();
    	
        return manager;

    }

    /**
     * Finds a User with the login <code>login</code> in a the given list 
     * @param <U> - Type that extends the interface <code>User</code>
     * @param list - list of U elements
     * @param login - login of the user to find
     * @return 	Returns the User with the said login<br>
     * 			Or<br>
     * 			Returns <code>null</code> if that user wasnt found
     * @see User
     */
    private <U extends User> U findUser(List<U> list, String login) {

        Iterator<U> userIterator = list.iterator();

        while (userIterator.hasNext()) {
            U user = userIterator.next();
            if (user.getLogin().equalsIgnoreCase(login))
                return user;
        }

        return null;
    }
}
