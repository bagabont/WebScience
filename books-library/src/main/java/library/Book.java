package library;

import javax.validation.constraints.Size;

/**
 * Represents a Book class
 *
 */
public class Book {

	@Size(min = 1, max = 50, message = "Author is invalid.")
	private String author;

	@Size(min = 1, max = 200, message = "Title is invalid.")
	private String title;

	@Size(min = 9, max = 13, message = "ISBN is invalid.")
	private String isbn;

	/**
	 * Creates a new instance of a Book
	 * 
	 * @param isbn
	 *            Book ISBN
	 * @param title
	 *            Book title
	 * @param author
	 *            Book author
	 */
	public Book(String isbn, String title, String author) {
		setIsbn(isbn);
		setTitle(title);
		setAuthor(author);
	}

	/**
	 * Creates a new instance of a Book
	 */
	public Book() {
	}

	/**
	 * Gets the book's ISBN
	 * 
	 * @return ISBN string
	 */
	public String getIsbn() {
		return isbn;
	}

	/**
	 * Sets the book's ISBN
	 */
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	/**
	 * Gets the book's title
	 * 
	 * @return Title string
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the book's title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Gets the book's author
	 * 
	 * @return author string
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * Sets the book's author
	 */
	public void setAuthor(String author) {
		this.author = author;
	}
}
