package uz.jl.lunchorderbot.controller.department;

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
import uz.jl.lunchorderbot.service.department.DepartmentService;

import java.util.List;

@Controller
@RequestMapping("/admin/department/*")
public class DepartmentController extends AbstractController<DepartmentService> {
    protected DepartmentController(DepartmentService service) {
        super(service);
    }

    @RequestMapping(value = "create/", method = RequestMethod.GET)
    public String createPage() {
        return "department/create";
    }

    @RequestMapping(value = "create/", method = RequestMethod.POST)
    public String create(@ModelAttribute DepartmentCreateDto dto, Model model) {
        dto.setCreatedBy(dto.getUserId());
        service.create(dto);
        List<DepartmentGetDto> list = service.list();
        model.addAttribute("departments", list);
        return "redirect:/admin/department/list/";
    }

    @RequestMapping(value = "update/{id}/", method = RequestMethod.GET)
    public String updatePage(@PathVariable(name = "id") Long id, Model model) {
        DepartmentGetDto dto = service.get(id);
        dto.setId(id);
        model.addAttribute("department", dto);
        return "department/update";
    }

    @RequestMapping(value = "update/{id}/", method = RequestMethod.POST)
    public String update(@PathVariable(name = "id") Long id, @ModelAttribute DepartmentUpdateDto dto, Model model) {
        dto.setId(id);
        service.update(dto);
        List<DepartmentGetDto> list = service.list();
        model.addAttribute("departments", list);
        return "redirect:/admin/department/list/";
    }

    @RequestMapping(value = "delete/{id}/", method = RequestMethod.GET)
    public String deletePage(@PathVariable(name = "id") Long id, Model model) {
        DepartmentGetDto dto = service.get(id);
        model.addAttribute("department", dto);
        return "department/delete";
    }

    @RequestMapping(value = "delete/{id}/", method = RequestMethod.POST)
    public String delete(@PathVariable(name = "id") Long id, Model model) {
        service.delete(id);
        List<DepartmentGetDto> list = service.list();
        model.addAttribute("departments", list);
        return "redirect:/admin/department/list/";
    }

    @RequestMapping(value = "list/", method = RequestMethod.GET)
    public String list(Model model) {
        List<DepartmentGetDto> list = service.list();
        model.addAttribute("departments", list);
        return "department/list";
    }
}
