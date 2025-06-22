package chaoking.java.allinone.bytebuddyagent;

import net.bytebuddy.asm.Advice;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.implementation.bind.annotation.AllArguments;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import net.bytebuddy.implementation.bind.annotation.SuperCall;

import java.lang.invoke.MethodHandle;
import java.lang.reflect.Method;
import java.util.concurrent.Callable;

public class MethodInterceptor {
    @RuntimeType
    public static String intercept(@SuperCall Callable<String> superCall,@AllArguments Object[] args) throws Throwable {
        System.out.printf("before");
        // 执行原方法，如果不想执行原方法屏蔽即可
        // TODO: 2024/2/22 私有方法如何处理？？
        String result = superCall.call();

//        methodHandle.invoke(args);
//        String result = "superCall.call()";
        System.out.printf("after");

        return "modified: "+result;
    }
}
