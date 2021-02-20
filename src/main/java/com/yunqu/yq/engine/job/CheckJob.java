package com.yunqu.yq.engine.job;

import com.alibaba.fastjson.JSONObject;
import com.yunqu.yq.engine.dao.ZyTaskDao;
import com.yunqu.yq.engine.dao.ZyTaskObjDao;
import com.yunqu.yq.engine.entity.ZyTask;
import com.yunqu.yq.engine.entity.ZyTaskObj;
import com.yunqu.yq.engine.utils.DateUtils;
import com.yunqu.yq.engine.utils.MobileInfoUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: Bianhl
 * @Date: 2020/5/22 1:43
 */
@Slf4j
@Component
public class CheckJob {

	@Resource ZyTaskDao zyTaskDao;
	@Resource ZyTaskObjDao zyTaskObjDao;

	private static boolean IS_RUNNING = false;

	@Scheduled(cron = "0 0/1 * * * ?")
	@Async("asyncServiceExecutor")
	public void checkPhoneJob() {
		if(IS_RUNNING) return ;
		try {
			IS_RUNNING = true;
			List<ZyTask> list=zyTaskDao.queryAll(ZyTask.builder().taskState("1").build());
			if(list==null || list.size()==0) return;
			for(ZyTask task : list) {
				List<ZyTaskObj> objs = zyTaskObjDao.queryAllByLimit(0,100,task.getId());
				if(objs==null || objs.size()==0) {
					task.setTaskState("2");
					zyTaskDao.update(task);
					continue;
				};
				checkNum(objs);
			}
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		} finally {
			IS_RUNNING = false;
		}
	}

	private void checkNum(List<ZyTaskObj> objs) {
		for(ZyTaskObj obj : objs) {
			try {
				Thread.currentThread().sleep(1000*5);
			} catch (Exception e) {
				log.error(e.getMessage(),e);
			}
			JSONObject json = MobileInfoUtils.so(obj.getPhoneNum());
			if(json==null) continue;
			obj.setMarkNum(json.getIntValue("count"));
			obj.setOptTime(DateUtils.now());
			obj.setMemo(json.getString("source")+">>>"+json.getString("type"));
			obj.setIsSearched(1);
			zyTaskObjDao.update(obj);
		}
	}
}
