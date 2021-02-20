package com.yunqu.yq.engine.controller;

import com.yunqu.yq.engine.entity.ZyTaskObj;
import com.yunqu.yq.engine.service.ZyTaskObjService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (ZyTaskObj)表控制层
 *
 * @author Bianhl
 * @since 2020-05-20 22:45:38
 */
@RestController
@RequestMapping("zyTaskObj")
public class ZyTaskObjController {
    /**
     * 服务对象
     */
    @Resource
    private ZyTaskObjService zyTaskObjService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public ZyTaskObj selectOne(String id) {
        return this.zyTaskObjService.queryById(id);
    }

}