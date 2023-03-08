package datastruct;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MyUnsortedListTest {

	UnsortedList<Integer> integers,emptyList;
	@Before
	public void init() {
		integers = MyUnsortedList.of(1,2,3,4);
		emptyList = MyUnsortedList.of();
	}
	
	@After
	public void cleanUp() {
		
	}
	
	@Test
	public void testIsEmpty() {
		assertEquals("List not Empty",false,integers.isEmpty());
		assertEquals("List Empty",true,emptyList.isEmpty());
	}
	
	@Test
	public void testSize() {
		assertEquals("Get size of not empty list",4,integers.size());
		assertEquals("Get size of empty list",0,emptyList.size());
	}
	
	@Test
	public void testPrependListNotEmpty() {
		// test prepend on not empty list
		integers.prepend(0);
		assertEquals("Add integer so new size",5,integers.size());
		UnsortedList<Integer> expected = MyUnsortedList.of(0,1,2,3,4);
		assertEquals("after preprend",expected, integers);
	}
	
	@Test
	public void testPrependListEmpty() {
		emptyList.prepend(0);
		assertEquals("Add integer so new size",1,emptyList.size());
		UnsortedList<Integer>  expected = MyUnsortedList.of(0);
		assertEquals("Add integer at first place (empty list)",expected, emptyList);
	}
	
	@Test
	public void testAppendListNotEmpty() {
		// test append on not empty list
		integers.append(7);
		assertEquals("size after append on not empty list", 5,integers.size());
		UnsortedList<Integer> expected = MyUnsortedList.of(1,2,3,4,7);
		assertEquals("Append no empty list", expected, integers);
	}
	
	@Test
	public void testAppendListEmpty() {
		// test append on empty list
		emptyList.append(6);
		assertEquals("size after append on empty list",1,emptyList.size());
		UnsortedList<Integer> expected = MyUnsortedList.of(6);
		assertEquals("Append empty list",expected, emptyList);
	}
	
	@Test
	public void testInsert() {
		// test insert on not empty list
		integers.insert(20, 0);
		integers.insert(30, integers.size());
		integers.insert(40, 2);
		UnsortedList<Integer> expected = MyUnsortedList.of(20,1,40,2, 3, 4, 30);
		assertEquals("Insert several values", expected, integers);
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testInsertException() {
		integers.insert(20, -1);
	}
	
	@Test
	public void testPop() {
		int elem = integers.pop();
		assertEquals("pop", 1, elem);
	}
	
	@Test(expected = EmptyListException.class)
	public void testPopException() {
		emptyList.pop();
	}
	
	@Test
	public void testPopLast() {
		int elem = integers.popLast();
		assertEquals("pop", 4, elem);
	}
	
	@Test(expected = EmptyListException.class)
	public void testPopLastException() {
		emptyList.popLast();
	}
	
	@Test
	public void testRemove() {
		integers.remove(integers.size()-1);
		integers.remove(0);
		UnsortedList<Integer> expected = MyUnsortedList.of(2,3);
		assertEquals("remove last and first elem of list", expected, integers);

		integers.remove(0);
		integers.remove(0);
		assertEquals("remove every elem of list", emptyList, integers);
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testRemoveException() {
		integers.remove(899);
	}
	
	@Test
	public void testEquals() {
		UnsortedList<Integer> testList = MyUnsortedList.of(1, 2, 3, 4);
		boolean isEqual = testList.equals(integers);
		assertEquals("list A = list B", true, isEqual);
		isEqual = integers.equals(emptyList);
		assertEquals("list A != empty list", false, isEqual);

	}
	
	

}
