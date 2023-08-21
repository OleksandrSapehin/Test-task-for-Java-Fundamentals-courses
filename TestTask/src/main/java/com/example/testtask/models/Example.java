package com.example.testtask.models;

import jakarta.persistence.*;

@Entity
@Table(name = "Mathexample")
public class Example {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "example")
    private String example;
    @Column(name = "roots")
    private Double roots;

    public Example() {

    }

    public Example(String example, Double roots) {
        this.roots = roots;
        this.example = example;

    }
    public Double getRoots() {
        return roots;
    }

    public void setRoots(Double roots) {
        this.roots = roots;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }


}
