package com.example.yuxin.mobilesafer.utils;

import java.util.Hashtable;

import android.util.Log;

/** 
 * The class for print log 
 *
 */  
public class MyLogger  
{  
    private final static boolean                logFlag         = true;  //调试模式 true  上线模式 false
      
    public final static String                  tag             = "MobileSafer";//日志打印  tag
    private final static int                    logLevel        = Log.VERBOSE; //日志级别
    //集合
    private static Hashtable<String, MyLogger>    sLoggerTable    = new Hashtable<String, MyLogger>();  
    private String                              mClassName;

    //不同开发人员的日志使用对象
    private static MyLogger                     slog;
    private static MyLogger                     jlog;
    private static MyLogger                     klog;

    //开发人员的名字
    private static final String                 SIMON           = "@simon@ ";
    private static final String                 JAMES           = "@james@ ";
    private static final String                 KESEN           = "@kesen@ ";

    private MyLogger(String name)  
    {  
        mClassName = name;  
    }  
      
    /** 
     *  
     * @param className 
     * @return 
     */  
    @SuppressWarnings("unused")  
    private static MyLogger getLogger(String className)  
    {  
        MyLogger classLogger = (MyLogger) sLoggerTable.get(className);  
        if(classLogger == null)  
        {  
            classLogger = new MyLogger(className);  
            sLoggerTable.put(className, classLogger);  
        }  
        return classLogger;  
    }  
      
    /** 
     * Purpose:Mark user one 
     * @return 
     */  
    public static MyLogger kLog()  
    {  
        if(klog == null)  
        {  
            klog = new MyLogger(KESEN);  
        }  
        return klog;  
    }  
    /** 
     * Purpose:Mark user two 
     * @return 
     */  
    public static MyLogger jLog()  
    {  
        if(jlog == null)  
        {  
            jlog = new MyLogger(JAMES);  
        }  
        return jlog;  
    }

    /**
     * Purpose:Mark user three
     * @return
     */
    public static MyLogger slog()
    {
        if(slog == null)
        {
            slog = new MyLogger(SIMON);
        }
        return slog;
    }

    /** 
     * Get The Current Function Name 
     * @return 
     */  
    private String getFunctionName()  
    {  
        StackTraceElement[] sts = Thread.currentThread().getStackTrace();  
        if(sts == null)  
        {  
            return null;  
        }  
        for(StackTraceElement st : sts)  
        {  
            if(st.isNativeMethod())  
            {
                //本地方法native  jni
                continue;  
            }  
            if(st.getClassName().equals(Thread.class.getName()))  
            {
                //线程
                continue;  
            }  
            if(st.getClassName().equals(this.getClass().getName()))  
            {
                //构造方法
                continue;  
            }  
            return mClassName + "[ " + Thread.currentThread().getName() + ": "  
                    + st.getFileName() + ":" + st.getLineNumber() + " "  
                    + st.getMethodName() + " ]";  
        }  
        return null;  
    }  
      
    /** 
     * The Log Level:i 
     * @param str 
     */  
    public void i(Object str)  
    {  
        if(logFlag)  
        {  
            if(logLevel <= Log.INFO)  
            {  
                String name = getFunctionName();  
                if(name != null)  
                {  
                    Log.i(tag, name + " - " + str);  
                }  
                else  
                {  
                    Log.i(tag, str.toString());  
                }  
            }  
        }  
          
    }  
      
    /** 
     * The Log Level:d 
     * @param str 
     */  
    public void d(Object str)  
    {  
        if(logFlag)  
        {  
            if(logLevel <= Log.DEBUG)  
            {  
                String name = getFunctionName();  
                if(name != null)  
                {  
                    Log.d(tag, name + " - " + str);  
                }  
                else  
                {  
                    Log.d(tag, str.toString());  
                }  
            }  
        }  
    }  
      
    /** 
     * The Log Level:V 
     * @param str 
     */  
    public void v(Object str)  
    {  
        if(logFlag)  
        {  
            if(logLevel <= Log.VERBOSE)  
            {  
                String name = getFunctionName();  
                if(name != null)  
                {  
                    Log.v(tag, name + " - " + str);  
                }  
                else  
                {  
                    Log.v(tag, str.toString());  
                }  
            }  
        }  
    }  
      
    /** 
     * The Log Level:w 
     * @param str 
     */  
    public void w(Object str)  
    {  
        if(logFlag)  
        {  
            if(logLevel <= Log.WARN)  
            {  
                String name = getFunctionName();  
                if(name != null)  
                {  
                    Log.w(tag, name + " - " + str);  
                }  
                else  
                {  
                    Log.w(tag, str.toString());  
                }  
            }  
        }  
    }  
      
    /** 
     * The Log Level:e 
     * @param str 
     */  
    public void e(Object str)  
    {  
        if(logFlag)  
        {  
            if(logLevel <= Log.ERROR)  
            {  
                String name = getFunctionName();  
                if(name != null)  
                {  
                    Log.e(tag, name + " - " + str);  
                }  
                else  
                {  
                    Log.e(tag, str.toString());  
                }  
            }  
        }  
    }  
      
    /** 
     * The Log Level:e 
     * @param ex 
     */  
    public void e(Exception ex)  
    {  
        if(logFlag)  
        {  
            if(logLevel <= Log.ERROR)  
            {  
                Log.e(tag, "error", ex);  
            }  
        }  
    }  
      
    /** 
     * The Log Level:e 
     * @param log 
     * @param tr 
     */  
    public void e(String log, Throwable tr)  
    {  
        if(logFlag)  
        {  
            String line = getFunctionName();  
            Log.e(tag, "{Thread:" + Thread.currentThread().getName() + "}"  
                    + "[" + mClassName + line + ":] " + log + "\n", tr);  
        }  
    }  
}  



