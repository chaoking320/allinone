package chaoking.java.allinone.longpoller;

import com.google.common.base.Optional;

import java.util.concurrent.CountDownLatch;

public class RemoteDataLoader {
    private static LongPoller LONG_POLLER;

    public void init() {
        startLongPoller();
    }

    private void startLongPoller() {
        LONG_POLLER = new LongPoller(new LongPoller.ConfigChangedCallback() {
            @Override public Optional<CountDownLatch> onChanged(Integer index) {
                System.out.println(String.format("onchanged!the value is %s", index));
                final CountDownLatch latch = new CountDownLatch(1);
                return Optional.of(latch);
            }
        });
        LONG_POLLER.start();
    }

}
