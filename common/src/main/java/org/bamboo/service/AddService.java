package org.bamboo.service;


import org.bamboo.plugins.annotation.Operator;

@Operator("#a + #b")
public interface AddService {
    public Object add(int a, int b);

}
