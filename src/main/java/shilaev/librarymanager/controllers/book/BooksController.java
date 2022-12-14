package shilaev.librarymanager.controllers.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import shilaev.librarymanager.dao.author.AuthorDao;
import shilaev.librarymanager.dao.book.BooksDao;
import shilaev.librarymanager.models.author.Author;
import shilaev.librarymanager.models.book.Book;

import javax.validation.Valid;

@Controller
@RequestMapping("/books")
public class BooksController {

    private final BooksDao booksDao;
    private final AuthorDao authorDao;

    @Autowired
    public BooksController(BooksDao booksDao, AuthorDao authorDao) {
        this.booksDao = booksDao;
        this.authorDao = authorDao;
    }

    // CREATE
    @GetMapping("/add-book")
    public String addBookPage(@ModelAttribute("new_book") Book newBook,
                              @ModelAttribute("author") Author author,
                              Model model) {
        model.addAttribute("authors", authorDao.getAllAuthorLastNames());
        return "book/add_book";
    }

    @PostMapping("/add-book")
    public String addBook(@ModelAttribute("new_book") @Valid Book newBook, BindingResult newBookErrors,
                          @ModelAttribute("author") Author author,
                          Model model) {
        // SET VALUES
        newBook.setAuthorId(author.getId());
        newBook.setAuthorLastName(authorDao.getAuthorById(author.getId()).getLastName());

        // VALIDATE
        if (newBookErrors.hasErrors()) {
            model.addAttribute("authors", authorDao.getAllAuthorLastNames());
            return "book/add_book";
        }

        booksDao.addNewBook(newBook);
        return "redirect:/books";
    }

    // READ
    @GetMapping()
    public String getAllBooks(Model model) {
        model.addAttribute("books_list", booksDao.selectAllBooks());
        return "book/all_books";
    }

    // UPDATE


    // DELETE
    @DeleteMapping("delete-book-{isbn}")
    public String deleteBook(@PathVariable("isbn") String isbn){
        booksDao.deleteBookByIsbn(isbn);
        return "redirect:/books";
    }

}
