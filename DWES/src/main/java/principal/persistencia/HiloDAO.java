package principal.persistencia;
 
import java.util.ArrayList;  

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import org.hibernate.Session;
import org.hibernate.Transaction;

import principal.modelo.Hilo;
import principal.util.JPAUtil;


public class HiloDAO {

	//insertarJPA
	
		public void insertarBocadilloJPA(Hilo post) {
			//JPA
			EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
			
			try {
				em.getTransaction().begin();
				em.persist(post);
				em.getTransaction().commit();
			} catch (PersistenceException e) {
				em.getTransaction().rollback();
				System.out.println(e.getMessage());
			}
			finally {
				em.close();
			}
			
			
			
		}
		
	
		
		public void modificarBocadilloJPA(Hilo post) {
			//JPA
					EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
					
					try {
						em.getTransaction().begin();
						em.merge(post);
						em.getTransaction().commit();
					} catch (PersistenceException e) {
						em.getTransaction().rollback();
						System.out.println(e.getMessage());
					}
					finally {
						em.close();
					}
		}
		
	
		
		public void eliminarBocadilloJPA(Hilo post) {
			//JPA
			EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
			
			try {
				em.getTransaction().begin();
				em.remove(em.contains(post)? post:em.merge(post) );
				em.getTransaction().commit();
			} catch (PersistenceException e) {
				em.getTransaction().rollback();
				System.out.println(e.getMessage());
			}
			finally {
				em.close();
			}
		}
		
		
		
		public ArrayList<Hilo> listarHilosJPA() {
			//JPA
					EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
					
					try {
						em.getTransaction().begin();
						ArrayList<Hilo> misHilos = (ArrayList<Hilo>) em.createQuery("from Hilos").getResultList();
						em.getTransaction().commit();
						return misHilos;
					} catch (PersistenceException e) {
						em.getTransaction().rollback();
						System.out.println(e.getMessage());
					}
					finally {
						em.close();
					}
					return null;
			
			
		}
		
	
		public void imprimirHilos(ArrayList<Hilo> misHilos) {
			System.out.println("Listado de Hilos");
			for(Hilo p:misHilos) {
				p.toString();
			}
		}
		
		
		public Hilo buscarBocadilloPorIdJPA(int i) {
			
			EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
			
			try {
				em.getTransaction().begin();
				Hilo hilo = (Hilo) em.find(Hilo.class,i);
				System.out.println("El hilo buscado se llama " +hilo.getId());
				return hilo;
			} catch (PersistenceException e) {
				em.getTransaction().rollback();
				System.out.println(e.getMessage());
			}
			finally {
				em.close();
			}
			return null;
			
		}
		
	
	
	
}
