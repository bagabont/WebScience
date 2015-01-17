package library;

import java.util.List;

public interface IBooksDao {

	/**
	 * Gets all books
	 * 
	 * @return List of books
	 */
	List<Book> getAll();

	/**
	 * Searches for a book
	 * 
	 * @param isbn
	 *            of the book
	 * @return the book to which the specified ISBN is mapped
	 */
	Book find(String isbn);

	/**
	 * Removes a book
	 * 
	 * @param isbn
	 *            of the book to be removed
	 */
	void remove(String isbn);

	/**
	 * Inserts a book
	 * 
	 * @param book
	 *            Book instance
	 * @return Book which was replaced or null if newly created
	 */
	Book insert(Book book);
}
