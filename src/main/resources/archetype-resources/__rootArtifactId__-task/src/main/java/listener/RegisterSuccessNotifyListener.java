#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
/*
 * Copyright (C) 2019 Fastjrun, Inc. All Rights Reserved.
 */
package ${package}.listener;

import com.fastjrun.listener.BaseListener;
import ${package}.entity.User;

public class RegisterSuccessNotifyListener extends BaseListener {
    public void processUser(User user) {
        log.info(" user :" + user);
    }
}

