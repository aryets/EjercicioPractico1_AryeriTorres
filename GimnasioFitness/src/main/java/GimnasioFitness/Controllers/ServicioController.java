package GimnasioFitness.Controllers;

import GimnasioFitness.Domain.Servicio;
import GimnasioFitness.Service.CategoriaService;
import GimnasioFitness.Service.ServicioService;
import java.util.Optional;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/servicio")
public class ServicioController {

    private final ServicioService servicioService;
    private final CategoriaService categoriaService;

    public ServicioController(ServicioService servicioService, CategoriaService categoriaService) {
        this.servicioService = servicioService;
        this.categoriaService = categoriaService;
    }

    @GetMapping("/listado")
    public String listado(Model model) {
        var servicios = servicioService.getServicios();
        var categorias = categoriaService.getCategorias();
        model.addAttribute("servicios", servicios);
        model.addAttribute("categorias", categorias);
        model.addAttribute("servicio", new Servicio()); 
        return "servicio/listado";
    }
    
    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Servicio servicio, @RequestParam("categoria.id") Integer categoriaId) {
        // Resolver la categoría a partir del id recibido
        categoriaService.getCategoria(categoriaId).ifPresent(servicio::setCategoria);
        servicioService.save(servicio);
        return "redirect:/servicio/listado";
    }

    @PostMapping("/eliminar")
    public String eliminar(@RequestParam("id") Integer id) {
        try {
            servicioService.delete(id);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: servicio no existe");
        } catch (IllegalStateException e) {
            System.out.println("Error: servicio con datos asociados");
        } catch (Exception e) {
            System.out.println("Error inesperado al eliminar servicio");
        }
        return "redirect:/servicio/listado";
    }
    
    @GetMapping("/modificar/{id}")
    public String modificar(@PathVariable("id") Integer id, Model model) {
        Optional<Servicio> servicioOpt = servicioService.getServicio(id);
        if (servicioOpt.isEmpty()) {
            return "redirect:/servicio/listado";
        }
        model.addAttribute("servicio", servicioOpt.get());
        model.addAttribute("categorias", categoriaService.getCategorias());
        return "servicio/modifica";
    }
}