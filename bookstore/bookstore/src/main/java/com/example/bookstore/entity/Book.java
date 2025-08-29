package com.example.bookstore.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

@Entity
@Table(name = "books")
@Schema(description = "Entidade que representa um livro na livraria")
public class Book {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único do livro", example = "1")
    private Long id;
    
    @Column(nullable = false, length = 200)
    @Schema(description = "Título do livro", example = "O Senhor dos Anéis")
    private String title;
    
    @Column(nullable = false, length = 100)
    @Schema(description = "Autor do livro", example = "J.R.R. Tolkien")
    private String author;
    
    @Column(nullable = false)
    @Schema(description = "Preço do livro", example = "120.0")
    private Double price;
    
    @Column(name = "publish_year", nullable = false)
    @Schema(description = "Ano de publicação do livro", example = "1954")
    private Integer publishYear;
    
    // Constructors
    public Book() {}
    
    public Book(String title, String author, Double price, Integer publishYear) {
        this.title = title;
        this.author = author;
        this.price = price;
        this.publishYear = publishYear;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    
    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }
    
    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }
    
    public Integer getPublishYear() { return publishYear; }
    public void setPublishYear(Integer publishYear) { this.publishYear = publishYear; }
    
    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", price=" + price +
                ", publishYear=" + publishYear +
                '}';
    }
}