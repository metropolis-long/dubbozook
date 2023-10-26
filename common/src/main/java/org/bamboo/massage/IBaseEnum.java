package org.bamboo.massage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

public interface IBaseEnum <T>{
    default void init(T code, String msg) {
        EnumContainer.put(this,code,msg);
    }
    default T getCode(){
        return EnumContainer.get(this).getCode();
    }
    default String getMsg(){
        return EnumContainer.get(this).getMsg();
    }


    static <T, R extends IBaseEnum<T>> R getByCode(Class<? extends IBaseEnum<T>> clazz,T code){

        IBaseEnum<T>[] constants = clazz.getEnumConstants();

        return Stream.of(clazz.getEnumConstants())
                .filter(eb-> eb.getCode().equals(code))
                .map(v->(R)v)
                .findAny()
                .orElse(null);

    }


    class EnumContainer{
        private static final Map<IBaseEnum, EnumBean> DICT = new ConcurrentHashMap<>();

        public static <T> void put(IBaseEnum<T> baseEnum,T code,String msg){
            DICT.put(baseEnum,new EnumBean(code,msg));
        }
        static <K extends IBaseEnum<T>,T> EnumBean<T> get(K dict){
            return DICT.get(dict);
        }

    }

    class EnumBean<T>{
        public T code;
        public String msg;

        public EnumBean(T code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public T getCode() {
            return code;
        }

        public void setCode(T code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        @Override
        public String toString() {
            return "EnumBean{" +
                    "code=" + code +
                    ", msg='" + msg + '\'' +
                    '}';
        }
    }
}
