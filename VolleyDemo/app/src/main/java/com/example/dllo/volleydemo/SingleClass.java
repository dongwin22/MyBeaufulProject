package com.example.dllo.volleydemo;

/**
 * Created by dllo on 16/7/12.
 * 单例类
 */

public class SingleClass {
    /**
     * 1私有化构造方法 外部不能创建对象
     * 2对外土工一个static的对象,保持单例(单独的实例对象)
     *
     */
    // 定义一个单类的对象,权限是静态的
    private static SingleClass instance;

    private SingleClass(){

    }
    /**
     * 对外提供一个方法来获取单例对象
     * 1.双重校验锁
     */
    public static SingleClass getInstance(){
        //判断当前对象是否为空
        if (instance == null){
            // 线程里同步验证一下这个类
            synchronized (SingleClass.class){
                //再次判断当前对象
                if (instance == null){
                    //如果为null,创建对象
                    instance = new SingleClass();
                }

            }
        }
        return instance;
    }
     public static SingleClass getSingle(){
         return SingleClassHolder.singleClass;
     }
    //第二种单例方式:静态内部类
    private static class SingleClassHolder{
        private static SingleClass singleClass = new SingleClass();
    }
}
