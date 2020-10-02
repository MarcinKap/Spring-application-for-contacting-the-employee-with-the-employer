package com.example.Project_Spring.mappers;

public interface Mapper<F, T> {

    T map(F from);
    F reverseMap(T to);
}