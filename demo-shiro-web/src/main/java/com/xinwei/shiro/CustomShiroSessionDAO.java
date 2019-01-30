package com.xinwei.shiro;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.Serializable;
import java.util.Collection;

public class CustomShiroSessionDAO extends AbstractSessionDAO {

	private Logger log = LoggerFactory.getLogger(CustomShiroSessionDAO.class);

	private ShiroSessionRepository shiroSessionRepository;

	public ShiroSessionRepository getShiroSessionRepository() {
		return shiroSessionRepository;
	}

	public void setShiroSessionRepository(ShiroSessionRepository shiroSessionRepository) {
		this.shiroSessionRepository = shiroSessionRepository;
	}

	public void delete(Session session) {
		if (session == null) {
			log.error("session can not be null,delete failed");
			return;
		}
		Serializable id = session.getId();
		if (id != null)
			getShiroSessionRepository().deleteSession(id);
	}

	public Collection<Session> getActiveSessions() {
		return getShiroSessionRepository().getAllSessions();
	}

	public void update(Session session) throws UnknownSessionException {
		getShiroSessionRepository().saveSession(session);
	}

	protected Serializable doCreate(Session session) {
		Serializable sessionId = this.generateSessionId(session);
		this.assignSessionId(session, sessionId);
		getShiroSessionRepository().saveSession(session);
		return sessionId;
	}

	protected Session doReadSession(Serializable sessionId) {
		return getShiroSessionRepository().getSession(sessionId);
	}
}
