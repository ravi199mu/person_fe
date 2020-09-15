package in.ecgc.erp.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import in.ecgc.erp.model.Person;

@FeignClient(name = "personBe",url = "http://localhost:8010/")
public interface PersonBEServiceClient {

	@PostMapping(value = "/persons/save", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Person> savePersonDetails(@RequestBody Person person);
	
	@PutMapping(value = "/persons/update", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Person> updatePersonDetails(@RequestBody Person person);
	

	@GetMapping(value = "/persons/list",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Person>> getAllPersonList();
	
	@GetMapping(value="/persons/get/{id}",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Person> getPersonDetailsUsingId(@PathVariable Integer id);
	
	@DeleteMapping(value = "/persons/delete/{id}",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Person>> deletePersonById(@PathVariable Integer id);
	
}
