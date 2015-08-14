package com.sdw.alibaba.spark;

import java.util.HashMap;
import java.util.Map;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.api.java.UDF1;
import org.apache.spark.sql.hive.HiveContext;
import org.apache.spark.sql.types.DataTypes;

public class TestSparkHiveQL {
    public static void main(String[] args) {
        String logFile = "D:/spark/spark-1.4.1-bin-without-hadoop/README.md"; // Should be some file on your system
        SparkConf conf = new SparkConf().setAppName("Simple Application").setMaster("local[3]");
        JavaSparkContext sc = new JavaSparkContext(conf);
        
        HiveContext hiveContext = new HiveContext(sc.sc());
        
        Map<String, String> map = new HashMap<String, String>();
        map.put("url", "jdbc:mysql://192.168.8.84:13306/sanzang_reportservice?user=root&password=rootadchina");
        map.put("dbtable", "api_define");
        
        DataFrame df1 = hiveContext.load("jdbc", map);
        
        
        df1.registerTempTable("t1");
        
        hiveContext.sql("select * from t1").show();
        
        hiveContext.udf().register("toString", new UDF1<Long, String>() {

            @Override
            public String call(Long t1) throws Exception {
                return t1.toString();
            }

           
            
        }, DataTypes.StringType);
        
        
        hiveContext.sql("select toString(id), name from t1 ").show();
    }
    
    static class toInt<T1, R> implements UDF1<T1, R>{

        @Override
        public R call(T1 t1) throws Exception {
            return null;
        }
        
    }

    
}
