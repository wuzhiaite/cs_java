package com.wuzhiaite.javaweb.test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Book implements Serializable,Cloneable {

     String name;
     String author;
     BaseInfo baseInfo;

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public Object deepClone() throws IOException, OptionalDataException,
             ClassNotFoundException {
                 // 将对象写到流里
                 ByteArrayOutputStream bo = new ByteArrayOutputStream();
                 ObjectOutputStream oo = new ObjectOutputStream(bo);
                 oo.writeObject(this);
                 // 从流里读出来
                 ByteArrayInputStream bi = new ByteArrayInputStream(bo.toByteArray());
                 ObjectInputStream oi = new ObjectInputStream(bi);
                 return (oi.readObject());
    }










}
