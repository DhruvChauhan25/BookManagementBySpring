//package com.bookmanagement.bookmanagement.Service;
//
//import com.bookmanagement.bookmanagement.Book;
//import com.bookmanagement.bookmanagement.Repository.BookRepository;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class BookService {
//    Logger logger = LoggerFactory.getLogger(BookService.class);
//    @Autowired
//    private BookRepository bookRepository;
//
//    public List<Book> getAllBooks() {
//        logger.debug("Showing all up the users");
//        return bookRepository.findAll();
//    }
//
//    public Optional<Book> getBookById(Long id) {
//        logger.debug("Finding all te user");
//        return bookRepository.findById(Math.toIntExact(id));
//    }
//
//    public Book saveBook(Book book) {
//        logger.debug("Saving the book");
//        return bookRepository.save(book);
//    }
//
//    public void deleteBook(Long id) {
//        logger.debug("Deleting up the users");
//        bookRepository.deleteById(Math.toIntExact(id));
//    }
//
//    public Book updateBook(Long id, Book updatedBook) {
//        logger.debug("Updating the book by id");
//        Optional<Book> optionalBook = bookRepository.findById(Math.toIntExact(id));
//
//        if (optionalBook.isPresent()) {
//            Book existingBook = optionalBook.get();
//            existingBook.setBookName(updatedBook.getBookName());
//            existingBook.setAuthor(updatedBook.getAuthor());
//            existingBook.setPrice(updatedBook.getPrice());
//
//            return bookRepository.save(existingBook);
//        } else {
//            // Handle the case where the book with the given id is not found
//            throw new IllegalArgumentException("Book with id " + id + " not found");
//        }
//    }
//
//}




package com.bookmanagement.bookmanagement.Service;

import com.bookmanagement.bookmanagement.Book;
import com.bookmanagement.bookmanagement.BookDTO;
import com.bookmanagement.bookmanagement.Repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {
    Logger logger = LoggerFactory.getLogger(BookService.class);

    @Autowired
    private BookRepository bookRepository;

    public List<BookDTO> getAllBooks() {
        logger.debug("Showing all up the users");
        List<Book> books = bookRepository.findAll();
        return books.stream()
                .map(BookDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public Optional<BookDTO> getBookById(Long id) {
        logger.debug("Finding all the user");
        return bookRepository.findById(Math.toIntExact(id))
                .map(BookDTO::fromEntity);
    }

    public BookDTO saveBook(BookDTO bookDTO) {
        logger.debug("Saving the book");
        Book book = bookRepository.save(BookDTO.toEntity(bookDTO));
        return BookDTO.fromEntity(book);
    }

    public void deleteBook(Long id) {
        logger.debug("Deleting up the users");
        bookRepository.deleteById(Math.toIntExact(id));
    }

    public BookDTO updateBook(Long id, BookDTO updatedBookDTO) {
        logger.debug("Updating the book by id");
        Optional<Book> optionalBook = bookRepository.findById(Math.toIntExact(id));

        if (optionalBook.isPresent()) {
            Book existingBook = optionalBook.get();
            existingBook.setBookName(updatedBookDTO.getBookName());
            existingBook.setAuthor(updatedBookDTO.getAuthor());
            existingBook.setPrice(updatedBookDTO.getPrice());

            Book updatedBook = bookRepository.save(existingBook);
            return BookDTO.fromEntity(updatedBook);
        } else {
            // Handle the case where the book with the given id is not found
            throw new IllegalArgumentException("Book with id " + id + " not found");
        }
    }
}
