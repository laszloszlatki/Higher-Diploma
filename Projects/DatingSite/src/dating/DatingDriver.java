/*
 * @author: Laszlo Szlatki
 * @date: 02/Mar/2020
 * */

package dating;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class DatingDriver {

	private static int matchCount = 0;
	// create members array to store users
	static List<Users> members = new ArrayList<>(8);

	// create new user in the members List
	private static int createUser() {
		Users user = new Users();
		members.add(user);
		System.out.println("usercount is: " + Users.getUserCount());
		System.out.println("members[] size is: " + members.size());
		return members.size();
	}

	public static void main(String[] args) {

		boolean moreEntry = true;

		do {
			// ask admin if they want to enter more users
			String more = JOptionPane.showInputDialog(null, "Would you like to enter another user? (Y/N)");
			more = more.toUpperCase();
			// get first letter and convert to char
			if (more.isEmpty()) {
				break;
			}
			char moreChar = more.charAt(0);
			if (moreChar != 'Y') {
				moreEntry = false;
				break;
			}

			// create new user in the members arrayList
			System.out.println("members[] size is: " + createUser());

			// request admin to enter user details
			String name = JOptionPane.showInputDialog(null, "Please enter user's name: ");
			System.out.println("this is user index: " + (Users.getUserCount() - 1));
			members.get(Users.getUserCount() - 1).setName(name);

			String gender = JOptionPane.showInputDialog(null, "Please enter user's gender: ");
			members.get(Users.getUserCount() - 1).setGender(gender);

			String genderPreference = JOptionPane.showInputDialog(null, "Please enter user's gender preference: ");
			members.get(Users.getUserCount() - 1).setGenderPreference(genderPreference);

			String colour = JOptionPane.showInputDialog(null, "Please enter user's favourite colour: ");
			members.get(Users.getUserCount() - 1).setFavColour(colour);

			String band = JOptionPane.showInputDialog(null, "Please enter user's favourite band: ");
			members.get(Users.getUserCount() - 1).setFavBand(band);

			String restaurant = JOptionPane.showInputDialog(null, "Please enter user's favourite restaurant: ");
			members.get(Users.getUserCount() - 1).setFavRestaurant(restaurant);

			String sport = JOptionPane.showInputDialog(null, "Please enter user's favourite sport: ");
			members.get(Users.getUserCount() - 1).setFavSport(sport);

			String active = JOptionPane.showInputDialog(null, "Is the user active? ");
			members.get(Users.getUserCount() - 1).setActive(active);

			// request up to 5 available dates
			int numDates = 5;
			String[] dates = new String[numDates];
			for (int i = 0; i < numDates; i++) {
				String available = JOptionPane.showInputDialog(null, "When is the user available for a date? ");
				dates[i] = available;
			}
			members.get(Users.getUserCount() - 1).setAvailable(dates);
			// print current member
			// System.out.println("Current member is: " + members.get(Users.getUserCount() -
			// 1));

		} while (moreEntry);

		// print members array
		for (Users member : members) {
			System.out.println("Members in the array are: " + member);
		}

		// check for matches
		System.out.println("matches are: " + checkForMatch(members));

//		// create 8 users and store them in the members array
//		Users Jim = new Users("M", "F", "blue", "U2", "Milano", "dance", "yes", "05/03/2020", "10/03/2020",
//				"16/03/2020");
//		Users Ber = new Users("F", "M", "red", "RHCP", "Milano", "fisch", "YES", "07/03/2020", "10/03/2020",
//				"12/03/2020", "18/03/2020");
//		Users Sarah = new Users("F", "M", "blue", "U2", "Milano", "dance", "Y", "05/03/2020", "11/03/2020",
//				"14/03/2020", "19/03/2020");
//		Users John = new Users("M", "F", "blue", "U2", "Milano", "dance", "y", "05/03/2020", "10/03/2020",
//				"16/03/2020");
//		
//		System.out.println(Jim);
//		System.out.println(Ber);
//		System.out.println(Sarah);
//		System.out.println(John);
//		members.add(Jim);
//		members.add(Ber);
//		members.add(Sarah);
//		members.add(John);
//		System.out.println("matches are: " + checkForMatch(members));

		/*
		 * Count as a match, if: - Members member1 !=Members member2 and member1's
		 * gender, interest etc == member2 genderPreference, interests etc and member1
		 * available[i] == member2 available[j] each matching date pair are separate
		 * matches!
		 */
	}

	private static int checkForMatch(List<Users> members) {
		for (int i = 0; i < Users.getUserCount(); i++) {
			for (int j = 0; j < Users.getUserCount(); j++) {
				// if skip checks if member1 == member2
				if (i == j) {
					System.out.println("Same user");
					continue;
				}
				// matching by gender and preference
				if (members.get(i).getGender() == members.get(j).getGenderPreference()) {
					System.out.println("matching gender preference");
					// matching by activity level
					if (members.get(i).isActive() == members.get(j).isActive()) {
						System.out.println("Matching activity level");
						System.out.println(members.get(i).getFavBand().contentEquals(members.get(j).getFavBand()));
						System.out.println(
								members.get(i).getFavRestaurant().equalsIgnoreCase(members.get(j).getFavRestaurant()));
						System.out.println(members.get(i).getFavSport().equals(members.get(j).getFavSport()));
						System.out.println(members.get(i).getFavRestaurant());
						System.out.println(members.get(j).getFavRestaurant());

						// matching at least one favourite
						if (members.get(i).getFavBand().contentEquals(members.get(j).getFavBand())
								|| members.get(i).getFavRestaurant().equalsIgnoreCase(members.get(j).getFavRestaurant())
								|| members.get(i).getFavSport().equals(members.get(j).getFavSport())) {
							System.out.println("Matching favourite");

							// compare each date with all others, and count matches
							for (int m = 0; m < members.get(i).getAvailable().size(); m++) {
								for (int n = 0; n < members.get(j).getAvailable().size(); n++) {

									// check if date is null and skip it
									if (members.get(i).getAvailable().get(m) == null
											|| (members.get(j).getAvailable().get(n) == null)) {
										continue;
									}
									
									System.out.println("Comparing dates");
									System.out.println(members.get(i).getAvailable().get(m));
									System.out.println(members.get(j).getAvailable().get(n));
									System.out.println(members.get(i).getAvailable().get(m)
											.compareTo(members.get(j).getAvailable().get(n)));

									// compare LocalDate with LocalDate
									if (members.get(i).getAvailable().get(m)
											.compareTo(members.get(j).getAvailable().get(n)) == 0) {

										matchCount++;
									}
								}
							}
						}
					}
				}
			}
		}
		// divide count by 2 as member1 matches member2 and member2 matches member1
		return (matchCount / 2);
	}
}
