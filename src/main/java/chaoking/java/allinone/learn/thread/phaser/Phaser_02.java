package chaoking.java.allinone.learn.thread.phaser;

import java.util.concurrent.*;

public class Phaser_02 {



    public static void main(String[] args) {
        // region  验证屏障数
//        Phaser phaser = new Phaser(1);
//        ThreadA a = new ThreadA(phaser);
//        a.start();
        // endregion

        // region 验证注册数
//        Phaser phaser = new Phaser(5);
//        System.out.println(phaser.getRegisteredParties());
//
//        phaser.register();
//        System.out.println(phaser.getRegisteredParties());
//
//        phaser.register();
//        System.out.println(phaser.getRegisteredParties());
//
//        phaser.register();
//        System.out.println(phaser.getRegisteredParties());
//
//        phaser.register();
//        System.out.println(phaser.getRegisteredParties());
        // endregion

        // region 验证批量注册数
//        Phaser phaser = new Phaser(10);
//        System.out.println(phaser.getRegisteredParties());
//
//        phaser.bulkRegister(10);
//        System.out.println(phaser.getRegisteredParties());
//
//        phaser.bulkRegister(10);
//        System.out.println(phaser.getRegisteredParties());
//
//        phaser.bulkRegister(10);
//        System.out.println(phaser.getRegisteredParties());
//
//        phaser.bulkRegister(10);
//        System.out.println(phaser.getRegisteredParties());
        // endregion

        // region onAdvance
//        Phaser phaser = new Phaser(2) {
//            protected boolean onAdvance(int phase, int registeredParties) {
//                System.out.println("到达了未通过！phase=" + phase
//                        + " registeredParties=" + registeredParties);
//                return super.onAdvance(phase, registeredParties);
//            };
//        };
//        System.out.println("A1 getPhase=" + phaser.getPhase()
//                + " getRegisteredParties=" + phaser.getRegisteredParties()
//                + " getArrivedParties=" + phaser.getArrivedParties());
//        phaser.arrive();
//        System.out.println("A1 getPhase=" + phaser.getPhase()
//                + " getRegisteredParties=" + phaser.getRegisteredParties()
//                + " getArrivedParties=" + phaser.getArrivedParties());
//
//        System.out.println("A2 getPhase=" + phaser.getPhase()
//                + " getRegisteredParties=" + phaser.getRegisteredParties()
//                + " getArrivedParties=" + phaser.getArrivedParties());
//        phaser.arrive();
//        System.out.println("A2 getPhase=" + phaser.getPhase()
//                + " getRegisteredParties=" + phaser.getRegisteredParties()
//                + " getArrivedParties=" + phaser.getArrivedParties());
//        // //////////////
//
//        System.out.println("B1 getPhase=" + phaser.getPhase()
//                + " getRegisteredParties=" + phaser.getRegisteredParties()
//                + " getArrivedParties=" + phaser.getArrivedParties());
//        phaser.arrive();
//        System.out.println("B1 getPhase=" + phaser.getPhase()
//                + " getRegisteredParties=" + phaser.getRegisteredParties()
//                + " getArrivedParties=" + phaser.getArrivedParties());
//
//        System.out.println("B2 getPhase=" + phaser.getPhase()
//                + " getRegisteredParties=" + phaser.getRegisteredParties()
//                + " getArrivedParties=" + phaser.getArrivedParties());
//        phaser.arrive();
//        System.out.println("B2 getPhase=" + phaser.getPhase()
//                + " getRegisteredParties=" + phaser.getRegisteredParties()
//                + " getArrivedParties=" + phaser.getArrivedParties());
//        // //////////////
//        System.out.println("C1 getPhase=" + phaser.getPhase()
//                + " getRegisteredParties=" + phaser.getRegisteredParties()
//                + " getArrivedParties=" + phaser.getArrivedParties());
//        phaser.arrive();
//        System.out.println("C1 getPhase=" + phaser.getPhase()
//                + " getRegisteredParties=" + phaser.getRegisteredParties()
//                + " getArrivedParties=" + phaser.getArrivedParties());
//        System.out.println("C2 getPhase=" + phaser.getPhase()
//                + " getRegisteredParties=" + phaser.getRegisteredParties()
//                + " getArrivedParties=" + phaser.getArrivedParties());
//        phaser.arrive();
//        System.out.println("C2 getPhase=" + phaser.getPhase()
//                + " getRegisteredParties=" + phaser.getRegisteredParties()
//                + " getArrivedParties=" + phaser.getArrivedParties());
            // endregion

        // region Future 利用callable可以返回信息
//        try {
//            Runnable runnable = new Runnable() {
//                @Override
//                public void run() {
//                    System.out.println("打印的信息");
//                }
//            };
//
//            Callable callable = new Callable() {
//                @Override
//                public Object call() throws Exception {
//                    return "哈哈";
//                }
//            };
//
//            ExecutorService executorRef = Executors.newCachedThreadPool();
//            // runnable 无返回值
//            Future future = executorRef.submit(runnable);
//            System.out.println(future.get() + " " + future.isDone());
//            // callable 有返回值
//            Future future1 = executorRef.submit(callable);
//            System.out.println(future1.get() + " " + future1.isDone());
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
        // endregion

        // region 执行一组任务 并获得结果 ExecutorCompletionService
//        try {
//            // take方法：获取并移除表示下一个已完成任务的 Future，如果目前不存在这样的任务，则等待。
//            ExecutorService executorService = Executors.newCachedThreadPool();
//            CompletionService csRef = new ExecutorCompletionService(
//                    executorService);
//            for (int i = 0; i < 10; i++) {
//                csRef.submit(new Callable<String>() {
//                    public String call() throws Exception {
//                        long sleepValue = (int) (Math.random() * 1000);
//                        System.out.println("sleep=" + sleepValue + " "
//                                + Thread.currentThread().getName());
//                        Thread.sleep(sleepValue);
//                        return "高洪岩：" + sleepValue + " "
//                                + Thread.currentThread().getName();
//                    }
//                });
//            }
//            for (int i = 0; i < 10; i++) {
//                System.out.println(csRef.take().get());
//            }
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
        // endregion


        try {
            ForkJoinPool pool = new ForkJoinPool();
            System.out.println("begin " + System.currentTimeMillis());
            ForkJoinTask task = pool.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(5000);
                        System.out.println("ThreadName="
                                + Thread.currentThread().getName());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            System.out.println(task.get());
            System.out.println("  end " + System.currentTimeMillis());
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

//        try {
//            Phaser phaser = new Phaser(3);
//            ThreadB a = new ThreadB(phaser);
//            a.setName("A");
//            a.start();
//            Thread.sleep(5000);
//            a.interrupt();
//            System.out.println("中断了c");
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        ExecutorService executorService = Executors.newCachedThreadPool();
//        ArrayBlockingQueue  LinkedBlockingQueue SynchronousQueue

//        ThreadPoolExecutor executor = new ThreadPoolExecutor(2,2,5,
//                TimeUnit.SECONDS,new ArrayBlockingQueue<>(2),
//                new ThreadPoolExecutor.AbortPolicy());
//        executor.getCompletedTaskCount();
////        executor.prestartCoreThread();
////        executor.getPoolSize();
////        LinkedBlockingDeque<Runnable>;
//
//        Runnable runnable = new Runnable() {
//            public void run() {
//                try {
//                    Thread.sleep(5000);
//                    System.out.println(Thread.currentThread().getName()
//                            + " run end!");
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        };
//
//        executor.execute(runnable);// 不报错
////        System.out.printf(String.format("%s,%s",executor.getCorePoolSize() + executor.getPoolSize()));
//        executor.execute(runnable);// 不报错
//        executor.execute(runnable);// 不报错
//        executor.execute(runnable);// 不报错
//        executor.execute(runnable);// 不报错
////        System.out.printf(String.format("%s,%s",executor.getCorePoolSize() + executor.getPoolSize()));
//        executor.execute(runnable);// 报错
    }

   static class ThreadA extends Thread {

        private Phaser phaser;

        public ThreadA(Phaser phaser) {
            super();
            this.phaser = phaser;
        }

        public void run() {
            System.out.println("A  begin");
            phaser.arriveAndAwaitAdvance();
            System.out.println("A    end phase value=" + phaser.getPhase());

            System.out.println("A  begin");
            phaser.arriveAndAwaitAdvance();
            System.out.println("A    end phase value=" + phaser.getPhase());

            System.out.println("A  begin");
            phaser.arriveAndAwaitAdvance();
            System.out.println("A    end phase value=" + phaser.getPhase());

            System.out.println("A  begin");
            phaser.arriveAndAwaitAdvance();
            System.out.println("A    end phase value=" + phaser.getPhase());

        }

    }

   static class ThreadB extends Thread {

       private Phaser phaser;

       public ThreadB(Phaser phaser) {
           super();
           this.phaser = phaser;
       }

       public void run() {
           System.out.println(Thread.currentThread().getName() + " A1 begin="
                   + System.currentTimeMillis());
           phaser.awaitAdvance(0);
           System.out.println(Thread.currentThread().getName() + " A1   end="
                   + System.currentTimeMillis());
       }
   }
}
