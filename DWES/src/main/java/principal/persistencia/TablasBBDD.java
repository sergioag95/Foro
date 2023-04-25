package principal.persistencia;

import java.util.ArrayList;
import java.util.List;

import principal.modelo.Categoria;
import principal.modelo.Hilo;
import principal.modelo.Post;
import principal.modelo.Usuario;

public class TablasBBDD {
	
	public void crearTablas() {
		
		Usuario u1 = new Usuario();
		
		u1.setUsername("sergioag95");
		u1.setNombre("Sergio");
		
		//List hilosu1 = new ArrayList <Hilo>();
		
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		
		
		Categoria c1 = new Categoria();
		
		c1.setNombre("Deportes");
	
		CategoriaDAO categoriaDAO = new CategoriaDAO();
		
		
		
		
		Hilo h1 = new Hilo();
		
		
		h1.setTitulo("Baloncesto");
		h1.setUsuario(u1);
		h1.setCategoria(c1);
		h1.setUsuario(u1);
		//hilosu1.add(h1);
		//u1.setHilos(hilosu1);
		
		
		HiloDAO hiloDAO = new HiloDAO();
		
		Post p1 = new Post();
		
		p1.setHilo(h1);
		p1.setUsuario(u1);
		p1.setContenido("Texto Ejemplo");
		
		PostDAO postDAO = new PostDAO();
		
		
		usuarioDAO.insertarUsuarioJPA(u1);
		categoriaDAO.insertarCategoriaJPA(c1);
		hiloDAO.insertarBocadilloJPA(h1);
		postDAO.insertarPostJPA(p1);


		
		
		
		}
	}

