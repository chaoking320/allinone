package chaoking.java.allinone.bytebuddyagent.test;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;

import java.lang.instrument.Instrumentation;
import java.lang.reflect.InvocationTargetException;

public class MyClass {
        private void myPrivateMethod() {
            System.out.println("Private method executed");
        }

        public void publicMethod() {
            myPrivateMethod();
        }

        public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
            MyClass myClass = new ByteBuddy()
                    .subclass(MyClass.class)
                    // TODO: 2024/2/28  
                    .method(ElementMatchers.named("publicMethod"))
                    .intercept(MethodDelegation.to(MyInterceptor.class))
                    .make()
                    .load(MyClass.class.getClassLoader())
                    .getLoaded()
                    .getDeclaredConstructor()
                    .newInstance();
            
            myClass.myPrivateMethod();
            myClass.publicMethod();
        }
    }



