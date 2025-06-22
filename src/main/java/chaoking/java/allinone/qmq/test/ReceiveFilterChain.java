package chaoking.java.allinone.qmq.test;

import java.util.ArrayList;
import java.util.List;

public class ReceiveFilterChain implements Disposable {

    private final List<ReceiveFilter> filters = new ArrayList<>();

    public ReceiveFilterChain() {
        addFilter(new ValidateFilter());
    }

    public Invoker buildFilterChain(Invoker invoker) {
        Invoker last = invoker;
        if (filters.size() > 0) {
            for (int i = filters.size() - 1; i >= 0; i--) {
                final ReceiveFilter filter = filters.get(i);
                final Invoker next = last;
                last = message -> filter.invoke(next, message);
            }
        }
        return last;
    }

    private void addFilter(ReceiveFilter filter) {
        filters.add(filter);
    }

    @Override
    public void destroy() {

    }
}
