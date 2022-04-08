package com.github.afezeria.freedao.classic.runtime;

import java.sql.Connection;
import java.util.List;

/**
 */
@FunctionalInterface
public interface SqlExecutor<T> {
    T execute(Connection connection,
              Object[] methodArgs,
              String sql, List<Object> args);
}
