package nz.ac.massey.cs159272.ass1.id17272381;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;


public class StudentStorage {
	
	public static void save(Collection<Student> data,File file) throws IOException{
		
		
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
		
		for (Student e : data) {
			
			out.writeObject(e);
			
		}
		
		out.flush();
		out.close();
		
		
		
	}
	
	public static void save(Collection<Student> data,String fileName) throws IOException{
		
		File file = new File(fileName);
		
		save(data,file);
		
	}
	
	public static Collection<Student> fetch(File file) throws FileNotFoundException, IOException, ClassNotFoundException     {
		

        Collection<Student> students = new ArrayList<>();
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
		
		while (true) {
			
		
			try{
				
				Student student = (Student) in.readObject();
				
				
				students.add(student);
			}
			
			catch(EOFException e) {
				in.close();
				return students;
			}
			
			
		}
	}
	
}
