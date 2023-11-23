package org.bamboo.anno;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class OperatorFactoryBean implements FactoryBean<Object>, InitializingBean {

    private Class<?> type;

    private String expression;
    private Expression sprExpression;
    @Override
    public Object getObject() throws Exception {
        return Proxy.newProxyInstance(type.getClassLoader(), new Class<?>[]{type}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                EvaluationContext standardEvaluationContext = new StandardEvaluationContext();
                char var1 = 'a';
                for (Object arg : args) {
                    standardEvaluationContext.setVariable(String.valueOf(var1++),arg);
                }
                return sprExpression.getValue(standardEvaluationContext);
            }
        });
    }

    @Override
    public Class<?> getObjectType() {
        return this.type;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        ExpressionParser parser = new SpelExpressionParser();
        this.sprExpression = parser.parseExpression(this.expression);
    }

    public Class<?> getType() {
        return type;
    }

    public void setType(Class<?> type) {
        this.type = type;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }
}
