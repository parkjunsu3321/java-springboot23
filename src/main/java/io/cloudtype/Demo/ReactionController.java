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
    PostgreSQLConnection psc;
    boolean login_b = false;
    @GetMapping()
    public String getHello() 
    {
        return "good";
    }

    @GetMapping("/exit")
    public void ExitonClose() 
    {
        login_b = false;
        psc = null;
    }
    
    @PostMapping("/login")
    public String Login(@RequestBody Map<String, String> loginData) 
    {
        String id = loginData.get("id");
        String pass = loginData.get("pass");
        psc = new PostgreSQLConnection(id, pass);
        login_b = true;
        return psc.Login();
    }

    @PutMapping("/change")
    public String Id_Change(@RequestParam(name = "pass", required = false) String n_pass) 
    {
        if(login_b)
        {
            return psc.Change_Password(n_pass);
        }
        else
        {
            return "로그인 하세여!";
        }
    }

    @DeleteMapping("/delete")
    public String deleteMovie(@RequestParam(name = "id", required = false) String id) 
    {
        return id;
    }
    
    @GetMapping("/db")
    public String joindb() 
    {
        return String.valueOf(login_b);
    }
    
    @GetMapping("/test")
    public String gettest() 
    {
        return "test";
    }
}
