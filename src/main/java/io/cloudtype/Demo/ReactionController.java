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
    public boolean getlogin_b() 
    {
        return login_b;
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

    @PostMapping("/info")
    public String[] getInfoRequest() 
    {
        String[] str = new String[3];
        str = psc.getInfo();
        return str;
    }

    @PostMapping("/drop")
    public boolean DropUser(@RequestBody Map<String, String> loginData)
    {
        String pass = loginData.get("pw");
        if(pass.equals(psc.password))
        {
            if(psc.DeleteId()==true)
            {
                login_b = false;
                psc = new PostgreSQLConnection();
                return true;
            }
            else
            {
                return false;
            }
        }
        else
        {
            return false;
        }
    }

    @PostMapping("/change")
    public String ChangePassword(@RequestBody Map<String, String> loginData)
    {
        String pass = loginData.get("pw");
        String newPass = loginData.get("new_pw");
        return pass+newPass;
    }
}
