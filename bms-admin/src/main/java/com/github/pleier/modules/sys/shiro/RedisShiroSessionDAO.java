package com.github.pleier.modules.sys.shiro;

import com.github.pleier.common.utils.RedisKeys;
import com.github.pleier.common.utils.RedisUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.ValidatingSession;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * shiro与redis集成
 *
 * @author : pleier
 * @date : 2018/3/22
 */
@Component
public class RedisShiroSessionDAO extends EnterpriseCacheSessionDAO {

    @Autowired
    private RedisUtils redisUtils;

    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = super.doCreate(session);
        final String key = RedisKeys.getShiroSessionKey(sessionId.toString());
        setShiroSession(key, session);
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        Session session = super.doReadSession(sessionId);
        if (session == null) {
            final String key = RedisKeys.getShiroSessionKey(sessionId.toString());
            session = getShiroSession(key);
        }
        return session;
    }

    @Override
    protected void doUpdate(Session session) {
        //如果会话过期/停止 没必要再更新了
        if(session instanceof ValidatingSession && !((ValidatingSession)session).isValid()) {
            return;
        }
        super.doUpdate(session);
        final String key = RedisKeys.getShiroSessionKey(session.getId().toString());
        setShiroSession(key, session);
    }

    @Override
    protected void doDelete(Session session) {
        super.doDelete(session);
        final String key = RedisKeys.getShiroSessionKey(session.getId().toString());
        redisUtils.delete(key);
    }

    /**
     * 从redis中获取session
     *
     * @param key
     * @return
     */
    private Session getShiroSession(String key) {
        return redisUtils.get(key, Session.class);
    }

    /**
     * session 放入redis
     *
     * @param key
     * @param session
     */
    private void setShiroSession(String key, Session session) {
        redisUtils.set(key, session, 1800L);
    }
}
