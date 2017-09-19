package com.ijson.platform.database.db;

import com.ijson.platform.database.db.hibernate.DaoHibernateImpl;
import com.ijson.platform.database.model.MethodParam;
import com.ijson.platform.database.model.Page;

import java.util.List;


/**
 * description: 持久层的实现 可以重写此类的方法来满足特定的业务要求
 *
 * @author cuiyongxu 创建时间：Oct 27, 2015
 */
public abstract class DaoImpl implements IDao {

    protected DaoHibernateImpl hibernateDao;

    public long count(MethodParam param) {
        return hibernateDao.count(param);
    }

    /* (non-Javadoc)
     * @see com.persistenceLayer.core.db.IDao#delete(com.persistenceLayer.core.model.MethodParam)
     */
    public boolean delete(MethodParam param) {
        return hibernateDao.delete(param);
    }

    /* (non-Javadoc)
     * @see com.persistenceLayer.core.db.IDao#deleteBath(com.persistenceLayer.core.model.MethodParam)
     */
    public boolean deleteBath(MethodParam param) {
        return hibernateDao.deleteBath(param);
    }

    /* (non-Javadoc)
     * @see com.persistenceLayer.core.db.IDao#edit(com.persistenceLayer.core.model.MethodParam)
     */
    public boolean edit(MethodParam param) {
        return hibernateDao.edit(param);
    }

    /* (non-Javadoc)
     * @see com.persistenceLayer.core.db.IDao#editBath(com.persistenceLayer.core.model.MethodParam)
     */
    public boolean editBath(MethodParam param) {
        return hibernateDao.editBath(param);
    }

    /* (non-Javadoc)
     * @see com.persistenceLayer.core.db.IDao#insert(com.persistenceLayer.core.model.MethodParam)
     */
    public boolean insert(MethodParam param) {
        return hibernateDao.insert(param);
    }

    /* (non-Javadoc)
     * @see com.persistenceLayer.core.db.IDao#insertBath(com.persistenceLayer.core.model.MethodParam)
     */
    public boolean insertBath(MethodParam param) {
        return hibernateDao.insertBath(param);
    }

    /* (non-Javadoc)
     * @see com.persistenceLayer.core.db.IDao#pageSelect(com.persistenceLayer.core.model.MethodParam)
     */
    public Page pageSelect(MethodParam param) {
        return hibernateDao.pageSelect(param);
    }

    /* (non-Javadoc)
     * @see com.persistenceLayer.core.db.IDao#select(com.persistenceLayer.core.model.MethodParam)
     */
    public List select(MethodParam param) {
        return hibernateDao.select(param);
    }

    /* (non-Javadoc)
     * @see com.persistenceLayer.core.db.IDao#selectSingle(com.persistenceLayer.core.model.MethodParam)
     */
    public Object selectSingle(MethodParam param) {
        return hibernateDao.selectSingle(param);
    }

    public Object selectById(MethodParam param) {
        return hibernateDao.selectById(param.getSpanceName(), param.getKey(), param.getInfoId(), param.getCacheId());
    }

    public void setHibernateDao(DaoHibernateImpl hibernateDao) {
        this.hibernateDao = hibernateDao;
    }

    public List selectByObject(MethodParam param) {
        return hibernateDao.selectByObject(param);
    }

    public Object selectSingleByObject(MethodParam param) {
        return hibernateDao.selectSingleByObject(param);
    }

}
