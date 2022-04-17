package ma.enset.students_managment.web;


import lombok.AllArgsConstructor;
import ma.enset.students_managment.Entities.Student;
import ma.enset.students_managment.repositories.StudentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@AllArgsConstructor
public class StudentController {
    private StudentRepository studentRepository;

    @GetMapping(path = "/user/index")
    public String students(Model model,
                            @RequestParam(name = "page", defaultValue = "0") int page,
                            @RequestParam(name = "size",defaultValue = "5") int size,
                            @RequestParam(name = "keyword",defaultValue = "") String keyword){
        Page<Student> pageStudents = studentRepository.findByNomContains(keyword, PageRequest.of(page,size));
        model.addAttribute("listStudents",pageStudents.getContent());
        model.addAttribute("pages",new int[pageStudents.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("keyword",keyword);
        return "students";
    }
    @GetMapping(path = "/admin/delete")
    public String delete(Long id, String keyword, int page){
        studentRepository.deleteById(id);
        return "redirect:/user/index?page="+page+"&keyword="+keyword;
    }

    @GetMapping(path = "/")
    public String Home(){
        return "home";
    }

    @GetMapping("/user/students")
    @ResponseBody
    public List<Student> listStudents(){
        return studentRepository.findAll();
    }

    @GetMapping("/admin/formStudents")
    public String formStudents(Model model){
        model.addAttribute("student",new Student());
        return "formStudents";
    }

    @PostMapping("/admin/save")
    public String save(Model model, @Valid Student student, BindingResult bindingResult,
                       @RequestParam(defaultValue = "0") int page,
                       @RequestParam(defaultValue = "") String keyword){
        if(bindingResult.hasErrors()) return "formStudents";
        studentRepository.save(student);
        return "redirect:/user/index?page="+page+"&keyword="+keyword;
    }
    @GetMapping("/admin/editStudent")
    public String editStudent(Model model,Long id,String keyword, int page){
        Student student = studentRepository.findById(id).orElse(null);
        if (student == null) throw new RuntimeException("Student introuvable");
        model.addAttribute("student",student);
        model.addAttribute("page", page);
        model.addAttribute("keyword", keyword);
        return "editStudent";
    }

}
