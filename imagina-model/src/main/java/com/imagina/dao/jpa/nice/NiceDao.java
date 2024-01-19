package com.imagina.dao.jpa.nice;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.imagina.dao.jpa.JpaDao;

@Stateless
public class NiceDao extends JpaDao {
	
	@PersistenceContext(unitName = "imagina-model-nice")
	public void setEntityManager(EntityManager em) {
		this.em = em;
	}

}
