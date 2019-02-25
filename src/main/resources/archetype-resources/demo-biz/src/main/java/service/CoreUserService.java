#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
/*
 * Copyright (C) 2018 Fastjrun, Inc. All Rights Reserved.
 */
package ${package}.service;

import java.util.Date;

import ${package}.entity.User;

 public interface CoreUserService {
    

     void checkLogin(String uuid, String deviceId);

     User autoLogin(String deviceId, String uuidOld, String uuidNew);

     User login(String loginName, String loginPwd, String deviceId,String uuid);

     void logOut(String uuid, String deviceId);
    
     void unlockUserPwd(Date date);

     void inValidUserLoginCredential(Date date);

}
