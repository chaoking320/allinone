package chaoking.java.allinone.learn.Aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ControllerAspect {
    private static final Logger logger = LoggerFactory.getLogger(ControllerAspect.class);

    /*@Autowired
    private EntityManager entityManager;*/

    // region Before

    @Before("execution(* chaoking.java.allinone.controller.*.*(..))")
    public void before(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
    }

    // endregion

    // region After

       @After("execution(* chaoking.java.allinone.controller.*.*(..))")
    public void after(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
    }

 @AfterReturning("execution(* chaoking.java.allinone.controller.*.*(..))")
    public void AfterReturning(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
    }

    @AfterThrowing("execution(* chaoking.java.allinone.controller.*.*(..))")
    public void AfterThrowing(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
    }

    // endregion

    // region Around

    /**
     * 调用controller包下的任意类的任意方法时均会调用此方法
     */
    @Around("execution(* chaoking.java.allinone.controller.*.*(..))")
    public Object run1(ProceedingJoinPoint joinPoint) throws Throwable {
        //获取方法参数值数组
        Object[] args = joinPoint.getArgs();
        //得到其方法签名
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        //获取方法参数类型数组
        Class[] paramTypeArray = methodSignature.getParameterTypes();
     /*   if (EntityManager.class.isAssignableFrom(paramTypeArray[paramTypeArray.length - 1])) {
            //如果方法的参数列表最后一个参数是entityManager类型，则给其赋值
            args[args.length - 1] = entityManager;
        }*/
        logger.info("请求参数为{}",args);
        //动态修改其参数
        //注意，如果调用joinPoint.proceed()方法，则修改的参数值不会生效，必须调用joinPoint.proceed(Object[] args)
        Object result = joinPoint.proceed(args);
        logger.info("响应结果为{}",result);
        //如果这里不返回result，则目标对象实际返回值会被置为null
        return result;
    }

    @Pointcut("execution(* chaoking.java.allinone.controller.*.*(..))")
    public void pointCut2() {}

    @Around("pointCut2()")
    public Object run2(ProceedingJoinPoint joinPoint) throws Throwable {
        //获取方法参数值数组
        Object[] args = joinPoint.getArgs();
        //得到其方法签名
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        //获取方法参数类型数组
        Class[] paramTypeArray = methodSignature.getParameterTypes();
      /*  if (EntityManager.class.isAssignableFrom(paramTypeArray[paramTypeArray.length - 1])) {
            //如果方法的参数列表最后一个参数是entityManager类型，则给其赋值
            args[args.length - 1] = entityManager;
        }*/
        logger.info("请求参数为{}",args);
        //动态修改其参数
        //注意，如果调用joinPoint.proceed()方法，则修改的参数值不会生效，必须调用joinPoint.proceed(Object[] args)
        Object result = joinPoint.proceed(args);
        logger.info("响应结果为{}",result);
        //如果这里不返回result，则目标对象实际返回值会被置为null
        return result;
    }

    //endregion

}
