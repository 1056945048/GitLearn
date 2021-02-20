package com.yunqu.yq.engine.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yangkun
 * @date 2021-02-20
 */
@RestController
public class MyselfTestController {
    @RequestMapping("/branch/dev")
    public String GitBranchTest(){
        return "dev分支测试";
    }
}
