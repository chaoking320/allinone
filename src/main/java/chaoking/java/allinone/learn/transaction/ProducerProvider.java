package chaoking.java.allinone.learn.transaction;

public class ProducerProvider implements Producer {

    private SpringTransactionProvider springTransactionProvider;
    public void setTransactionProvider(SpringTransactionProvider transactionProvider){
        springTransactionProvider = transactionProvider;
    }

    @Override
    public void sendMessage(String msg) {
        System.out.println("sendmessage");
    }
}
