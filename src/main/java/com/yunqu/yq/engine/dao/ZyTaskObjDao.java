package com.yunqu.yq.engine.dao;

import com.yunqu.yq.engine.entity.ZyTaskObj;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (ZyTaskObj)表数据库访问层
 *
 * @author Bianhl
 * @since 2020-05-22 01:01:29
 */
@Mapper
public interface ZyTaskObjDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ZyTaskObj queryById(String id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<ZyTaskObj> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit, @Param("taskId") String taskId);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param zyTaskObj 实例对象
     * @return 对象列表
     */
    List<ZyTaskObj> queryAll(ZyTaskObj zyTaskObj);

    /**
     * 新增数据
     *
     * @param zyTaskObj 实例对象
     * @return 影响行数
     */
    int insert(ZyTaskObj zyTaskObj);

    /**
     * 修改数据
     *
     * @param zyTaskObj 实例对象
     * @return 影响行数
     */
    int update(ZyTaskObj zyTaskObj);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(String id);

    /**
     * 通过任务主键删除数据
     *
     * @param taskId 任务主键
     * @return 影响行数
     */
    int deleteByTaskId(String taskId);

}
