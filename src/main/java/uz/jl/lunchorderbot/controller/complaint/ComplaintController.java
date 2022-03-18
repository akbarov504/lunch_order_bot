package uz.jl.lunchorderbot.controller.complaint;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import uz.jl.lunchorderbot.controller.AbstractController;
import uz.jl.lunchorderbot.dto.complaint.ComplaintGetDto;
import uz.jl.lunchorderbot.dto.department.DepartmentGetDto;
import uz.jl.lunchorderbot.service.complaint.ComplaintService;

import java.util.List;

@Controller
@RequestMapping("/admin/complaint/*")
public class ComplaintController extends AbstractController<ComplaintService> {
    protected ComplaintController(ComplaintService service) {
        super(service);
    }

    @RequestMapping(value = "delete/{id}/", method = RequestMethod.GET)
    public String deletePage(@PathVariable(name = "id") Long id, Model model) {
        ComplaintGetDto dto = service.get(id);
        model.addAttribute("complaint", dto);
        return "complaint/delete";
    }

    @RequestMapping(value = "delete/{id}/", method = RequestMethod.POST)
    public String delete(@PathVariable(name = "id") Long id, Model model) {
        service.delete(id);
        List<ComplaintGetDto> list = service.list();
        model.addAttribute("complaints", list);
        return "redirect:/admin/complaint/list/";
    }

    @RequestMapping(value = "list/", method = RequestMethod.GET)
    public String list(Model model) {
        List<ComplaintGetDto> list = service.list();
        model.addAttribute("complaints", list);
        return "complaint/list";
    }
}
