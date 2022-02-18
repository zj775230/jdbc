package com.yian.collection;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author zyt
 * @create 2022-02-10 9:40
 */
public class ForTest {
    @Test
    public void test1(){
        Collection coll = new ArrayList();
        coll.add("aaa");
        coll.add(1231);
        coll.add(new Person("xixi",22));
        coll.add(new String("tom"));
        coll.add(false);

        for (Object o : coll) {
            System.out.println(o);
        }
    }
}
