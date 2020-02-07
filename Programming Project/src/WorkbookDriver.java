// A test driver for the Workbook class
public class WorkbookDriver {

	public static void main(String[] args) {
		
		// Test constructors and toString()
		System.out.println("Test constructors and toString()");
		Workbook wb = new Workbook();
		Workbook wb1 = new Workbook();
		System.out.println(wb);
		System.out.println(wb1);
		Workbook wb2 = new Workbook(10);
		wb2.display();
		System.out.println(wb2);
				
		// Test index() and sheetName()
		System.out.println("\nTest index() and sheetName()");
		System.out.println("Sheet2 is in position: " + wb.index("Sheet2"));
		System.out.println("Sheet9 is in position (N/A): " + wb2.index("Sheet9"));
		System.out.println("The sheet on the third position is: " + wb.sheetName(3));
		System.out.println("The sheet on the sixth position is (N/A): " + wb2.sheetName(6));
		
		// Test display() and length()
		System.out.println("\nTest display() and length()");
		wb1.display();
		wb2.display();
		System.out.printf("The first workbook has %03d sheets at the moment.\n", wb.length());
		
		// Test add(), remove(pos) and remove("sheetName")
		System.out.println("\nTest add(), remove(pos) and remove(\"sheetName\")");		
		System.out.println("Removing sheet from 'wb1' 1st position. The removed sheet is: " + wb1.remove(1));
		System.out.println("Removing \"Sheet3\" from 'wb1'. The sheet is remved from position: " + wb1.remove("Sheet3"));
		System.out.println("Removing sheet from 'wb1' 4th position(N/A). The removed sheet is: " + wb1.remove(4));
		System.out.print("'wb1': ");
		wb1.display();
		System.out.println("Adding a new sheet to 'wb' was successfull: " + wb.add());
		System.out.println("Adding a new sheet to 'wb' was successfull: " + wb.add());
		System.out.println("Adding a new sheet to 'wb2'(N/A) was successfull: " + wb2.add());
		System.out.print("After adding 2 extra sheets, 'wb': ");
		wb.display();
		System.out.println("After filling wb2");
		for(int i = 0; i < 10; i++) {
			System.out.println(wb2.add());
		}
		wb2.display();
		
		// Test move(pos) and move("sheetName")
		System.out.println("\nTest move(pos) and move(\"sheetName\")");
		System.out.println("Moving sheet from position 5 to before pos 1. The name of the moved sheet is: " + wb2.move(5, 1, true));
		wb2.display();
		System.out.println("Moving \"Sheet2\" after \"Sheet3\". The original position of the moved sheet was: " + wb2.move("Sheet2", "Sheet3", false));
		System.out.println("Moving sheet form position 3 to before pos 8 (N/A). The name of the moved sheet is: " + wb1.move(3, 8, true));
		System.out.println("Moving \"CS4141\" (N/A) after \"Sheet2\". The moved sheet's position was: " + wb2.move("CS4141", "Sheet2", false));
		System.out.println("Moving \"Sheet2\"  after \"Sheet2\" (N/A). The moved sheet's position was: " + wb2.move("Sheet2", "Sheet2", false));
		wb2.display();
		
		// Test moveToEnd(pos) and moveToEnd("sheetName")
		System.out.println("\nTest moveToEnd(pos) and moveToEnd(\"sheetName\")");
		System.out.println("Moving sheet from position 3 to end. The name of the moved sheet is: " + wb2.moveToEnd(3));
		wb2.display();
		System.out.println("Moving \"Sheet1\" to end. The original position of the moved sheet was: " + wb2.moveToEnd("Sheet1"));
		System.out.println("Moving \"Sheet1\" to end (N/A). The original position of the moved sheet was: " + wb2.moveToEnd("Sheet1"));
		System.out.println("Moving sheet from position 0 (N/A) to end. The name of the moved sheet is: " + wb2.moveToEnd(0));
		System.out.println("Moving \"CS4141\" (N/A) to end. The original position of the moved sheet was: " + wb2.moveToEnd("CS4141"));
		wb2.display();
	
		// Test rename("currentName","newName") and add()
		System.out.println("\nTest rename(\"currentName\",\"newName\") and add()");
		System.out.println("Renaming \"Sheet5\" to \"CS4141\". The position of the renamed sheet is: " + wb2.rename("Sheet5", "CS4141"));
		System.out.println("Renaming \"Sheet2\" to \"Sheet6\". The position of the renamed sheet is: " + wb2.rename("Sheet2", "Sheet6"));
		System.out.println("Renaming \"Sheet4\" to \"SHEet6\" (N/A). The position of the renamed sheet is: " + wb2.rename("Sheet4", "SHEet6"));
		System.out.println("Renaming \"Sheet5\" to \"she@\" (N/A). The position of the renamed sheet is: " + wb2.rename("Sheet5", "she@"));
		wb2.display();
		System.out.println("\nBefore renaming, the highest sheet number was \"Sheet5\". " +
						   "We renamed a sheet to \"Sheet6\". We cannot create a new sheet with already existing name." + 
						   "\nWe can still add new sheet with unique name: " + wb2.add());
		wb2.display();	
	}	
}