package nz.ac.massey.cs159272.ass1.id17272381;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestPersistency {
	
	private Student student1 = new Student();
	private Student student2 = new Student();
	private Collection<Student> students1 = new ArrayList<>();
	private Collection<Student> students2 = new ArrayList<>();
	private Collection<Student> manyStudents = new ArrayList<>();
	private Course course = new Course();
	private Address address1 = new Address();
	private Address address2 = new Address();
	private Date date = new Date();
	
	@Before 
	public void setUp() throws Exception  {
		
		course.setCourseName("A" );
		course.setCourseNumber("111");
		
		address1.setHouseNumber(0);
		address1.setPostCode("000");
		address1.setStreet("AAA");
		address1.setTown("BBB");
		
		Calendar c = Calendar.getInstance();
		c.set(1900, 0, 30); 
		date = c.getTime();

		student1.setId("1");
		student2.setId("2");
		student1.setName("");
		student2.setName("");
		student1.setFirstName("");
		student2.setFirstName("");
		student1.setCourse(course);
		student2.setCourse(course);
		student1.setAddress(address1);
		student2.setAddress(address2);
		student1.setDob(date);
		student2.setDob(date);
		
		//add two different students
		students1.add(student1);
		students1.add(student2);
		
		for (int i=0 ; i < 10000; i++) {
			
			manyStudents.add(student1.clone());
			
		}
		
	}
	
	@After 
	public void tearDown() {
		student1 = null;
		student2 = null;
		course = null;
		address1 = null;
		address2 = null;
		students1 =null;
		students2 =null;
	}
	private void testContent() {
		

		assertEquals (students1, students2);
		assertEquals (students1.size(), students2.size());
		assertNotSame(students1 ,students2);
		assertEquals (students1.hashCode(), students2.hashCode());
		
		assertNotSame(((ArrayList<Student>) students2).get(0).getId(), 
				((ArrayList<Student>) students2).get(1).getId());
		assertNotEquals (((ArrayList<Student>) students2).get(0).getId(), 
					((ArrayList<Student>) students2).get(1).getId() );		
		assertNotEquals (((ArrayList<Student>) students2).get(0).getId().hashCode(), 
				((ArrayList<Student>) students2).get(1).getId().hashCode() );	
		assertEquals (((ArrayList<Student>) students2).get(0).getId().getClass(), 
				((ArrayList<Student>) students2).get(1).getId().getClass() );
		
		assertSame(((ArrayList<Student>) students2).get(0).getName(), 
				((ArrayList<Student>) students2).get(1).getName());
		assertEquals (((ArrayList<Student>) students2).get(0).getName() ,
					((ArrayList<Student>) students2).get(1).getName() );	
		assertEquals (((ArrayList<Student>) students2).get(0).getName().hashCode() ,
				((ArrayList<Student>) students2).get(1).getName().hashCode() );
		assertEquals (((ArrayList<Student>) students2).get(0).getName().getClass() ,
				((ArrayList<Student>) students2).get(1).getName().getClass() );
		
		assertSame(((ArrayList<Student>) students2).get(0).getFirstName(), 
				((ArrayList<Student>) students2).get(1).getFirstName());
		assertEquals (((ArrayList<Student>) students2).get(0).getFirstName(), 
				((ArrayList<Student>) students2).get(1).getFirstName());	
		assertEquals (((ArrayList<Student>) students2).get(0).getFirstName().hashCode(), 
				((ArrayList<Student>) students2).get(1).getFirstName().hashCode());
		assertEquals (((ArrayList<Student>) students2).get(0).getFirstName().getClass(), 
				((ArrayList<Student>) students2).get(1).getFirstName().getClass());
		
		assertSame(((ArrayList<Student>) students2).get(0).getDob(), 
					((ArrayList<Student>) students2).get(1).getDob());
		assertEquals (((ArrayList<Student>) students2).get(0).getDob(), 
				((ArrayList<Student>) students2).get(1).getDob());
		assertEquals (((ArrayList<Student>) students2).get(0).getDob().hashCode(), 
				((ArrayList<Student>) students2).get(1).getDob().hashCode());
		assertEquals (((ArrayList<Student>) students2).get(0).getDob().getClass(), 
				((ArrayList<Student>) students2).get(1).getDob().getClass());
		
		assertNotSame(((ArrayList<Student>) students2).get(0).getAddress(), 
						((ArrayList<Student>) students2).get(1).getAddress());
		assertNotEquals (((ArrayList<Student>) students2).get(0).getAddress(), 
						((ArrayList<Student>) students2).get(1).getAddress() );	
		assertNotEquals (((ArrayList<Student>) students2).get(0).getAddress().hashCode(), 
				((ArrayList<Student>) students2).get(1).getAddress().hashCode() );	
		assertEquals (((ArrayList<Student>) students2).get(0).getAddress().getClass(), 
				((ArrayList<Student>) students2).get(1).getAddress().getClass() );	
		
	
		assertSame(((ArrayList<Student>) students2).get(0).getCourse(), 
					((ArrayList<Student>) students2).get(1).getCourse());	
		assertEquals (((ArrayList<Student>) students2).get(0).getCourse(), 
					((ArrayList<Student>) students2).get(1).getCourse() );	
		assertEquals (((ArrayList<Student>) students2).get(0).getCourse().hashCode(), 
				((ArrayList<Student>) students2).get(1).getCourse().hashCode() );
		assertEquals (((ArrayList<Student>) students2).get(0).getCourse().getClass(), 
				((ArrayList<Student>) students2).get(1).getCourse().getClass() );	
		
		
		
		
	}
	@Test
    public void testSaveFile() throws Exception {
		
		File inputFile = new File("TestPersistencyFile");
		
		StudentStorage.save(students1,inputFile);
		
		students2 = StudentStorage.fetch(inputFile);
	
		testContent();
		
	}
	
	@Test
    public void testSaveAsFileName() throws Exception {
		
		
		File inputFile = new File("TestPersistencyFile");
		
		StudentStorage.save(students1,"TestPersistencyFile");
		
		students2 = StudentStorage.fetch(inputFile);
	
		testContent();
		
	}
	
	@Test
    public void testSaveFileForManyStudents() throws Exception {
		
		File inputFile = new File("TestPersistencyForManyStudentsFile");
		
		StudentStorage.save(manyStudents,inputFile);
		
		students2 = StudentStorage.fetch(inputFile);
	
		assertEquals (manyStudents, students2);
		assertEquals (manyStudents.size(), students2.size());
		assertNotSame(manyStudents ,students2);
		assertEquals (manyStudents.hashCode(), students2.hashCode());
		
	}
	
	@Test (expected = FileNotFoundException.class)
    public void testInvalidFileName() throws IOException, ClassNotFoundException    {
		
		
		File inputFile = new File(" ");
		
		StudentStorage.fetch(inputFile);
	
		
		
	}
	
	@Test (expected = IOException.class) 
	public void testErrorInputType() throws IOException, ClassNotFoundException    {
		
		
		File inputFile = new File("ErrorInputTypeFile");
		FileWriter out = new FileWriter(inputFile);
		
		out.write("aaa ");
		out.close();
		
		StudentStorage.fetch(inputFile);
	
		
		
	}
	
}
