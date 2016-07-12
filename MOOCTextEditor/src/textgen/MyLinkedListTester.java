/**
 * 
 */
package textgen;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

/**
 * @author UC San Diego MOOC team
 *
 */
public class MyLinkedListTester {

	private static final int LONG_LIST_LENGTH =10; 

	MyLinkedList<String> shortList;
	MyLinkedList<Integer> emptyList;
	MyLinkedList<Integer> longerList;
	MyLinkedList<Integer> list1;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		// Feel free to use these lists, or add your own
	    shortList = new MyLinkedList<String>();
		shortList.add("A");
		shortList.add("B");
		emptyList = new MyLinkedList<Integer>();
		longerList = new MyLinkedList<Integer>();
		for (int i = 0; i < LONG_LIST_LENGTH; i++)
		{
			longerList.add(i);
		}
		list1 = new MyLinkedList<Integer>();
		list1.add(65);
		list1.add(21);
		list1.add(42);
		
	}

	/** Test if the get method is working correctly.
	 */
	/*You should not need to add much to this method.
	 * We provide it as an example of a thorough test. */
	@Test
	public void testGet()
	{
		//test empty list, get should throw an exception
		try {
			emptyList.get(0);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		
		// test short list, first contents, then out of bounds
		assertEquals("Check first", "A", shortList.get(0));
		assertEquals("Check second", "B", shortList.get(1));
		
		try {
			shortList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			shortList.get(2);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		// test longer list contents
		for(int i = 0; i<LONG_LIST_LENGTH; i++ ) {
			assertEquals("Check "+i+ " element", (Integer)i, longerList.get(i));
		}
		
		// test off the end of the longer array
		try {
			longerList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			longerList.get(LONG_LIST_LENGTH);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		
	}
	
	/** Test setting an element in the list */
	@Test
	public void testSet()
	{
	    // TODO: implement this test
		int a = list1.set(0, 30);
		assertEquals("Check set(0,30) replacement returning", 65, a);
		assertEquals("Check set(0,30) replaced element", (Integer) 30, list1.get(0));
		a = list1.set(list1.size() -1, 22);
		assertEquals("Check set(list1.size() -1, 22) replacement last returning", a, 42);
		assertEquals("Check set(list1.size() -1, 22) replacement last element", (Integer)22, list1.get(list1.size()-1));
		
		// incorrect set tests
		try {
			emptyList.set(0, 11);
			fail("Already empty emptyList.set(0, 11)");
		}
		catch(IndexOutOfBoundsException e) {
			
		}
		try {
			shortList.set(shortList.size(), "22");
			fail("Out of bounds shortList.set(shortList.size(), 22)");
		}
		catch(IndexOutOfBoundsException e) {
			
		}
		try {
			shortList.set(-1, "33");
			fail("Out of bounds shortList.set(-1, 33)");
		}
		catch(IndexOutOfBoundsException e) {
			
		}
		try {
			this.longerList.set(1, null);
			fail("Check null element longerList.add(1, null)");
		} 
		catch(NullPointerException e) {
			
		}

		//System.out.println("testSet " + list1);
		
	    
	}
	
	/** Test the size of the list */
	@Test
	public void testSize()
	{
		// TODO: implement this test
		assertEquals("Check size of list1", 3, this.list1.size());
		//System.out.println(this.list1);
		assertEquals("Check size of longList", LONG_LIST_LENGTH, this.longerList.size());
		//System.out.println(this.longerList);
		assertEquals("Check size of emptyList", 0, this.emptyList.size());
	}
	
	/** Test adding an element into the list at a specified index,
	 * specifically:
	 * public void add(int index, E element)
	 * */
	@Test
	public void testAddAtIndex()
	{
        // TODO: implement this test
		shortList.add(1, "C"); // insert between 'A','B' list
		shortList.add(shortList.size(), "D"); // add to the end
		shortList.add(0, "E"); // insert into head 'A','C','B','D' list

		assertEquals("Check add 3 element", "C", shortList.get(2));     // must be 'E','A','C','B','D'
		assertEquals("Check add last element", "D", shortList.get(4));  // must be 'E','A','C','B','D'
		assertEquals("Check add first element", "E", shortList.get(0)); // must be 'E','A','C','B','D'

		// check incorrect conditions
		try {
			this.longerList.add(-1, 10);
			fail("Check negative index longerList.add(-1, 10)");
		} 
		catch(IndexOutOfBoundsException e) {
			
		}
		try {
			this.longerList.add(longerList.size() + 1, 10);
			fail("Check out of band index longerList.add(longerList.size() + 1, 10)");
		} 
		catch(IndexOutOfBoundsException e) {
			
		}
		try {
			this.longerList.add(1, null);
			fail("Check null element longerList.add(1, null)");
		} 
		catch(NullPointerException e) {
			
		}
		
		//System.out.println(longerList);
		
	}

	/** Test adding an element into the end of the list, specifically
	 *  public boolean add(E element)
	 * */
	@Test
	public void testAddEnd()
	{
        // TODO: implement this test
		this.list1.add(99);
		assertEquals("add 99 to list1", (Integer) 99, this.list1.get(this.list1.size()-1));
		//System.out.println(this.list1);
		
		this.emptyList.add(100);
		assertEquals("add 100 to emptyList", (Integer) 100, this.emptyList.get(this.emptyList.size()-1));
		//System.out.println(emptyList);
		
		try {
			this.longerList.add(null);
			fail("Check null element longerList.add(null)");
		} 
		catch(NullPointerException e) {
			
		}

	}

	/** Test removing an element from the list.
	 * We've included the example from the concept challenge.
	 * You will want to add more tests.  */
	@Test
	public void testRemove()
	{
		//System.out.println(this.list1);
		int a = list1.remove(0);
		//System.out.println(this.list1);

		
		assertEquals("Remove: check a is correct ", 65, a);
		assertEquals("Remove: check element 0 is correct ", (Integer)21, list1.get(0));
		assertEquals("Remove: check size is correct ", 2, list1.size());
		//System.out.println(this.list1);

		// TODO: Add more tests here
		int b = list1.remove(list1.size()-1);
		assertEquals("Remove end element", 42, b);
		assertEquals("Remove: check size is correct ", 1, list1.size());
		//System.out.println(this.list1);		
		
		int c = list1.remove(list1.size()-1);
		assertEquals("Remove end element", 21, c);
		assertEquals("Remove: check size is correct ", 0, list1.size());
		//System.out.println(this.list1);
		
		// incorrect remove tests
		try {
			list1.remove(0);
			fail("Already empty list1.remove(0)");
		}
		catch(IndexOutOfBoundsException e) {
			
		}
		try {
			shortList.remove(shortList.size());
			fail("Out of bounds shortList.remove(shortList.size())");
		}
		catch(IndexOutOfBoundsException e) {
			
		}
		try {
			shortList.remove(-1);
			fail("Out of bounds shortList.remove(-1)");
		}
		catch(IndexOutOfBoundsException e) {
			
		}
		
	}

	
	// TODO: Optionally add more test methods.
	
}
