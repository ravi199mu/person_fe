package in.ecgc.erp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import in.ecgc.erp.client.PersonBEServiceClient;
import in.ecgc.erp.model.Person;

@Service
public class PersonService {
	
	@Autowired
	private PersonBEServiceClient personBEServiceClient;

	public String savePersonDetails(Person person){
		ResponseEntity<Person> response = personBEServiceClient.savePersonDetails(person);
		Person p = response.getBody();
		return "Person is added successfully with id : "+p.getId();
	}
	
	public String updatePersonDetails(Person person){
		ResponseEntity<Person> response = personBEServiceClient.updatePersonDetails(person);
		Person p = response.getBody();
		return "Person is updated successfully with id : "+p.getId();
	}
	

	public List<Person> getAllPersonList(){
		return personBEServiceClient.getAllPersonList().getBody();
	}
	
	public Person getPersonDetailsUsingId(Integer id){
		return personBEServiceClient.getPersonDetailsUsingId(id).getBody();
	}
	
	public List<Person> deletePersonById(Integer id){
		return personBEServiceClient.deletePersonById(id).getBody();
	}
}
