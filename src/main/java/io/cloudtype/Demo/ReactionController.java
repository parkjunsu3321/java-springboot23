package io.cloudtype.Demo;
import io.cloudtype.Demo.PostgreSQLConnection;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class ReactionController {

    @GetMapping()
    public String getHello() {
        return "good";
    }
    
    @GetMapping("/check")
    public String fetchDataFromDB(@RequestParam(name = "id", required = false) String id, @RequestParam(name = "pass", required = false) String pass) 
    {
        PostgreSQLConnection psc = new PostgreSQLConnection(id, pass);
        return psc.Login();
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) 
    {
        return "Login successful!";
    }

    @GetMapping("/db")
    public void joindb() 
    {
        new PostgreSQLConnection();
    }
    
    @GetMapping("/test")
    public String gettest() {
        return "test";
    }
    public class RequestData 
    {
        private String id;
        private String pass;
        public String getId()
        {
            return id;
        }
        public String getPass()
        {
            return pass;
        }
    }

}
