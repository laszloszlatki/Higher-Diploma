/*
 * @author: Laszlo Szlatki
 * @date: 03/Mar/2020 
 * */

/*
 * This class describes the characteristics of the user of the dating site
 * gender (M/F)
 * gender preference (M/F)
 * favourite: colour, band, restaurant, sport
 * active/inactive
 * up to 5 free dates in the next fortnight
 * */
package dating;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Users {

	private static int userCount = 0;
	private String name;
	private char gender; // M or F
	private char genderPreference; // M or F
	private String favColour;
	private String favBand;
	private String favRestaurant;
	private String favSport;
	private boolean isActive;

	// up to 5 dates free in the next fortnight
	private List<LocalDate> available = new ArrayList<>(5);

	/*
	 * constructor with no args
	 */
	public Users() {
		userCount++;
		this.name = "";
		this.gender = '\0'; // M or F
		this.genderPreference = '\0'; // M or F
		this.favColour = "";
		this.favBand = "";
		this.favRestaurant = "";
		this.favSport = "";
		this.isActive = false;
		setAvailable();
	}

	/*
	 * overloaded constructor, what takes in all values
	 */
	public Users(String gender, String genderPreference, String favColour, String favBand, String favRestaurant,
			String favSport, String isActive, String... available) {
		userCount++;
		setGender(gender);
		setGenderPreference(genderPreference);
		setFavColour(favColour);
		setFavBand(favBand);
		setFavRestaurant(favRestaurant);
		setFavSport(favSport);
		setActive(isActive);
		setAvailable(available);
	}

	/*
	 * set dates when user available for a date
	 * 
	 * @param: up to 5 dates available in String varargs
	 */
	public void setAvailable(String... dates) {
		//DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate localDate;
		// if 5 dates are already saved, don't allow any more
		if (available != null && available.size() == 5) {
			System.out.println("Cannot save more dates");
			return;
		}
		// call DateValidator class to check each date if valid value was entered
		for (String date : dates) {
			localDate = DateValidator.validateDate(date);
			available.add(localDate);
		}
	}

	/*
	 * return available dates
	 * 
	 * @return: returns a copy of available dates
	 */
	public List<LocalDate> getAvailable() {
		// create a copy of the list to be returned and the original still kept private
		List<LocalDate> ret = new ArrayList<>(5);
		for (int i = 0; i < available.size(); i++) {
			ret.add(available.get(i));
		}
		return ret;
	}

	/*
	 * return the gender of the user
	 * 
	 * @return: char representation of gender ('M'/'F')
	 */
	public char getGender() {
		return gender;
	}

	/*
	 * set user entered gender after validating if input starts with m of f, gender
	 * set
	 * 
	 * @param: gender input in string format
	 * 
	 * @return: true if set, false otherwise
	 */
	boolean setGender(String gender) {
		// check for empty sting
		if (gender.isEmpty()) {
			return false;
		}
		gender = gender.toUpperCase();
		// get first letter and convert to char
		char genderChar = gender.charAt(0);
		if (genderChar == 'M' || genderChar == 'F') {
			this.gender = genderChar;
			return true;
		}
		return false;
	}

	/*
	 * return the gender preference of the user
	 * 
	 * @return: char representation of gender preference ('M'/'F')
	 */
	public char getGenderPreference() {
		return genderPreference;
	}

	/*
	 * set user entered gender preference after validating if input starts with m of
	 * f, gender preference set
	 * 
	 * @param: gender preference input in string format
	 * 
	 * @return: true if set, false otherwise
	 */
	boolean setGenderPreference(String genderPreference) {
		// check for empty sting
		if (genderPreference.isEmpty()) {
			return false;
		}
		genderPreference = genderPreference.toUpperCase();
		// get first letter and convert to char
		char genderPref = genderPreference.charAt(0);
		if (genderPref == 'M' || genderPref == 'F') {
			this.genderPreference = genderPref;
			return true;
		}
		return false;
	}

	/*
	 * return user's name
	 * 
	 * @return: user's name as a String
	 */
	public String getName() {
		return name;
	}

	/*
	 * set users name per input
	 * 
	 * @param: name to be set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/*
	 * return user's favourite colour
	 * 
	 * @return: user's favourite colour as a String
	 */
	public String getFavColour() {
		return favColour;
	}

	/*
	 * set users favourite colour
	 * 
	 * @param: favourite colour in upper case to be set
	 */
	void setFavColour(String favColour) {
		this.favColour = favColour.toUpperCase();
	}

	/*
	 * return user's favourite band
	 * 
	 * @return: user's favourite band as a String
	 */
	public String getFavBand() {
		return favBand;
	}

	/*
	 * set users favourite band
	 * 
	 * @param: favourite band in upper case to be set
	 */
	void setFavBand(String favBand) {
		this.favBand = favBand.toUpperCase();
	}

	/*
	 * return user's favourite restaurant
	 * 
	 * @return: user's favourite restaurant as a String
	 */
	public String getFavRestaurant() {
		return favRestaurant;
	}

	/*
	 * set users favourite restaurant
	 * 
	 * @param: favourite restaurant in upper case to be set
	 */
	void setFavRestaurant(String favRestaurant) {
		this.favRestaurant = favRestaurant.toUpperCase();
	}

	/*
	 * return user's favourite sport
	 * 
	 * @return: user's favourite sport as a String
	 */
	public String getFavSport() {
		return favSport;
	}

	/*
	 * set users favourite sport
	 * 
	 * @param: favourite sport in upper case to be set
	 */
	void setFavSport(String favSport) {
		this.favSport = favSport.toUpperCase();
	}

	/*
	 * return user's activity
	 * 
	 * @return: true if user is active
	 */
	public boolean isActive() {
		return isActive;
	}

	/*
	 * set users activity level (active/inactive only) if input starts with 'y', set
	 * to active. inactive otherwise
	 * 
	 * @param: answer to the question: is user active?
	 */
	void setActive(String active) {
		active = active.toUpperCase();
		// get first letter and convert to char
		char activeChar = active.charAt(0);
		if (activeChar == 'Y') {
			this.isActive = true;
		}
	}

	/*
	 * return number of users created
	 * 
	 * @return: count of users
	 */
	public static int getUserCount() {
		return userCount;
	}

	/*
	 * overriding object's hashCode
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((available == null) ? 0 : available.hashCode());
		result = prime * result + ((favBand == null) ? 0 : favBand.hashCode());
		result = prime * result + ((favColour == null) ? 0 : favColour.hashCode());
		result = prime * result + ((favRestaurant == null) ? 0 : favRestaurant.hashCode());
		result = prime * result + ((favSport == null) ? 0 : favSport.hashCode());
		result = prime * result + gender;
		result = prime * result + genderPreference;
		result = prime * result + (isActive ? 1231 : 1237);
		return result;
	}

	/*
	 * overriding object's equal method to check if 2 users are the same
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Users other = (Users) obj;
		if (available == null) {
			if (other.available != null)
				return false;
		} else if (!available.equals(other.available))
			return false;
		if (favBand == null) {
			if (other.favBand != null)
				return false;
		} else if (!favBand.equals(other.favBand))
			return false;
		if (favColour == null) {
			if (other.favColour != null)
				return false;
		} else if (!favColour.equals(other.favColour))
			return false;
		if (favRestaurant == null) {
			if (other.favRestaurant != null)
				return false;
		} else if (!favRestaurant.equals(other.favRestaurant))
			return false;
		if (favSport == null) {
			if (other.favSport != null)
				return false;
		} else if (!favSport.equals(other.favSport))
			return false;
		if (gender != other.gender)
			return false;
		if (genderPreference != other.genderPreference)
			return false;
		if (isActive != other.isActive)
			return false;
		return true;
	}

	/*
	 * overriding object's toString to personalise output
	 */
	@Override
	public String toString() {
		return "User [gender=" + gender + ", genderPreference=" + genderPreference + ", favColour=" + favColour
				+ ", favBand=" + favBand + ", favRestaurant=" + favRestaurant + ", favSport=" + favSport + ", isActive="
				+ isActive + ", available=" + getAvailable() + "]";
	}
}