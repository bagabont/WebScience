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
		contentProvider.put("1", new Book("1", "One", "Author 1"));
		contentProvider.put("2", new Book("2", "Two", "Author 2"));
		contentProvider.put("3", new Book("3", "Three", "Author 3"));
	}

	/**
	 * Gets all books
	 * 
	 * @return List of books
	 */
	public List<Book> getAll() {
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
		return contentProvider.get(isbn);
	}

	/**
	 * Removes a book
	 * 
	 * @param isbn
	 *            of the book to be removed
	 */
	public void remove(String isbn) {
		contentProvider.remove(isbn);
	}

	/**
	 * Inserts a new book
	 * 
	 * @param book
	 *            book to be inserted
	 */
	public void insert(Book book) {
		contentProvider.put(book.getIsbn(), book);
	}

	/**
	 * Updates a book.
	 * 
	 * @param book
	 *            book to be replaced
	 * @return the previous book associated with the specified ISBN
	 */
	public Book replace(Book book) {
		return contentProvider.replace(book.getIsbn(), book);
	}
}
