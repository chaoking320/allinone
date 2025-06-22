package chaoking.java.allinone.orm;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.function.Consumer;

public interface ITbContext<T> {

    /**
     * 写入数据
     * <p>
     * 自动填充主键（需要满足以下三个标记）
     * <p>
     * 注意：对于自增主键一定不要设置值，如果有值就会更新失败，并且不会抛出异常
     * <p>
     * 如果 Titan 中的连接参数设置为 rewriteBatchedStatements=true ，该属性会把批处理语句通过“;”拼接为一条语句发送到数据库执行。
     * <p>
     * 如果没有该选项，缺省状态下批处理是单条依次执行，每条语句单独的一个request。可能存在效率问题。用了该选项后，虽然数据库操作成功，但返回的影响行数值都为-2.
     * <p>
     * 1, @Id
     * <p>
     * 2, @GeneratedValue(strategy = GenerationType.AUTO)
     * <p>
     * 3, @Type(value = Types.BIGINT) 或 @Type(value = Types.INTEGER) 或 @Type(value = Types.SMALLINT)
     */
    int insert(T entity);


    /**
     * 删除对象
     */
    int delete(T entity);

    /**
     * 更新数据
     */
    int update(T entity);

    /**
     * 查询对象
     * <p>
     * 返回一个对象
     * <p>
     * 如果不存在，则返回 null
     * <p>
     * 所有赋值的字段都会作为查询条件
     * <p>
     * 注意：设置的条件应该命中索引，避免大批量数据的查询
     */
    T firstOrDefault(@NotNull Consumer<T> make);

    /**
     * 查询对象
     * <p>
     * 指定返回的行数，如果数量不够，则以实际返回的行数为准
     * <p>
     * 所有赋值的字段都会作为查询条件
     * <p>
     * 注意：设置的条件应该命中索引，避免大批量数据的查询
     */
    List<T> where(@NotNull Consumer<T> make, int cnt);
}
