package com.rest.dao.first.custom;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.jpa.repository.support.QueryDslRepositorySupport;

import com.mysema.query.jpa.impl.JPAQuery;
import com.rest.dao.first.First;
import com.rest.dao.first.QFirst;

public class FirstRepositoryImpl extends QueryDslRepositorySupport implements FirstCustomRepository {

	public FirstRepositoryImpl() {
		super(First.class);
	}
	
	@PersistenceContext(unitName = "first")
	private EntityManager entityManager;

	private QFirst first = QFirst.first;
	
	@Override
	public List<String> messages() {
		JPAQuery query = new JPAQuery(entityManager);
		return query.from(first).list(first.message);
	}
	
}
