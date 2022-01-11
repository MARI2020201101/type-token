package com.mariworld.typetoken;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class TypeTokenV2 {


    static class TypesafeMap{
        Map<Class<?>, Object> map = new HashMap<>();
        <T> void put(Class<T> clazz, T value){
            map.put(clazz, value);
        }
        <T> T get(Class<T> clazz){
            //T o = (T) map.get(clazz); 이렇게 써도됨. 단 safe하다고 어노테이션 달아주기.
            return clazz.cast(map.get(clazz)); //타입세이프하다.~
        }
    }


    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        TypesafeMap t = new TypesafeMap();
        t.put(String.class,"a");
        t.put(Integer.class, 1);
        //t.put(Long.class,"aaa"); 컴파일타임에 잡아준다. 타입체크안정성 확보.
        String s = t.get(String.class);
        System.out.println(s.getClass());
        Integer integer = t.get(Integer.class);
        System.out.println(integer);

    }


}
