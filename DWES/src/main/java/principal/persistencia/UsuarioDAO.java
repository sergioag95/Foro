package principal.persistencia;

import java.util.ArrayList;  

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import org.hibernate.Session;
import org.hibernate.Transaction;

import principal.modelo.Usuario;
import principal.util.JPAUtil;



public class UsuarioDAO {

	//insertarJPA
	
		public void insertarUsuarioJPA(Usuario usuario) {
			//JPA
			EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
			
			try {
				em.getTransaction().begin();
				em.persist(usuario);
				em.getTransaction().commit();
			} catch (PersistenceException e) {
				em.getTransaction().rollback();
				System.out.println(e.getMessage());
			}
			finally {
				em.close();
			}
			
			
			
		}
		
		
		public void modificarUsuarioJPA(Usuario usuario) {
			//JPA
					EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
					
					try {
						em.getTransaction().begin();
						em.merge(usuario);
						em.getTransaction().commit();
					} catch (PersistenceException e) {
						em.getTransaction().rollback();
						System.out.println(e.getMessage());
					}
					finally {
						em.close();
					}
		}
		
		
		
		public void eliminarUsuarioJPA(Usuario usuario) {
			//JPA
			EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
			
			try {
				em.getTransaction().begin();
				em.remove(em.contains(usuario)? usuario:em.merge(usuario) );
				em.getTransaction().commit();
			} catch (PersistenceException e) {
				em.getTransaction().rollback();
				System.out.println(e.getMessage());
			}
			finally {
				em.close();
			}
		}
		
		
		
		public ArrayList<Usuario> listarUsuariosJPA() {
			//JPA
					EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
					
					try {
						em.getTransaction().begin();
						ArrayList<Usuario> misAlumnos = (ArrayList<Usuario>) em.createQuery("from Usuario").getResultList();
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
		
		
		
		public void imprimirUsuarios(ArrayList<Usuario> misUsuarios) {
			System.out.println("Listado de Usuarios");
			for(Usuario p:misUsuarios) {
				p.toString();
			}
		}
		
		
		public Usuario buscarUsuarioPorIdJPA(int i) {
			
			EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
			
			try {
				em.getTransaction().begin();
				Usuario usuario = (Usuario) em.find(Usuario.class,i);
				System.out.println("El Usuario buscado se llama " +usuario.getId());
				return usuario;
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
