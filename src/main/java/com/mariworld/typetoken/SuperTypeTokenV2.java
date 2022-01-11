package com.mariworld.typetoken;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public class SuperTypeTokenV2 {
    static class Sup<T>{
        T value;
    }

    public static void main(String[] args) throws NoSuchFieldException {
        class Sub extends Sup<List<String>>{
        }
        Sup<List<String>> sup = new Sup<>(){};

        Sup b = new Sup<List<String>>(){};
        //익명클래스로 깔끔하게 구현해버리기 ㅋ
        
        Type type = b.getClass().getGenericSuperclass();
        ParameterizedType ptype = (ParameterizedType) type;
        System.out.println(ptype.getActualTypeArguments()[0]);
    }
}
