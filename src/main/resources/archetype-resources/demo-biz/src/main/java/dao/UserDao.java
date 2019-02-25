#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
/*
 * Copyright (C) 2018 Fastjrun, Inc. All Rights Reserved.
 */
package ${package}.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Update;

import ${package}.entity.User;

public interface UserDao {

    @Insert("insert into t_user(`loginPwd`,`nickName`,`sex`,`mobileNo`,`loginErrCount`,`lastLoginTime`,`createTime`,"
            + "`loginName`,`lastModifyTime`,`email`) values(${symbol_pound}{loginPwd},${symbol_pound}{nickName},${symbol_pound}{sex},${symbol_pound}{mobileNo},"
            + "${symbol_pound}{loginErrCount},${symbol_pound}{lastLoginTime},${symbol_pound}{createTime},${symbol_pound}{loginName},${symbol_pound}{lastModifyTime},${symbol_pound}{email})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public int insertNoStatusAndLastRecordLoginErrTime(User user);

    @Update("update t_user set `loginPwd` = ${symbol_pound}{loginPwd},`nickName` = ${symbol_pound}{nickName},`sex` = ${symbol_pound}{sex},`mobileNo` = "
            + "${symbol_pound}{mobileNo},`loginName` = ${symbol_pound}{loginName},`email` = ${symbol_pound}{email} where id = ${symbol_pound}{id} ")
    public int updateUserInfoById(User user);

}
