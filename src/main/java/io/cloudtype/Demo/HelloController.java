package io.cloudtype.Demo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class HelloController {

    @GetMapping("/hello")
    public String getHello() {
        return "hello";
    }
}
