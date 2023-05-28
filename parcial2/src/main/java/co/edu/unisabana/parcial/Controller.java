package co.edu.unisabana.parcial;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class Controller {
    List<Libros> librosList;

    public Controller() {
        this.librosList = new ArrayList<>();
        librosList.add(new Libros("Principito", "Sara", "Ficcion",123));
        librosList.add(new Libros("Caperucita", "Sara", "Fantasia",111));
        librosList.add(new Libros("Don Quijote", "Cervantes", "Historia",222));
    }

    @GetMapping(path = "/libros/todos")
    public List<Libros> obtenerLibros() {
        return librosList;
    }

    @GetMapping(path = "/libro/buscar/autor/{autor}")
    public List<Libros> obtenerLibrosPorAutor(@PathVariable String autor) {
        List<Libros> busquedaAutor = new ArrayList<>();
        for (Libros libros : librosList) {
            if (libros.getAutor().equals(autor)) {
                busquedaAutor.add(libros);
            }
        }
        return busquedaAutor;
    }

    @GetMapping(path = "/libro/buscar/titulo/{titulo}")
    public Libros obtenerLibroPorTitulo (@PathVariable String titulo) {
        for (Libros libros : librosList) {
            if (libros.getTitulo().equals(titulo)) {
                return libros;
            }
        }
        return new Libros();
    }

    @GetMapping(path = "/libro/buscar/categoria/{genero}")
    public List<Libros> obtenerLibrosPorGenero(@PathVariable String genero) {
        List<Libros> busquedaGenero = new ArrayList<>();
        for (Libros libros : librosList) {
            if (libros.getGenero().equals(genero)) {
                busquedaGenero.add(libros);
            }
        }
        return busquedaGenero;
    }

    @PostMapping(path = "/libro/crear")
    public Respuesta crearLibro (@RequestBody Libros libros) {
        libros.setId((int) (Math.random() * 1000));
        librosList.add(libros);
        return new Respuesta("Libro creado correctamente");
    }

    @DeleteMapping(path = "/libro/eliminar/{id}")
    public Respuesta eliminarLibroPorCodigo(@PathVariable int id) {
        Libros libroAEliminar = null;
        for (Libros estudiante : librosList) {
            if (estudiante.getId() == id) {
                libroAEliminar = estudiante;
                break;
            }
        }
        if (libroAEliminar != null) {
            librosList.remove(libroAEliminar);
            return new Respuesta("Libro eliminado correctamente");
        } else {
            return new Respuesta("No se encontró un libro con el código digitado");
        }
    }

}
