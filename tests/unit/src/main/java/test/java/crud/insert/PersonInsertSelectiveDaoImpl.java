//package test.java.crud.insert;
//
//import com.github.afezeria.freedao.TrimHelper;
//import com.github.afezeria.freedao.runtime.classic.DaoContext;
//import com.github.afezeria.freedao.runtime.classic.LogHelper;
//import com.github.afezeria.freedao.runtime.classic.SqlExecutor;
//import com.github.afezeria.freedao.runtime.classic.SqlSignature;
//import java.lang.Boolean;
//import java.lang.Exception;
//import java.lang.Integer;
//import java.lang.Object;
//import java.lang.Override;
//import java.lang.RuntimeException;
//import java.lang.String;
//import java.lang.StringBuilder;
//import java.sql.PreparedStatement;
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Objects;
//import java.util.function.Function;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//@Component
//public final class PersonInsertSelectiveDaoImpl implements PersonInsertSelectiveDao {
//    private static final Logger __logger = LoggerFactory.getLogger(PersonInsertSelectiveDaoImpl.class);
//
//    private final SqlSignature insertSelective_0_sign = new SqlSignature(com.github.afezeria.freedao.StatementType.INSERT,test.java.crud.insert.PersonInsertSelectiveDaoImpl.class,"insertSelective",int.class, test.java.crud.insert.PersonInsertSelective.class);
//
//    private final Function<Object[], Object[]> insertSelective_0_sql = _params -> {
//        PersonInsertSelective p_entity = (PersonInsertSelective) _params[0];
//
//        List<Object> l_sqlArgs_0 = new ArrayList<>();
//        StringBuilder l_builder_0 = new StringBuilder();
//        //insert
//        l_builder_0.append("\n"
//                + "insert into \"person\" (");
//        {
//            StringBuilder l_builder_1 = new StringBuilder();
//            //trim postfixOverrides=",", prefixOverrides=""
//            Boolean l_tmpVar_0 = false;
//            l_tmpVar_0 = p_entity.getName() != null;
//            if (l_tmpVar_0) {
//                //if test="entity.name != null"
//                l_builder_1.append("\"name\", ");
//            }
//            Boolean l_tmpVar_1 = false;
//            l_tmpVar_1 = p_entity.getCreateDate() != null;
//            if (l_tmpVar_1) {
//                //if test="entity.createDate != null"
//                l_builder_1.append("\"create_date\", ");
//            }
//            l_builder_1 = TrimHelper.appendAndTrim(l_builder_1,"",new String[]{""},new String[]{","});
//            l_builder_0.append(l_builder_1);
//        }
//        l_builder_0.append("))\n"
//                + "values (");
//        {
//            StringBuilder l_builder_1 = new StringBuilder();
//            //trim postfixOverrides=",", prefixOverrides=""
//            Boolean l_tmpVar_2 = false;
//            l_tmpVar_2 = p_entity.getName() != null;
//            if (l_tmpVar_2) {
//                //if test="entity.name != null"
//                l_builder_1.append("?, ");
//                String l_tmpVar_3 = p_entity.getName();
//                l_sqlArgs_0.add(l_tmpVar_3);
//            }
//            Boolean l_tmpVar_4 = false;
//            l_tmpVar_4 = p_entity.getCreateDate() != null;
//            if (l_tmpVar_4) {
//                //if test="entity.createDate != null"
//                l_builder_1.append("?, ");
//                LocalDateTime l_tmpVar_5 = p_entity.getCreateDate();
//                l_sqlArgs_0.add(l_tmpVar_5);
//            }
//            l_builder_1 = TrimHelper.appendAndTrim(l_builder_1,"",new String[]{""},new String[]{","});
//            l_builder_0.append(l_builder_1);
//        }
//        l_builder_0.append(")\n");
//
//        String l_sql_0 = l_builder_0.toString();
//        return new Object[]{l_sql_0, l_sqlArgs_0};
//    };
//
//    private final SqlExecutor<Integer> insertSelective_0_executor = (__connection, __method_args__, __sql, __args) -> {
//        if (__logger.isDebugEnabled()) {
//            LogHelper.logSql(__logger, __sql);
//            LogHelper.logArgs(__logger, __args);
//        }
//        try (PreparedStatement __stmt = __connection.prepareStatement(__sql)) {
//            for (int __idx = 0; __idx < __args.size(); __idx++) {
//                __stmt.setObject(__idx + 1, __args.get(__idx));
//            }
//            __stmt.execute();
//            return __stmt.getUpdateCount();
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    };
//
//    @Autowired
//    private DaoContext __context__;
//
//    @Override
//    public int insertSelective(PersonInsertSelective entity) {
//        Objects.requireNonNull(entity);
//        Object[] __method_args__ = {entity};
//        Object[] __sqlAndArgs__ = __context__.buildSql(insertSelective_0_sign, __method_args__, insertSelective_0_sql);
//        return __context__.execute(insertSelective_0_sign, __method_args__, (String) __sqlAndArgs__[0], (List<Object>) __sqlAndArgs__[1], insertSelective_0_executor);
//    }
//}