package com.yunqu.yq.engine.dao;

import com.yunqu.yq.engine.entity.ZyTask;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (ZyTask)表数据库访问层
 *
 * @author Bianhl
 * @since 2020-05-22 00:09:22
 */
@Mapper
public interface ZyTaskDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ZyTask queryById(String id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<ZyTask> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param zyTask 实例对象
     * @return 对象列表
     */
    List<ZyTask> queryAll(ZyTask zyTask);

    /**
     * 新增数据
     *
     * @param zyTask 实例对象
     * @return 影响行数
     */
    int insert(ZyTask zyTask);

    /**
     * 修改数据
     *
     * @param zyTask 实例对象
     * @return 影响行数
     */
    int update(ZyTask zyTask);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(String id);

}
