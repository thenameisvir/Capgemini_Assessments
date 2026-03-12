package com.example.springMvcCaseBased.controller;

import com.example.springMvcCaseBased.model.Book;
import com.example.springMvcCaseBased.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/add")
    public String addBookForm(Model model){
        model.addAttribute("book",
                new Book());
        return "add-book";
    }

    @PostMapping("/add")
    public String saveBook(
            @Valid @ModelAttribute Book book,
            BindingResult result){

        if(result.hasErrors())
            return "add-book";

        bookService.addBook(book);

        return "redirect:/books/view";
    }

    @GetMapping("/view")
    public String viewBooks(Model model){
        model.addAttribute("books",
                bookService.getAllBooks());
        return "view-books";
    }

    @GetMapping("/{id}")
    public String bookDetails(
            @PathVariable int id,
            Model model){

        model.addAttribute("book",
                bookService.getBookById(id));

        return "book-details";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(
            @PathVariable int id){

        bookService.deleteBook(id);

        return "redirect:/books/view";
    }
}