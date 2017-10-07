package src.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
 public class StoreController {

    @RequestMapping(value = "/store", method = RequestMethod.GET)
    String method() {
        return "Store";
    }

}