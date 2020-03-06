/*
 * @author: Laszlo Szlatki
 * @date: 02/Mar/2020 
 * */

/*
 * Class to validate dates entered
 * */
package dating;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateValidator {

	public static LocalDate validateDate(String date) {
		// if date not entered, skip it
		if (date.isEmpty()) {
			return null;
		}
		 DateTimeFormatter format1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		 
		try {
			// if not valid, it will throw DateTimeParseException
			LocalDate localDate = LocalDate.parse(date, format1);
			
			// return null, if date is not in the next fortnight
			if(localDate < LocalDate.now() || localDate > LocalDate.now().plusDays(14)) {}
			return localDate;

		} catch (DateTimeParseException e) {
			System.out.println("Date format not valid. Needs to be: dd/MM/yyyy");
			//e.printStackTrace();
			return null;
		}	catch (DateTimeException e) {
			System.out.println("Invalid date entered!");
			return null;
		}
		
	}
}