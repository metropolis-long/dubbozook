package org.bamboo.controller;


import org.bamboo.service.AddService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/third")
public class AddCtrl {

    @Autowired
    private AddService addService;

    @GetMapping("/add")
    public Object add(){
       return addService.add(1,2);
    }

}
