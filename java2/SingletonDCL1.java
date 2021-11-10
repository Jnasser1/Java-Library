package jb4;

// Ensures one instance of Singleton class is created through app life cycle.

// There are two checks for (instance == null), the first without locking,
// the second inside the synchronized block, and only locks one time during the lifespan of Singleton.

class Singleton {
	private static volatile Singleton instance;

	private Singleton() {
	}

	public static Singleton getInstance() {
		if (instance == null) { // First check.

			synchronized (Singleton.class) {
				if (instance == null) { // Second check.
					instance = new Singleton();
				}
			}
		}
		return instance;
	}

}
