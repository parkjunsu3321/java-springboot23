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
    PostgreSQLConnection psc = new PostgreSQLConnection();
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
        psc = new PostgreSQLConnection();
    }
    
    @PostMapping("/login")
    public boolean React_Login(@RequestBody Map<String, String> loginData) 
    {
        String id = loginData.get("id");
        String pass = loginData.get("pass");
        psc = new PostgreSQLConnection(id, pass);
        login_b = psc.Login();
        if(login_b == true)
        {
            
        }
        else
        {
            
        psc = new PostgreSQLConnection();
        }
        return login_b;
    }

    @PostMapping("/logout")
    public boolean React_loginout() 
    {
        psc = new PostgreSQLConnection();
        login_b = false;
        return true;
    }

    @PostMapping("/checkid")
    public boolean React_loginout(@RequestBody Map<String, String> loginData) 
    {
        String id = loginData.get("id");
        return psc.CheckId(id);
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
    
    @PostMapping("/join")
    public boolean joindb(@RequestBody Map<String, String> JoinData) 
    {
        String id = JoinData.get("id");
        String name = JoinData.get("name");
        String password = JoinData.get("pw");
        return psc.Join(id, name, password);
    }
}
