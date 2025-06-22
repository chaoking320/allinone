package chaoking.java.allinone.bytebuddyagent.test.test2;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.implementation.bind.annotation.AllArguments;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import net.bytebuddy.implementation.bind.annotation.SuperCall;
import net.bytebuddy.implementation.bind.annotation.This;
import net.bytebuddy.matcher.ElementMatchers;

import java.lang.reflect.Method;
import java.util.concurrent.Callable;

public class PrivateMethodInterceptor {
    public static void main(String[] args) throws Exception {
        Foo foo = new ByteBuddy()
                .subclass(Foo.class)
//                .method(ElementMatchers.isPrivate()) // 拦截私有方法
                .method(ElementMatchers.named("publicMethod"))
                .intercept(MethodDelegation.to(PrivateMethodInterceptor.class))
                .make()
                .load(PrivateMethodInterceptor.class.getClassLoader())
                .getLoaded()
                .getConstructor()
                .newInstance();

        foo.publicMethod(); // 调用公有方法
        foo.privateMethod(); // 调用私有方法
    }

    @RuntimeType
    public static Object intercept(@This Object obj, @SuperCall Callable<?> superMethod, @AllArguments Object[] args) throws Exception {
        Method currentMethod = new Object() {}.getClass().getEnclosingMethod();
        System.out.println("Intercepting method: " + currentMethod.getName());

        // 在这里添加你的判断逻辑
        if (shouldSkipMethod(currentMethod)) {
            System.out.println("Skipping method: " + currentMethod.getName());
            return null; // 跳过方法执行
        }

        return superMethod.call();
    }

    private static boolean shouldSkipMethod(Method method) {
        // 在这里添加你的判断逻辑，判断是否需要跳过该方法的执行
        return false; // 默认不跳过
    }

    public static class Foo {
        public void publicMethod() {
            System.out.println("Executing publicMethod");
        }

        private void privateMethod() {
            System.out.println("Executing privateMethod");
        }
    }
}
