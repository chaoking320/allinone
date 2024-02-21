package chaoking.java.allinone.bytebuddyagent;

import net.bytebuddy.implementation.bind.annotation.AllArguments;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import net.bytebuddy.implementation.bind.annotation.SuperCall;

import java.util.concurrent.Callable;

public class MethodInterceptor {
    @RuntimeType
    public static String intercept(@SuperCall Callable<String> superCall, @AllArguments Object[] args) throws Exception{
        System.out.printf("before");
//        String result = superCall.call();
        String result = "superCall.call()";
        System.out.printf("after");

        return "modified: "+ result;
    }
}
