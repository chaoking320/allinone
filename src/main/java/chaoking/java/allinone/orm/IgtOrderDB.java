package chaoking.java.allinone.orm;

import chaoking.java.allinone.orm.po.*;


public class IgtOrderDB {
    public static ITbContext<OrderVersionPo> orderVersion = new TbContext<>(OrderVersionPo.class);
}
