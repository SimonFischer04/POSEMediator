public class Calculator {
    public int add(int a, int b) {
        return a + b;
    }

    public int multiply(int a, int b) {
        return a * b;
    }
}

@RequiredArgsConstructor
public class CalculartorApp {
    private final Calculator calculator;

    public int add(int a, int b) {
        return calculator.add(a, b);
    }

    public int multiply(int a, int b) {
        return calculator.multiply(a, b);
    }
}

public class Main {
    public static void main(String[] args) {
        var calcApp = new CalculartorApp();
        System.out.println(calcApp.add(3, 14));

        System.out.println(calcApp.multiply(4, 2));
    }
}
