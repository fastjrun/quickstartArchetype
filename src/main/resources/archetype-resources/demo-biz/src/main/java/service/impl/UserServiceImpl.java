#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
/*
 * Copyright (C) 2018 Fastjrun, Inc. All Rights Reserved.
 */
package ${package}.service.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fastjrun.common.ServiceException;
import com.fastjrun.helper.UUID;
import com.fastjrun.service.BaseService;
import ${package}.dao.BaseUserDao;
import ${package}.dao.UserDao;
import ${package}.entity.User;
import ${package}.packet.app.AutoLoginRestRequestBody;
import ${package}.packet.app.LoginRestRequestBody;
import ${package}.packet.app.LoginRestResponseBody;
import ${package}.packet.app.RegistserRestRequestBody;
import ${package}.service.CoreUserService;
import ${package}.service.UserServiceRest;

@Service("userServiceRest")
public class UserServiceImpl extends BaseService implements UserServiceRest {
    @Autowired
    private UserDao userDao;
    @Autowired
    private BaseUserDao baseUserDao;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private CoreUserService coreUserService;

    private final static String USER_ALREADY_EXISTS = "0001";

    @Override
    public void register(
            RegistserRestRequestBody request) {
        String loginPwd = request.getLoginpwd();
        String loginName = request.getLoginId();
        String nickName = request.getNickName();
        Integer sex = Integer.valueOf(request.getSex());
        String mobileNo = request.getMobileNo();
        String email = request.getEmail();
        String condition = " and `loginName`='" + loginName + "' and `mobileNo`='"
                + mobileNo + "'";
        List<User> users = baseUserDao.queryForListCondition(condition);
        if (!users.isEmpty()) {
            throw new ServiceException(USER_ALREADY_EXISTS,
                    serviceMessageSource.getMessage(USER_ALREADY_EXISTS, null,
                            null));
        } else {
            Timestamp curTimestamp = new Timestamp(System.currentTimeMillis());
            User user = new User();
            user.setCreateTime(curTimestamp);
            user.setEmail(email);
            user.setLastModifyTime(curTimestamp);
            user.setLoginName(loginName);
            user.setLoginPwd(loginPwd);
            user.setMobileNo(mobileNo);
            user.setNickName(nickName);
            user.setSex(sex);
            user.setStatus("1");
            user.setLastRecordLoginErrTime(null);
            user.setLoginErrCount(new Integer("0"));
            baseUserDao.insert(user);
            try {
                rabbitTemplate.convertAndSend(user);
            } catch (Exception e) {
                log.warn("", e);
            }
        }
    }

    @Override
    public LoginRestResponseBody login(LoginRestRequestBody request, String deviceId) {
        String loginName = request.getLoginName();
        String loginPwd = request.getLoginpwd();
        String uuid = UUID.getUUID();
        final User user = this.coreUserService.login(loginName, loginPwd,
                deviceId, uuid);
        LoginRestResponseBody responseBody = new LoginRestResponseBody();
        responseBody.setUuid(uuid);
        responseBody.setNickName(user.getNickName());
        responseBody.setEmail(user.getEmail());
        responseBody.setSex(String.valueOf(user.getSex()));
        responseBody.setMobileNo(user.getMobileNo());
        return responseBody;
    }

    @Override
    public LoginRestResponseBody loginv1_1(LoginRestRequestBody request, String deviceId) {
        String loginName = request.getLoginName();
        String loginPwd = request.getLoginpwd();
        String uuid = UUID.getUUID();
        final User user = this.coreUserService.login(loginName, loginPwd,
                deviceId, uuid);

        LoginRestResponseBody responseBody = new LoginRestResponseBody();
        responseBody.setUuid(uuid);
        responseBody.setNickName(user.getNickName());
        responseBody.setEmail(user.getEmail());
        responseBody.setSex(String.valueOf(user.getSex()));
        responseBody.setMobileNo(user.getMobileNo());
        return responseBody;
    }

    @Override
    public LoginRestResponseBody autoLogin(AutoLoginRestRequestBody request, String deviceId) {
        String uuidOld = request.getUuidOld();
        String uuidNew = UUID.getUUID();
        final User user = this.coreUserService.autoLogin(deviceId, uuidOld,
                uuidNew);

        LoginRestResponseBody responseBody = new LoginRestResponseBody();
        responseBody.setUuid(uuidNew);
        responseBody.setNickName(user.getNickName());
        responseBody.setEmail(user.getEmail());
        responseBody.setSex(String.valueOf(user.getSex()));
        responseBody.setMobileNo(user.getMobileNo());
        return responseBody;
    }
}
