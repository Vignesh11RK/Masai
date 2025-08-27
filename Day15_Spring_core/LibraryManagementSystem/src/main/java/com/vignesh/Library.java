package com.vignesh;

import java.util.List;

public class Library {
    private String name;
    private Member member;
    private List<Book> books;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public void displayLibraryDetails(){
        System.out.println("Library"+name);
        System.out.println("Membar"+member);
        System.out.println("Books:");
        for(Book book:books){
            System.out.println(book);
        }
    }
}
