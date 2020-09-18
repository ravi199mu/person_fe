package in.ecgc.erp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.netflix.discovery.converters.Auto;

import in.ecgc.erp.client.PersonBEServiceClient;
import in.ecgc.erp.model.Person;

@Service
public class PersonService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private PersonBEServiceClient personBEServiceClient;
	
	private static String baseUrl = "http://localhost:8010/persons";

	public String savePersonDetails(Person person){
//		HttpHeaders headers = new HttpHeaders();
//		headers.setContentType(MediaType.MULTIPART_FORM_DATA);
//		
//		MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
//		map.add("file", person.getResume().getResource());
//		
//		HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(map,headers);
//		System.out.println(requestEntity.getBody());
//		
//		ResponseEntity<String> fileUploadResponse = restTemplate.postForEntity(baseUrl+"/upload", requestEntity, String.class);
//		person.setFileId(fileUploadResponse.getBody());
		
		ResponseEntity<Person> response = personBEServiceClient.savePersonDetails(person);
		Person p = response.getBody();
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);
		
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
		map.add("file", person.getResume().getResource());
		map.add("personId", p.getId());
		
		HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(map,headers);
		
		ResponseEntity<String> fileUploadResponse = restTemplate.postForEntity(baseUrl+"/upload", requestEntity, String.class);
		
		return "Person is added successfully with id : "+p.getId()+" file upload "+fileUploadResponse;
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

	public Person getResumeByPersonId(int id) {
		

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);
		
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
		map.add("id", id);
		
		HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(map,headers);
		
		//.postForEntity(baseUrl+"/downlaod/{id}", requestEntity, Person.class);
		ResponseEntity<Person> personResponse = restTemplate.exchange(baseUrl+"/downlaod/{id}", HttpMethod.GET, requestEntity, Person.class);
		
		//write the file here
		
		return personResponse.getBody();
	}
}
