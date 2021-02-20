package com.yunqu.yq.engine.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: Bianhl
 * @Date: 2020/5/20 23:09
 */
@Controller
public class MappingController {
	@RequestMapping("/task")
	public String taskList() {
		return "task/task-index";
	}

	@RequestMapping("/addTask")
	public String addTask() {
		return "task/task-add";
	}
}
