package lms.domain;

import java.io.Serializable;

public abstract class BaseDomain<T> implements Serializable {

	
	private static final long serialVersionUID = -5133638638094137072L;

	public static long getSerialVersionUID() {
	        return serialVersionUID;
	    }


}
