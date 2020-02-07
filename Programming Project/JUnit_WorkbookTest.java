/**
 * @author	Laszlo Szlatki
 * @date	02/01/2020
 * @project	mimicking the working of Excel tabs
 */
package com.szlatki.excel.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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
		"Sheet1, Sheet8, 0",
		"Sheet3, CS4141, 2",
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
		"Sheet1, 0",
		"Sheet2, 1",
		"shEET3, 2",
		"Sheet4, 3",
		"Sheet5, -1",
		"Sheet9, -1",
		"CS4141, -1",
		
	})
	void canMoveToEndStringInput(String from, int expectedSheetPosition) {
		int sheetPosition = wbShort.moveToEnd(from);
		assertEquals(expectedSheetPosition, sheetPosition);
	}	
	
	@ParameterizedTest(name = "{index} Workbook ({0}, {1}")
	@CsvSource({
		"0, Sheet1",
		"1, Sheet2",
		"2, Sheet3",
		"3, Sheet4",
		"4, ",
		"5, ",
		"8, ",
		"-1, ",
		
	})
	void canMoveToEndIntInput(int from, String expectedSheetName) {
		String sheetName = wbShort.moveToEnd(from);
		assertEquals(expectedSheetName, sheetName);
	}
	
	@ParameterizedTest(name = "{index} Workbook ({0}, {1}, {2}) is {3}")
	@CsvSource({
		"Sheet1, Sheet5, true, 3",
		"Sheet1, Sheet5, false, 4",
		"Sheet5, Sheet1, true, 0",
		"Sheet5, Sheet1, false, 1",
		"Sheet1, sheet2, true, -1",		// before the following
		"sheet5, sheet4, false, -1",	// after the preceding
		"shEET3, sheet5, false, 4",
		"sHeeT2, ShEEt4, true, 2",	
		"Sheet4, Sheet1, false, 1",
		"Sheet2, CS4141, true, -1",
		"Sheet2, Sheet2, true, -1",
		"cs4141, Sheet1, true, -1",
		
	})
	void canMoveStringInput(String from, String to, boolean before, int expectedSheetPosition) {
		int sheetPosition = wbShort.move(from, to, before);
		assertEquals(expectedSheetPosition, sheetPosition);
	}
	
	@ParameterizedTest(name = "{index} Workbook ({0}, {1}, {2}) is {3}")
	@CsvSource({
		"0, 4, true, Sheet1",
		"0, 4, false, Sheet1",
		"4, 0, true, Sheet5",
		"4, 0, false, Sheet5",
		"2, 4, false, Sheet3",
		"1, 3, true, Sheet2",
		"1, 5, true, ",
		"1, 1, false, ",
		"3, 1, false, Sheet4",
		"3, 0, true, Sheet4",
		"0, 1, true, ",			// before the following
		"2, 1, false, ",		// after the preceding
		"7, 2, true, ",
		
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
		assertEquals(1, rem1);
		assertEquals(0, rem2);
		assertEquals(-1, rem3);
	}
	
	@Test
	void canRemoveIntInput() {
		String[] expected = new String[256];
		expected[0] = "Sheet3";
		String rem1 = wb.remove(0);
		String rem2 = wb.remove(0);
		String rem3 = wb.remove(0);
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
		"0, Sheet1",
		"2, Sheet3",
		"1, Sheet2",
		"6, ",
		"-1, ",
		
	})
	void testSheetName(int index, String expectedSheetName) {
		String sheetName = wb.sheetName(index);		
		assertEquals(expectedSheetName, sheetName);
	}	
	
	@ParameterizedTest(name = "{0}, {1}")
	@CsvSource({
		"Sheet1, 0",
		"Sheet3, 2",
		"Sheet2, 1",
		"SHEET2, 1",
		"Sheet6, -1",
		"Sheet0, -1",
		
	})
	void testIndex(String sheetName, int expectedIndex) {
		int index = wb.index(sheetName);	
		assertEquals(expectedIndex, index);
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
