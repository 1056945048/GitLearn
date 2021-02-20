package com.yunqu.yq.engine.service;

import com.github.pagehelper.PageInfo;
import com.yunqu.yq.engine.entity.ZyTask;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * (ZyTask)表服务接口
 *
 * @author Bianhl
 * @since 2020-05-20 22:36:00
 */
public interface ZyTaskService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ZyTask queryById(String id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
	PageInfo<ZyTask> queryAllByLimit(int offset, int limit, ZyTask zyTask);

    /**
     * 新增数据
     *
     * @param zyTask 实例对象
     * @return 实例对象
     */
    ZyTask insert(ZyTask zyTask);

    /**
     * 修改数据
     *
     * @param zyTask 实例对象
     * @return 实例对象
     */
    ZyTask update(ZyTask zyTask);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);

	/**
	 * 上传文件创建任务
	 * @param file
	 * @return
	 */
	boolean createTask(MultipartFile file) throws IOException;

}
