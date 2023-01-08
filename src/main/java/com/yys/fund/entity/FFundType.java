package com.yys.fund.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 基金类型
 * </p>
 *
 * @author yys
 * @since 2022-12-03
 */
public class FFundType {

    private static final long serialVersionUID = 1L;

    /**
     * 是否删除: 0 未删除 ; 1 已删除
     */

	private Integer deleteStatus;
    /**
     * 更新时间
     */

	private Date updateTime;
    /**
     * 创建时间
     */
	private Date createTime;
    /**
     * 更新人ID
     */
	private Integer updateUserId;
    /**
     * 创建人ID
     */
	private Integer createUserId;
    /**
     * 类型名称
     */
	private String fundTypeName;
    /**
     * 编号
     */
	private Integer fundTypeNumber;
	/**
	 * 排序
	 */
	private Integer sorting;
    /**
     * 主键ID
     */
	private Integer id;

	public Integer getDeleteStatus() {
		return deleteStatus;
	}

	public void setDeleteStatus(Integer deleteStatus) {
		this.deleteStatus = deleteStatus;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getUpdateUserId() {
		return updateUserId;
	}

	public void setUpdateUserId(Integer updateUserId) {
		this.updateUserId = updateUserId;
	}

	public Integer getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}

	public String getFundTypeName() {
		return fundTypeName;
	}

	public void setFundTypeName(String fundTypeName) {
		this.fundTypeName = fundTypeName;
	}

	public Integer getFundTypeNumber() {
		return fundTypeNumber;
	}

	public void setFundTypeNumber(Integer fundTypeNumber) {
		this.fundTypeNumber = fundTypeNumber;
	}

	public Integer getSorting() {
		return sorting;
	}

	public void setSorting(Integer sorting) {
		this.sorting = sorting;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
