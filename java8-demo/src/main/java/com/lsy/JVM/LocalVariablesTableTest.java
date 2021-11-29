package com.lsy.JVM;

/**
 * 局部变量表
 * 容量以变量槽为最小单位，每个变量槽都可以存放一个boolean,byte,char,short,int,float,reference或returnAddress类型的数据，
 * 都可以使用32位或更小的物理内存来存储
 * Java虚拟机会以高位对其的方式为其分配两个连续的变量槽空间， 对于64位的数据类型，只有long和double两种，
 * 这里把long和double数据类型分割存储的做法与"long 和 double 的非原子性协定" 中允许把一次long和double数据类型读写分割为两次32位读写的做法类似
 * 局部变量表是建立在线程堆栈中的属于线程私有的数据，无论读写两个连续的变量槽是否为原子操作，都不会引起数据竞争和线程安全问题
 *
 * 为了尽可能节省栈帧耗用的内存空间，局部变量表中的变量槽是可以重用的，方法体中定义的变量，其作用与并不一定会覆盖整个方法体，
 * 如果当前字节码pc计数器的值已经超出了某个变量的作用域，那个变量对应的变量槽就可以交给其他变量来重用。
 *
 * VM options  :  -verbose:gc
 */
public class LocalVariablesTableTest {
    public static void main(String[] args) {
        {
            byte[] b = new byte[64 * 1024 * 1024];
        }
        //若没有int i 则b原本所占用的变量槽没有被其他变量所复用， 所以作为gc root 一部分的局部变量表仍然保持着对他的关联
        //int i = 0;
        System.gc(); // 执行gc时，在同一作用域内的变量不会被回收 。   b不在当前作用域内
    }
}
