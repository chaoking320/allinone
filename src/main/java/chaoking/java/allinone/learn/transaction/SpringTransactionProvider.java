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
		// ��ʾʧ�ܶ������Ӱ�죬������aftercommit���Լ�ʹ�쳣Ҳ������ֹdb������
        // int a = 123/num;
    }

    @Override public void afterCompletion(int i) {

    }
}
