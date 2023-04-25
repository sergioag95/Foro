package principal.persistencia;

import java.util.ArrayList; 

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import org.hibernate.Session;
import org.hibernate.Transaction;

import principal.modelo.Post;
import principal.util.JPAUtil;



public class PostDAO {

	//insertarJPA
	
		public void insertarPostJPA(Post Post) {
			//JPA
			EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
			
			try {
				em.getTransaction().begin();
				em.persist(Post);
				em.getTransaction().commit();
			} catch (PersistenceException e) {
				em.getTransaction().rollback();
				System.out.println(e.getMessage());
			}
			finally {
				em.close();
			}
			
			
			
		}
		
		
		public void modificarPostJPA(Post Post) {
			//JPA
					EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
					
					try {
						em.getTransaction().begin();
						em.merge(Post);
						em.getTransaction().commit();
					} catch (PersistenceException e) {
						em.getTransaction().rollback();
						System.out.println(e.getMessage());
					}
					finally {
						em.close();
					}
		}
		
		
		public void eliminarPostJPA(Post Post) {
			//JPA
			EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
			
			try {
				em.getTransaction().begin();
				em.remove(em.contains(Post)? Post:em.merge(Post) );
				em.getTransaction().commit();
			} catch (PersistenceException e) {
				em.getTransaction().rollback();
				System.out.println(e.getMessage());
			}
			finally {
				em.close();
			}
		}
		
		
		
		public ArrayList<Post> listarPostsJPA() {
			//JPA
					EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
					
					try {
						em.getTransaction().begin();
						ArrayList<Post> misAlumnos = (ArrayList<Post>) em.createQuery("from Post").getResultList();
						em.getTransaction().commit();
						return misAlumnos;
					} catch (PersistenceException e) {
						em.getTransaction().rollback();
						System.out.println(e.getMessage());
					}
					finally {
						em.close();
					}
					return null;
			
			
		}
		
	
		
		public void imprimirPosts(ArrayList<Post> misPosts) {
			System.out.println("Listado de Posts");
			for(Post p:misPosts) {
				p.toString();
			}
		}
		
		
		public Post buscarPostPorIdJPA(int i) {
			
			EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
			
			try {
				em.getTransaction().begin();
				Post Post = (Post) em.find(Post.class,i);
				System.out.println("El Post buscado se llama " +Post.getId());
				return Post;
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
