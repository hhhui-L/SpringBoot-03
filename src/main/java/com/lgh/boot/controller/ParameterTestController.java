package com.lgh.boot.controller;

import com.lgh.boot.bean.Person;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ParameterTestController {

    /**
     * 数据绑定：页面提交的请求数据（GET、POST）都可以和对象属性进行绑定
     * @param person
     * @return
     */
    @PostMapping("/saveuser")
    public Person saveUser(Person person){
        return person;
    }

    // car/2/owner/zhangsan
    @GetMapping("/car/{id}/owner/{username}")
    public Map<String,Object> getCar(@PathVariable("id") Integer id,
                                     @PathVariable("username") String name,
                                     // @PathVariable提供Map装所有的key和value,但必须是String类型
                                     @PathVariable Map<String,String> pv,
                                     @RequestHeader("User-Agent") String userAgent,
                                     // @RequestHeader提供Map装所有的key和value,但必须是String类型
                                     @RequestHeader Map<String,String> header,
                                     @RequestParam("age") Integer age,
                                     @RequestParam("inters") List<String> inters,
                                     // @RequestParam提供Map装所有的key和value,但必须是String类型
                                     @RequestParam Map<String,String> params
                                     // @CookieValue("_ga") String _ga,
                                     // @CookieValue("_ga") Cookie cookie,
                                     ){

        Map<String,Object> map = new HashMap<>();
//        map.put("id",id);
//        map.put("name",name);
//        map.put("pv",pv);
//        map.put("userAgent",userAgent);
//        map.put("header",header);
        map.put("age",age);
        map.put("inters",inters);
        map.put("params",params);
//        map.put("_ga",_ga);
//        System.out.println(cookie.getName()+"====>"+cookie.getValue());
        return map;
    }

    @PostMapping("/save")
    // @RequestBody--->获取请求体的值，只有Post请求才有请求体，例如表单提交会有很多key,value，获取这些数据
    public Map postMethod(@RequestBody String content){

        Map<String,Object> map = new HashMap<>();
        map.put("content",content);
        return map;

    }

    // 1.语法： /cars/sell;low=34;brand=byd,audi,yd
    // 2.SpringBoot默认是禁用了矩阵变量的功能，需要手动开启
    //  原理：对于路径的处理，UrlPathHelper进行解析，removeSemicolonContent（移除分号内容）支持矩阵变量的
    //3.矩阵变量必须有url路径变量才能被解析
    @GetMapping("/cars/{path}") //---> 路径变量必须这样写
    public Map carsSell(@MatrixVariable("low") Integer low,
                        @MatrixVariable("brand") List<String> brand,
                        @PathVariable("path") String path){

        Map<String,Object> map = new HashMap<>();
        map.put("low",low);
        map.put("brand",brand);
        map.put("path",path);
        return map;

    }

    // /boss/1;age=20/2;age=10
    // pathVar：指定获取哪个路径下的值
    @GetMapping("/boss/{bossId}/{empId}")
    public Map boss(@MatrixVariable(value = "age",pathVar = "bossId") Integer bossAge,
                    @MatrixVariable(value = "age",pathVar = "empId") Integer empAge){

        Map<String,Object> map = new HashMap<>();
        map.put("bossAge",bossAge);
        map.put("empAge",empAge);
        return map;

    }
}
