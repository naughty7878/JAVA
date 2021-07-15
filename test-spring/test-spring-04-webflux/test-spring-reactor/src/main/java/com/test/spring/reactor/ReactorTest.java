package com.test.spring.reactor;

import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class ReactorTest {

    // 声明数据流
    @Test
    public void test01() {
        // just 方法直接声明 数据流
        Flux.just(1, 2, 3, 4);
        Mono.just(1);

        // 其他方法
        Integer[] array = new Integer[]{1, 2, 3};
        Flux.fromArray(array);

        List<Integer> list = new ArrayList<>();
        Flux.fromIterable(list);

        Stream<Integer> stream = list.stream();
        Flux.fromStream(stream);
    }

    @Test
    public void test02() {
        // just 方法直接声明 发布者
        Flux<Integer> flux = Flux.just(1, 2, 3, 4);
        // 订阅 数据流，同时会触发数据流
        flux.subscribe(System.out::println);
    }


    @Test
    public void test03() {
        // just 方法直接声明 发布者
        Flux<Integer> flux = Flux.just(1, 2, 3, 4);
        // 操作符-映射
        flux = flux.map(x -> x * x);
        // 订阅 数据流，同时会触发数据流
        flux.subscribe(System.out::println);
    }

    @Test
    public void test04() {
        // just 方法直接声明 发布者
        Flux<String> flux = Flux.just("abc", "def", "gh");
        // 操作符-映射
        Flux<Character> flux2 = (Flux<Character>) flux.flatMap(x -> {
            Character[] characters = x.chars().mapToObj(c -> (char) c).toArray(Character[]::new);
            return Flux.fromArray(characters);
            }
        );
        // 订阅 数据流，同时会触发数据流
        flux2.subscribe(System.out::println);

    }

    @Test
    public void test05() {
        // just 方法直接声明 发布者
        Flux<String> flux = Flux.just("abc", "def", "gh");
        flux.flatMap(value -> Mono.just(value))
                // 获取下一个元素
                .next()
                .subscribe(System.out::println);

    }

    @Test
    public void test06() {
        // just 方法直接声明 发布者
        Flux<String> flux = Flux.just("abc", "def", "gh");
        flux.concatMap(value -> Mono.just(value))
                .subscribe(System.out::println);

    }

    @Test
    public void test07() {
        // just 方法直接声明 发布者
        Flux<String> flux = Flux.just("abc", "def", "gh");
        // 添加当Flux发出项目时触发的行为
        flux.doOnNext(System.out::println)
                .subscribe(System.out::println);

    }

    @Test
    public void test08() {
        // just 方法直接声明 发布者
        Flux<String> flux = Flux.just("abc", "def", "gh");
        // 添加当Flux发出项目时触发的行为
        flux.filterWhen(x -> x.length() > 2? Mono.just(Boolean.TRUE): Mono.just(Boolean.FALSE))
                .subscribe(System.out::println);

    }

    @Test
    public void test09() {
        // just 方法直接声明 发布者
        Flux<String> flux = Flux.just("abc", "def", "gh");
        // next() 获取下一个元素
        // then() 使用参数mono 替换 当前 mono
        flux.next().then(Mono.empty());
    }
}
