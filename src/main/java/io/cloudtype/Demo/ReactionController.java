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
    public ResponseEntity<String> fetchDataFromDB(@RequestBody RequestData requestData) 
    {
        String id = requestData.getId();
        String pass = requestData.getPass();
        PostgreSQLConnection psc = new PostgreSQLConnection(id, pass);
        return ResponseEntity.ok(id + pass + ".");
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
    }

}
