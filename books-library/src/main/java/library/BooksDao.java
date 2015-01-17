package library;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Singleton mock up used as DAO implementation of IBooksDao
 * 
 */
public enum BooksDao implements IBooksDao {
	instance;

	private HashMap<String, Book> contentProvider = new HashMap<String, Book>();

	BooksDao() {
		
		// Initialize mock up data
		Book book = new Book("123456781", "Harry Potter", "Joan R.");
		contentProvider.put(book.getIsbn(), book);

		book = new Book("123456782", "Code Complete", "Pesho G.");
		contentProvider.put(book.getIsbn(), book);

		book = new Book("123456783", "Some old book", "Ekaterina II");
		contentProvider.put(book.getIsbn(), book);
	}

	/**
	 * Gets all books
	 * 
	 * @return List of books
	 */
	public List<Book> getAll() {
		// Cast books from collection to list
		List<Book> books = new ArrayList<Book>();
		books.addAll(contentProvider.values());
		return books;
	}

	/**
	 * Searches for a book
	 * 
	 * @param isbn
	 *            of the book
	 * @return the book to which the specified ISBN is mapped
	 */
	public Book find(String isbn) {
		// Search in the map for a book with this ISBN
		return contentProvider.get(isbn);
	}

	/**
	 * Removes a book
	 * 
	 * @param isbn
	 *            of the book to be removed
	 */
	public void remove(String isbn) {
		// Remove the book from the map
		contentProvider.remove(isbn);
	}

	/**
	 * Inserts a book
	 * 
	 * @param book
	 *            Book instance
	 * @return Book which was replaced or null if newly created
	 */
	public Book insert(Book book) {
		// Add new book to the map
		return contentProvider.put(book.getIsbn(), book);
	}
}
