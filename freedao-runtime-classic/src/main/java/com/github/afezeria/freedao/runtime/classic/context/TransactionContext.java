package com.github.afezeria.freedao.runtime.classic.context;

import com.github.afezeria.freedao.runtime.classic.SqlExecutor;
import com.github.afezeria.freedao.runtime.classic.SqlSignature;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

/**
 *
 */
public class TransactionContext extends DaoContext {

    DataSource dataSource;

    public TransactionContext(DataSource dataSource) {
        super(null);
        Objects.requireNonNull(dataSource);
        this.dataSource = dataSource;
    }

    private Boolean transactionFlag = false;

    private Connection currentConn = null;

    @Override
    public <T> T withTx(Function<Connection, T> function) {
        try {
            if (currentConn != null) {
                return function.apply(currentConn);
            } else {
                try (Connection connection = dataSource.getConnection()) {
                    currentConn = connection;
                    currentConn.setAutoCommit(false);
                    T apply = function.apply(connection);
                    currentConn.commit();
                    return apply;
                } finally {
                    currentConn = null;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Object[] buildSql(SqlSignature signature, Object[] args, Function<Object[], Object[]> sqlBuilder) {
        throw new IllegalStateException("method not implemented");
    }

    @Override
    public <T> T execute(SqlSignature signature, Object[] methodArgs, String sql, List<Object> sqlArgs, SqlExecutor<T> executor) {
        throw new IllegalStateException("method not implemented");
    }
}
