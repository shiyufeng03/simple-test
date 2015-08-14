package com.sdw.alibaba.spark;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class TestJavaEngine {

    public static void main(String[] args) throws ScriptException {
        runJavascript();
        runScala();
    }
    
    private static void runJavascript() throws ScriptException{
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("js");        
        Object result = engine.eval("3+4");
        System.out.println(result + ", type:" + result.getClass());
    }
    
    private static void runScala() throws ScriptException{
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("scala");    
        Object result = engine.eval("3+4");
        System.out.println(result + ", type:" + result.getClass());
    }
    
}
