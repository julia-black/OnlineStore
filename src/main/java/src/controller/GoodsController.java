package src.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import src.model.Good;
import src.model.GoodsDAO;

import java.util.List;

@RestController
public class GoodsController {

    @RequestMapping(method = RequestMethod.POST, value = "/goods")
    public String create(String name, Integer price) {


        Good good = null;
        try {
            good = new Good(name, price);
            goodsDAO.save(good);
        }
        catch (Exception ex) {
            return "Error creating the good: " + ex.toString();
        }
        return "Good succesfully created! (id = " + good.getId() + ")";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/goods", params = {"name"})
    public Good getByName(@RequestParam("name") String name) {
        String goodId;
        Good good;
        try {
            good = goodsDAO.findByName(name);
        }
        catch (Exception ex) {
            return null;
        }
        return good;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/goods", params = {"id"})
    public String getById(@RequestParam("id") Integer id) {
        Good good;
        try {
            good = goodsDAO.findById(id);
        }
        catch (Exception ex) {
            return null;
        }
        return good.getName() + " " + good.getPrice();
    }

     @RequestMapping(method = RequestMethod.GET, value = "/goods")
     public List<Good> getAllGoods(){
         List<Good> goods = (List<Good>) goodsDAO.findAll();
         return goods;
     }

    @RequestMapping(method=RequestMethod.GET,value = "/goods/string")
    public String getAllGoodsString(){
        List<Good> goods = (List<Good>) goodsDAO.findAll();
        String s = "";
        for (Good good:
             goods) {
            s += good.getId() + "&" + good.getName() + "&" + good.getPrice() + "-";
        }
        return s;
    }

    @Autowired
    private GoodsDAO goodsDAO;

}
