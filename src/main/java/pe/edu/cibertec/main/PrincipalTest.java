package pe.edu.cibertec.main;

import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import pe.edu.cibertec.dominio.Producto;
import pe.edu.cibertec.dominio.Usuario;
import pe.edu.cibertec.repositorio.CarritoCompraRepositorio;
import pe.edu.cibertec.repositorio.CategoriaRepositorio;
import pe.edu.cibertec.repositorio.ProductoRepositorio;
import pe.edu.cibertec.repositorio.UsuarioRepositorio;
import pe.edu.cibertec.repositorio.impl.CarritoCompraJpaRepositorioImpl;
import pe.edu.cibertec.repositorio.impl.CategoriaJpaRepositorioImpl;
import pe.edu.cibertec.repositorio.impl.ProductoJpaRepositorioImpl;
import pe.edu.cibertec.repositorio.impl.UsuarioJpaRepositorioImpl;

public class PrincipalTest {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("labtest");
		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();

		CategoriaRepositorio categoriaRepositorio = new CategoriaJpaRepositorioImpl().setEntityManager(em);

		categoriaRepositorio.obtenerTodos().forEach(c -> {
			System.out.printf("Categoria: %s\n", c.getNombre());
		});

		UsuarioRepositorio usuarioRepositorio = new UsuarioJpaRepositorioImpl().setEntityManager(em);
		Random random = new Random();
		
		for (int i = 0; i < 10; i++) {
			Usuario nuevo = new Usuario();
			nuevo.setNombre("Carlos " + random.nextInt(100));
			nuevo.setApellido("Perez " + random.nextInt(100));
			usuarioRepositorio.crear(nuevo);	
		}		
		Usuario usuario = usuarioRepositorio.buscar(1L);
		if (usuario != null) {
			System.out.printf("Usuario: %d %s %s %d\n", usuario.getId(), usuario.getNombre(), usuario.getApellido(),
					usuario.getEdad());
		}		
		
		ProductoRepositorio productoRepositorio = new ProductoJpaRepositorioImpl().setEntityManager(em);
		productoRepositorio.obtenerTodos().forEach(p -> {
			System.out.printf("Producto: %s, Categoria: %s\n", p.getNombre(), p.getCategoria().getNombre());
		});
		Producto producto = productoRepositorio.buscar(1l);
		
		if (producto != null) {
		System.out.printf("Producto: %s - Categoria: %s\n", producto.getNombre(), producto.getCategoria().getNombre());
		}
		
		productoRepositorio.obtenerPorCategoriaCriteriaApi(1L).forEach(p -> {
			System.out.printf("Producto: %s - Categoria: %s\n", p.getNombre(), p.getCategoria().getNombre());
		});
		em.getTransaction().commit();
		em.close();
		emf.close();
	}
}
