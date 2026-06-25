package GimnasioFitness.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/contacto")
public class ContactoController {

    @GetMapping("/listado")
    public String listado() {
        // Solo devuelve la vista, sin lógica de BD
        return "contacto/listado"; 
    }
}