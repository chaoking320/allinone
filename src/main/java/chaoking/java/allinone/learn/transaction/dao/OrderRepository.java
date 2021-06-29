package chaoking.java.allinone.learn.transaction.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {

    public List<Order> findById(Integer orderId);

    @Modifying
    @Query(value = "insert into orders (name,order_id) value(?1,?2)",nativeQuery = true)
    public Integer Add(String name, Long orderId);
}
