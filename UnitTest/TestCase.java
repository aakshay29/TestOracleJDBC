import static org.junit.Assert.*;

import org.junit.Test;

public class TestCase {

	@Test
	public void test() {
		String state = "MD";
		int id = TestOracleJDBC.getState(state);
		assertTrue(id == 16) ;
	}

}
