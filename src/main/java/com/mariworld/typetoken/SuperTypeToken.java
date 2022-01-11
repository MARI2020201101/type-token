package com.mariworld.typetoken;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public class SuperTypeToken {
    static class Sup<T>{
        T value;
    }

    static class Sub extends Sup<String>{
        //상속한 타입정보는 구체화되어 소거되지 않고 남아있다.
    }

    static class SubV2 extends Sup<List<String>>{

    }

    public static void main(String[] args) throws NoSuchFieldException {
        Sup<String> s = new Sup<>();
        System.out.println(s.getClass().getDeclaredField("value").getType());//소거되어 오브젝트 타입

        Sub b = new Sub();
        Type t = b.getClass().getGenericSuperclass(); //부모 클래스 반환
        ParameterizedType ptype = (ParameterizedType) t;
        System.out.println(ptype.getActualTypeArguments()[0]);
        //type 에 여러개가 들어갈 수 도 있으므로 array 로 준다
        //부모 클래스의 type arguments를 반환.

        SubV2 c = new SubV2();
        Type type = c.getClass().getGenericSuperclass();
        ParameterizedType ptype2 = (ParameterizedType) type;
        System.out.println(ptype2.getActualTypeArguments()[0]); //오~~ 개쩐다 다가져온다ㅋㅋㅋ

    }
}
