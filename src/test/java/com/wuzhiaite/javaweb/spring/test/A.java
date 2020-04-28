package com.wuzhiaite.javaweb.spring.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

public interface A<T extends A> {

}

interface B<T extends A> extends A<T>{

}
interface C<T extends A> extends B<T>{

}

@Component
class D implements C<D>{
    @Autowired
    D d;
    public  void test(){
        System.out.println(d.getClass());
    }
    public static void main(String[] args) {
        D test = new D();
        test.test();
    }
}