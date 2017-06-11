package space.invaders.infrastructure;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import space.invaders.domain.Rank;

public class DBContext {
	private EntityManagerFactory emf;
	private EntityManager em;
	
	public DBContext(){
		emf = Persistence.createEntityManagerFactory("myDatabase");
		em = emf.createEntityManager();
	}
	
	public void addScoreToRank(Rank rank){
		em.getTransaction().begin();
		em.persist(rank);
		em.getTransaction().commit();
		System.out.println("Score saved");
	}
	
	public void clearRank(){
		
	}
}
