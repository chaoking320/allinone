package chaoking.java.allinone.learn.thread;

/**
 * 意在说明ThreadLocal的使用场景及方法：即多个线程使用同一个对象的时候为了避免冲突，线程将对象在本地赋值，这样就做到了互不干扰
 * 但是在线程池的场景中，如果不主动remove，则可能造成内存泄露
 */
public class MyThreadLocal {

    public static void main(String[] args) {

        Person person =new Person();

        new Thread(new Runnable() {
            @Override public void run() {
                person.setName("Hello");
                try {
                    Thread.sleep(3000);
                    System.out.println("1 name 为："+person.getName());
                    // 用完即清除，避免内存溢出
                    person.remove();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override public void run() {
                person.setName("World");
                try {
                    Thread.sleep(3000);
                    System.out.println("2 name 为："+person.getName());
                    // 用完即清除，避免内存溢出
                    person.remove();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    static class Person{
        private ThreadLocal<String> name = new ThreadLocal<>();

        public void setName(String name){
            this.name.set(name);
        }

        public String getName(){
            return this.name.get();
        }

        /**
         * 避免线程池中使用时内存溢出，故需要主动remove
         */
        public void remove(){
            this.name.remove();
        }
    }
}
