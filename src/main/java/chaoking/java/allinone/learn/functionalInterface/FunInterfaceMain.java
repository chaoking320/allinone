package chaoking.java.allinone.learn.functionalInterface;

public class FunInterfaceMain {

    static GreetingService greetingService = message -> {
        System.out.println("Hello "+ message);
    };

    public static void main(String[] args) {
        greetingService.sayMessage("111");
        System.out.printf("222");
    }
}
