package space.invaders.infrastructure;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

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
	
	public List<Rank> getTop5Scores(){
		TypedQuery<Rank> query = em.createQuery("SELECT r FROM Rank r ORDER BY r.Score DESC", Rank.class);
		return query.setMaxResults(5).getResultList();
	}
	
	public void clearRank(){
		
	}
}
