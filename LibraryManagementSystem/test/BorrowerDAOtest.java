package lms.test;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import lms.dao.BorrowerDAO;
import lms.domain.Borrower;


import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.runners.MockitoJUnitRunner;

@ExtendWith(MockitoExtension.class)

public class BorrowerDAOtest {

    @Mock
    private DataSource ds;

    @Mock
    private Connection c;

    @Mock
    private PreparedStatement stmt;

    @Mock
    private ResultSet rs;

    private Borrower borrower;

    @Before
    public void setUp() throws Exception {
    	
        assertNotNull(ds);
        when(c.prepareStatement(any(String.class))).thenReturn(stmt);
        when(ds.getConnection()).thenReturn(c);

        borrower = new Borrower();
        borrower.setCardNo(100);
        borrower.setName("Test name");
        borrower.setAddress("test address");
        borrower.setPhone("test phone");
        
        when(rs.first()).thenReturn(true);
        when(rs.getInt(1)).thenReturn(100);
        when(rs.getString(2)).thenReturn(borrower.getName());
        when(rs.getString(3)).thenReturn(borrower.getAddress());
        when(rs.getString(4)).thenReturn(borrower.getPhone());
        when(stmt.executeQuery()).thenReturn(rs);
    }

    @Test(expected=IllegalArgumentException.class)
    public void nullCreateThrowsException() throws ClassNotFoundException, SQLException {
        new BorrowerDAO(c).add(null);
    }

    @Test
    public void createPerson() throws ClassNotFoundException, SQLException {
    	new BorrowerDAO(c).add(borrower);
    }

    @Test
    public void createAndRetrievePerson() throws Exception {
        BorrowerDAO borrowerdao= new BorrowerDAO(c);
        borrowerdao.add(borrower);
        List<Borrower> borrlist = new ArrayList<>();
       List<Borrower> borr =  borrowerdao.readAllBorrowers();
        assertEquals(borrlist,borr);
    }

}