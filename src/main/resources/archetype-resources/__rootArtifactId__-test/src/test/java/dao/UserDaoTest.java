#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
/*
 * Copyright (C) 2018 Fastjrun, Inc. All Rights Reserved.
 */
package ${package}.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.JsonNode;
import ${package}.entity.User;
import com.fastjrun.test.AbstractAdVancedTestNGSpringContextTest;
import com.fastjrun.utils.JacksonUtils;

public class UserDaoTest extends AbstractAdVancedTestNGSpringContextTest {

    @Autowired
    UserDao userDao;

    @BeforeTest
    @org.testng.annotations.Parameters({
            "envName"
    })
    protected void init(String envName) {
        this.initParam(envName);
    }

    @Test(dataProvider = "loadParam")
    @org.testng.annotations.Parameters({
            "reqParamsJsonStrAndAssert"
    })
    public void testInsertNoStatusAndLastRecordLoginErrTime(String reqParamsJsonStrAndAssert) {
        String[] reqParamsJsonStrAndAssertArray = reqParamsJsonStrAndAssert.split(",assert=");
        String reqParamsJsonStr = reqParamsJsonStrAndAssertArray[0];
        log.debug(reqParamsJsonStr);
        JsonNode reqParamsJson = JacksonUtils.toJsonNode(reqParamsJsonStr);
        User user = null;
        if (reqParamsJson != null) {
            JsonNode userJson = reqParamsJson.get("user");
            user = JacksonUtils.readValue(userJson.toString(), User.class);
        }
        JsonNode assertJson = null;
        if (reqParamsJsonStrAndAssertArray.length == 2) {
            assertJson = JacksonUtils.toJsonNode(reqParamsJsonStrAndAssertArray[1]);
        }
        int res = 0;
        if (assertJson != null) {
            JsonNode codeNode = assertJson.get("exception");
            if (codeNode != null) {
                try {
                    res = userDao.insertNoStatusAndLastRecordLoginErrTime(user);
                } catch (Exception e) {

                }
            } else {
                res = userDao.insertNoStatusAndLastRecordLoginErrTime(user);
            }

        } else {
            res = userDao.insertNoStatusAndLastRecordLoginErrTime(user);
        }
        log.info(res);

    }

}
