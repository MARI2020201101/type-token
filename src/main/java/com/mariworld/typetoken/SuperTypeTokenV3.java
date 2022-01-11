package com.mariworld.typetoken;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class SuperTypeTokenV3 {
    static class TypesafeMap{
        Map<TypeReference<?>, Object> map = new HashMap<>();

        <T> void put(TypeReference<T> tr, T value){
            map.put(tr, value);
        }
        <T> T get(TypeReference<T> tr){
            return ((Class<T>)tr.type).cast(map.get(tr.type));
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


        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            TypeReference<?> that = (TypeReference<?>) o;
            return Objects.equals(type, that.type);
        }

        @Override
        public int hashCode() {
            return Objects.hash(type);
        }
    }


    public static void main(String[] args) throws NoSuchFieldException {

        TypeReference<List<String>> t = new TypeReference<>(){}; //상속한 익명클래스 생성
//        TypeReference<List<String>> t2 = new TypeReference<>();
        System.out.println(t.type);
//        System.out.println(t2.type); //위에서 설정한 런타임익셉션 발생.
//        TypeReference를 상속한 인스턴스가 아니고 타입레퍼런스 인스턴스 그 자체이다



        TypesafeMap m = new TypesafeMap();
        TypeReference<List<String>> t3 = new TypeReference<>() {
        };
        m.put(t3, List.of("a","b","c"));
        System.out.println(m.get(t3));


        TypesafeMap m2 = new TypesafeMap();
        TypeReference<String> t4 = new TypeReference<>() {
        };
        m2.put(t4, "a");
        System.out.println(m2.get(t4));

    }
}
