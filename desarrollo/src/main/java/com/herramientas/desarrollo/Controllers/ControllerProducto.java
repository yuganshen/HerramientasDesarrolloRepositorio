package com.herramientas.desarrollo.Controllers;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.MediaType;


import com.herramientas.desarrollo.Entidades.Producto;
import com.herramientas.desarrollo.Repositorios.ProductoRepositorio;


@RestController
@RequestMapping("/apiproducto")
@CrossOrigin(origins = "http://localhost:4200") // permite Angular local
public class ControllerProducto {
	
	
	@Autowired
	private ProductoRepositorio productoRepositorio;
	
	@GetMapping("/obtenerProductos")
	public ResponseEntity<List<Producto>> obtenerProductos() {
	    return ResponseEntity.ok(productoRepositorio.findAll());
	}
	
	@GetMapping("/filtrarProductos")
	public ResponseEntity<List<Producto>> filtrarPorTipo(@RequestParam("tipo") String tipo) {
	    List<Producto> lista = productoRepositorio.findByTipoProducto(tipo);
	    return ResponseEntity.ok(lista);
	}
	
	@PostMapping(
		    value = "/agregaProducto",
		    consumes = "multipart/form-data"
		)
		public ResponseEntity<String> agregarProducto(

		        @RequestParam("nombre") String nombre,
		        @RequestParam("descripcion") String descripcion,
		        @RequestParam("precio") Double precio,
		        @RequestParam("stock") Integer stock,
		        @RequestParam("imagenPrincipal") MultipartFile imagen,
		        @RequestParam("marca") String marca,
		        @RequestParam("fechaCreacion") String fechaCreacion,
		        @RequestParam("tipoProducto") String tipoProducto,
		        @RequestParam("estado") String estado,
		        @RequestParam(value = "categoria", required = false) Long categoriaId
		) throws IOException {

		    // Nombre único
		    String nombreImagen = UUID.randomUUID().toString() + "_" + imagen.getOriginalFilename();

		    // Ruta donde guardar
		    String carpeta = "C:/otros2/HerramientasDesarrolloRepositorio/uploads/img/";
		    Path ruta = Paths.get(carpeta + nombreImagen);

		    // Crear carpeta si no existe
		    Files.createDirectories(Paths.get(carpeta));

		    // Guardar archivo
		    Files.copy(imagen.getInputStream(), ruta, StandardCopyOption.REPLACE_EXISTING);

		    // Crear producto
		    Producto producto = new Producto(
		        null,
		        nombre,
		        descripcion,
		        precio,
		        stock,
		        nombreImagen,
		        marca,
		        LocalDateTime.parse(fechaCreacion),
		        tipoProducto,
		        estado,
		        null
		    );

		    productoRepositorio.save(producto);

		    return ResponseEntity.ok("Producto agregado correctamente");
		}
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<String> eliminarProducto(@PathVariable("id") long id) {

	    if (!productoRepositorio.existsById(id)) {
	        return ResponseEntity.status(404).body("Producto no encontrado");
	    }

	    productoRepositorio.deleteById(id);
	    return ResponseEntity.ok("Producto eliminado correctamente");
	}

	@GetMapping("/producto/{id}")
	public ResponseEntity<Producto> obtenerProductoPorId(@PathVariable("id") long id) {
		Producto producto = productoRepositorio.findById(id).get(); // .get() porque sabes que existe
		return ResponseEntity.ok(producto);
	}
	
