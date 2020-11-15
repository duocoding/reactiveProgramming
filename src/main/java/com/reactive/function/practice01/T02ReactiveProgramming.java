package com.reactive.function.practice01;

import com.reactive.function.domain.Book;
import com.reactive.function.domain.InmenoryDataSource;
import reactor.core.publisher.Flux;

import java.util.Comparator;

/**
 * @ClassName T02ReactiveProgramming
 * @Description TODO
 * @Author qulingxiao
 * @Date 2020/11/15 11:16
 * @Version 1.0
 */
public class T02ReactiveProgramming {

    public static Flux<Book> getMostExpensiveBooksByCatagoryReactive(Flux<Book> books) {

        return books.collectMultimap(Book::getCatagory)
                .flatMapMany( m -> Flux.fromIterable(m.entrySet()))
                .flatMap( e -> Flux.fromIterable(e.getValue())
                        .sort(Comparator.comparing(Book::getPrice).reversed())
                        .next())
                ;
    }

    public static void main(String[] args) {
        Flux<Book> mostExpensiveBooksByCatagoryReactive = getMostExpensiveBooksByCatagoryReactive(Flux.just(InmenoryDataSource.books));
        mostExpensiveBooksByCatagoryReactive.doOnNext(System.out::println).subscribe();
    }
}
