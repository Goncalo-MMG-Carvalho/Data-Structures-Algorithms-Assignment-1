package tempPackage;

/**
 * Implements the <code>Manager</code> interface and extends UserClass
 * 
 * @see Manager
 * @see UserClass
 * @author Goncalo Carvalho 61605 gmm.carvalho@gmail.com
 * @author Augusto Mateus 	61626 aj.mateus@campus.fct.unl.pt
 */
public class ManagerClass extends UserClass implements  Manager {

	static final long serialVersionUID= 0L;

	/**
	 * <code>ManagerClass</code> constructor, calls the UserClass constructor
	 * @see UserClass
	 * @param managerLogin - login of the manager
	 * @param managerName - name of the manager
	 * @param universityName - name of the university of the manager
	 */
	public ManagerClass(String managerLogin, String managerName, String universityName) {
		super(managerLogin, managerName, universityName);
	}

	@Override
    public String toString() {
        return 	getLogin() + " " + getName() + "\n" 
        	  +	getUniversityName();
    }
}
