package com.toby01;

import java.util.Arrays;
import java.util.List;

public class DynamicDispatch {
    private static abstract class Service {
        abstract void run();
    }

    private static class MyService1 extends Service {
        @Override
        void run() {
            System.out.println("My Service1....");
        }
    }

    private static class MyService2 extends Service {
        @Override
        void run() {
            System.out.println("My Service2....");
        }
    }

    public static void main(String[] args) {
        List<Service> service = Arrays.asList(new MyService1(), new MyService2());

        service.forEach(Service::run);
    }
}
