public class TestDriver {
	public static void main(String[] args) 	{
		// Create an instance of the WorkbookTabs class
		Shinnors_workbookTabs Book1 = new Shinnors_workbookTabs();
		System.out.println("An instance of the class WorkbookTabs is created without passing any parameters");
		System.out.println("This creates a String Array of size 256 to store the sheet names");
		System.out.println();
		
		//Demonstrating Display Method
		System.out.println("Displaying the current list using Display Method...");
		Book1.display();
		System.out.println("As can been seen, the 3 default sheets are listed");
		System.out.println();
		
		// Create an instance of the WorkbookTabs class with array size 10 for testing purposes
		Shinnors_workbookTabs Book2 = new Shinnors_workbookTabs(10);
		System.out.println("An instance of the class WorkbookTabs is created by passing the integer 10 as parameter");
		System.out.println("This creates a String Array of size 10 to store the sheet names to test our methods");	
		System.out.println();
		System.out.println("Displaying the current list using Display Method...");
		Book2.display();
		System.out.println();
		
		//Demonstrating add Method
		System.out.println("Adding 8 items by using add Method and also tracking the current size of the list using length Method");
		for(int i = 0; i<8;i++)
		{
			if(Book2.add())
			{
				System.out.println("Successful add...");
				System.out.println("Current length of list is " + Book2.length());
			}
			else
			{
				System.out.println("Unsuccessful add...");
				System.out.println("Current length of list is " + Book2.length());
			}
		}
		System.out.println();
		System.out.println("Displaying the current list using Display Method...");
		Book2.display();
		System.out.println();
		
		
		//Demonstrating remove Method
		System.out.println("Removing existing sheetname \"sheet3\" using remove Method");
		System.out.println(Book2.remove("sheet3"));
		System.out.println();
		System.out.println("Displaying the current list using Display Method...");
		Book2.display();
		System.out.println();
		System.out.println("Removing non-existing sheetname \"FindMe\" using remove Method");
		System.out.println(Book2.remove("FindMe"));
		System.out.println();
		System.out.println("Displaying the current list using Display Method...");
		Book2.display();
		System.out.println();
		System.out.println("Removing sheetname at index 8 within the current range (0-8) using remove Method");
		System.out.println(Book2.remove(8));
		System.out.println();
		System.out.println("Displaying the current list using Display Method...");
		Book2.display();
		System.out.println();
		System.out.println("Removing sheetname at index 8 outside the current range (0-7) using remove Method");
		System.out.println(Book2.remove(8));
		System.out.println();
		System.out.println("Displaying the current list using Display Method...");
		Book2.display();
		System.out.println();
		System.out.println("Removing sheets from index position 0, 8 times using remove Method");
		for(int i = 0; i<8;i++)
		{
			if(Book2.remove(0) != null)
			{
				System.out.println("Successful remove...");
				System.out.println("Current length of list is " + Book2.length());
			}
			else
			{
				System.out.println("Unsuccessful remove...");
				System.out.println("Current length of list is " + Book2.length());
			}
		}
		System.out.println();
		System.out.println("Displaying the current list using Display Method...");
		Book2.display();
		System.out.println();
		
	    //Demonstrating add Method
		System.out.println("Adding 8 items by using add Method and also tracking the current size of the list using length Method");
		for(int i = 0; i<8;i++)
		{
			if(Book2.add())
			{
				System.out.println("Successful add...");
				System.out.println("Current length of list is " + Book2.length());
			}
			else
			{
				System.out.println("Unsuccessful add...");
				System.out.println("Current length of list is " + Book2.length());
			}
		}
		System.out.println();
		System.out.println("Displaying the current list using Display Method...");
		Book2.display();
		System.out.println();
		
		
		
		System.out.println("Removing existing sheetname \"sheeT15\" using remove Method");
		System.out.println(Book2.remove("sheeT15"));
		System.out.println();
		System.out.println("Displaying the current list using Display Method...");
		Book2.display();
		System.out.println();
	
		//Demonstrating moveToEnd Method using sheet name
		System.out.println("Moving existing sheetname \"Sheet16\" to the end of the list using moveToEnd Method");
		System.out.println(Book2.moveToEnd("Sheet16"));
		System.out.println();
		System.out.println("Displaying the current list using Display Method...");
		Book2.display();
		System.out.println();
		System.out.println("Moving existing sheetname \"SHeet18\" to the end of the list using moveToEnd Method");
		System.out.println(Book2.moveToEnd("Sheet18"));
		System.out.println();
		System.out.println("Displaying the current list using Display Method...");
		Book2.display();
		System.out.println();
		System.out.println("Moving non-existing sheetname \"Non\" to the end of the list using moveToEnd Method");
		System.out.println(Book2.moveToEnd("Non"));
		System.out.println();
		System.out.println("Displaying the current list using Display Method...");
		Book2.display();
		System.out.println();
		
		//Demonstrating moveToEnd Method using index
		System.out.println("Moving sheet name at index 5 within the range (0-9) to the end of the list using moveToEnd Method");
		System.out.println(Book2.moveToEnd(5));
		System.out.println();
		System.out.println("Displaying the current list using Display Method...");
		Book2.display();
		System.out.println();
		System.out.println("Moving sheet name at index 9 within the range (0-9) to the end of the list using moveToEnd Method");
		System.out.println(Book2.moveToEnd(9));
		System.out.println();
		System.out.println("Displaying the current list using Display Method...");
		Book2.display();
		System.out.println();
		System.out.println("Moving sheet name at index 10 outside the range (0-9) to the end of the list using moveToEnd Method");
		System.out.println(Book2.moveToEnd(10));
		System.out.println();
		System.out.println("Displaying the current list using Display Method...");
		Book2.display();
		System.out.println();
		
		//Demonstrating move Method using index and before
		System.out.println("Moving sheet name at index 1 within the range (0-9) to before the sheet name at index 5 using move Method");
		System.out.println(Book2.move(1,5,true));
		System.out.println();
		System.out.println("Displaying the current list using Display Method...");
		Book2.display();
		System.out.println();
		System.out.println("Moving sheet name at index 8 within the range (0-9) to before the sheet name at index 2 using move Method");
		System.out.println(Book2.move(8,2,true));
		System.out.println();
		System.out.println("Displaying the current list using Display Method...");
		Book2.display();
		System.out.println();
		System.out.println("Moving sheet name at index 2 within the range (0-9) to before the sheet name at index 12 (outside) using move Method");
		System.out.println(Book2.move(2,12,true));
		System.out.println();
		System.out.println("Displaying the current list using Display Method...");
		Book2.display();
		System.out.println();
		System.out.println("Moving sheet name at index 10 outside the range (0-9) to before the sheet name at index 2 (within) using move Method");
		System.out.println(Book2.move(10,2,true));
		System.out.println();
		System.out.println("Displaying the current list using Display Method...");
		Book2.display();
		System.out.println();
		System.out.println("Moving sheet name at index 0 within the range (0-9) to before the sheet name at index 0 (same) using move Method");
		System.out.println(Book2.move(0,0,true));
		System.out.println();
		System.out.println("Displaying the current list using Display Method...");
		Book2.display();
		System.out.println();
		
		//Demonstrating move Method using index and after
		System.out.println("Moving sheet name at index 1 within the range (0-9) to after the sheet name at index 5 using move Method");
		System.out.println(Book2.move(1,5,false));
		System.out.println();
		System.out.println("Displaying the current list using Display Method...");
		Book2.display();
		System.out.println();
		System.out.println("Moving sheet name at index 8 within the range (0-9) to after the sheet name at index 2 using move Method");
		System.out.println(Book2.move(8,2,false));
		System.out.println();
		System.out.println("Displaying the current list using Display Method...");
		Book2.display();
		System.out.println();
		System.out.println("Moving sheet name at index 2 within the range (0-9) to after the sheet name at index 12 (outside) using move Method");
		System.out.println(Book2.move(2,12,false));
		System.out.println();
		System.out.println("Displaying the current list using Display Method...");
		Book2.display();
		System.out.println();
		System.out.println("Moving sheet name at index 10 outside the range (0-9) to after the sheet name at index 2 (within) using move Method");
		System.out.println(Book2.move(10,2,false));
		System.out.println();
		System.out.println("Displaying the current list using Display Method...");
		Book2.display();
		System.out.println();
		System.out.println("Moving sheet name at index 0 within the range (0-9) to after the sheet name at index 0 (same) using move Method");
		System.out.println(Book2.move(0,0,false));
		System.out.println();
		System.out.println("Displaying the current list using Display Method...");
		Book2.display();
		System.out.println();
		
		//Demonstrating move Method using sheet name and before
		System.out.println("Moving existing sheet name \"LASt1\" before existing sheet name \"SheeT17\" using move Method");
		System.out.println(Book2.move("LASt1","SheeT17",true));
		System.out.println();
		System.out.println("Displaying the current list using Display Method...");
		Book2.display();
		System.out.println();
		System.out.println("Moving existing sheet name \"SheeT17\" before existing sheet name \"Sheet16\" using move Method");
		System.out.println(Book2.move("SheeT17","Sheet16",true));
		System.out.println();
		System.out.println("Displaying the current list using Display Method...");
		Book2.display();
		System.out.println();
		System.out.println("Moving non-existing sheet name \"First1\" before existing sheet name \"SheeT17\" using move Method");
		System.out.println(Book2.move("First1","SheeT17",true));
		System.out.println();
		System.out.println("Displaying the current list using Display Method...");
		Book2.display();
		System.out.println();
		System.out.println("Moving existing sheet name \"LASt1\" before non-existing sheet name \"SheeT1\" using move Method");
		System.out.println(Book2.move("LASt1","SheeT1",true));
		System.out.println();
		System.out.println("Displaying the current list using Display Method...");
		Book2.display();
		System.out.println();
		System.out.println("Moving existing sheet name \"LASt1\" before existing sheet name \"LaSt1\" using move Method");
		System.out.println(Book2.move("LASt1","LaSt1",true));
		System.out.println();
		System.out.println("Displaying the current list using Display Method...");
		Book2.display();
		System.out.println();
		
		//Demonstrating move Method using sheet name and after
		System.out.println("Moving existing sheet name \"LASt1\" after existing sheet name \"SheeT17\" using move Method");
		System.out.println(Book2.move("LASt1","SheeT17",false));
		System.out.println();
		System.out.println("Displaying the current list using Display Method...");
		Book2.display();
		System.out.println();
		System.out.println("Moving existing sheet name \"SheeT17\" after existing sheet name \"Sheet19\" using move Method");
		System.out.println(Book2.move("SheeT17","Sheet19",false));
		System.out.println();
		System.out.println("Displaying the current list using Display Method...");
		Book2.display();
		System.out.println();
		System.out.println("Moving non-existing sheet name \"First1\" after existing sheet name \"SheeT17\" using move Method");
		System.out.println(Book2.move("First1","SheeT17",false));
		System.out.println();
		System.out.println("Displaying the current list using Display Method...");
		Book2.display();
		System.out.println();
		System.out.println("Moving existing sheet name \"LASt1\" after non-existing sheet name \"SheeT1\" using move Method");
		System.out.println(Book2.move("LASt1","SheeT1",false));
		System.out.println();
		System.out.println("Displaying the current list using Display Method...");
		Book2.display();
		System.out.println();
		System.out.println("Moving existing sheet name \"LASt1\" after existing sheet name \"LaSt1\" using move Method");
		System.out.println(Book2.move("LASt1","LaSt1",false));
		System.out.println();
		System.out.println("Displaying the current list using Display Method...");
		Book2.display();
		System.out.println();
	}
}
