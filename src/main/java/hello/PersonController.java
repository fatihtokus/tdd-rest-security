package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class PersonController {

/*
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    @Autowired
    CustomerRepository customerRepository;
*/


    @Autowired
    PersonRepository personRepository;

    @RequestMapping(path="/person/add")
    public Person addPerson(@RequestParam(value="name", defaultValue="Person") String name) {
        if(name.equals("Person")){
            long count = personRepository.count();
            name = name + " " + (count + 1);
        }

        Person person = new Person(name);
        person = personRepository.save(person);
        return person;

    }

    @GetMapping(path="/person/all")
    public Iterable<Person> allPerson() {
        return personRepository.findAll();

    }


}
