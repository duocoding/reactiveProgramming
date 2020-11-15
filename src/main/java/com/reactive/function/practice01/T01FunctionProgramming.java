package com.reactive.function.practice01;

import com.reactive.function.domain.Book;
import com.reactive.function.domain.InmenoryDataSource;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @ClassName T01FunctionProgramming
 * @Description TODO
 * @Author qulingxiao
 * @Date 2020/11/15 11:15
 * @Version 1.0
 */
public class T01FunctionProgramming {

    //返回一个包含每种类别中最贵的书的列表
    public static List<Book> getMostExpensiveBooksByCatagory() {
        Map<String, Book> map = new HashMap<>();
        for (Book book : InmenoryDataSource.books) {
            Book aBook = map.get(book.getCatagory());
            if (aBook != null) {
                if (book.getPrice().compareTo(aBook.getPrice()) > 0) {
                    map.put(book.getCatagory(), book);
                }
            } else {
                map.put(book.getCatagory(), book);
            }
        }
        return (new ArrayList<>(map.values()));
    }

    public static List<Book> getMostExpensiveBooksByCatagoryFunctional() {
        return Stream.of(InmenoryDataSource.books)
                .collect(Collectors.groupingBy(Book::getCatagory))
                .entrySet()
                .stream()
                .map( b -> b.getValue()
                        .stream()
                        .sorted( Comparator.comparing(Book::getPrice).reversed())
                        .findFirst()
                        .get()
                )
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        List<Book> mostExpensiveBooksByCatagory = getMostExpensiveBooksByCatagory();
        mostExpensiveBooksByCatagory.stream()
                .forEach(System.out::println);

        List<Book> mostExpensiveBooksByCatagoryFunctional = getMostExpensiveBooksByCatagoryFunctional();
        mostExpensiveBooksByCatagoryFunctional.stream()
                .forEach(System.out::println);
    }
}
