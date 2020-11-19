package chaoking.java.allinone.longpoller;

import chaoking.java.allinone.base.TomcatStateViewer;
import chaoking.java.allinone.learn.thread.NamedThreadFactory;
import com.google.common.base.Optional;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class LongPoller implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(LongPoller.class);

    private static final TomcatStateViewer TOMCAT_STATE = TomcatStateViewer.getInstance();

    private static final long OVERRIDE_CHECK_INTERVAL = 60 * 1000L;
    private static final long SLEEP_TIME = 5000L;
    private static final long LATCH_WAIT_TIME = 20L;
    private static final long INITIALED_EMPTY_CHECK_INTERVAL = 3 * 1000L;
    private static final long UNINITIALED_EMPTY_CHECK_INTERVAL = 30 * 1000L;
    private static final int EXCEPTION_DELAY = 60 * 1000;

    private static final Random LONG_POLLING_RANDOM = new Random();
    private static final ScheduledExecutorService LONG_POLLING_EXECUTOR = Executors.newSingleThreadScheduledExecutor(new NamedThreadFactory("mypoller#"));

    private volatile AtomicBoolean initialed = new AtomicBoolean(false);
    private AtomicBoolean isStop = new AtomicBoolean(false);

    private ConfigChangedCallback callback;

    LongPoller(ConfigChangedCallback callback) {
        this.callback = callback;
    }

    @Override
    public void run() {
        if (isStop.get()) {
            return;
        }
        while (TOMCAT_STATE.isStopped()) {
            try {
                logger.debug("[[title=qconfig]]" + "tomcat is stopped, qconfig sleep");
                Thread.sleep(SLEEP_TIME);
            } catch (InterruptedException e) {
                logger.warn("[[title=qconfig]]" + "tomcat stop sleep interrupted", e);
                return;
            }
        }

        logger.debug("[[title=qconfig]]" + "start qconfig reloading");
        try {

            System.out.println("....哈哈哈....");

            Optional<CountDownLatch> latch = reLoading();
            if (latch.isPresent()) {
                if (!latch.get().await(LATCH_WAIT_TIME, TimeUnit.SECONDS)) {
                    logger.warn("[[title=qconfig]]" + "20 seconds elapsed and qconfig file change loading not finish, perhaps something wrong");
                }
                LONG_POLLING_EXECUTOR.execute(this);
            } else {
                long emptyCheckDelay;
                if (initialed.compareAndSet(false, true)) {
                    emptyCheckDelay = INITIALED_EMPTY_CHECK_INTERVAL;
                } else {
                    emptyCheckDelay = UNINITIALED_EMPTY_CHECK_INTERVAL;
                }
                LONG_POLLING_EXECUTOR.schedule(this, emptyCheckDelay, TimeUnit.MILLISECONDS);
            }
        } catch (RuntimeException resultUnexpectedException) {
            long delay = LONG_POLLING_RANDOM.nextInt(EXCEPTION_DELAY);
            logger.info("[[title=qconfig]]" + "long polling fail, going to sleep {}ms",
                delay, resultUnexpectedException);
            LONG_POLLING_EXECUTOR.schedule(this, delay, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            //Throwable 和Exception 区别
            long delay = LONG_POLLING_RANDOM.nextInt(EXCEPTION_DELAY);
            logger.error("[[title=qconfig]]" + "long polling fail, going to sleep {}ms", delay, e);
            LONG_POLLING_EXECUTOR.schedule(this, delay, TimeUnit.MILLISECONDS);
        }
    }

    void start() {
        LONG_POLLING_EXECUTOR.execute(this);
    }

    void stop() {
        isStop.set(true);
    }

    private Optional<CountDownLatch> reLoading() throws ExecutionException, InterruptedException {
        //TypedCheckResult remote = CLIENT.longPollingCheckUpdate(map).get();
        return this.callback.onChanged(1);
    }

    interface ConfigChangedCallback {
        Optional<CountDownLatch> onChanged(Integer index);
    }
}
