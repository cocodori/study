package com.toby01;

public class StaticDispatch {

    private static class Service {
        void run(int number) {
            System.out.println("run() " + number);
        }

        void
        run(String str) {
            System.out.println("run() " + str);
        }
    }

    public static void main(String[] args) {
        new Service().run(1);
        new Service().run("1");
    }
}