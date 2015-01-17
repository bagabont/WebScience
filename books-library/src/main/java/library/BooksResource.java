package library;

import java.util.List;
import java.util.Set;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ConstraintViolation;

@Path("/books")
public class BooksResource {

	IBooksDao dao = BooksDao.instance;
	static Validator validator = Validation.buildDefaultValidatorFactory()
			.getValidator();

	/**
	 * Get list of all books
	 * 
	 * @return JSON representation of the list of all books
	 */
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getBooks() {

		// Get all books
		List<Book> books = dao.getAll();

		// If collection is null or empty return Not Found
		if (books == null || books.isEmpty()) {
			return Response.status(Status.NOT_FOUND).build();
		}
		// Return Success
		return Response.status(Status.OK).entity(books).build();
	}

	/**
	 * Get a book
	 * 
	 * @param isbn
	 *            ISBN of the book
	 * @return JSON representation of the book
	 */
	@GET
	@Path("/{isbn}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getBook(@PathParam("isbn") String isbn) {

		// Find book by ISBN
		Book book = dao.find(isbn);

		if (book != null) {
			// Return success
			return Response.status(Status.OK).entity(book).build();
		}

		// Return not found
		return Response.status(Status.NOT_FOUND).build();
	}

	/**
	 * Create a book
	 * 
	 * @param isbn
	 *            ISBN of the book
	 * @param book
	 *            JSON representation of the book
	 * @return No content response
	 */
	@POST
	@Path("/{isbn}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createBook(@PathParam("isbn") String isbn, @Valid Book book) {

		try {
			// Validate input JSON
			ValidateBook(isbn, book);

			// Insert book and return success
			dao.insert(book);
			return Response.status(Status.CREATED).build();

		} catch (ConstraintViolationException e) {
			// Return Bad Request
			return Response.status(Status.BAD_REQUEST).build();
		}
	}

	/**
	 * Replace a book
	 * 
	 * @param isbn
	 *            ISBN of the book
	 * @param book
	 *            New book.
	 * @return No content Created/OK response or BadRequest response.
	 */
	@PUT
	@Path("/{isbn}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateBook(@PathParam("isbn") String isbn, @Valid Book book) {
		try {
			// Validate input JSON
			ValidateBook(isbn, book);

			// Update the book and set the status code, depending on if the
			// book is newly inserted or actually updated.
			Status status = dao.replace(book) != null ? Status.CREATED
					: Status.OK;

			// Return successful result
			return Response.status(status).build();

		} catch (ConstraintViolationException e) {
			// Return Bad Request
			return Response.status(Status.BAD_REQUEST).build();
		}
	}

	/**
	 * Delete a book
	 * 
	 * @param isbn
	 *            ISBN of the book
	 * @return No content response or Not Found
	 */
	@DELETE
	@Path("/{isbn}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteBook(@PathParam("isbn") String isbn) {

		// Check if book exists
		Book book = dao.find(isbn);

		// Nothing to remove, therefore return Not Found
		if (book == null) {
			return Response.status(Status.NOT_FOUND).build();
		}

		// Delete book and return success.
		dao.remove(book.getIsbn());
		return Response.status(Status.OK).build();
	}

	/**
	 * Validates a book and matching ISBN input path parameter
	 * 
	 * @param isbn
	 *            ISBN input parameter
	 * @param book
	 *            Book instance
	 */
	private void ValidateBook(String isbn, Book book) {

		// Check if parameter ISBN matches the book ISBN.
		if (isbn != book.getIsbn()) {
			throw new ConstraintViolationException(
					"ISBN does not match book's ISBN.", null);
		}

		validator.validate(book);
		Set<ConstraintViolation<Book>> errors = validator.validate(book);
		if (errors.size() > 0) {
			throw new ConstraintViolationException(errors);
		}
	}
}
