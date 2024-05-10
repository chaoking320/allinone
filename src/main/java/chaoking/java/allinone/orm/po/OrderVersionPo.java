package chaoking.java.allinone.orm.po;

import com.ctrip.platform.dal.dao.DalPojo;
import com.ctrip.platform.dal.dao.annotation.Database;
import com.ctrip.platform.dal.dao.annotation.Type;

import javax.persistence.*;
import java.sql.Timestamp;
import java.sql.Types;

/**
 * @author wc王超(直属)
 * @date 2022-08-15
 */
@Entity
@Database(name = "igtorderdb_W")
@Table(name = "order_version")
public class OrderVersionPo implements DalPojo {

    /**
     * 主键
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Type(value = Types.BIGINT)
    private Long id;

    /**
     * 订单号
     */
    @Column(name = "order_id")
    @Type(value = Types.BIGINT)
    private Long orderId;

    /**
     * 子产线
     */
    @Column(name = "category")
    @Type(value = Types.VARCHAR)
    private String category;

    /**
     * 订单版本： 0：合规 1：非合规走新支付流程
     */
    @Column(name = "version")
    @Type(value = Types.INTEGER)
    private Integer version;

    /**
     * 类型 0：支付结算改版前流程 1：支付结算改版后流程
     */
    @Column(name = "type")
    @Type(value = Types.INTEGER)
    private Integer type;

    /**
     * 支付类型 -1：默认值 2：信用付
     */
    @Column(name = "pay_type")
    @Type(value = Types.INTEGER)
    private Integer payType;

    /**
     * 创建时间
     */
    @Column(name = "datachange_createtime")
    @Type(value = Types.TIMESTAMP)
    private Timestamp datachangeCreatetime;

    /**
     * 更新时间
     */
    @Column(name = "datachange_lasttime", insertable = false, updatable = false)
    @Type(value = Types.TIMESTAMP)
    private Timestamp datachangeLasttime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public Timestamp getDatachangeCreatetime() {
        return datachangeCreatetime;
    }

    public void setDatachangeCreatetime(Timestamp datachangeCreatetime) {
        this.datachangeCreatetime = datachangeCreatetime;
    }

    public Timestamp getDatachangeLasttime() {
        return datachangeLasttime;
    }

    public void setDatachangeLasttime(Timestamp datachangeLasttime) {
        this.datachangeLasttime = datachangeLasttime;
    }

}

