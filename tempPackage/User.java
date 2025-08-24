package tempPackage;

import java.io.*;

/**
 * An interface representing a user<br>
 * Extends Serializable
 * @see Serializable
 * @author Goncalo Carvalho 61605 gmm.carvalho@gmail.com
 * @author Augusto Mateus 	61626 aj.mateus@campus.fct.unl.pt
 *
 */
public interface User extends Serializable {

	/**
	 * @return The login of the user 
	 */
	String getLogin();

	/**
	 * @return The name of the user
	 */
    String getName();
	
    /**
     * @return The name of the university of the user
     */
	String getUniversityName();

}
