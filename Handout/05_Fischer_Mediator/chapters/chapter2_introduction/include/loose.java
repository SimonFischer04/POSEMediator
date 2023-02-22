public interface ICalculator {
    public int calculate(int a, int b);
}

public class Adder implements ICalculator {
    public int calculate(int a, int b) {
        return a + b;
    }
}

public class Multiplier implements ICalculator {
    public int calculate(int a, int b) {
        return a * b;
    }
}

@RequiredArgsConstructor
public class CalculartorApp {
    private final ICalculator calculator;

    public int calculate(int a, int b) {
        return calculator.calculate(a, b);
    }
}

public class Main {
    public static void main(String[] args) {
        var adder = new CalculatorClient(new Adder());
        System.out.println(adder.calculate(3, 14));

        var multiplier = new CalculatorClient(new Multiplier());
        System.out.println(multiplier.calculate(4, 2));
    }
}
