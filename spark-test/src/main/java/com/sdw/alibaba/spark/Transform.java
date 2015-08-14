package com.sdw.alibaba.spark;

import org.apache.spark.api.java.function.Function;
import org.apache.spark.sql.Row;

public interface Transform <T> extends Function<Row, T>{

}
