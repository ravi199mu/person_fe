package in.ecgc.erp.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import in.ecgc.erp.service.PersonService;

@Controller
public class PersonFeController {

	@Autowired
	private PersonService personService;
	
	
}
