package principal.persistencia;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import principal.modelo.Categoria;
import principal.modelo.Hilo;
import principal.modelo.Post;
import principal.modelo.Rol;
import principal.modelo.Usuario;

public class TablasBBDD {
   public void crearTablas() {
	   
	   CategoriaDAO categoriaDAO = new CategoriaDAO();
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        HiloDAO hiloDAO = new HiloDAO();
        PostDAO postDAO = new PostDAO();

       
        
        
        
        LocalDateTime fechaAlta = LocalDateTime.now(); // Obtén la fecha y hora actual
        String username = "admin";


            // Crea el usuario "admin"
            Usuario admin = new Usuario(username, "admin", null, "admin@gmail.com", fechaAlta, 0);

            // Agrega el rol de administrador al usuario
            Rol rolAdmin = new Rol("ROLE_ADMIN");
            admin.getRoles().add(rolAdmin);
            
            String password = "admin"; // Contraseña sin encriptar

            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String encodedPassword = passwordEncoder.encode(password);

            // Asigna la contraseña encriptada al usuario
            admin.setPassword(encodedPassword);

            // Guarda el usuario en la base de datos o realiza las operaciones necesarias
        
            
            LocalDateTime fechaAlta2 = LocalDateTime.now(); // Obtén la fecha y hora actual
            String username2 = "moderador";


                // Crea el usuario "admin"
                Usuario moderador = new Usuario(username2, "moderador", null, "moderador@gmail.com", fechaAlta2, 0);

                // Agrega el rol de administrador al usuario
                Rol rolModerador = new Rol("ROLE_MOOD");
                moderador.getRoles().add(rolModerador);
                
                String password2 = "moderador"; // Contraseña sin encriptar

                BCryptPasswordEncoder passwordEncoder2 = new BCryptPasswordEncoder();
                String encodedPassword2 = passwordEncoder2.encode(password2);

                // Asigna la contraseña encriptada al usuario
                moderador.setPassword(encodedPassword2);

                // Guarda el usuario en la base de datos o realiza las operaciones necesarias
                
                
                
                LocalDateTime fechaAlta3 = LocalDateTime.now(); // Obtén la fecha y hora actual
                String username3 = "usuario";


                    // Crea el usuario "admin"
                    Usuario usuario = new Usuario(username3, "usuario", null, "usuario@gmail.com", fechaAlta3, 0);

                    // Agrega el rol de administrador al usuario
                    Rol rolUsuario = new Rol("ROLE_USER");
                    usuario.getRoles().add(rolUsuario);
                    
                    String password3 = "usuario"; // Contraseña sin encriptar

                    BCryptPasswordEncoder passwordEncoder3 = new BCryptPasswordEncoder();
                    String encodedPassword3 = passwordEncoder3.encode(password3);

                    // Asigna la contraseña encriptada al usuario
                    usuario.setPassword(encodedPassword3);

                    // Guarda el usuario en la base de datos o realiza las operaciones necesarias
            
            
                    usuarioDAO.insertarUsuarioJPA(admin);
                    //usuarioDAO.insertarUsuarioJPA(moderador);
                    //usuarioDAO.insertarUsuarioJPA(usuario);
        
        
       //Usuario u1 = new Usuario();
       // u1.setNombre("sergioag95");
        
        //Usuario u2 = new Usuario();
        //u2.setNombre("luisop13");

        /*
        List<Hilo> listaHilos1 = new ArrayList<Hilo>();
        List<Hilo> listaHilos2 = new ArrayList<Hilo>();

        List<Post> listaPosts1 = new ArrayList<Post>();
        List<Post> listaPosts2 = new ArrayList<Post>();


        Categoria c1 = new Categoria();
        c1.setNombre("Deportes");
        c1.setUsuario(admin);
        categoriaDAO.insertarCategoriaJPA(c1);
        
        Categoria c2 = new Categoria();
        c2.setNombre("Tecnología");
        c2.setUsuario(admin);
        categoriaDAO.insertarCategoriaJPA(c2);
        
        
        Hilo h1 = new Hilo();
        h1.setCategoria(c1);
        h1.setTitulo("Baloncesto");
        h1.setUsuario(admin);

        // Agrega el hilo a la lista de hilos del usuario
        listaHilos1.add(h1);
        admin.setHilos(listaHilos1);
        
        Hilo h2 = new Hilo();
        h2.setCategoria(c2);
        h2.setTitulo("Informatica");
        h2.setUsuario(moderador);

        // Agrega el hilo a la lista de hilos del usuario
        listaHilos2.add(h2);
        moderador.setHilos(listaHilos2);

        Post p1 = new Post();
        p1.setContenido("Post Ejemplo");
        p1.setHilo(h1);
        p1.setUsuario(admin);

        // Agrega el post a la lista de posts del usuario
        listaPosts1.add(p1);
        admin.setPosts(listaPosts1);

        
        
        // Asigna la lista de hilos al usuario y a la categoría
        c1.setHilos(listaHilos1);
        
        
        
         Post p2 = new Post();
        p2.setContenido("Post Informatica Ejemplo");
         p2.setHilo(h2);
         p2.setUsuario(moderador);

        // Agrega el post a la lista de posts del usuario
         listaPosts2.add(p2);
         moderador.setPosts(listaPosts2);

        // Asigna la lista de hilos al usuario y a la categoría
         c2.setHilos(listaHilos2);
        
        
         Post p3 = new Post();
         p3.setContenido("Post Baloncesto Ejemplo");
         p3.setHilo(h1);
         p3.setUsuario(moderador);
        //
        // Agrega el post a la lista de posts del usuario
        listaPosts2.add(p3);
        moderador.setPosts(listaPosts2);

        // Asigna la lista de hilos al usuario y a la categoría
        c1.setHilos(listaHilos2);

         

          hiloDAO.insertarHiloJPA(h1);
         postDAO.insertarPostJPA(p1);
        
         hiloDAO.insertarHiloJPA(h2);
         postDAO.insertarPostJPA(p2);
         */
    }
}
