package com.sdw.alibaba.spark;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SQLContext;

public class TestSparkSQL {

    public static void main(String[] args) {
        String logFile = "D:/spark/spark-1.4.1-bin-without-hadoop/README.md"; // Should be some file on your system
        SparkConf conf = new SparkConf().setAppName("Simple Application").setMaster("local[3]");
        JavaSparkContext sc = new JavaSparkContext(conf);
        
        SQLContext sqlContext = new SQLContext(sc); 
        Map<String, String> map = new HashMap<String, String>();
        map.put("url", "jdbc:mysql://192.168.8.84:13306/sanzang_reportservice?user=root&password=rootadchina");
        map.put("dbtable", "api_define");
        
        DataFrame df1 = sqlContext.load("jdbc", map);
        df1.registerTempTable("t1");
        
        sqlContext.sql("select * from t1").show();
        
        List<String> result = sqlContext.sql("select * from t1").javaRDD().map(new Function<Row, String>(){

            @Override
            public String call(Row v1) throws Exception {
                return "Name:" + v1.getString(1);
            }}).collect();
        
        for(String item : result){
            System.out.println(item);
        }
        
        long re = sqlContext.executeSql("select id, name from t1").toRdd().count();
        
        System.out.println(re);
        
        result = sqlContext.sql("select * from t1").javaRDD().map(new TestTransform()).collect();
        
        for(String item : result){
            System.out.println(item);
        }
        
        
    }
}
