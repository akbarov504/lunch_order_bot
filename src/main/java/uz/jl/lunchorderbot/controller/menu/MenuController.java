package uz.jl.lunchorderbot.controller.menu;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import uz.jl.lunchorderbot.controller.AbstractController;
import uz.jl.lunchorderbot.dto.menu.MenuCreateDto;
import uz.jl.lunchorderbot.dto.menu.MenuGetDto;
import uz.jl.lunchorderbot.dto.menu.MenuUpdateDto;
import uz.jl.lunchorderbot.repository.meal.MealRepository;
import uz.jl.lunchorderbot.service.meal.MealService;
import uz.jl.lunchorderbot.service.menu.MenuService;

import java.util.List;

@Controller
@RequestMapping("/admin/menu/*")
public class MenuController extends AbstractController<MenuService> {
    protected MenuController(MenuService service) {
        super(service);
    }

    @RequestMapping(value = "create/", method = RequestMethod.GET)
    public String createPage(Model model) {
        MealService mealService = new MealService(new MealRepository());
        model.addAttribute("meals", mealService.list());
        return "menu/create";
    }

    @RequestMapping(value = "create/", method = RequestMethod.POST)
    public String create(@ModelAttribute MenuCreateDto dto, Model model) {
        dto.setCreatedBy(-1L);
        service.create(dto);
        List<MenuGetDto> list = service.list();
        model.addAttribute("menus", list);
        return "redirect:/admin/menu/list/";
    }

    @RequestMapping(value = "delete/{id}/", method = RequestMethod.GET)
    public String deletePage(@PathVariable(name = "id") Long id, Model model) {
        MenuGetDto dto = service.get(id);
        model.addAttribute("menu", dto);
        return "menu/delete";
    }

    @RequestMapping(value = "delete/{id}/", method = RequestMethod.POST)
    public String delete(@PathVariable(name = "id") Long id, Model model) {
        service.delete(id);
        List<MenuGetDto> list = service.list();
        model.addAttribute("menus", list);
        return "redirect:/admin/menu/list/";
    }

    @RequestMapping(value = "list/", method = RequestMethod.GET)
    public String list(Model model) {
        List<MenuGetDto> list = service.list();
        model.addAttribute("menus", list);
        return "menu/list";
    }
}
