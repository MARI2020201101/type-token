package com.mariworld.typetoken;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class SuperTypeTokenV4 {
    static class TypesafeMap{
        Map<Type, Object> innerMap = new HashMap<>();

        <T> void put(TypeReference<T> tr, T value){
            innerMap.put(tr.type, value);
        }

        @SuppressWarnings("unchecked")
        <T> T get(TypeReference<T> tr){
            return (T) innerMap.get(tr.type);
        }
    }

    static class TypeReference<T>{
        Type type;
        public TypeReference(){
            Type stype = getClass().getGenericSuperclass();
            if(stype instanceof ParameterizedType){
                ParameterizedType ptype = (ParameterizedType) stype;
                this.type = ptype.getActualTypeArguments()[0];

            }else throw new RuntimeException();
        }
    }


    public static void main(String[] args) throws NoSuchFieldException {

        TypesafeMap m = new TypesafeMap();
        TypeReference<List<String>> t3 = new TypeReference<>() {};
        m.put(t3, List.of("a","b","c"));
        System.out.println(m.get(t3));
        System.out.println(m.get(t3).getClass());

        TypeReference<Map<String,Integer>> map = new TypeReference<>() {};
        Map<String,Integer> integerMap = new HashMap<>();
        integerMap.put("a",1);
        integerMap.put("b",2);
        integerMap.put("c",3);
        m.put(map , integerMap);
        System.out.println(m.get(map));
        System.out.println(m.get(map).getClass());
    }
}
