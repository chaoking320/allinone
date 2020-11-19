package chaoking.java.allinone.learn.future;

import com.google.common.util.concurrent.*;

import javax.annotation.Nullable;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;

public class FutureTest {
    public static void main(String[] args) {
        ListeningExecutorService executorService = MoreExecutors.listeningDecorator(Executors.newCachedThreadPool());
        final ListenableFuture<Integer> listenableFuture = executorService.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return null;
            }
        });

        // 方式一
        listenableFuture.addListener(new Runnable() {
            @Override
            public void run() {

            }
        },executorService);

        // 方式二
        Futures.addCallback(listenableFuture, new FutureCallback<Integer>() {
            @Override public void onSuccess(@Nullable Integer result) {

            }

            @Override public void onFailure(Throwable t) {

            }
        });
    }
}
