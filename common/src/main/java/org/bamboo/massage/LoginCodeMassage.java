package org.bamboo.massage;

public enum LoginCodeMassage implements IBaseEnum<Integer> {
    MY(1,"dddddd"),
    MX(2,"哇哇哇哇哇哇哇哇哇哇哇哇");

    LoginCodeMassage(int i, String dddddd) {
        init(i,dddddd);
    }
}
