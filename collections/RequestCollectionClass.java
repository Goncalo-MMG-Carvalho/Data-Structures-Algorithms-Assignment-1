package collections;

import dataStructures.*;
import tempPackage.*;

/**
 * Implements the <code>RequestCollection</code> interface
 * @see RequestCollection
 * @author Goncalo Carvalho 61605 gmm.carvalho@gmail.com
 * @author Augusto Mateus 	61626 aj.mateus@campus.fct.unl.pt
 *
 */
public class RequestCollectionClass implements RequestCollection {
	
	private static final long serialVersionUID = 0L;
	/**
	 * data structure that holds the requests
	 * @see dataStructures.List
	 */
    private List<Request> requests;

    /**
     * <code>RequestCollectionClass</code> constructor
     */
    public RequestCollectionClass() {
        requests = new DoubleList<>();
    }
    
    @Override
    public void addRequest(Request req) {
    	
        requests.addLast(req);
    }

    @Override
    public Request findRequest(String roomID) {
        Iterator<Request> requestIterator = requests.iterator();

        while (requestIterator.hasNext()) {
            Request request = requestIterator.next();
            if (request.getRoomID().equals(roomID))
                return request;
        }

        return null;
    }

    @Override
    public int numberOfRequests() {
    	return requests.size();
    }
    
    @Override
    public Iterator<Request> removeAllRequests() {
    	Iterator<Request> tmp = iterator();
    	requests = new DoubleList<>();
        
    	return tmp;
    }

    @Override
	public Iterator<Request> iterator() {
		return requests.iterator();
	}

    @Override
	public void removeRequest(Request req) {
		requests.remove(req);
	}
}
