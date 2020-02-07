import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * @author	Laszlo Szlatki
 * @date	23/11/2019
 * @project	CS4141
 */
class JUnit_WorkbookTest {
	
	Workbook wb;
	Workbook wbShort;
	
	@BeforeEach
	void setUp() {
		wb = new Workbook(256);
		wbShort = new Workbook(5);
		wbShort.add();
		wbShort.add();
	}
	
	@ParameterizedTest(name = "{0}, {1}, {2}")
	@CsvSource({
		"Sheet1, Sheet8, 1",
		"Sheet3, CS4141, 3",
		"Sheet5, sheet5, -1",
		"Sheet7, CS4141, -1",
		"CS4141, HelloWorld, -1",
		"Sheet1, CS#4141, -1",
		"Sheet1, sheet2, -1",
		
	})
	void canRename(String currentName, String newName, int expectedSheetPosition) {
		int sheetPosition = wb.rename(currentName, newName);
		assertEquals(expectedSheetPosition, sheetPosition);
	}	

	@ParameterizedTest(name = "{index} Workbook ({0}, {1}")
	@CsvSource({
		"Sheet1, 1",
		"shEET3, 3",
		"sHeeT5, -1",
		"Sheet9, -1",
		"CS4141, -1",
		
	})
	void canMoveToEndStringInput(String from, int expectedSheetPosition) {
		int sheetPosition = wbShort.moveToEnd(from);
		assertEquals(expectedSheetPosition, sheetPosition);
	}	
	
	@ParameterizedTest(name = "{index} Workbook ({0}, {1}")
	@CsvSource({
		"1, Sheet1",
		"3, Sheet3",
		"5, ",
		"8, ",
		"9, ",
		
	})
	void canMoveToEndIntInput(int from, String expectedSheetName) {
		String sheetName = wbShort.moveToEnd(from);
		assertEquals(expectedSheetName, sheetName);
	}
	
	@ParameterizedTest(name = "{index} Workbook ({0}, {1}, {2}) is {3}")
	@CsvSource({
		"Sheet1, Sheet5, true, 4",
		"Sheet1, Sheet5, false, 5",
		"Sheet5, Sheet1, true, 1",
		"Sheet5, Sheet1, false, 2",
		"Sheet1, sheet2, true, -1",
		"shEET3, sheet5, false, 5",
		"sHeeT2, ShEEt4, true, 3",
		"sheet5, sheet4, false, -1",
		"Sheet2, CS4141, true, -1",
		"Sheet4, Sheet1, false, 2",
		"Sheet2, Sheet2, true, -1",
		"cs4141, Sheet1, true, -1",
		
	})
	void canMoveStringInput(String from, String to, boolean before, int expectedSheetPosition) {
		int sheetPosition = wbShort.move(from, to, before);
		assertEquals(expectedSheetPosition, sheetPosition);
	}
	
	@ParameterizedTest(name = "{index} Workbook ({0}, {1}, {2}) is {3}")
	@CsvSource({
		"1, 2, true, ",
		"1, 5, true, Sheet1",
		"1, 5, false, Sheet1",
		"5, 1, true, Sheet5",
		"5, 1, false, Sheet5",
		"3, 5, false, Sheet3",
		"2, 4, true, Sheet2",
		"2, 6, true, ",
		"2, 2, false, ",
		"4, 2, false, Sheet4",
		"4, 1, true, Sheet4",
		"3, 2, false, ",
		"8, 3, true, ",
		
	})
	void canMoveIntInput(int from, int to, boolean before, String expectedSheetName) {
		String sheetName = wbShort.move(from, to, before);
		assertEquals(expectedSheetName, sheetName);
	}
	
	@Test
	void canRemoveStringInput() {
		String[] expected = new String[256];
		expected[0] = "Sheet3";
		int rem1 = wb.remove("Sheet2");
		int rem2 = wb.remove("Sheet1");
		int rem3 = wb.remove("Sheet3");
		String[] result = wb.toArray();
		assertArrayEquals(expected, result);
		assertEquals(2, rem1);
		assertEquals(1, rem2);
		assertEquals(-1, rem3);
	}
	
	@Test
	void canRemoveIntInput() {
		String[] expected = new String[256];
		expected[0] = "Sheet3";
		String rem1 = wb.remove(1);
		String rem2 = wb.remove(1);
		String rem3 = wb.remove(1);
		String[] result = wb.toArray();
		assertArrayEquals(expected, result);
		assertEquals("Sheet1", rem1);
		assertEquals("Sheet2", rem2);
		assertEquals(null, rem3);
	}
	
	@Test
	void canNotAdd() {
		String[] expected = new String[5];
		expected[0] = "Sheet1";
		expected[1] = "Sheet2";
		expected[2] = "Sheet3";
		expected[3] = "Sheet4";
		expected[4] = "Sheet5";		
		boolean added = wbShort.add();
		String[] result = wbShort.toArray();
		assertArrayEquals(expected, result);
		assertEquals(false, added);
	}
	
	@Test
	void canAdd() {
		String[] expected = new String[256];
		expected[0] = "Sheet1";
		expected[1] = "Sheet2";
		expected[2] = "Sheet3";
		expected[3] = "Sheet4";
		boolean added = wb.add();
		String[] result = wb.toArray();
		assertArrayEquals(expected, result);
		assertEquals(true, added);
	}
	
	@Test
	void canGetLength() {
		int result = wb.length();
		assertEquals(3, result);
	}
	
	@ParameterizedTest(name = "{0}, {1}")
	@CsvSource({
		"1, Sheet1",
		"3, Sheet3",
		"2, Sheet2",
		"6, ",
		"0, ",
		
	})
	void testSheetName(int index, String expectedSheetName) {
		wb.sheetName(index);		
	}	
	
	@ParameterizedTest(name = "{0}, {1}")
	@CsvSource({
		"Sheet1, 1",
		"Sheet3, 3",
		"Sheet2, 2",
		"Sheet6, -1",
		"Sheet0, -1",
		"SHEET2, 2",
		
	})
	void testIndex(String sheetName, int expectedIndex) {
		wb.index(sheetName);		
	}	
	
	@Test
	void canCreateWorkbook() {
		String[] expected = new String[256];
		expected[0] = "Sheet1";
		expected[1] = "Sheet2";
		expected[2] = "Sheet3";
		String[] result = wb.toArray();
		assertArrayEquals(expected, result);
	}
}
