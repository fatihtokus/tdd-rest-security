package hello;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    @Autowired
    CustomerRepository customerRepository;

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        //System.out.println("Aloo");
        return new Greeting(counter.incrementAndGet(),
                            String.format(template, name));
    }

    @GetMapping(path="/customer/add")
    public Customer greeting1(@RequestParam(value="name", defaultValue="World") String name) {
        //return Collections.EMPTY_LIST;
        Customer customer = new Customer(name, name);
        customer = customerRepository.save(customer);
        return customer;
    }

    @GetMapping(path="/customer/all")
    public Iterable<Customer> greeting3(@RequestParam(value="name", defaultValue="World") String name) {
        //return Collections.EMPTY_LIST;
        return customerRepository.findAll();
    }
}
