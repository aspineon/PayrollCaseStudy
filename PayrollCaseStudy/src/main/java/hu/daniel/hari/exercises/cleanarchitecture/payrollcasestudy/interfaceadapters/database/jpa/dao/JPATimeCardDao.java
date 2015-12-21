package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.database.jpa.dao;

import java.util.Collection;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.database.jpa.model.paymentclassification.hourly.JPATimeCard;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.DateInterval;

public class JPATimeCardDao {
	
	private EntityManager entityManager;
	
	@Inject
	public JPATimeCardDao(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public Collection<JPATimeCard> findJPATimeCardsIn(DateInterval dateInterval) {
		return entityManager.createQuery(
				  "SELECT tc FROM JPATimeCard tc "
				+ "WHERE tc.id.date >= :intervalFrom "
				+ "AND	 tc.id.date <= :intervalTo ",
				JPATimeCard.class)
				.setParameter("intervalFrom", dateInterval.from)
				.setParameter("intervalTo", dateInterval.to)
				.getResultList();
	}
	
	
}