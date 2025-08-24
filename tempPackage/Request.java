package tempPackage;

import java.io.*;

/**
 * An interface representing a Request<br>
 * Extends Serializable
 * @see Serializable
 * @author Goncalo Carvalho 61605 gmm.carvalho@gmail.com
 * @author Augusto Mateus 	61626 aj.mateus@campus.fct.unl.pt
 *
 */
public interface Request extends Serializable {
	
	/**
	 * @return The login of the student that made the request
	 */
	String getStudentLogin();

	/**
	 * @return The ID of the room the request was made to
	 */
	String getRoomID();

}
