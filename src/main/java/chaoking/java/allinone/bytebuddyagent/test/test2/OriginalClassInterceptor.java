package chaoking.java.allinone.bytebuddyagent.test.test2;

import net.bytebuddy.implementation.bind.annotation.AllArguments;
import net.bytebuddy.implementation.bind.annotation.Argument;
import net.bytebuddy.implementation.bind.annotation.SuperCall;

import java.util.Locale;
import java.util.concurrent.Callable;

public class OriginalClassInterceptor {

//     方式1
    public static String intercept(@AllArguments Object[] args, @SuperCall Callable<String> callable) throws Exception {
        // callable.call() 将会输出 "foo {val}"
        System.out.printf("你好"+args[0]);
        return "hello " + args[0];
    }

//    // 方式2
//    public static String hello(@Argument(0) String val, @SuperCall Callable<String> callable) throws Exception {
//        System.out.printf("你好"+val);
//        return callable.call();
//    }
}
