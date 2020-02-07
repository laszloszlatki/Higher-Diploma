import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.Test;

class PasswordGeneartorTest {

	@Test
	public void canGenerate() {
		new PasswordGenerator();
	}
	
	@Test
	public void randomRangeBetween0And61() {
		new PasswordGenerator();
		int i;
		int min = 63;
		int max = -1;
		for(i = 0; i < 1000000; i++) {
		int number = PasswordGenerator.randomNumber();
		if (max < number) {
			max = number;
		}
		if (min > number) {
			min = number;
		}
		}
		assertEquals(0, min);
		assertEquals(61, max);
	}

	@Test
	public void firstCharIsLowercasea() {
		
		char rndChar = PasswordGenerator.randomChar(0);
		assertEquals('a', rndChar);
		
	}
	
	@Test
	public void LastCharIs9() {
		
		char rndChar = PasswordGenerator.randomChar(61);
		assertEquals('9', rndChar);
		
	}
	
	@Test
	public void passwordLengthIsEight() {
		
		String password = PasswordGenerator.password(8);
		assertEquals(8, password.length());
	}
}