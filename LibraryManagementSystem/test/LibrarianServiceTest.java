package lms.test;

import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.mockito.stubbing.Stubber;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.*;

import lms.service.LibrarianService;

import lms.domain.BookCopy;
import lms.domain.LibraryBranch;
import lms.dao.BookCopyDAO;
import lms.dao.LibraryBranchDAO;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/*
 * @Mock
 * X (repo), DAO
 * 
 * @InjectMocks
 * Y (service)
 * 
 * @Mock: Things in X have dependency to Y, mock object X is used to simulat behavior.
 * @InjectMocks: We simulate the services of Y,  so create @InjectMock based on Y.
 * 
 *  @InjectMocks Creates the mock implementation and injects the
 *  dependent mocks marked with @Mocks into it.
 */

@ExtendWith(MockitoExtension.class)

public class LibrarianServiceTest {

//	@Rule public MockitoRule rule = MockitoJUnit.rule();

	@Mock
	private LibraryBranchDAO lbdaoMock;
	@Mock
	private LibraryBranch lbranch;

	@Mock
	private BookCopyDAO bcdaoMock;
	@Mock
	private BookCopy bookcopy;

	@InjectMocks
	private LibrarianService service;

	@BeforeAll
	public void init() throws Exception {
		MockitoAnnotations.initMocks(this);
	
	}

//	@Test
//	public void testUpdateNoOfCopies() throws ClassNotFoundException, SQLException, ExceptionInInitializerError {
//		bookcopy = new BookCopy(1, 4, 101);
//		bcdaoMock = mock(BookCopyDAO.class);
//
//		when(bcdaoMock).updateNoOfCopiesBC(bookcopy).thenReturn("true");
//
//		assertEquals(202, service.updateNoOfCopies(bookcopymock, 102));
//
//		assertEquals((double) 1, (double) bookcopy.getBookId());
//		assertEquals((double) 4, (double) bookcopy.getBranchId());
//		assertEquals((double) 102, (double) bookcopy.getNoOfCopies());
//	}

//	@Test
//	public void newtest() {
//		when(bcdaoMock).readAllBookCopies().thenReturn("true");
//		System.out.println("dao result :: " + bcdaoMock.readAllBookCopies());
//		verify(bcdaoMock).readAllBookCopies();
//
//	}

	@Test
	public void testDisplayBranches() throws ClassNotFoundException, SQLException, ExceptionInInitializerError {
		List<LibraryBranch> list = new ArrayList<LibraryBranch>();
		LibraryBranch branchOne = new LibraryBranch(101, "John", "test address1");
		LibraryBranch branchTwo = new LibraryBranch(102, "Alex", "test address2");
		LibraryBranch branchThree = new LibraryBranch(103, "Steve", "test address3");

		list.add(branchOne);
		list.add(branchTwo);
		list.add(branchThree);

		// Mockito.when(lbdaoMock.readEverythingBranches()).thenReturn(list);
		doReturn(list).when(lbdaoMock).readEverythingBranches();

		// test
		List<LibraryBranch> BranchList = service.readAllBranches();

		assertEquals(3, BranchList.size());
		verify(lbdaoMock, times(1)).readEverythingBranches();

	}
	
	


	
	
	
	/*
	  @Test
	  public void update() throws SQLException {
	  
	  LibraryBranch libranch  = new LibraryBranch();
	  
	 libranch.setBranchAddress("test address");
	 libranch.setBranchName("test name");
	 libranch.setBranchId(2222);

	  when(databaseMock.update(any(LibraryBranch.class))) 
	                   .thenAnswer(new Answer<LibraryBranch>() {
	  
	  
	   public LibraryBranch answer(InvocationOnMock invocation) throws Throwable {
	  
	  LibraryBranch libranch = (LibraryBranch) invocation.getArguments()[0];
	  
	  libranch.setBranchAddress("new address");
	  // library_Br.setId(10);
	  
	  return libranch; }

	
	  
	  });
	  
	 assertNotNull(libranch.getBranchId());
	 String s = new String();
	 s = LibrarianService.updateBranchAddress(libranch.getBranchId(), "new address");
	  
	  System.out.println("s =" + s );
	  
	  
	  
	  assertEquals(libranch.getBranchAddress(), "new address"); 
	  
	  }
	  
	*/  
	  
	  
	
	  
	  
	 
	 

	/*
	 * @Test public void newTest() throws ClassNotFoundException, SQLException,
	 * ExceptionInInitializerError { libraryservices.readAllBranches();
	 * verify(databaseMock).add(any(LibraryBranch.class));
	 * 
	 * 
	 * doReturn("okay").when(databaseMock).readEverythingBranches();
	 * assertTrue("okay".equals(librarian.readAllBranches()));
	 * 
	 * 
	 * }
	 */

	/*
	 * 
	 * @Test public void getAllBranchesTest() throws ClassNotFoundException,
	 * SQLException, ExceptionInInitializerError { List<LibraryBranch> list = new
	 * ArrayList<LibraryBranch>(); LibraryBranch branchOne = new LibraryBranch(1,
	 * "John", "test address1"); LibraryBranch branchTwo = new LibraryBranch(2,
	 * "Alex", "test address2"); LibraryBranch branchThree = new LibraryBranch(3,
	 * "Steve", "test address3");
	 * 
	 * list.add(branchOne); list.add(branchTwo); list.add(branchThree);
	 * 
	 * when(databaseMock.readEverythingBranches()).thenReturn(list);
	 * 
	 * // When the librarian reads all branches should return list.
	 * List<LibraryBranch> BranchList = liibraryservices.readAllBranches();
	 * 
	 * assertEquals(3, BranchList.size()); verify(databaseMock,
	 * times(1)).readEverythingBranches(); }
	 * 
	 */

	/*
	 * LibrarianService instance;
	 * 
	 * private HibernateTemplate hibernateTemplateMock;
	 * 
	 * @Before public void before(){
	 * 
	 * this.instance = new LibrarianService(); this.instance.d = new FooDAO();
	 * this.hibernateTemplateMock = mock( HibernateTemplate.class );
	 * this.instance.dao.setHibernateTemplate( this.hibernateTemplateMock ); }
	 * 
	 * @Test public void whenCreateIsTriggered_thenNoException(){ // When
	 * this.instance.create( new Foo( "testName" ) ); }
	 * 
	 * @Test( expected = NullPointerException.class ) public void
	 * whenCreateIsTriggeredForNullEntity_thenException(){ // When
	 * this.instance.create( null ); }
	 * 
	 * @Test public void whenCreateIsTriggered_thenEntityIsCreated(){ // When Foo
	 * entity = new Foo( "testName" ); this.instance.create( entity );
	 * 
	 * // Then ArgumentCaptor< Foo > argument = ArgumentCaptor.forClass( Foo.class
	 * ); verify( this.hibernateTemplateMock ).save( argument.capture() );
	 * assertThat( entity, is( argument.getValue() ) ); }
	 */

}