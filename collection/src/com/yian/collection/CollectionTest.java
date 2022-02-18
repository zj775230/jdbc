package com.yian.collection;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

/**
 * @author zyt
 * @create 2022-02-09 10:24
 */
public class CollectionTest {
    @Test
    public void test1(){
        //add(Object e) 将元素添加到
        Collection coll = new ArrayList();
        coll.add("aa");
        coll.add("AA");
        coll.add(123);
        coll.add(new Date());
        System.out.println(coll.size());

        //addAll(collection coll1):将coll1集合中的元素添加到当前集合
        Collection coll1 = new ArrayList();
        coll1.add("dsds");
        coll1.add(789);
        coll.addAll(coll1);
        System.out.println(coll.size());
        System.out.println(coll);
        //clear():清空集合元素
        coll.clear();
        //isEmpty():判断当前集合是否为空
        System.out.println(coll.isEmpty());
        System.out.println(coll1.isEmpty());

    }
    @Test
    public void test2(){
        Collection coll = new ArrayList();
        coll.add("aaa");
        coll.add("dsds");
        coll.add(1231);
        coll.add(new String("tom"));
        coll.add(new Person("xixi",22));
        //contains(Object obj):判断当前集合中是否包含obj
        //在判断时会调用obj对象所在类的equals().
        boolean contains = coll.contains(123);
        System.out.println(contains);
        System.out.println(coll.contains(new String("tom")));
        System.out.println(coll.contains(new Person("xixi",22)));//false,如需true则需重写equals()
        //containsAll(colletion coll1):判断形参coll1中所有的元素是否都存在于当前集合
        Collection coll1 = Arrays.asList(123,4123);
        System.out.println(coll.containsAll(coll1));

    }
    @Test
    public void test3(){
        //remove
        //removeAll
    }
}
