package chaoking.java.allinone.learn.transaction;

import org.springframework.transaction.support.TransactionSynchronization;

import javax.sql.DataSource;

public class SpringTransactionProvider implements TransactionSynchronization {

    private DataSource dataSource;
    int num = 0;

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
        System.out.println("beforeCommit");
    }

    @Override public void beforeCompletion() {

    }

    @Override
    public void afterCommit() {
        System.out.println("afterCommit");
		// 演示失败对事务的影响，由于是aftercommit所以即使异常也不会阻止db操作。
        // int a = 123/num;
    }

    @Override public void afterCompletion(int i) {

    }
}
