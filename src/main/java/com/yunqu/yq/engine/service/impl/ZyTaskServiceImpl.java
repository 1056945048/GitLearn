package com.yunqu.yq.engine.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yunqu.yq.engine.dao.ZyTaskObjDao;
import com.yunqu.yq.engine.entity.ZyTask;
import com.yunqu.yq.engine.dao.ZyTaskDao;
import com.yunqu.yq.engine.entity.ZyTaskObj;
import com.yunqu.yq.engine.service.ZyTaskService;
import com.yunqu.yq.engine.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

/**
 * (ZyTask)表服务实现类
 *
 * @author Bianhl
 * @since 2020-05-20 22:36:00
 */
@Slf4j
@Service("zyTaskService")
public class ZyTaskServiceImpl implements ZyTaskService {
    @Resource
    private ZyTaskDao zyTaskDao;
    @Resource
	private ZyTaskObjDao zyTaskObjDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public ZyTask queryById(String id) {
        return this.zyTaskDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public PageInfo<ZyTask> queryAllByLimit(int pageIndex, int pageCount,ZyTask zyTask) {
		//设置分页信息
		PageHelper.startPage(pageIndex, pageCount);
		List<ZyTask> list = zyTaskDao.queryAll(zyTask);
		//生成分页信息对象
		PageInfo<ZyTask> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    /**
     * 新增数据
     *
     * @param zyTask 实例对象
     * @return 实例对象
     */
    @Override
    public ZyTask insert(ZyTask zyTask) {
        this.zyTaskDao.insert(zyTask);
        return zyTask;
    }

    /**
     * 修改数据
     *
     * @param zyTask 实例对象
     * @return 实例对象
     */
    @Override
    public ZyTask update(ZyTask zyTask) {
        this.zyTaskDao.update(zyTask);
        return this.queryById(zyTask.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
    	zyTaskObjDao.deleteByTaskId(id);
        return this.zyTaskDao.deleteById(id) > 0;
    }

	@Override
	@Transactional (propagation = Propagation.REQUIRED,isolation= Isolation.DEFAULT)
	public boolean createTask(MultipartFile file) throws IOException {
    	String fileName = file.getOriginalFilename();
    	String taskId = UUID.randomUUID().toString();
    	ZyTask task = ZyTask.builder()
				.taskName(fileName)
				.id(taskId)
				.taskState("0")
				.dateId(DateUtils.getDateId())
				.createTime(DateUtils.now())
				.build();
    	zyTaskDao.insert(task);
    	try(InputStream is = file.getInputStream();
			Workbook workbook = WorkbookFactory.create(is)){
			//工作表对象
			Sheet sheet = workbook.getSheetAt(0);
			//总行数
			int rowLength = sheet.getLastRowNum() -1;
			//工作表的列
			for (int i = 0; i < rowLength; i++) {
				Row row = sheet.getRow(i);
				Cell cell = row.getCell(0);
				cell.setCellType(CellType.STRING);
				String num = cell.getStringCellValue();
				addTaskObj(num,taskId);
			}
			return true;
		} catch (Exception e) {
    		log.error(e.getMessage(),e);
    		throw e;
		}
	}

	private void addTaskObj(String num,String taskId) {
		ZyTaskObj obj = ZyTaskObj.builder()
				.id(UUID.randomUUID().toString())
				.isSearched(0)
				.markNum(0)
				.phoneNum(num)
				.taskId(taskId)
				.optTime(DateUtils.now())
				.build();
		zyTaskObjDao.insert(obj);
	}
}
