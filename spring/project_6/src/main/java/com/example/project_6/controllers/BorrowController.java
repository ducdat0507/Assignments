package com.example.project_6.controllers;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.project_6.models.Book;
import com.example.project_6.models.BorrowedBook;
import com.example.project_6.repositories.BorrowedBookRepository;
import com.example.project_6.repositories.BorrowerRepository;
import com.example.project_6.services.BookService;

import java.time.LocalDate;

@Controller
public class BorrowController {

    private final BookService bookService;
    private final BorrowerRepository borrowerRepository;
    private final BorrowedBookRepository borrowedBookRepository;

    public BorrowController(BookService bookService, BorrowerRepository borrowerRepository, BorrowedBookRepository borrowedBookRepository) {
        this.bookService = bookService;
        this.borrowerRepository = borrowerRepository;
        this.borrowedBookRepository = borrowedBookRepository;
    }

    @GetMapping("/borrow")
    public String showBorrowForm(Model model) {
        model.addAttribute("borrowedBook", new BorrowedBook());
        model.addAttribute("borrowers", borrowerRepository.findAll());
        model.addAttribute("books", bookService.findAllBooks());
        return "borrow-book";
    }

    @PostMapping("/borrow")
    public String borrowBook(@Valid @ModelAttribute("borrowedBook") BorrowedBook borrowedBook, BindingResult result) {
        if (result.hasErrors()) {
            return "borrow-book";
        }

        Book book = borrowedBook.getBook();
        if (book.getCopiesAvailable() > 0) {
            book.setCopiesAvailable(book.getCopiesAvailable() - 1);
            bookService.saveBook(book);
            borrowedBook.setBorrowDate(LocalDate.now());
            borrowedBookRepository.save(borrowedBook);
        }

        return "redirect:/borrowed-books";
    }

    @GetMapping("/borrowed-books")
    public String listBorrowedBooks(Model model) {
        model.addAttribute("borrowedBooks", borrowedBookRepository.findAll());
        return "list-borrowed-books";
    }
}