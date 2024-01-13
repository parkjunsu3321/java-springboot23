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
    
    @PutMapping("/login")
    public String fetchDataFromDB(@RequestBody RequestData requestData) 
    {
        try
        {
            String id = requestData.getId();
            String pass = requestData.getPass();
            return "받은 ID: " + id + ", 비밀번호: " + pass;
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
            return "요청 처리 중 오류 발생: " + e.getMessage();
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
