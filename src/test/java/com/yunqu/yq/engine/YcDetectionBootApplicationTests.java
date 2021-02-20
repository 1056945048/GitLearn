package com.yunqu.yq.engine;

import com.yunqu.yq.engine.dao.ZyTaskDao;
import com.yunqu.yq.engine.entity.ZyTask;
import com.yunqu.yq.engine.service.ZyTaskService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.UUID;

@SpringBootTest
class YcDetectionBootApplicationTests {

	@Resource
	ZyTaskDao zyTaskDao;
	@Resource
	ZyTaskService zyTaskService;

	@Test
	void contextLoads() {
		ZyTask zyTask = ZyTask.builder().build();
		zyTask.setId(UUID.randomUUID().toString());
		System.out.println(zyTaskDao.insert(zyTask));
	}

	@Test
	void pageSearch() {
		System.out.println(zyTaskService.queryAllByLimit(1,5,ZyTask.builder().build()));
	}
}
