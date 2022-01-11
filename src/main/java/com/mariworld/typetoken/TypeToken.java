package com.mariworld.typetoken;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class TypeToken {
    //제네릭을 이용하면 컴파일 시점에 type을 잡아주기 때문에 안정적으로 사용이 가능
    static <T> T create(Class<T> clazz) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Constructor<T> constructor = clazz.getDeclaredConstructor(null);
        return constructor.newInstance();
    }

    static class TypesafeMap{
        Map<String, Object> map = new HashMap<>();
        void run(){
            map.put("a","a");
            map.put("b", 1);
            Integer b = (Integer)map.get("b"); //타입안정성을 포기하는 코드.
        }
    }

    static class Generic<T>{
        T value;
        void set(T t){
            value = t;
        }
        T get(){
            return value;
        }
    }

    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        String s = create(String.class);
        System.out.println(s.getClass());
        Generic<String> g = new Generic<>();
        g.value="string";
        System.out.println(g.get());

    }


}
