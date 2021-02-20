package com.yunqu.yq.engine.service;

import com.yunqu.yq.engine.entity.ZyTaskObj;
import java.util.List;

/**
 * (ZyTaskObj)表服务接口
 *
 * @author Bianhl
 * @since 2020-05-20 22:45:38
 */
public interface ZyTaskObjService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ZyTaskObj queryById(String id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    //List<ZyTaskObj> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param zyTaskObj 实例对象
     * @return 实例对象
     */
    ZyTaskObj insert(ZyTaskObj zyTaskObj);

    /**
     * 修改数据
     *
     * @param zyTaskObj 实例对象
     * @return 实例对象
     */
    ZyTaskObj update(ZyTaskObj zyTaskObj);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);

}
