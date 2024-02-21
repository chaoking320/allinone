package chaoking.java.allinone.bytebuddyagent;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.agent.ByteBuddyAgent;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;

import java.lang.instrument.Instrumentation;
import java.lang.reflect.Method;

public class ByteBuddyAgentTest {
    /**
     * 拦截所执行的方法，做自己的逻辑
     * @param args
     */
    public static void main1(String[] args) {
        // 获取Instrumentation实例
        Instrumentation instrumentation = ByteBuddyAgent.install();

        // 创建一个AgentBuilder
        new AgentBuilder.Default()
                .type(target -> target.getName().equals("chaoking.java.allinone.bytebuddyagent.HelloByteBuddy")) // 替换成你的目标类名
                .transform((builder, type, classLoader, module) ->
                        builder.method(method -> method.getName().equals("doWrite")) // 替换成你的目标方法名
                                .intercept(Advice.to(SkipExecutionAdvice.class))) // 使用Advice类进行拦截
                .installOn(instrumentation);

        // 执行调用
        HelloByteBuddy helloByteBuddy = new HelloByteBuddy();
        helloByteBuddy.doWrite("123");
    }

    /**
     * 屏蔽所需执行的方法
     * @param args
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        // 创建一个AgentBuilder
        HelloByteBuddy proxy =  new ByteBuddy()
                .subclass(HelloByteBuddy.class)
                .method(ElementMatchers.named("doWrite"))
                .intercept(MethodDelegation.to(MethodInterceptor.class))
                .make()
                .load(HelloByteBuddy.class.getClassLoader())
                .getLoaded()
                .newInstance();
        proxy.doWrite("123");
    }

    public static class SkipExecutionAdvice {
//        @Advice.OnMethodEnter
//        public static Object skipExecution(@Advice.Origin Method method) {
//            // 在方法执行前拦截，可以选择跳过原方法的执行
//            System.out.println("Skipping execution of method: " + method.getName());
//            return null; // 直接返回null跳过原方法的执行
//        }

        @Advice.OnMethodEnter
        public static void skipExecution(@Advice.Origin Method method) {
            System.out.println("Skipping execution of method: " + method.getName());
            throw new SkipExecutionException(); // 抛出自定义的异常，跳过原方法的执行
        }

        public static class SkipExecutionException extends RuntimeException {
        }

//        @Advice.OnMethodEnter
//        public static Object skipExecution(@Advice.Return(readOnly = false) Object result) {
//            // 保存原方法的返回值
//            Object originalResult = result;
//            // 返回一个默认值，跳过原方法的执行
//            return originalResult;
//        }
//
//        @Advice.OnMethodExit
//        public static void checkExecution(@Advice.Return Object result) {
//            // 判断返回值是否为默认值，如果是则跳过原方法的执行
//            if (result == null) {
//                return;
//            }
//            // 执行原方法的逻辑
//            // ...
//        }
    }


}
