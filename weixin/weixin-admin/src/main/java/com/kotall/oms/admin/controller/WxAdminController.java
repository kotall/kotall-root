package com.kotall.oms.admin.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zpwang
 * @version 1.0.0
 */
@Slf4j
@Controller
@RequestMapping("/")
public class WxAdminController {

    /**
     * 页面跳转
     * @param module
     * @param function
     * @param url
     * @return
     */
    @RequestMapping("{module}/{function}/{url}.html")
    public String page(@PathVariable("module") String module, @PathVariable("function") String function,
                       @PathVariable("url") String url) {
        return module + "/" + function + "/" + url;
    }

    /**
     * 页面跳转
     * @param module
     * @param url
     * @return
     */
    @RequestMapping("{module}/{url}.html")
    public String page(@PathVariable("module") String module, @PathVariable("url") String url) {
        return module + "/" + url;
    }


}
