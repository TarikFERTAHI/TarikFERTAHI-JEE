package ma.enset.patientsmvc.web;

import lombok.AllArgsConstructor;
import ma.enset.patientsmvc.entities.Patient;
import ma.enset.patientsmvc.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller @AllArgsConstructor
public class PatientController {
    @Autowired
    private PatientRepository patientRepository;

    @GetMapping("/index")
    public String patients(Model model,
                          @RequestParam(name = "page",defaultValue = "0") int page,
                           @RequestParam(name = "size",defaultValue = "5") int size,
                           @RequestParam(name = "keyword",defaultValue = "") String keyword ){
        Page<Patient> PagePatients = patientRepository.findByNomContains(keyword ,PageRequest.of(page,size));
        model.addAttribute("listPatients", PagePatients.getContent());
        model.addAttribute("pages", new int[PagePatients.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("keyword",keyword);
        return "patients";
    }
    @GetMapping("/delete")
    public String delete(Long id , String keyword, int page) {
        patientRepository.deleteById(id);
        return "redirect:/index?page="+page+"&keyword="+keyword;
    }
    @GetMapping("/")
    public String home() {
        return "redirect:/index";
    }
    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public String addPatient(Model model){
        model.addAttribute("patient",new Patient());
        return "formPatient";
    }
    @RequestMapping(value = "/form",method = RequestMethod.GET)
    public String fromPatient(Model model){
        model.addAttribute("patient",new Patient());
        return "formPatient";
    }
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public String save(Model model, Patient patient){
        //model.addAttribute("patient",new Patient());
        patientRepository.save(patient);
        return "confirmation";
    }
    @RequestMapping(value = "/edit",method = RequestMethod.GET)
    public String edit(Model model,Long id){
       //Patient patient = patientRepository.findOne(id);
        //model.addAttribute("patient",patient);
        return "editPatient";
    }

}
