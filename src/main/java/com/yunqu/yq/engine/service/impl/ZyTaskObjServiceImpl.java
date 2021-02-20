package com.yunqu.yq.engine.service.impl;

import com.yunqu.yq.engine.entity.ZyTaskObj;
import com.yunqu.yq.engine.dao.ZyTaskObjDao;
import com.yunqu.yq.engine.service.ZyTaskObjService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (ZyTaskObj)表服务实现类
 *
 * @author Bianhl
 * @since 2020-05-20 22:45:38
 */
@Service("zyTaskObjService")
public class ZyTaskObjServiceImpl implements ZyTaskObjService {
    @Resource
    private ZyTaskObjDao zyTaskObjDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public ZyTaskObj queryById(String id) {
        return this.zyTaskObjDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    /*@Override
    public List<ZyTaskObj> queryAllByLimit(int offset, int limit) {
        return this.zyTaskObjDao.queryAllByLimit(offset, limit);
    }*/

    /**
     * 新增数据
     *
     * @param zyTaskObj 实例对象
     * @return 实例对象
     */
    @Override
    public ZyTaskObj insert(ZyTaskObj zyTaskObj) {
        this.zyTaskObjDao.insert(zyTaskObj);
        return zyTaskObj;
    }

    /**
     * 修改数据
     *
     * @param zyTaskObj 实例对象
     * @return 实例对象
     */
    @Override
    public ZyTaskObj update(ZyTaskObj zyTaskObj) {
        this.zyTaskObjDao.update(zyTaskObj);
        return this.queryById(zyTaskObj.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.zyTaskObjDao.deleteById(id) > 0;
    }
}
