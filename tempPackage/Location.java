package tempPackage;

import java.io.*;

/**
 * An interface representing a location<br>
 * Extends Serializable
 * @see Serializable
 * @author Goncalo Carvalho 61605 gmm.carvalho@gmail.com
 * @author Augusto Mateus 	61626 aj.mateus@campus.fct.unl.pt
 *
 */
public interface Location extends Serializable {
	
	/**
	 * 
	 * @return Returns the name of the Location
	 */
	String getLocationName();
	
}
