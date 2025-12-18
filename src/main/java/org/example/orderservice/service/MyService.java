package org.example.orderservice.service;

import org.springframework.stereotype.Service;

@Service
public class MyService {

    public String sayHello(String name) {
        System.out.println("sayHello executing....");
        return "Hello, " + name + "!";
    }

    public int addNumbers(int a, int b) {
        return a + b;
    }
}
