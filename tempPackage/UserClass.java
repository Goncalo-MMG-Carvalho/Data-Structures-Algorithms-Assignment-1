package tempPackage;

/**
 * Implements the <code>Room</code> interface
 * 
 * @see Room
 * @author Goncalo Carvalho 61605 gmm.carvalho@gmail.com
 * @author Augusto Mateus 	61626 aj.mateus@campus.fct.unl.pt
 */
public abstract class UserClass implements User {
	
	static final long serialVersionUID= 0L;
	/**
	 * the login of the user
	 */
	private final String login;
	/**
	 * the name of the user
	 */
    private final String name;
    /**
     * the name of the university of the user
     */
    private final String universityName;

    /**
     * <code>UserClass</code> constructor
     * @param login - login of the user
     * @param name - name of the user
     * @param universityName - name of the university of the user
     */
    public UserClass(String login, String name, String universityName) {

        this.login = login;
        this.name = name;
        this.universityName = universityName;

    }
	
	@Override
	public String getUniversityName() {
		return universityName;
	}

	@Override
	public String getLogin() {
		return login;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public boolean equals(Object o) {
		if(o == null)
			return false;
		
		if(!(o instanceof User other))
			return false;

		return this.getLogin().equalsIgnoreCase(other.getLogin());
	}
}
