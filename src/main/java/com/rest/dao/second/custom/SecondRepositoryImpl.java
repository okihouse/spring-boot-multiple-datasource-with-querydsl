package com.rest.dao.second.custom;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.mysema.query.jpa.impl.JPAQuery;
import com.rest.config.QueryDslRepositorySupportWrapper;
import com.rest.dao.second.QSecond;
import com.rest.dao.second.Second;

public class SecondRepositoryImpl extends QueryDslRepositorySupportWrapper implements SecondCustomRepository {

	public SecondRepositoryImpl() {
		super(Second.class);
	}
	
	private EntityManager entityManager;
	
	@PersistenceContext(unitName = "second")
	public void setFirstEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
		super.setEntityManager(entityManager);
	}
	
	private QSecond second = QSecond.second;
	
	@Override
	public List<String> messages() {
		JPAQuery query = new JPAQuery(entityManager);
		return query.from(second).list(second.message);
	}

}
