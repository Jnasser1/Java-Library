package lms.test;

import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.junit.jupiter.MockitoExtension;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import lms.service.BorrowerService;

import lms.domain.Borrower;

import lms.dao.BorrowerDAO;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;


import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)

public class TestBorrower {



	@Mock
	private BorrowerDAO borrowerdaoMock;
	@Mock
	private Borrower borrower;

	@InjectMocks
	private BorrowerService borrowerService;

	@BeforeAll
	public void init() throws Exception {
		MockitoAnnotations.initMocks(this);

//		borrowerdaoMock.setService(borrowerService);
//		borrowerService.readAllBorrowers();
//		

	}

	@Test
	public void testreadAllBorrowers() throws ClassNotFoundException, SQLException, ExceptionInInitializerError {
		List<Borrower> list = new ArrayList<Borrower>();
		Borrower borrOne = new Borrower(101, "John", "test1", "test2");
		Borrower borrTwo = new Borrower(102, "test1", "test2", "test3");
		Borrower borrThree = new Borrower(103, "test1", "test2", "test3");

		list.add(borrOne);
		list.add(borrTwo);
		list.add(borrThree);

		Borrower sampleBorrower = new Borrower(50, "50", "50", "50");

		Mockito.doReturn(list).when(borrowerdaoMock).readAllBorrowers();

		// test
		List<Borrower> borr_list = borrowerService.readAllBorrowers();

		assertEquals(3, borr_list.size());
		verify(borrowerdaoMock, times(1)).readAllBorrowers();

	}

	//
//	@Test
//	public void newtest() {
//	when(bcdaoMock).readAllBookCopies().thenReturn("true");
//    System.out.println("dao result :: "+bcdaoMock.readAllBookCopies());
//    verify(bcdaoMock).readAllBookCopies();
//	
//	}

}