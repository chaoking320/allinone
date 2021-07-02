package chaoking.java.allinone.learn.thread.executortest;

public class Executor2Test implements Runnable {

    public Executor2Test(){
        System.out.println("constructor");
    }

    @Override
    public void run() {
        while(true){
            try {
                System.out.println("hhha");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
