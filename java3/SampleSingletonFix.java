package jb5;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class SampleSingletonFix {

	private static Connection conn = null;

	private static SampleSingletonFix instance;
	
	// constructor is private so the class cannot be instantiated
	private SampleSingletonFix() {}
	
	// one object available, get that object.
	public static SampleSingletonFix getInstance() {
		if( instance == null ) {
            instance = new SampleSingletonFix();
        }
         return instance;
	}
	

}