package com.moss.fileextensionblock.userinterface;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.info.BuildProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private String version;
    private final BuildProperties buildProperties;

    @PostConstruct
    public void afterPropertiesSet() {
        this.version = buildProperties.getVersion();
    }

    @GetMapping(value={"*"})
    public ModelAndView index() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("index");
        mav.addObject("version", version);
        return mav;
    }
}