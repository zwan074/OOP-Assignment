package nz.ac.massey.cs159272.ass1.id17272381;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerListModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class MyFrame extends JFrame implements ActionListener, ListSelectionListener, ListDataListener, PropertyChangeListener, DocumentListener, ChangeListener {
	
	private static final long serialVersionUID = 1L;
	private JButton exitButton = new JButton("exit");
	private JButton loadButton = new JButton("Load");
	private JButton saveButton = new JButton("Save");
	private JButton addButton = new JButton("Add");
	private JButton cloneButton = new JButton("clone");
	private JFileChooser fc;
	private JList list;
    private DefaultListModel listModel;
    private Collection<Student> students = null;
    private Collection<Course> courses = null;
    private List<Student> studentslist = null;
    private List<String> courseString = null;
	private File file;
	private JFormattedTextField  dobField;
	private JTextField idField,nameField, firstNameField,courseField,
	addressHouseNumberField, addressStreetField,addressPostCodeField,addressTownField;
	private boolean studentInterfaceIsLoaded = false;
	private JScrollPane listScrollPane = null ;
	private SpinnerListModel CourseList = null;
	private JPanel fieldsPanel = new JPanel(); ;
	
    public MyFrame(String string) {
		super();
		init();
	}
	
	private void init() {
		//set tile and size of JFrame.
		this.setTitle("Student Editor");
		this.setLocation(100,100);
		this.setSize(1500,600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(new BorderLayout());
		
		//add buttons
		JPanel toolbar = new JPanel();
		toolbar.setLayout(new FlowLayout(FlowLayout.LEFT));
		toolbar.add(exitButton);
		toolbar.add(loadButton);
		toolbar.add(saveButton);
		toolbar.add(addButton);
		toolbar.add(cloneButton);
		this.exitButton.addActionListener(this);
		this.saveButton.addActionListener(this);
		this.loadButton.addActionListener(this);
		this.addButton.addActionListener(this);
		this.cloneButton.addActionListener(this);
		
		//Add entry fields
		createEntryFields(); 
		this.getContentPane().add(toolbar,BorderLayout.NORTH);
		//Add load and save dialog box
		fc = new JFileChooser();
		
		
	}


	protected void createEntryFields() {
		
        fieldsPanel.setLayout(new GridLayout(12,1,10,10));
        
        //Create the text field and set it up.
        
        idField  = new JTextField();
        idField.setColumns(20);
        JLabel idFieldLabel = new JLabel("id: ",JLabel.TRAILING);
        idFieldLabel.setLabelFor(idField);
        fieldsPanel.add(idFieldLabel);
        fieldsPanel.add(idField);
        idField.addActionListener(this);
        
        firstNameField = new JTextField();
        firstNameField.setColumns(20);
        JLabel firstNameFieldLabel = new JLabel("first name: ",JLabel.TRAILING);
        firstNameFieldLabel.setLabelFor(firstNameField);
        fieldsPanel.add(firstNameFieldLabel);
        fieldsPanel.add(firstNameField);
        firstNameField.addActionListener(this);
        
        nameField = new JTextField();
        nameField.setColumns(20);
        JLabel nameFieldLabel = new JLabel("name: ",JLabel.TRAILING);
        nameFieldLabel.setLabelFor(nameField);
        fieldsPanel.add(nameFieldLabel);
        fieldsPanel.add(nameField);
        nameField.addActionListener(this);
        
        DateFormat format = new SimpleDateFormat("dd/MM/YYYY");
        dobField = new JFormattedTextField ( format );
        dobField.setValue(new Date());
        ((JFormattedTextField) dobField).setColumns(20);
        JLabel dobFieldLabel = new JLabel("date of birth: ",JLabel.TRAILING);
        dobFieldLabel.setLabelFor(dobField);
        fieldsPanel.add(dobFieldLabel);
        fieldsPanel.add(dobField);
        dobField.addActionListener(this);
        
        
        initialiseCourses();
        CourseList = new SpinnerListModel();
        courseString = new ArrayList<String> () ;
        for (Course c:courses) {
        	courseString.add(c.getCourseNumber() + " " + c.getCourseName());
        }
        CourseList.setList(courseString);
        courseField = new JTextField();
        JLabel CourseListFieldLabel1 = new JLabel("course: ",JLabel.TRAILING);
        JLabel CourseListFieldLabel2 = new JLabel("Available courses : ",JLabel.TRAILING);
        JSpinner CourseListSpinner = new JSpinner(CourseList);
       
        CourseListFieldLabel1.setLabelFor(courseField);
        CourseListFieldLabel2.setLabelFor(CourseListSpinner);
        CourseListSpinner.addChangeListener(this);
        
        fieldsPanel.add(CourseListFieldLabel1);
        fieldsPanel.add(courseField);
        fieldsPanel.add(CourseListFieldLabel2);
        fieldsPanel.add(CourseListSpinner);
      
        addressHouseNumberField = new JTextField();
        addressHouseNumberField.setColumns(20);
        
        addressStreetField = new JTextField();
        addressStreetField.setColumns(20);
        
        addressPostCodeField = new JTextField();
        addressPostCodeField.setColumns(20);
        
        addressTownField = new JTextField();
        addressTownField.setColumns(20);
        
        JLabel addressFieldLabel1 = new JLabel("address-houseNumber: ",JLabel.TRAILING);
        JLabel addressFieldLabel2 = new JLabel("address-street: ",JLabel.TRAILING);
        JLabel addressFieldLabel3 = new JLabel("address-post code: ",JLabel.TRAILING);
        JLabel addressFieldLabel4 = new JLabel("address-town: ",JLabel.TRAILING);
     
        
        addressFieldLabel1.setLabelFor(addressHouseNumberField);
        addressFieldLabel2.setLabelFor(addressStreetField);
        addressFieldLabel3.setLabelFor(addressPostCodeField);
        addressFieldLabel4.setLabelFor(addressTownField);
        
        fieldsPanel.add(addressFieldLabel1);
        fieldsPanel.add(addressHouseNumberField);
        fieldsPanel.add(addressFieldLabel2);
        fieldsPanel.add(addressStreetField);
        fieldsPanel.add(addressFieldLabel3);
        fieldsPanel.add(addressPostCodeField);
        fieldsPanel.add(addressFieldLabel4);
        fieldsPanel.add(addressTownField);
        
        
        addressHouseNumberField.addActionListener(this);
        addressStreetField.addActionListener(this);
        addressPostCodeField.addActionListener(this);
        addressTownField.addActionListener(this);
        
        JLabel hint = new JLabel(" Press 'Enter' to confirm student edit ",JLabel.TRAILING);
        fieldsPanel.add(hint);
        
		this.getContentPane().add(fieldsPanel,BorderLayout.CENTER);
    }

	

	protected void initialiseCourses() {
		//set up available courses
		Course course1 = new Course();
		Course course2 = new Course();
		Course course3 = new Course();
		Course course4 = new Course();
		courses = new ArrayList<>();
		
		course1.setCourseName("Computer Science");
		course1.setCourseNumber("001");
		course2.setCourseName("Software Engineering");
		course2.setCourseNumber("002");
		course3.setCourseName("Civil Engineering");
		course3.setCourseNumber("003");
		course4.setCourseName("Art");
		course4.setCourseNumber("004");
		
		courses.add(course1);
		courses.add(course2);
		courses.add(course3);
		courses.add(course4);
	}
	@SuppressWarnings("rawtypes")
	@Override
	public void actionPerformed(ActionEvent e) {

		//action performed for exit button
		if (e.getSource() == exitButton) {
			this.dispose();
		}
		
		

		//action performed for load button
		else if (e.getSource() == loadButton) {
			
			fieldsPanel = new JPanel();
			
			//clear existing list scroll pane if load a new file
			if (studentInterfaceIsLoaded  ) {
				
				this.remove(listScrollPane);
				
			}
			
			
			int returnVal = fc.showOpenDialog(MyFrame.this);
			 
	        if (returnVal == JFileChooser.APPROVE_OPTION) {

	        	listModel = new DefaultListModel();

	        	file = fc.getSelectedFile();
	         	
	        	Boolean isInpurError = false;
	    		
		        try {
		        		 
		        	students= StudentStorage.fetch(file);
					
						
				} catch (Exception e1) {
						
					System.out.println("Incorrect input file type");
					isInpurError = true;
						
				}
		        
		        //if invalid input do nothing.
		       
		        if (isInpurError) {
		        		
		        	isInpurError = false;	
		        		
		        }
		        else {
		        	
		        	//create student list to display as list scroll pane
		        	studentslist = (List<Student>) students;
		        	studentInterfaceIsLoaded = true;
		        	for (Object student: studentslist) {
			        		 
			        	String firstName = ((Student) student).getFirstName();
			        	String Name = ((Student) student).getName();
			        	listModel.addElement( firstName +" " + Name  );
			        		 
			        }
			        	
			        setTextField(0);
						        	
			        list = new JList(listModel);
			        list.setSelectionMode( ListSelectionModel.SINGLE_INTERVAL_SELECTION );
			        list.setSelectedIndex(0);
			        list.addListSelectionListener(this);
			            
			        listScrollPane = new JScrollPane(list);
			        listScrollPane.setPreferredSize(new Dimension(250, 100));
			             
			        this.getContentPane().add(listScrollPane,BorderLayout.WEST);	
		        		
		        		
		        		
		        
		        	
	        	}
	            
	        }
	        
		}
		// action performed for save button
		else if (e.getSource() == saveButton) {
			 int returnVal = fc.showSaveDialog(MyFrame.this);
			 
	         if (returnVal == JFileChooser.APPROVE_OPTION) {
	        	 
	        	 file = fc.getSelectedFile();
	        	 
	        	
	        	try {
					StudentStorage.save(studentslist, file);
					
				} catch (IOException e1) {
					
					e1.printStackTrace();
				}
	        	
	        	file = fc.getSelectedFile();
	         }
		}
		// action performed for add button
		else if (e.getSource() == addButton) {
			
			//add a new student in list
			listModel.addElement( "New Student"  );
			list.setSelectedIndex(listModel.getSize());
			
			Student student = new Student();
			Course course = new Course();
			Address address = new Address();
			Date date = new Date();
			
			address.setHouseNumber(0);
			address.setPostCode("");
			address.setStreet("");
			address.setTown("");
			course.setCourseName("");
			course.setCourseNumber("");
			
			student.setFirstName("New");
			student.setName("Student");
			student.setCourse(course);
			student.setAddress(address);
			student.setDob(date);
			
			studentslist.add(student);
			
			
		}
		
		// action performed for clone button
		else if (e.getSource() == cloneButton) {
			
	        int index = list.getSelectedIndex();
	        
	        // if nothing selected return
	        if (index == -1 ) {   	
	            return;
	        } 
	        // if selected , add cloned student below selected index    
	        else {
	            listModel.insertElementAt(list.getSelectedValue() + ".clone", index+1);
	        	
	            Student student = studentslist.get(index);
	            
	            
	            try {
	            	studentslist.add(index+1,student.clone());
					
				} catch (CloneNotSupportedException e1) {
					
					e1.printStackTrace();
				}
                
	            list.setSelectedIndex(index+1);
	        }
	        
	        
			
		}
		
			
		//action performed in student edit field
		else if (idField == e.getSource() || firstNameField == e.getSource() ||
				nameField == e.getSource() || dobField == e.getSource() || 
				addressStreetField == e.getSource() ||  addressPostCodeField == e.getSource() ||
				addressTownField == e.getSource() || addressHouseNumberField == e.getSource() )
				
		{	
			
			// if any student file has not been loaded, do nothing 
			if( !studentInterfaceIsLoaded ) {
				return;
			}
			
			//else edit student information and press "enter' to confirm changes
			else  {
				int index = list.getSelectedIndex();
				
				studentslist.get(index).setId(idField.getText());
				studentslist.get(index).setFirstName(firstNameField.getText());
				studentslist.get(index).setName(nameField.getText());
				listModel.set(index, firstNameField.getText() + " " + nameField.getText());
				
				try {
					Date date = new SimpleDateFormat("dd/MM/YYYY", Locale.ENGLISH).parse(dobField.getText());
					studentslist.get(index).setDob(date);
				} catch (ParseException e1) {
					
					e1.printStackTrace();
				}
				
				Address address = new Address();
				
				
				try
			    {
					address.setHouseNumber(Integer.parseInt(addressHouseNumberField.getText()) );
			       
			    } catch (NumberFormatException ex)
			    {
			    	address.setHouseNumber(0);
			    }
				
				address.setStreet(addressStreetField.getText());
				address.setPostCode(addressPostCodeField.getText());
				address.setTown(addressTownField.getText());
				studentslist.get(index).setAddress(address);
				
				
			}
			
			 
			
		}
		
		//refresh field panel
		this.revalidate();
		this.repaint();	
				
		
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		// update student edit filed if value changes
		int index = list.getSelectedIndex();
		setTextField(index);

    	this.revalidate();
		this.repaint();	
		
	}
	
	protected void setTextField (int index) {
		
		idField.setText(studentslist.get(index).getId());
		firstNameField.setText(studentslist.get(index).getFirstName());
		nameField.setText(studentslist.get(index).getName());
		
		Format format = new SimpleDateFormat("dd/MM/YYYY");
		dobField.setText(format.format(studentslist.get(index).getDob()));
		courseField.setText(studentslist.get(index).getCourse().getCourseNumber() + " " +
				studentslist.get(index).getCourse().getCourseName());
		
		addressHouseNumberField.setText(Integer.toString(
				studentslist.get(index).getAddress().getHouseNumber()) );
				
		addressStreetField.setText(studentslist.get(index).getAddress().getStreet());
		addressPostCodeField.setText(studentslist.get(index).getAddress().getPostCode());
		addressTownField.setText(studentslist.get(index).getAddress().getTown());
			
		
	}
	
	

	@Override
	public void stateChanged(ChangeEvent e) {
		
		//edit course spinner data
		
		if( !studentInterfaceIsLoaded ) {
			
		}
		else  {
	        if (CourseList instanceof SpinnerListModel) {
	        	
	            int index = list.getSelectedIndex();
	            
	            
	            for (Course c : courses) {
	            	String comp = c.getCourseNumber() + " " + c.getCourseName();
	            	if (comp .equals(CourseList.getValue())) {
	            		
	            		studentslist.get(index).setCourse(c);
	            	}
	            	
	            }
	            
			
	        	
	        }

		}
		
		this.revalidate();
		this.repaint();	
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void propertyChange(PropertyChangeEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contentsChanged(ListDataEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void intervalAdded(ListDataEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void intervalRemoved(ListDataEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	
}