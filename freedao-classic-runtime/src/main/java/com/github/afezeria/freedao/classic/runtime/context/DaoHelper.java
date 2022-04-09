package com.github.afezeria.freedao.classic.runtime.context;

import com.github.afezeria.freedao.classic.runtime.DS;
import com.github.afezeria.freedao.classic.runtime.DataSourceContextHolder;
import com.github.afezeria.freedao.classic.runtime.Page;

import java.lang.annotation.Annotation;
import java.util.*;
import java.util.function.Supplier;

import static com.github.afezeria.freedao.classic.runtime.FreedaoGlobalConfiguration.optimizeCountSql;

/**
 * @author afezeria
 */
public class DaoHelper {

    /**
     * 设置临时上下文参数
     */
    public static <T> T withContextParameter(String key, Object value, Supplier<T> block) {
        ParameterContext.ParameterMap parameterMap =
                Objects.requireNonNull(ParameterContext.local.get(), "ParameterContext not initialized");
        parameterMap.put(key, value);
        try {
            return block.get();
        } finally {
            parameterMap.reset();
        }
    }

    /**
     * 设置临时上下文参数
     */
    public static <T> T withContextParameters(Map<String, Object> params, Supplier<T> block) {
        ParameterContext.ParameterMap parameterMap =
                Objects.requireNonNull(ParameterContext.local.get(), "ParameterContext not initialized");
        parameterMap.putAll(params);
        try {
            return block.get();
        } finally {
            parameterMap.reset();
        }
    }

    /**
     * {@link DaoHelper#ds(DS, Supplier)}
     */
    public static <T> T ds(String name, Supplier<T> supplier) {
        return ds(name, true, supplier);
    }

    /**
     * {@link DaoHelper#ds(DS, Supplier)}
     */
    public static <T> T ds(String name, boolean isPrefix, Supplier<T> supplier) {
        return ds(new DS() {
            @Override
            public String value() {
                return name;
            }

            @Override
            public boolean prefix() {
                return isPrefix;
            }

            @Override
            public Class<? extends Annotation> annotationType() {
                return null;
            }
        }, supplier);
    }

    /**
     * 切换数据源，暂时只支持spring-boot
     *
     * @param ds {@link DS}
     */
    public static <T> T ds(DS ds, Supplier<T> supplier) {
        DS outer = DataSourceContextHolder.get();
        if (outer != null) {
            if (outer.prefix() == ds.prefix() && Objects.equals(outer.value(), ds.value())) {
                return supplier.get();
            }
        }
        try {
            DataSourceContextHolder.set(ds);
            return supplier.get();
        } finally {
            DataSourceContextHolder.set(outer);
        }
    }

    public static <E> Page<E> pagination(int pageIndex, int pageSize, Supplier<Collection<E>> closure) {
        return pagination(pageIndex, pageSize, optimizeCountSql, closure);
    }

    public static <E> Page<E> pagination(int pageIndex, int pageSize, boolean optimizeCountSql, Supplier<Collection<E>> closure) {
        if (pageIndex < 1) {
            throw new IllegalArgumentException("pageIndex must greater than 0");
        }
        if (pageSize < 1) {
            throw new IllegalArgumentException("pageSize must greater than 0");
        }
        Page<E> page = new Page<>();
        page.setPageIndex(pageIndex);
        page.setPageSize(pageSize);
        page.setOptimizeCountSql(optimizeCountSql);
        PaginationQueryContext.local.set(page);
        try {
            Collection<E> res = closure.get();
            if (res != null) {
                page.setRecords(new ArrayList<>(res));
            } else {
                page.setRecords(new ArrayList<>());
            }
            return page;
        } finally {
            PaginationQueryContext.local.remove();
        }
    }

}
