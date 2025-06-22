package chaoking.java.allinone.orm;

import com.ctrip.platform.dal.dao.DalHints;
import com.ctrip.platform.dal.dao.DalParser;
import com.ctrip.platform.dal.dao.DalTableDao;
import com.ctrip.platform.dal.dao.StatementParameters;
import com.ctrip.platform.dal.dao.helper.DalDefaultJpaParser;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class TbContext<T> implements ITbContext<T> {

    private Class<T> clazz;
    private DalParser<T> parser;
    private DalTableDao<T> dalTableDao;

    public TbContext(Class<T> clazz){
        this.clazz = clazz;
        try {
            this.parser = new DalDefaultJpaParser<T>(clazz);
            this.dalTableDao = new DalTableDao<T>(parser);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    @Override
    public int insert(T entity) {

        try {
            return dalTableDao.insert(new DalHints(),entity);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int delete(T entity) {
        return 0;
    }

    @Override
    public int update(T entity) {
        return 0;
    }

    @Override
    public T firstOrDefault(Consumer<T> make) {

        try {
            T obj = clazz.newInstance();
            make.accept(obj);
            BuildResult buildResult = buildQuery(obj);
            return dalTableDao.queryFirst(buildResult.whereClause,buildResult.parameters,new DalHints().setFields(buildResult.fields));
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<T> where(Consumer<T> make, int cnt) {

        try {
            T obj = clazz.newInstance();
            make.accept(obj);
//            BuildResult buildResult = buildQuery(obj);
            return dalTableDao.queryBy(obj,new DalHints());
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

//    private DalHints buildDalHint(T entity) {
//        val shareKeyField = CachedDict.shareKeyDict[clazz]
//        DalHints hins = new DalHints();
//        if (shareKeyField != null) {
//            val mod = shareKeyField.annotations.filterIsInstance<ShareKey>().first().mod
//            val key = shareKeyField.get(entity)
//            if (key is Long) {
//                hins.inTableShard((key % mod).toInt())
//            } else if (key is Int) {
//                hins.inTableShard(key % mod)
//            }
//        }
//        return hins
//    }

    private BuildResult buildQuery(T obj) {
        StatementParameters parameters = new StatementParameters();
        Map<String, ?> fields = parser.getFields(obj);
        Map<String, ?> queryCriteria = dalTableDao.filterNullFileds(fields);
        dalTableDao.addParameters(parameters, queryCriteria);
        String whereClause = dalTableDao.buildWhereClause(queryCriteria);

        return new BuildResult(whereClause, parameters, fields);
    }

    static class BuildResult{
        private String whereClause;
        private StatementParameters parameters;
        Map<String, ?> fields;

        public BuildResult(String whereClause,StatementParameters parameters,Map<String, ?> fields){
            this.whereClause = whereClause;
            this.parameters = parameters;
            this.fields = fields;
        }

        public String getWhereClause() {
            return whereClause;
        }

        public void setWhereClause(String whereClause) {
            this.whereClause = whereClause;
        }

        public StatementParameters getParameters() {
            return parameters;
        }

        public void setParameters(StatementParameters parameters) {
            this.parameters = parameters;
        }

        public Map<String, ?> getFields() {
            return fields;
        }

        public void setFields(Map<String, ?> fields) {
            this.fields = fields;
        }
    }
}
