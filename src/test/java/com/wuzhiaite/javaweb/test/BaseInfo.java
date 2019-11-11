package com.wuzhiaite.javaweb.test;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@AllArgsConstructor
@ToString
public class BaseInfo implements Serializable,Cloneable {

     String lable;


}
