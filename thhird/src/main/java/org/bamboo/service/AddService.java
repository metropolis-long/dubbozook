package org.bamboo.service;


import org.bamboo.anno.Operator;

@Operator("#a + #b")
public interface AddService {
    public Object add(int a, int b);

}
