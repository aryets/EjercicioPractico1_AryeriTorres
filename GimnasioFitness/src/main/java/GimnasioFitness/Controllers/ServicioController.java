package GimnasioFitness.Controllers;

import GimnasioFitness.Domain.Servicio;
import GimnasioFitness.Service.CategoriaService;
import GimnasioFitness.Service.ServicioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
    public String guardar(@ModelAttribute Servicio servicio) {
        servicioService.save(servicio);
        return "redirect:/servicio/listado";
    }

    @PostMapping("/eliminar")
    public String eliminar(@RequestParam("id") Integer id) {
        servicioService.delete(id);
        return "redirect:/servicio/listado";
    }
}
