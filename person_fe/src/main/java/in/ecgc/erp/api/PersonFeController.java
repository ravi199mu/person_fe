package in.ecgc.erp.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import in.ecgc.erp.model.Person;
import in.ecgc.erp.service.PersonService;

@Controller
@RequestMapping("/person")
public class PersonFeController {

	@Autowired
	private PersonService personService;
	
	@GetMapping("/form")
	public String getPersonForm(Model model) {
		model.addAttribute("person", new Person());
		return "person";
	}
	
	@PostMapping(value = "/save")
	public String savePersonDetails(@ModelAttribute Person person,Model model) {
		//System.out.println(person.getResume().getOriginalFilename());
		String response = personService.savePersonDetails(person);
		if(response!=null) {
			System.out.println(response);
			model.addAttribute("msg", response);
		}
		model.addAttribute("person", new Person());
		return "person";
	}
	
	@PutMapping(value = "/update")
	public String updatePersonDetails(@ModelAttribute Person person,RedirectAttributes message){
		String response = personService.updatePersonDetails(person);
		if(response!=null) {
			message.addFlashAttribute("msg", response);
		}
		return "person";
	}
	

	@GetMapping(value = "/list")
	public String getAllPersonList(Model model){
		List<Person> personList = personService.getAllPersonList();
		model.addAttribute("list", personList);
		return "persons";
	}
	
	@GetMapping(value="get/{id}")
	public String getPersonDetailsUsingId(@PathVariable Integer id,Model model){
		Person person = personService.getPersonDetailsUsingId(id);
		model.addAttribute("person", person);
		return "";
	}
	
	@GetMapping(value = "delete/{id}")
	public String deletePersonById(@PathVariable Integer id,Model model){
		List<Person> updatedList = personService.deletePersonById(id);
		model.addAttribute("list", updatedList);
		return "persons";
	}
	
	//download file 
	@GetMapping("/download/{personId}")
	public String getResumeByPersonId(@PathVariable("personId") int id) {
		personService.getResumeByPersonId(id);
		return "";
	}
}
