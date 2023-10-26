package org.bamboo.test;

import org.bamboo.massage.IBaseEnum;
import org.bamboo.massage.LoginCodeMassage;

public class MainTest {
    public static void main(String[] args) {
        System.out.println(LoginCodeMassage.MX.getCode());
        System.out.println(LoginCodeMassage.MX.getMsg());
        System.out.println(IBaseEnum.getByCode(LoginCodeMassage.class,2).getMsg());
    }
}
