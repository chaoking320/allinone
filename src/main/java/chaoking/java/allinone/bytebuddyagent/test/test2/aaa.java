package chaoking.java.allinone.bytebuddyagent.test.test2;

import chaoking.java.allinone.bytebuddyagent.ByteBuddyAgentTest;
import net.bytebuddy.agent.ByteBuddyAgent;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.implementation.bind.annotation.*;
import net.bytebuddy.matcher.ElementMatchers;

import java.lang.instrument.Instrumentation;
import java.lang.reflect.Method;
import java.util.Locale;
import java.util.concurrent.Callable;

public class aaa {
    public static void main(String[] args) throws Exception {
        // 初始化 Byte Buddy Agent
        Instrumentation instrumentation = ByteBuddyAgent.install();
// 修改 OriginalClass 字节码
//        new AgentBuilder.Default()
//                .type(ElementMatchers.is(OriginalClass.class))
////                .type(ElementMatchers.named("chaoking.java.allinone.bytebuddyagent.test.test2.OriginalClass"))
//                .transform( (builder, typeDescription, classloader, module)->
//                    builder.method(ElementMatchers.named("hello"))
//                            .intercept(MethodDelegation.to(OriginalClassInterceptor.class))
//                )
//                .installOn(instrumentation);

        // 创建一个AgentBuilder
        new AgentBuilder.Default()
                .type(ElementMatchers.is(OriginalClass.class)) // 替换成你的目标类名
                .transform((builder, type, classLoader, module) ->
                        builder.method(method -> method.getName().equals("hello")) // 替换成你的目标方法名
                                .intercept(Advice.to(SkipExecutionAdvice.class))) // 使用Advice类进行拦截
                .installOn(instrumentation);


        OriginalClass o = new OriginalClass();
        o.hello("123");

    }

    public static class SkipExecutionAdvice {

        @Advice.OnMethodEnter
        public static void skipExecution(@Advice.Origin Method method) {
            System.out.println("Skipping execution of method: " + method.getName());
//            throw new SkipExecutionException(); // 抛出自定义的异常，跳过原方法的执行
        }
    }
}
