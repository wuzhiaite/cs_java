package com.wuzhiaite.javaweb.common.pagelistconfig.enums;

import com.wuzhiaite.javaweb.base.utils.SpringSecurityUtil;

/**
 * 给定的魔术值，进行替换成对应的值
 * @author lpf
 * @since 20200826
 */
public enum  ParamsEnum {

   USER("###user###"){
       @Override
       public String mapperValue() {
           return SpringSecurityUtil.getCurrentUserName();
       }
   };



   private String label;
   ParamsEnum(String label){
       this.label = label;
   }
   public String label(){
       return this.label;
   }
   public abstract String mapperValue();




}
