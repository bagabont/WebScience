package library;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Book {

	@NotNull
	@Size(min = 1, max = 50)
	private String author;

	@NotNull
	@Size(min = 1, max = 200)
	private String title;

	@NotNull
	@Size(min = 9, max = 13)
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
