package nz.ac.massey.cs159272.ass1.id17272381;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestCloningStudents {
	
	private Student student1 = new Student();
	private Course course = new Course();
	private Address address = new Address();
	private Date date = new Date() ;
	
	@Before 
	public void setUp()   {
		
		Calendar c = Calendar.getInstance();
		c.set(2000, 0, 30); 
		date = c.getTime();
		
		course.setCourseName("Math");
		course.setCourseNumber("111");
		
		address.setHouseNumber(0);
		address.setPostCode("000");
		address.setStreet("AAA");
		address.setTown("BBB");
		
		student1.setId("1");
		student1.setName("CCC");
		student1.setFirstName("DDD");
		student1.setDob(date);
		student1.setCourse(course);
		student1.setAddress(address);
			
	}
	@After 
	public void tearDown() {
		student1 = null;
		course = null;
		address = null;
		date =null;
	}
    @Test
    public void testClone() throws CloneNotSupportedException  {
    	
    	
    	Student student2 = (Student) student1.clone();
    	
    	assertEquals(student1,student2);
    	assertEquals(student1.hashCode(),student2.hashCode());
    	assertEquals(student1.getClass(),student2.getClass());
    	assertNotSame(student1,student2);
    	
    	assertEquals(student1.getDob(), student2.getDob());
    	assertEquals(student1.getDob().hashCode(), student2.getDob().hashCode());
    	assertEquals(student1.getDob().getClass(), student2.getDob().getClass());
    	assertNotSame(student1.getDob(), student2.getDob());
    	
    	assertEquals(student1.getAddress(), student2.getAddress());
    	assertEquals(student1.getAddress().hashCode(), student2.getAddress().hashCode());
    	assertEquals(student1.getAddress().getClass(), student2.getAddress().getClass());
    	assertNotSame(student1.getAddress(), student2.getAddress());  
    	
    	assertEquals(student1.getCourse(), student2.getCourse());
    	assertEquals(student1.getCourse().hashCode(), student2.getCourse().hashCode());
    	assertEquals(student1.getCourse().getClass(), student2.getCourse().getClass());    
    	assertSame(student1.getCourse(), student2.getCourse());
    	
    	assertEquals(student1.getId(),student2.getId());
    	assertEquals(student1.getId().hashCode(),student2.getId().hashCode());
    	assertEquals(student1.getId().getClass(),student2.getId().getClass());
    	assertSame(student1.getId(),student2.getId());
    	
    	assertEquals(student1.getName(),student2.getName());
    	assertEquals(student1.getName().hashCode(),student2.getName().hashCode());
    	assertEquals(student1.getName().getClass(),student2.getName().getClass());
    	assertSame(student1.getName(),student2.getName());
    	
    	assertEquals(student1.getFirstName(),student2.getFirstName());
    	assertEquals(student1.getFirstName().hashCode(),student2.getFirstName().hashCode());
    	assertEquals(student1.getFirstName().getClass(),student2.getFirstName().getClass());
    	assertSame(student1.getFirstName(),student2.getFirstName());
    	
    }
    
    @Test
    public void testShallowCopyForCourse() throws CloneNotSupportedException  {
    	
		Student student2 = (Student) student1.clone();
		
		course.setCourseName("PE");
		course.setCourseNumber("111");
		
		assertSame(student1.getCourse(),student2.getCourse());
		assertEquals(student1.getCourse(),student2.getCourse());
		assertEquals(student1.getCourse().getClass(),student2.getCourse().getClass());
		assertEquals(student1.getCourse().hashCode(),student2.getCourse().hashCode());
    }
    
    
	@Test 
    public void testDeepCopyForDob() throws CloneNotSupportedException  {
    	
    
		Student student2 = (Student) student1.clone();
		
		Calendar c = Calendar.getInstance();
		c.set(1900, 0, 30); 
		date = c.getTime();
		
		student1.setDob(date);
		
		assertNotEquals(student1.getDob(), student2.getDob());
		assertNotSame(student1.getDob(), student2.getDob());
		assertEquals(student1.getDob().getClass(), student2.getDob().getClass());
		assertNotEquals(student1.getDob().hashCode(), student2.getDob().hashCode());
    	
    }
    
    @Test
    public void testDeepCopyForAddress() throws CloneNotSupportedException  {
    	
    	
		Student student2 = (Student) student1.clone();
		
		address.setHouseNumber(1);
		address.setPostCode("111");
		address.setStreet("CCC");
		address.setTown("DDD");
			
		
		assertNotSame(student1.getAddress(),student2.getAddress());
		assertNotEquals(student1.getAddress(),student2.getAddress());
		assertEquals(student1.getAddress().getClass(),student2.getAddress().getClass());
		assertNotEquals(student1.getAddress().hashCode(),student2.getAddress().hashCode());
    	
    }
   
    
    
    
    
    
}
