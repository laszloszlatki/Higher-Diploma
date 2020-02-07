
public class PasswordGenerator {

private static String lowerChar = "abcdefghijklmnopqrstuvwxyz";
private static String upperChar = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
private static String numbers = "0123456789";
private static String baseChars = lowerChar + upperChar + numbers;
	
public static int randomNumber() {
		
	int number = (int)(Math.random()*62);
	return number;
		
	}

public static char randomChar(int number) {
	
	char rndChar = baseChars.charAt(number);
	return rndChar;
	
	}

public static String password(int passwordLength) {
	
	String password = "";
	for (int i=0; i < passwordLength; i++) {
		char rndChar = randomChar(randomNumber());
		password = password + rndChar;
	}
	return password;
	}
}

