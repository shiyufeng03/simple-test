package com.sdw.alibaba.sql;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.select.Select;

public class TestParser {
	public static void main(String[] args) throws JSQLParserException {
		 final String sql = "SELECT tableName.columnName FROM tableName";
	        Select select = (Select) CCJSqlParserUtil.parse(sql);
	        
	        System.out.println(select);
	}
}
