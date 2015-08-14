package com.sdw.alibaba.spark

import org.apache.spark.sql.Row

class TestTransform extends Transform[java.lang.String] {

  def call(row: Row): java.lang.String =
    {

      return "name:" + row.get(0);

    }

}