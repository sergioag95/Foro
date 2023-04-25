package principal.persistencia;

import principal.modelo.Categoria;
import principal.modelo.Hilo;
import principal.modelo.Post;
import principal.modelo.Usuario;

public class TablasBBDD {
	
	public void crearTablas() {
		
		Usuario u1 = new Usuario();
		u1.setUsername("sergioag95");
		u1.setNombre("Sergio");
		
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarioDAO.insertarUsuarioJPA(u1);
		
		
		Categoria c1 = new Categoria();
		
		c1.setNombre("Deportes");
	
		CategoriaDAO categoriaDAO = new CategoriaDAO();
		
		categoriaDAO.insertarCategoriaJPA(c1);
		
		categoriaDAO.listarCategoriasJPA();
		
		Hilo h1 = new Hilo();
		h1.setTitulo("Baloncesto");
		h1.setUsuario(u1);
		h1.setCategoria(c1);
		
		HiloDAO hiloDAO = new HiloDAO();
		hiloDAO.insertarBocadilloJPA(h1);
		
		Post p1 = new Post();
		
		p1.setHilo(h1);
		p1.setUsuario(u1);
		p1.setContenido("Texto Ejemplo");
		
		PostDAO postDAO = new PostDAO();
		postDAO.insertarPostJPA(p1);
		
		
		
		}
	}

