package chaoking.java.allinone.others.FuncTest;

import org.springframework.stereotype.Component;

@Component
public class TestFuncServiceImpl implements TestFuncService {

    @Override
    public Long checkHealth(Long request, Func<Long> fallbackProvider) {
        return null;
    }

    /**
     * 重点在 ()->
     * @param args
     */
    public void test(String[] args) {
        checkHealth(1L,()->1L);
    }
}

