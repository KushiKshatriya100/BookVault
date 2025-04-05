package pkg_main;
import java.util.Scanner;

import Pkg_Person.Student;
import Pkg_Person.StudentManager;
import Pkg_book.Book;
import Pkg_book.BookManager;
import pkg_Exception.BookNotFoundException;
import pkg_Exception.StudentNotFoundException;
import pkg_transaction.BookTransactionManager;
public class Main {
	public static void main(String args[]) {
		
		int choice;
		Scanner sc = new Scanner(System.in);
		BookManager bm = new BookManager();
		StudentManager sm = new StudentManager();
		BookTransactionManager btm = new BookTransactionManager();
		
		do {
			System.out.println("Enter 1. if Student\nEnter 2. if Librarian\nEnter 3. if want to Exit : ");
			choice = sc.nextInt();
			if(choice == 1) //You Are a Student
			{
				System.out.println("Enter Your Roll Number :");
				int rollNo = sc.nextInt();
				try {
					Student s = sm.get(rollNo);
					if(s==null) 
						throw new StudentNotFoundException();
					
					int stud_choice;
					do {
						System.out.println("Enter 1. to View All Books\nEnter 2. to Search By ISBN\nEnter 3. to List Books By Subject\nEnter 4. to Issue a Book\nEnter 5.to return a Book\nEnter 99 to Exit");
						stud_choice = sc.nextInt();
						switch(stud_choice) {
						case 1:
							System.out.println("All The Records are :");
							bm.viewAllBook();
							break;
						case 2:
							System.out.println("Enter ISBN of the Book to Search ");
							
							int search_isbn = sc.nextInt();
							Book book = bm.searchBookByIsbn(search_isbn);
							if(book == null) {
								System.out.println("NO Book With this ISBN Exists in our Library");
							}
							else {
								System.out.println(book);
							}
							
							break;
						case 3:
							System.out.println("Enter the Subject to Search ");
							sc.nextLine();
							String search_subject = sc.nextLine();
							bm.listBooksBySubject(search_subject);
							break;
						case 4:
							System.out.println("Enter the ISBN to Issue a Book ");
							int issue_isbn = sc.nextInt();
							book = bm.searchBookByIsbn(issue_isbn);
							try{
								if(book == null) {
									throw new BookNotFoundException();
								}
								if(book.getAvailable_quantity()>0) {
									if(btm.issueBook(rollNo,issue_isbn)) {
										book.setAvailable_quantity(book.getAvailable_quantity()-1);
										System.out.println("Book has Been Issued....");
									}
								}
								else {
									System.out.println("All The Book have Been Issued...");
								}
							}
							catch(BookNotFoundException bnfe) {
								System.out.println(bnfe);
							}
							
							break;
						case 5:
							System.out.println("Please Enter the ISBN to Return a Book");
							int return_isbn = sc.nextInt();
							book = bm.searchBookByIsbn(return_isbn);
							if(book!=null) {
								if(btm.returnBook(rollNo, return_isbn)) {
									book.setAvailable_quantity(book.getAvailable_quantity()+1);
									System.out.println("Thank You for Returning the Book");
								}
								else {
									System.out.println("Could Not Return the Book");
								}
							}
							else {
								System.out.println("Book With this ISBN Does not Exists");
							}
							break;
						case 99:
							System.out.println("Thank You for Using Library");
							break;
						default :
							System.out.println("InValid Choice\n");
							break;
							
						}
						
					}while(stud_choice != 99);
				}
				catch(StudentNotFoundException snfe) {
					System.out.println(snfe);
				}
			}
			
			else if(choice==2) {   //User is a Librarian
				int lib_choice ;
				do {
				
				System.out.println("Enter 11 to View all Students\nEnter 12 to Print A Student by Roll Number\nEnter 13 to Register a Student\nEnter 14 to Update a Student\nEnter 15 to Delete a Student");
				System.out.println("Enter 21 to View All Books\nEnter 22 to Print a Book by ISBN\nEnter 23 to Add a New Book\nEnter 24 to Update a Book\nEnter 25 to Delete a Book");
				System.out.println("Enter 31 to View All Transactions");
				System.out.println("Enter 99 to Exit");
				lib_choice = sc.nextInt();
				switch(lib_choice) {
				case 11:          //View All Student
					System.out.println("All The Student Records : ");
					sm.viewAllStudents();
					break;
				case 12:            //Search a Student By the RollNo
					int get_rollNo;
					System.out.println("Enter Roll Number to Fetch Student's Record");
					get_rollNo=sc.nextInt();
					Student student = sm.get(get_rollNo);
					if(student == null) {
						System.out.println("No Record Matches this Roll Number");
					}
					else {
						System.out.println(student);
					}
					
					break;
				case 13:            //Register A Student
					System.out.println("Enter Students Details to Add");
					String name;
					String emailid;
					String phoneNO;
					String address;
					String dob;
					int rollno;
					int std;
					String division;
					sc.nextLine();
					System.out.println("Name :");
					name = sc.nextLine();
					System.out.println("Email Id :");
					emailid = sc.nextLine();
					System.out.println("Phone Number :");
					phoneNO = sc.nextLine();
					System.out.println("Address :");
					address = sc.nextLine();
					System.out.println("Date of Birth :");
					dob = sc.nextLine();
					System.out.println("Roll Number as Integer :");
					rollno = sc.nextInt();
					System.out.println("Standard as Integer :");
					std = sc.nextInt();
					sc.nextLine();
					System.out.println("division :");
					division = sc.nextLine();
					student = new Student(name, emailid, phoneNO, address,dob,rollno, std,division);
					sm.addAStudent(student);
					System.out.println("Student is Added ");
					break;
				
				case 14:     //For Updating Student
					System.out.println("Enter Roll Number to Modify the Record");
					int modify_rollNo= sc.nextInt();
					student = sm.get(modify_rollNo);
					
					try{if(student == null) 
						throw new StudentNotFoundException();
					
					sc.nextLine();
					System.out.println("Name :");
					name = sc.nextLine();
					System.out.println("Email Id :");
					emailid = sc.nextLine();
					System.out.println("Phone Number :");
					phoneNO = sc.nextLine();
					System.out.println("Address :");
					address = sc.nextLine();
					System.out.println("Date of Birth :");
					dob = sc.nextLine();
					
					System.out.println("Standard as Integer :");
					std = sc.nextInt();
					sc.nextLine();
					System.out.println("division :");
					division = sc.nextLine();
					
					sm.updateStudent(modify_rollNo, name, emailid, phoneNO, address, dob, std, division);
					System.out.println("Student Record is Updated");
					}
					catch(StudentNotFoundException snfe) {
						System.out.println(snfe);
					}
					break;
				
				case 15:     //Delete a Student
					System.out.println("Enter Roll Number to Modify the Record");
					int delete_rollNo= sc.nextInt();
					if(sm.deleteStudent(delete_rollNo))
						System.out.println("Student Record is Removed");
					else 
						System.out.println("No Record With Given RollNumber Exists");
						
					break;
					
				
				case 21:  //View All Books
					bm.viewAllBook();
					break;
				case 22: //Search a Book By ISBN
					int search_isbn;
					System.out.println("Enter ISBN of the Book to Search ");
					search_isbn = sc.nextInt();
					Book book = bm.searchBookByIsbn(search_isbn);
					if(book == null) {
						System.out.println("NO Book With this ISBN Exists in our Library");
					}
					else {
						System.out.println(book);
					}
					break;
				case 23:  //Add A Book
					System.out.println("Please Enter Book Details to Add");
					int isbn;
					String title;
					String author;
					String publisher;
					int edition;
					String subject;
					int available_quantity;
					System.out.println("ISBN : ");
					isbn = sc.nextInt();
					sc.nextLine();
					System.out.println("Title : ");
					title = sc.nextLine();
					System.out.println("Author : ");
					author = sc.nextLine();
					System.out.println("Publisher : ");
					publisher = sc.nextLine();
					System.out.println("Edition : ");
					edition = sc.nextInt();
					sc.nextLine();
					System.out.println("Subject : ");
					subject = sc.nextLine();
					System.out.println("Available Quantity : ");
					available_quantity = sc.nextInt();
					book = new Book(isbn, title, author, publisher, edition, subject, available_quantity);
					bm.addABook(book);
					System.out.println("A Book Record is Added ");
					break;
				case 24:  //Update A Record of Book
					System.out.println("Please Enter the ISBN to update the Redcord ");
					int update_isbn;
					update_isbn = sc.nextInt();
					try {
						book = bm.searchBookByIsbn(update_isbn);
						if(book == null) 
							throw new BookNotFoundException();
						
						System.out.println("Enter Book Details to Modify ");
						sc.nextLine();
						System.out.println("Title : ");
						title = sc.nextLine();
						System.out.println("Author : ");
						author = sc.nextLine();
						System.out.println("Publisher : ");
						publisher = sc.nextLine();
						System.out.println("Edition : ");
						edition = sc.nextInt();
						sc.nextLine();
						System.out.println("Subject : ");
						subject = sc.nextLine();
						System.out.println("Available Quantity : ");
						available_quantity = sc.nextInt();
						
						bm.updateBook(update_isbn, title, author, publisher, edition, subject, available_quantity);
						System.out.println("Please Enter the ISBN to update the Redcord ");
						update_isbn = sc.nextInt();
						
					}
					catch(BookNotFoundException bnfe) {
						System.out.println(bnfe);
					}
					break;
				case 25:    //Delete a Record of A Book
					System.out.println("Please Enter the ISBN to Delete the Redcord ");
					int delete_isbn;
					delete_isbn = sc.nextInt();
					try {
						book = bm.searchBookByIsbn(delete_isbn);
						if(book == null) 
							throw new BookNotFoundException();
						bm.deleteBook(delete_isbn);
						System.out.println("Book Record is Deleted");
					}
					catch(BookNotFoundException bnfe) {
						System.out.println(bnfe);
					}
					break;
				case 31:       //To View All Transactions
					System.out.println("All The Transaction are : ");
					btm.showAll();
					break;
				case 99:
					System.out.println("Thank You FOr Using Library");
					break;
				default :
					System.out.println("Invalid Choice");
					break;
				
				}
				
			}while(lib_choice != 99);
			}
		}while(choice != 3);
		sm.writeToFile();
		bm.writeToFile();
		btm.writeToFile();
		sc.close();
	}

}
