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
        Serializable sessionId = generateSessionId(session);
        assignSessionId(session, sessionId);
        final String key = RedisKeys.getShiroSessionKey(sessionId.toString());
        setShiroSession(key, session);
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        if (sessionId == null) {
            return null;
        }
        final String key = RedisKeys.getShiroSessionKey(sessionId.toString());
        Session session = getShiroSession(key);
        return session;

    }

    @Override
    protected void doUpdate(Session session) {
        //如果会话过期/停止 没必要再更新了
        if(session instanceof ValidatingSession && !((ValidatingSession)session).isValid()) {
            return;
        }
        if (session instanceof ShiroSession) {
            // 如果没有主要字段(除lastAccessTime以外其他字段)发生改变
            ShiroSession ss = (ShiroSession) session;
            if (!ss.isChanged()) {
                return;
            }
            //如果没有返回 证明有调用 setAttribute往redis 放的时候永远设置为false
            ss.setChanged(false);
        }
        final String key = RedisKeys.getShiroSessionKey(session.getId().toString());
        setShiroSession(key, session);
    }

    @Override
    protected void doDelete(Session session) {
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
