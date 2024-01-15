package io.cloudtype.Demo;
import io.cloudtype.Demo.PostgreSQLConnection;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class ReactionController {
    boolean login_b = false;
    @GetMapping()
    public String getHello() {
        return "good";
    }

    @PostMapping("/login")
    public String login(@RequestBody Map<String, String> loginData) 
    {
        String id = loginData.get("id");
        String pass = loginData.get("pass");
        PostgreSQLConnection psc = new PostgreSQLConnection(id, pass);
        login_b = true;
        return psc.Login();
    }

    @PutMapping("/change")
    public String login(@RequestBody Map<String, String> loginData) 
    {
        String id = loginData.get("id");
        String pass = loginData.get("pass");
        PostgreSQLConnection psc = new PostgreSQLConnection(id, pass);
        return psc.Login();
    }

    @GetMapping("/db")
    public void joindb() 
    {
        return login_b;
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
