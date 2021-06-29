package chaoking.java.allinone.learn.transaction;

import org.springframework.transaction.support.TransactionSynchronization;

import javax.sql.DataSource;

public class SpringTransactionProvider implements TransactionSynchronization {

    private DataSource dataSource;

    public SpringTransactionProvider(DataSource dataSource){
        this.dataSource = dataSource;
    }

    @Override public void suspend() {

    }

    @Override public void resume() {

    }

    @Override public void flush() {

    }

    @Override public void beforeCommit(boolean b) {

    }

    @Override public void beforeCompletion() {

    }

    @Override
    public void afterCommit() {
        System.out.println("afterCommit");
    }

    @Override public void afterCompletion(int i) {

    }
}
