package chaoking.java.allinone.learn.transaction.service;
import chaoking.java.allinone.learn.transaction.dao.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public Integer Add(String name,Long orderId){
        Integer i = orderRepository.Add(name,orderId);

        // 注意由于该类用了@Transactional，所以一个失败，则全部不插入成功
        //orderRepository.Add("hh",null);
        return i;
    }
}