	@PutMapping(value = "/editar/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<String> editarProducto(
	        @PathVariable("id") long id,

	        @RequestParam("nombre") String nombre,
	        @RequestParam("descripcion") String descripcion,
	        @RequestParam("precio") Double precio,
	        @RequestParam("stock") Integer stock,
	        @RequestParam("marca") String marca,
	        @RequestParam("tipoProducto") String tipoProducto,
	        @RequestParam("estado") String estado,

	        @RequestParam(value = "imagenPrincipal", required = false) MultipartFile imagen
	) {

	    if (!productoRepositorio.existsById(id)) {
	        return ResponseEntity.status(404).body("Producto no encontrado");
	    }

	    Producto p = productoRepositorio.findById(id).get();

	    p.setNombre(nombre);
	    p.setDescripcion(descripcion);
	    p.setPrecio(precio);
	    p.setStock(stock);
	    p.setMarca(marca);
	    p.setTipoProducto(tipoProducto);
	    p.setEstado(estado);

	    // === UNIFICAR CARPETA ===
	    if (imagen != null && !imagen.isEmpty()) {
	        try {
	            String nombreImagen = System.currentTimeMillis() + "_" + imagen.getOriginalFilename();

	            String carpeta = "C:/otros2/HerramientasDesarrolloRepositorio/uploads/img/";
	            Files.createDirectories(Paths.get(carpeta));

	            Path ruta = Paths.get(carpeta + nombreImagen);
	            Files.write(ruta, imagen.getBytes());

	            p.setImagenPrincipal(nombreImagen);

	        } catch (IOException e) {
	            return ResponseEntity.status(500).body("Error al guardar imagen");
	        }
	    }

	    productoRepositorio.save(p);
	    return ResponseEntity.ok("Producto actualizado correctamente");
	}

	
	
	
	
		 /*
	@PutMapping(value = "/editar/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<String> editarProducto(
	        @PathVariable("id") long id,

	        @RequestParam("nombre") String nombre,
	        @RequestParam("descripcion") String descripcion,
	        @RequestParam("precio") Double precio,
	        @RequestParam("stock") Integer stock,
	        @RequestParam("marca") String marca,
	        @RequestParam("tipoProducto") String tipoProducto,
	        @RequestParam("estado") String estado,

	        // Imagen opcional
	        @RequestParam(value = "imagenPrincipal", required = false) MultipartFile imagen
	) {

	    if (!productoRepositorio.existsById(id)) {
	        return ResponseEntity.status(404).body("Producto no encontrado");
	    }

	    Producto p = productoRepositorio.findById(id).get();

	    p.setNombre(nombre);
	    p.setDescripcion(descripcion);
	    p.setPrecio(precio);
	    p.setStock(stock);
	    p.setMarca(marca);
	    p.setTipoProducto(tipoProducto);
	    p.setEstado(estado);

	    // === Si viene nueva imagen, se reemplaza ===
	    if (imagen != null && !imagen.isEmpty()) {
	        try {
	            String nombreImagen = System.currentTimeMillis() + "_" + imagen.getOriginalFilename();
	            Path ruta = Paths.get("src/main/resources/static/img/" + nombreImagen);

	            Files.write(ruta, imagen.getBytes());

	            p.setImagenPrincipal(nombreImagen);
	        } catch (IOException e) {
	            return ResponseEntity.status(500).body("Error al guardar imagen");
	        }
	    }

	    productoRepositorio.save(p);

	    return ResponseEntity.ok("Producto actualizado correctamente");
	}
	
	*/
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	/*@PostMapping("/agregaProducto")
    public ResponseEntity<String> agregarProducto(@RequestBody DTOProducto dto) {

        System.out.println("Producto recibido:");
        System.out.println("Nombre: " + dto.getNombre());
        System.out.println("Precio: " + dto.getPrecio());
        System.out.println("Cantidad: " + dto);
        
        // Aquí luego podrás convertir el DTO a entidad y guardarlo en BD
        Producto producto = new Producto(
        	    null,                       
        	    dto.getNombre(),           
        	    dto.getDescripcion(),        
        	    dto.getPrecio(),            
        	    dto.getStock(),              
        	    dto.getImagenPrincipal(),    
        	    dto.getMarca(),              
        	    dto.getFechaCreacion(),      
        	    dto.getTipoProducto(),       
        	    "activo",                    
        	    dto.getCategoria()          
        	);

        productoRepositorio.save(producto);
        
        return ResponseEntity.ok("Producto agregado correctamente"); 
	}*/
	
	
}
