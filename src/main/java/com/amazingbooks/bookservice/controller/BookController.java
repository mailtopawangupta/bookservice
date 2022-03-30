package com.amazingbooks.bookservice.controller;

import com.amazingbooks.bookservice.repository.Book;
import com.amazingbooks.bookservice.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/amazingbooks")
public class BookController {

    @Autowired
    public BookService bookService;

    /*
    @return List of all books
     */
    @GetMapping("/books")
    public List<Book> getAllBooks(){
        return bookService.findAllBooks();
    }

    /*
    @param title
    @return List of books with matching title
     */
    @GetMapping("/books/title/{title}")
    public List<Book> getBooksByTitle(@PathVariable String title){
        return bookService.findByTitle(title);
    }

    /*
    @param author
    @return List of books with matching author
     */
    @GetMapping("/books/author/{author}")
        public List<Book> getBooksByAuthor(@PathVariable String author){
        return bookService.findByAuthor(author);
    }

    /*
    @param isbn
    @return book with matching isbn
     */
    @GetMapping("/book/isbn/{isbn}")
    public Book getBooksByIsbn(@PathVariable Long isbn){
        return bookService.findByIsbn(isbn);
    }

    /*
    @param book
    @return saved/updated book
     */
    @PostMapping("/book")
    public Book saveBook(@RequestBody Book book){
        return bookService.saveBook(book);
    }

    /*
   @param book
   @return saved book
    */
    @DeleteMapping("/book")
    public ResponseEntity<Long> deleteBook(@RequestBody Book book){
        if(null!=bookService.findByIsbn(book.getIsbn())){
            bookService.deleteBook(book);
            return new ResponseEntity<>(book.getIsbn(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

}
