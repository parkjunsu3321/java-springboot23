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
    
    @PutMapping("/check")
    public String fetchDataFromDB(@RequestBody RequestData requestData) 
    {
        try 
        {
            String id = requestData.getId();
            String pass = requestData.getPass();
            PostgreSQLConnection psc = new PostgreSQLConnection(id, pass);
            return id+pass;
        } 
        catch (Exception e) 
        {
            return e.getMessage();
        }
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
