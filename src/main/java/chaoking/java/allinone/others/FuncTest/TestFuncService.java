package chaoking.java.allinone.others.FuncTest;

public interface TestFuncService {
    Long checkHealth(Long request, Func<Long> fallbackProvider);
}
