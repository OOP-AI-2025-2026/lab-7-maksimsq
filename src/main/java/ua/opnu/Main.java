package ua.opnu;

import java.util.Arrays;
import java.util.function.Predicate;
import java.util.function.Function;
import java.util.function.Consumer;

public class Main {

    public static void main(String[] args) {

        // task 1
        Predicate<Integer> isPrime = n -> {
            if (n == null || n < 2) return false;
            for (int i = 2; i * i <= n; i++) {
                if (n % i == 0) return false;
            }
            return true;
        };

        System.out.println("7 просте?  " + isPrime.test(7));
        System.out.println("15 просте? " + isPrime.test(15));

        //task 2
        Student[] students = {
                new Student("Petrenko", "AI-241", new int[]{100, 90, 75}),
                new Student("Melnyk", "AI-242", new int[]{55, 40, 78}), // борг
                new Student("Shevchenko", "AI-241", new int[]{100, 100, 95}),
                new Student("Koval", "AI-243", new int[]{59, 58, 40}), // борг
        };

        Predicate<Student> hasDebts = s -> s.countDebts() >= 1;

        System.out.println("\nСтуденти з >=1 заборгованістю:");
        var filtered = filterStudents(students, hasDebts);
        Arrays.stream(filtered).forEach(s -> System.out.println(s.getName()));

        // task 3
        Predicate<Student> noDebts = s -> s.countDebts() == 0;
        Predicate<Student> isAI241 = s -> s.getGroup().equals("AI-241");

        System.out.println("\nСтуденти без боргів та з групи AI-241:");
        var filtered2 = filterStudentsTwo(students, noDebts, isAI241);
        Arrays.stream(filtered2).forEach(s -> System.out.println(s.getName()));

        // task 4
        Consumer<Student> printName = s ->
                System.out.println(s.getName() + " " + s.getGroup());

        System.out.println("\nConsumer forEach():");
        forEach(students, printName);

        // task 5
        Integer[] nums = {1,2,3,4,5,6,7,8,9,10};

        Predicate<Integer> isEven = x -> x % 2 == 0;
        Consumer<Integer> show = x -> System.out.println("OK: " + x);

        System.out.println("\nМетод doIf:");
        doIf(nums, isEven, show);

        // task 6
        Function<Integer, Integer> pow2 = n -> (int) Math.pow(2, n);

        int[] array10 = {0,1,2,3,4,5,6,7,8,9};

        System.out.println("\n2^n для масиву 0..9:");
        for (int n : array10) {
            System.out.println("n = " + n + " -> " + pow2.apply(n));
        }

        // task 7
        Function<Integer, String> numToWord = n -> switch (n) {
            case 0 -> "нуль";
            case 1 -> "один";
            case 2 -> "два";
            case 3 -> "три";
            case 4 -> "чотири";
            case 5 -> "п'ять";
            case 6 -> "шість";
            case 7 -> "сім";
            case 8 -> "вісім";
            case 9 -> "дев'ять";
            default -> "?";
        };

        System.out.println("\nstringify():");
        System.out.println(stringify(array10, numToWord));
    }

    // методи

    // task 2
    static Student[] filterStudents(Student[] arr, Predicate<Student> predicate) {
        return Arrays.stream(arr).filter(predicate).toArray(Student[]::new);
    }

    // task 3
    static Student[] filterStudentsTwo(Student[] arr,
                                       Predicate<Student> p1,
                                       Predicate<Student> p2) {
        return Arrays.stream(arr)
                .filter(s -> p1.test(s) && p2.test(s))
                .toArray(Student[]::new);
    }

    // task 4
    static void forEach(Student[] arr, Consumer<Student> consumer) {
        for (Student s : arr) consumer.accept(s);
    }

    // task 5
    static <T> void doIf(T[] arr, Predicate<T> predicate, Consumer<T> consumer) {
        for (T el : arr)
            if (predicate.test(el)) consumer.accept(el);
    }

    // task 7
    static String stringify(int[] arr, Function<Integer, String> function) {
        StringBuilder sb = new StringBuilder();
        for (int x : arr) sb.append(function.apply(x)).append(" ");
        return sb.toString().trim();
    }
}
