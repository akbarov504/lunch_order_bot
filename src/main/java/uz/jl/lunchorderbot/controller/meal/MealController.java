package uz.jl.lunchorderbot.controller.meal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import uz.jl.lunchorderbot.controller.AbstractController;
import uz.jl.lunchorderbot.dto.department.DepartmentCreateDto;
import uz.jl.lunchorderbot.dto.department.DepartmentGetDto;
import uz.jl.lunchorderbot.dto.department.DepartmentUpdateDto;
import uz.jl.lunchorderbot.dto.meal.MealCreateDto;
import uz.jl.lunchorderbot.dto.meal.MealGetDto;
import uz.jl.lunchorderbot.dto.meal.MealUpdateDto;
import uz.jl.lunchorderbot.service.meal.MealService;

import java.util.List;

@Controller
@RequestMapping("/admin/meal/*")
public class MealController extends AbstractController<MealService> {
    protected MealController(MealService service) {
        super(service);
    }

    @RequestMapping(value = "create/", method = RequestMethod.GET)
    public String createPage() {
        return "meal/create";
    }

    @RequestMapping(value = "create/", method = RequestMethod.POST)
    public String create(@ModelAttribute MealCreateDto dto, Model model) {
        dto.setCreatedBy(-1L);
        service.create(dto);
        List<MealGetDto> list = service.list();
        model.addAttribute("meals", list);
        return "redirect:/admin/meal/list/";
    }

    @RequestMapping(value = "update/{id}/", method = RequestMethod.GET)
    public String updatePage(@PathVariable(name = "id") Long id, Model model) {
        MealGetDto dto = service.get(id);
        model.addAttribute("meal", dto);
        return "meal/update";
    }

    @RequestMapping(value = "update/{id}/", method = RequestMethod.POST)
    public String update(@PathVariable(name = "id") Long id, @ModelAttribute MealUpdateDto dto, Model model) {
        dto.setId(id);
        service.update(dto);
        MealGetDto getDto = service.get(id);
        model.addAttribute("meal", getDto);
        return "redirect:/admin/meal/list/";
    }

    @RequestMapping(value = "delete/{id}/", method = RequestMethod.GET)
    public String deletePage(@PathVariable(name = "id") Long id, Model model) {
        MealGetDto dto = service.get(id);
        model.addAttribute("meal", dto);
        return "meal/delete";
    }

    @RequestMapping(value = "delete/{id}/", method = RequestMethod.POST)
    public String delete(@PathVariable(name = "id") Long id, Model model) {
        service.delete(id);
        List<MealGetDto> list = service.list();
        model.addAttribute("meals", list);
        return "redirect:/admin/meal/list/";
    }

    @RequestMapping(value = "list/", method = RequestMethod.GET)
    public String list(Model model) {
        List<MealGetDto> list = service.list();
        model.addAttribute("meals", list);
        return "meal/list";
    }
}
