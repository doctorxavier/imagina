package com.imagina.dao.jpa.imagina;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.imagina.dao.jpa.JpaDao;

@Stateless
public class ImaginaDao extends JpaDao {
	
	@PersistenceContext(unitName = "imagina-model")
	public void setEntityManager(EntityManager em) {
		this.em = em;
	}

}
