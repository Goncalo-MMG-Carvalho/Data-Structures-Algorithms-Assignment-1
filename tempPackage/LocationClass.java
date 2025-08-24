package tempPackage;

/**
 * Implements the <code>Location</code> interface
 * @see Location
 * @author Goncalo Carvalho 61605 gmm.carvalho@gmail.com
 * @author Augusto Mateus 	61626 aj.mateus@campus.fct.unl.pt
 *
 */
public class LocationClass implements Location {

	/**
	 * name of the Location
	 */
	private final String locationName;
	static final long serialVersionUID= 0L;
	
	/**
	 * <code>LocationClass</code> constructor
	 * @param locationName - name of the location to create
	 */
	public LocationClass(String locationName) {
		this.locationName = locationName;
	}

	public String getLocationName() {
		return locationName;
	}

	public boolean equals(Object o) {
		
		if(!(o instanceof Location))
			return false;
		
		Location other = (Location) o;
		
		return getLocationName().equals(other.getLocationName());
		
	}
}
