import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "https://web-reaction-hkty2alqiwtpix.sel4.cloudtype.app/")
public class HelloController {

    @GetMapping("/hello")
    public String getHello() {
        return "hello";
    }
}
