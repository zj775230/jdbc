package com.yian.collection;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * @author zyt
 * @create 2022-02-09 15:13
 */
public class IteratorTest {
    @Test
    public void test(){
        Collection coll = new ArrayList();
        coll.add("aaa");
        coll.add(1231);
        coll.add(new Person("xixi",22));
        coll.add(new String("tom"));
        coll.add(false);
        //循环遍历coll集合中元素
        Iterator iterator = coll.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
    @Test
    public void test1(){
        Collection coll = new ArrayList();
        coll.add("aaa");
        coll.add(1231);
        coll.add(new Person("xixi",22));
        coll.add(new String("tom"));
        coll.add(false);

        Iterator iterator = coll.iterator();
        while (iterator.hasNext()){
            Object next = iterator.next();
            if("tom".equals(next)){
                iterator.remove();
            }
        }
       iterator = coll.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}
