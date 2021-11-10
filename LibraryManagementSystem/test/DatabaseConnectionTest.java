package lms.test;

import java.sql.Connection;
import java.sql.Statement;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import lms.service.Utility;

@ExtendWith(MockitoExtension.class)
public class DatabaseConnectionTest {

	@InjectMocks
	private Utility util;
	@Mock
	private Connection mockConnection;
	@Mock
	private Statement mockStatement;

	@BeforeAll
	public void setUp() {
		MockitoAnnotations.initMocks(this);

	}

	@Test
	public void testMockDBConnection() throws Exception {
		Mockito.when(mockConnection.createStatement()).thenReturn(mockStatement);
		Mockito.when(mockConnection.createStatement().executeUpdate(Mockito.any())).thenReturn(1);
		int value = util.executeQuery("");
		Assert.assertEquals(value, 1);
		Mockito.verify(mockConnection.createStatement(), Mockito.times(1));
	}
}