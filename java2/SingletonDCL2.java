package jb4;

// The volatile modifier guarantees that any thread that reads 
// a field will  see the most recently written value.

//In cases where instance is already  initialized (most the time),
//the volatile field is only accessed once due to return localreference.

class SingletonDCL2 {
	private volatile SingletonDCL2 instance;

	public SingletonDCL2 getSingleton2() throws Exception {
		SingletonDCL2 localreference = instance;
		if (localreference == null) {
			synchronized (this) { // obtain lock on the current instance
				localreference = instance;
				if (localreference == null) {
					instance = localreference = new SingletonDCL2();
				}
			}
		}
		return localreference;

	}

	public static void main(String[] args) {
		SingletonDCL2 singleton2 = new SingletonDCL2();

	}
}
