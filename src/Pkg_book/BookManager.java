package Pkg_book;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ListIterator;

public class BookManager {
	ObjectOutputStream oos_book = null;
	ObjectInputStream ois_book = null;
	
	File book_file = new File("Books.dat");
	ArrayList<Book> book_list =null;
	
	@SuppressWarnings("unchecked")
	
	public BookManager() {                  
		book_list = new ArrayList<Book>();           //Initialize Book List
		
		if(book_file.exists()) {
			try {
			ois_book = new ObjectInputStream(new FileInputStream(book_file));
			Object obj = ois_book.readObject();
			if(obj instanceof ArrayList<?>){
				book_list = (ArrayList<Book>) obj;
			}
			else {
				System.out.println("The Read object is not Array list ");
			}
			//book_list = (ArrayList<Book>) ois_book.readObject();
			}
			catch(IOException e) {
				System.out.println("File Reading Exception "+e);
			}
			catch(ClassNotFoundException e) {
				System.out.println("Class Not Found Exception : "+e);
			}
		}
	}
	
	public void listBooksBySubject(String subject) {
		for(Book book: book_list) {
			if(book.getSubject().equals(subject)) 
				System.out.println(book);
			
		}
	}
	public void addABook(Book book) {
		book_list.add(book);
	}
	public void viewAllBook() {
		if (book_list != null) {
	        for (Book book : book_list) {
	            System.out.println(book);
	        }
	    } else {
	        System.out.println("No books available.");
	    }
	}
	public Book searchBookByIsbn(int search_isbn) {
		for(Book book: book_list) {
			if(book.getIsbn() == search_isbn) 
				return book;
		}
		return null;
		
	}
	public boolean deleteBook(int delete_isbn) {
		ListIterator<Book> book_iterator =(ListIterator<Book>) book_list.listIterator();
		while(book_iterator.hasNext()) {
			Book book = book_iterator.next();
			if(book.getIsbn() == delete_isbn) {
				book_list.remove(book);
				return true;
			}
		}
		return false;
	}
	
	public boolean updateBook(int update_isbn,String title,String author,String publisher,int edition,String subject,int available_quantity) {
		ListIterator<Book> book_iterator =(ListIterator<Book>) book_list.listIterator();
		while(book_iterator.hasNext()) {
			Book book = book_iterator.next();
			if(book.getIsbn() == update_isbn) {
				
				book.setAuthor(author);
				book.setAvailable_quantity(available_quantity);
				book.setEdition(edition);
				book.setPublisher(publisher);
				book.setSubject(subject);
				book.setTitle(title);
				return true;
			}
		}
		return false;
	}
	
	public void writeToFile() {
		try {
			oos_book = new ObjectOutputStream(new FileOutputStream(book_file));
			oos_book.writeObject(book_list);
		}  catch (IOException ioe) {
			
			ioe.printStackTrace();
		}
	}
	
	
}
