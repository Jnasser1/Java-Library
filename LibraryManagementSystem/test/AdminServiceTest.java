package lms.test;

import org.junit.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.junit.jupiter.MockitoExtension;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.MockitoAnnotations;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import lms.service.Utility;
import lms.service.AdminGenreService;
import lms.dao.GenreDAO;
import lms.domain.Genre;

@ExtendWith(MockitoExtension.class)

public class AdminServiceTest {

	@Mock
	private GenreDAO genredaoMock;
	private Genre genre;

	@InjectMocks
	private AdminGenreService admin;

	@BeforeAll
	public void init() throws Exception {
		MockitoAnnotations.initMocks(this);

	//	 admin=mock(AdminGenreService.class);
	//	 genredaoMock=mock(GenreDAO.class);
	//	 admin.setService(genredaoMock);
	}

	@Test
	public void testGetAllGenres() throws ClassNotFoundException, SQLException {
		List<Genre> list = new ArrayList<Genre>();
		 genre = new Genre(100, "test1");
		 list.add(genre);
		genre = new Genre(200, "test2");
		 list.add(genre);
		genre= new Genre(300, "test3");
		 list.add(genre);

	

	
		List<Genre> GenreList = admin.DisplayAllGenres();

		for (Genre g : GenreList) {
			System.out.println(g.getGenreName() + "\t" + g.getGenreId());
		}

		doReturn(list).when(genredaoMock).readAllGenres();

		// When the admin reads all branches should return list.

		assertEquals(3, GenreList.size());
		verify(genredaoMock, times(1)).readAllGenres();

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
	  
	  
	  @Test public void edit() {
	  
	  }
	  
	
	  @Test public void remove() {
	  
	  }
	  
	  
}