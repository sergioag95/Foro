package principal.persistencia;

import java.util.ArrayList;   


import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import org.hibernate.Session;
import org.hibernate.Transaction;

import principal.modelo.Categoria;
import principal.util.JPAUtil;
 
public class CategoriaDAO {
	
	//insertarJPA
	
	public void insertarCategoriaJPA(Categoria categoria) {
		//JPA
		EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
		
		try {
			em.getTransaction().begin();
			em.persist(categoria);
			em.getTransaction().commit();
		} catch (PersistenceException e) {
			em.getTransaction().rollback();
			System.out.println(e.getMessage());
		}
		finally {
			em.close();
		}
		
		
		
	}
	
	
	
	public void modificarCategoriaJPA(Categoria categoria) {
		//JPA
				EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
				
				try {
					em.getTransaction().begin();
					em.merge(categoria);
					em.getTransaction().commit();
				} catch (PersistenceException e) {
					em.getTransaction().rollback();
					System.out.println(e.getMessage());
				}
				finally {
					em.close();
				}
	}
	
	
	public void eliminarCategoriaJPA(Categoria categoria) {
		//JPA
		EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
		
		try {
			em.getTransaction().begin();
			em.remove(em.contains(categoria)? categoria:em.merge(categoria) );
			em.getTransaction().commit();
		} catch (PersistenceException e) {
			em.getTransaction().rollback();
			System.out.println(e.getMessage());
		}
		finally {
			em.close();
		}
	}
	

	
	public ArrayList<Categoria> listarCategoriasJPA() {
		//JPA
				EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
				
				try {
					em.getTransaction().begin();
					ArrayList<Categoria> misCategorias = (ArrayList<Categoria>) em.createQuery("from Categoria").getResultList();
					em.getTransaction().commit();
					return misCategorias;
				} catch (PersistenceException e) {
					em.getTransaction().rollback();
					System.out.println(e.getMessage());
				}
				finally {
					em.close();
				}
				return null;
		
		
	}
	

	
	public void imprimirCategorias(ArrayList<Categoria> misCategorias) {
		System.out.println("Listado de Categorias");
		for(Categoria a:misCategorias) {
			System.out.println(a.toString());
		}
	}
	
	
	public Categoria buscarPorIdJPA(int i) {
		
		EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
		
		try {
			em.getTransaction().begin();
			Categoria categoria = (Categoria) em.find(Categoria.class,i);
			System.out.println("La categoria buscada se llama " +categoria.getNombre());
			return categoria;
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


