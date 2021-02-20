package com.yunqu.yq.engine.controller;

import com.github.pagehelper.PageInfo;
import com.yunqu.yq.engine.entity.ZyTask;
import com.yunqu.yq.engine.helper.EasyResult;
import com.yunqu.yq.engine.service.ZyTaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * (ZyTask)表控制层
 *
 * @author Bianhl
 * @since 2020-05-20 22:36:00
 */
@Slf4j
@RestController
public class ZyTaskController {
    /**
     * 服务对象
     */
    @Resource
    private ZyTaskService zyTaskService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/zyTask/selectOne")
    public ZyTask selectOne(String id) {
        return this.zyTaskService.queryById(id);
    }

	@RequestMapping("/zyTask/queryForPage")
    public PageInfo<ZyTask> queryForPage(@RequestParam("page")int page,@RequestParam("limit")int limit,@RequestParam("taskName")String taskName) {
    	ZyTask task = ZyTask.builder().build();
    	task.setTaskName(taskName);
		return zyTaskService.queryAllByLimit(page,limit,task);
	}

	@RequestMapping("/zyTask/deleteTask")
	public EasyResult deleteTask(@RequestParam("id") String id) {
		boolean flag = zyTaskService.deleteById(id);
		if(flag) {
			return EasyResult.ok();
		}
		return EasyResult.fail();
	}

	@RequestMapping("/task/uploadFile")
	public EasyResult uploadFile(@RequestParam("file")MultipartFile file) {
		try {
			boolean flag = zyTaskService.createTask(file);
			if(flag) return EasyResult.ok();
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
    	return EasyResult.fail("创建任务失败！");
	}

	/**
	 * 启动任务
	 * @param taskId
	 * @return
	 */
	@RequestMapping("/zyTask/startJob")
	public EasyResult startJob(@RequestParam("taskId")String taskId) {
    	try {
			ZyTask task = ZyTask.builder().id(taskId).taskState("1").build();
			zyTaskService.update(task);
			return EasyResult.ok();
    	} catch (Exception e) {
    		log.error(e.getMessage(),e);
    	}
    	return EasyResult.fail();
	}
}
