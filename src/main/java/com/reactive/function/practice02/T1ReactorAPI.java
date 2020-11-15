package com.reactive.function.practice02;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * @ClassName T1ReactorAPI
 * @Description TODO
 * @Author qulingxiao
 * @Date 2020/11/15 13:08
 * @Version 1.0
 */
public class T1ReactorAPI {

    public static void createFluxFromExistingData() {
        Flux<Integer> just = Flux.just(1, 2, 3, 4, 5, 6);
        subscribeFlux("just", just);
        Flux<Integer> integerFlux = Flux.fromArray(new Integer[]{1, 2, 3, 4, 5, 6});
        subscribeFlux("integerFlux", integerFlux);
        Flux<Integer> iterableFLux = Flux.fromIterable(Arrays.asList(1, 2, 3, 4, 5, 6));
        subscribeFlux("iterableFlux", iterableFLux);
        Flux<Integer> streamFlux2 = Flux.fromStream(Stream.of(1, 2, 3, 4, 5, 6));
        subscribeFlux("streamFlux", streamFlux2);
        Flux<Integer> rangeFlux = Flux.range(1, 6);
        subscribeFlux("rangeFlux", rangeFlux);

    }

    public static void createFluxFromProgrammatically() {
        Flux<Object> generate = Flux.generate(() -> 1, (state, sink) -> {
            sink.next("message: " + state);
            if (state == 10) {
                sink.complete();
            }
            return state + 1;
        });
        subscribeFlux("generate", generate);
    }

    public static void mapVSFlatMap() {
        Flux<String> mapFlux = Flux.just(1, 2, 3).map(i -> "byMap " + i);
        subscribeFlux("byMap", mapFlux);

        Flux<String> flatMapFlux = Flux.just(1, 2, 3).flatMap(i -> Mono.just("byFlatMap " + i));
        subscribeFlux("flatMapFLux", flatMapFlux);
    }

    private static void subscribeFlux(String name, Flux flux) {
        flux.doOnSubscribe(s -> System.out.print(name + ": "))
                .doOnNext(e -> System.out.print(e + ", "))
                .doOnComplete(System.out::println)
                .subscribe();
    }

    public static void main(String[] args) {
        createFluxFromExistingData();
        createFluxFromProgrammatically();
        mapVSFlatMap();
    }
}
