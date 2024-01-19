package com.imagina.dao.jpa.nagios;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.imagina.dao.jpa.JpaDao;

@Stateless
public class NagiosDao extends JpaDao {
	
	@PersistenceContext(unitName = "imagina-model-nagios")
	public void setEntityManager(EntityManager em) {
		this.em = em;
	}

}
