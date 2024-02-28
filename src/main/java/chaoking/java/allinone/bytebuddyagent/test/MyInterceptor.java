package chaoking.java.allinone.bytebuddyagent.test;

import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import net.bytebuddy.implementation.bind.annotation.SuperCall;

import java.util.concurrent.Callable;

public class MyInterceptor {
    @RuntimeType
    public static void myPrivateMethod(@SuperCall Callable<?> zuper) throws Exception {
        if (shouldSkipMethod()) {
            System.out.println("Skipping private method execution");
        } else {
            System.out.println("Executing private method");
            zuper.call(); // Execute the original method
        }
    }

    private static boolean shouldSkipMethod() {
        // Implement your condition here
        return true; // Replace with your actual condition
    }
}
