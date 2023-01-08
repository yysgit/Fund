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
 * 
 * </p>
 *
 * @author yys
 * @since 2022-12-03
 */

public class FFundInfo {

    private static final long serialVersionUID = 1L;

    /**
     * 更新人ID
     */
	private Integer updateUserId;
    /**
     * 创建人ID
     */
	private Integer createUserId;
    /**
     * 删除状态: 0 正常, 1 删除
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
     * 排序
     */
	private Integer sorting;
    /**
     * 最大净值
     */
	private Double maxNetWorth;
	private Double volatilityValue;
    /**
     * 最大净值日期
     */
	private Date maxNetWorthDate;
    /**
     * 基金代码
     */
	private String fundCode;
    /**
     * 基金名称
     */
	private String fundName;
	private Integer fundTypeId;
    /**
     * 主键ID
     */
	private Integer id;

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

	public Double getVolatilityValue() {
		return volatilityValue;
	}

	public void setVolatilityValue(Double volatilityValue) {
		this.volatilityValue = volatilityValue;
	}

	public Double getMaxNetWorth() {
		return maxNetWorth;
	}

	public void setMaxNetWorth(Double maxNetWorth) {
		this.maxNetWorth = maxNetWorth;
	}

	public Date getMaxNetWorthDate() {
		return maxNetWorthDate;
	}

	public void setMaxNetWorthDate(Date maxNetWorthDate) {
		this.maxNetWorthDate = maxNetWorthDate;
	}




	public String getFundCode() {
		return fundCode;
	}

	public void setFundCode(String fundCode) {
		this.fundCode = fundCode;
	}

	public String getFundName() {
		return fundName;
	}

	public void setFundName(String fundName) {
		this.fundName = fundName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSorting() {
		return sorting;
	}

	public void setSorting(Integer sorting) {
		this.sorting = sorting;
	}

	public Integer getFundTypeId() {
		return fundTypeId;
	}

	public void setFundTypeId(Integer fundTypeId) {
		this.fundTypeId = fundTypeId;
	}
}
