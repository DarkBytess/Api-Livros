package com.example.bookstore.controller;

import com.example.bookstore.entity.Book;
import com.example.bookstore.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
@CrossOrigin(origins = "*")
@Tag(name = "Books", description = "API para gerenciamento de livros")
public class BookController {
    
    @Autowired
    private BookService bookService;
    
    @GetMapping
    @Operation(summary = "Listar todos os livros", description = "Retorna uma lista com todos os livros cadastrados")
    @ApiResponse(responseCode = "200", description = "Lista de livros retornada com sucesso")
    public ResponseEntity<List<Book>> getAllBooks() {
        try {
            List<Book> books = bookService.getAllBooks();
            return ResponseEntity.ok(books);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Buscar livro por ID", description = "Retorna um livro específico baseado no ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Livro encontrado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Livro não encontrado")
    })
    public ResponseEntity<Book> getBookById(
            @Parameter(description = "ID do livro", example = "1")
            @PathVariable Long id) {
        try {
            Optional<Book> book = bookService.getBookById(id);
            return book.map(ResponseEntity::ok)
                      .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @PostMapping
    @Operation(summary = "Criar novo livro", description = "Adiciona um novo livro ao sistema")
    @ApiResponse(responseCode = "201", description = "Livro criado com sucesso")
    public ResponseEntity<Book> createBook(
            @Parameter(description = "Dados do novo livro")
            @RequestBody Book book) {
        try {
            // Validação simples
            if (book.getTitle() == null || book.getTitle().trim().isEmpty()) {
                return ResponseEntity.badRequest().build();
            }
            if (book.getAuthor() == null || book.getAuthor().trim().isEmpty()) {
                return ResponseEntity.badRequest().build();
            }
            if (book.getPrice() == null || book.getPrice() <= 0) {
                return ResponseEntity.badRequest().build();
            }
            if (book.getPublishYear() == null || book.getPublishYear() <= 0) {
                return ResponseEntity.badRequest().build();
            }
            
            Book newBook = bookService.createBook(book);
            return ResponseEntity.status(HttpStatus.CREATED).body(newBook);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Atualizar livro", description = "Atualiza os dados de um livro existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Livro atualizado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Livro não encontrado")
    })
    public ResponseEntity<Book> updateBook(
            @Parameter(description = "ID do livro a ser atualizado", example = "1")
            @PathVariable Long id,
            @Parameter(description = "Novos dados do livro")
            @RequestBody Book bookDetails) {
        try {
            Book updatedBook = bookService.updateBook(id, bookDetails);
            if (updatedBook != null) {
                return ResponseEntity.ok(updatedBook);
            }
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar livro", description = "Remove um livro do sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Livro deletado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Livro não encontrado")
    })
    public ResponseEntity<Void> deleteBook(
            @Parameter(description = "ID do livro a ser deletado", example = "1")
            @PathVariable Long id) {
        try {
            boolean deleted = bookService.deleteBook(id);
            if (deleted) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}