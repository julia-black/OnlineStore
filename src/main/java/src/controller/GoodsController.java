package src.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import src.model.Good;
import src.model.GoodsDAO;
import sun.rmi.runtime.Log;

import java.util.ArrayList;
import java.util.List;

@RestController
public class GoodsController {

    @RequestMapping("/create")
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

    @RequestMapping("/get_by_name")
    public Good getByName(String name) {
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

  //@RequestMapping("/get_by_id")
  //public Good getById(Integer id) {
  //    Good good;
  //    try {
  //        good = goodsDAO.findById(id);
  //    }
  //    catch (Exception ex) {
  //        return null;
  //    }
  //    return good;
  //}

    @RequestMapping("/get_by_id")
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
     @RequestMapping("/get_all_goods")
     public List<Good> getAllGoods(){
         List<Good> goods = (List<Good>) goodsDAO.findAll();
         return goods;
     }

     @RequestMapping("/add_to_cart")
     public String addToCart(Integer id) {
         Good good;
             try {
                 good = goodsDAO.findById(id);
                 //Подключение к лич кабинету, добавление  к нему в корзину good
             } catch (Exception ex) {
                 return "Good with id = " + id + " not found";
             }
         return "Good + " + good.getName() + "added in Your cart";
         }


    @Autowired
    private GoodsDAO goodsDAO;

}
