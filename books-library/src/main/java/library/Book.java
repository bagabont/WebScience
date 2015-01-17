package library;

import javax.validation.constraints.Size;

public class Book {

	@Size(min = 1, max = 50, message = "Author is invalid.")
	private String author;

	@Size(min = 1, max = 200, message = "Title is invalid.")
	private String title;

	@Size(min = 9, max = 13, message = "ISBN is invalid.")
	private String isbn;

	public Book(String isbn, String title, String author) {
		setIsbn(isbn);
		setTitle(title);
		setAuthor(author);
	}

	public Book() {
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
}
