package chaoking.java.allinone.learn.thread;

import java.math.BigDecimal;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class ReetranLockDemo {
    public static void main(String[] args) throws Exception{
        ExecutorService executorService = Executors.newCachedThreadPool();
        ReentrantLock reentrantLock = new ReentrantLock();
        int[] count = {0};
        int num = 0;

        BigDecimal amount = BigDecimal.ZERO;
        TestBigDecimal(amount);

        TT tt = new TT();
        tt.setName("智卫");
        TestObject(tt);
        //region

        for (int i=0;i<10000;i++){
            executorService.submit(()->{
                try{
                    reentrantLock.lock();
                    count[0]++;
                    System.out.println(num);
                }catch(Exception e){
                    e.printStackTrace();
                }finally {
                    reentrantLock.unlock();
                }
            });
        }
        reentrantLock.unlock();
        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.HOURS);
        System.out.println(count);

        //endregion
    }

    private static void TestBigDecimal(BigDecimal amount){
        amount = BigDecimal.ONE;
    }

    private static void TestObject(TT tt){
        tt.setName("超载");
    }

    static class TT{
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
