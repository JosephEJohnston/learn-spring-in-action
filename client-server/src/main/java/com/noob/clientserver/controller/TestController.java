package com.noob.clientserver.controller;

import com.noob.commons.service.TestDependencyService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class TestController {

    /*
     * https://blog.csdn.net/u014365523/article/details/112385298
     * SpringBoot项目的Bean装配默认规则是根据Application类所在的包位置从上往下扫描！Application类是指SpringBoot项目入口类。
     * 这个类的位置很关键： 如果 Application 类所在的包为：com.openplat，则只会扫描 com.openplat 包及其所有子包，
     * 如果 mapper、service 所在包不在 com.openplat 及其子包下，则不会被扫描。
     */

    /*private final TestDependencyService testDependencyService;

    public TestController(TestDependencyService testDependencyService) {
        this.testDependencyService = testDependencyService;
    }

    @GetMapping
    public ResponseEntity<String> test() {
        testDependencyService.doTest();

        return ResponseEntity.ok("success");
    }*/
}
