package collections;

import exceptions.*;
import tempPackage.*;

import java.io.*;

/**
 * A collection of <code>User</code> with the necessary methods<br>
 * A substitute to using the data structures directly, that facilitates the eventual
 * change of data structures needed for phase 2
 * 
 * @author Goncalo Carvalho 61605 gmm.carvalho@gmail.com
 * @author Augusto Mateus 	61626 aj.mateus@campus.fct.unl.pt
 *
 */
public interface UserCollection extends Serializable  {
	
	/**
	 * Makes a new <code>Student</code> and adds him to the Collection
	 * @param login -  of the student to add
	 * @param name -  of the student to add
	 * @param age -  of the student to add
	 * @param location -  of the student to add
	 * @param university - name of the university of the student to add
	 * @throws UserAlreadyExistsException if there is already a <code>User</code>
	 * (<code>Student</code> or <code>Manager</code>) with the same login in the Collection
	 */
    void addStudent(String login, String name, int age, Location location, String university) throws UserAlreadyExistsException;

    /**
     * Finds the first <code>Student</code> with the login <code>studentLogin</code>
     * @param studentLogin - login of the student
     * @return The <code>Student</code> with said login
     * @throws StudentDoesntExistException if there is no such student in the Collection
     */
    Student findStudent(String studentLogin) throws StudentDoesntExistException;

    /**
     * Makes a new <code>Manager</code> and adds him to the Collection
     * @param login - of the manager to add
     * @param name - of the manager to add
     * @param university - name of the university of the manager to add
     * @throws UserAlreadyExistsException if there is already a <code>User</code>
	 * (<code>Student</code> or <code>Manager</code>) with the same login in the Collection
     */
    void addManager(String login, String name, String university) throws UserAlreadyExistsException;

    /**
     * Finds the first <code>Manager</code> with the login <code>managerLogin</code>
     * @param managerLogin - login of the manager
     * @return The <code>Manager</code> with said login
     * @throws ManagerDoesntExistException if there is no such manager in the Collection
     */
    Manager findManager(String managerLogin) throws ManagerDoesntExistException;

}
