package com.github.codingob.se.stream;

import java.util.Arrays;
import java.util.List;

/**
 * @author codingob
 * @version 1.0.0
 * @since JDK1.8
 */
public class StreamDemo {
    public static void main(String[] args) {
        User user1 = new User(1, "a", 0);
        User user2 = new User(2, "b", 2);
        User user3 = new User(3, "c", 4);
        User user4 = new User(4, "d", 6);
        User user5 = new User(5, "e", 8);
        List<User> list = Arrays.asList(user1, user2, user3, user4, user5);
        list.forEach(System.out::println);
        list.stream().filter(u -> u.getId() % 2 == 0)
                .forEach(System.out::println);
        list.stream().filter(u -> u.getId() % 2 == 0)
                .filter(u -> u.getAge() > 2)
                .forEach(System.out::println);
        list.stream().filter(u -> u.getId() % 2 == 0)
                .filter(u -> u.getAge() > 0)
                .map(user -> user.getName().toUpperCase())
                .sorted(String::compareTo)
                .limit(1)
                .forEach(System.out::println);
    }
}

class User {
    private int id;
    private String name;
    private int age;

    public User(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
