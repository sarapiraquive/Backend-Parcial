package co.edu.unisabana.parcial;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/libros")
public class Controller {
    List<Libros> librosList;

    public Controller() {
        this.librosList = new ArrayList<>();
        librosList.add(new Libros("Principito", "Antoine de Saint-Exupéry", "Ficcion"));
    }

        @GetMapping(path = "/libros/todos")
        public List<Libros> obtenerLibros() {
            return librosList;
        }

    @GetMapping(path = "/libros/buscar/{titulo}")
    public List<Libros> obtenerLibrosPorTitulo(@RequestParam String titulo) {
        List<Libros> busquedaTitulo = new ArrayList<>();
        for (Libros libros : librosList) {
            if (libros.getTitulo().equals(titulo)) {
                busquedaTitulo.add(libros);
            }
        }
        return busquedaTitulo;
    }



}
