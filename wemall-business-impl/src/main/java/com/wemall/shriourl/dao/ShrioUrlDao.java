package com.wemall.shriourl.dao;

import com.wemall.shriourl.entity.ShrioUrl;

public interface ShrioUrlDao {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_shiro_url
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_shiro_url
     *
     * @mbggenerated
     */
    int insert(ShrioUrl record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_shiro_url
     *
     * @mbggenerated
     */
    int insertSelective(ShrioUrl record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_shiro_url
     *
     * @mbggenerated
     */
    ShrioUrl selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_shiro_url
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(ShrioUrl record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_shiro_url
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(ShrioUrl record);
}