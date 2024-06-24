package com.tutego.date4u.interfaces;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Date4uWebController {

    @RequestMapping( "/**" )
    public String indexPage() { return "index"; }

    @RequestMapping( "/profile" )
    public String profilePage() { return "profile"; }

    @RequestMapping( "/search" )
    public String searchPage() { return "search"; }
}