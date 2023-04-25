package principal.modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "usuarios")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column (name="nombre")
	private String nombre;
	
	@Column(name="username", unique = true)
	private String username;
	
	@Column (name="password")
	private String password;
	
	
	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
	  private List<Hilo> hilos = new ArrayList<>();

	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
	  private List<Post> posts = new ArrayList<>();

	public Usuario() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Hilo> getHilos() {
		return hilos;
	}

	public void setHilos(List<Hilo> hilos) {
		this.hilos = hilos;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombre=" + nombre + ", username=" + username + ", password=" + password
				+ ", hilos=" + hilos + ", posts=" + posts + "]";
	}
	
	
	
}
