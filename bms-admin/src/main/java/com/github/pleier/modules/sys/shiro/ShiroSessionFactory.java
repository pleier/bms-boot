package com.github.pleier.modules.sys.shiro;

import com.github.pleier.common.utils.IpUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SessionContext;
import org.apache.shiro.session.mgt.SessionFactory;
import org.apache.shiro.web.session.mgt.DefaultWebSessionContext;

import javax.servlet.http.HttpServletRequest;

/**
 * @author : pleier
 * @date : 2018/10/12
 */
public class ShiroSessionFactory implements SessionFactory {
    @Override
    public Session createSession(SessionContext initData) {
        ShiroSession session = new ShiroSession();
        HttpServletRequest request = (HttpServletRequest)initData.get(DefaultWebSessionContext.class.getName() + ".SERVLET_REQUEST");
        session.setHost(IpUtils.getIpAddr(request));
        return session;
    }
}
