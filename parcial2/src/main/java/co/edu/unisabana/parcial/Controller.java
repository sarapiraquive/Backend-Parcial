package co.edu.unisabana.parcial;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class Controller {
    List<Libros> librosList;

    public Controller() {
        this.librosList = new ArrayList<>();
        librosList.add(new Libros("principito", "sara", "ficcion",123));
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

    @GetMapping(path = "/libro/buscar/categoria/{categoria}")
    public List<Libros> obtenerLibrosPorCategoria(@PathVariable String categoria) {
        List<Libros> busquedaCategoria = new ArrayList<>();
        for (Libros libros : librosList) {
            if (libros.getCategoria().equals(categoria)) {
                busquedaCategoria.add(libros);
            }
        }
        return busquedaCategoria;
    }

    @PostMapping(path = "/libro/crear")
    public Respuesta crearLibro (@RequestBody Libros libros) {
        libros.setCodigoLibro((int) (Math.random() * 1000));
        librosList.add(libros);
        return new Respuesta("Libro creado correctamente");
    }

    @DeleteMapping(path = "/libro/eliminar/{codigoLibro}")
    public Respuesta eliminarLibroPorCodigo(@PathVariable int codigoLibro) {
        Libros libroAEliminar = null;
        for (Libros estudiante : librosList) {
            if (estudiante.getCodigoLibro() == codigoLibro) {
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
