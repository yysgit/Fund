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
 * 交易流水
 * </p>
 *
 * @author yys
 * @since 2022-12-03
 */
public class UFundTransaction {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
	private Integer id;
    /**
     * 基金编号
     */
	private String fundInfoCode;
    /**
     * 买入金额
     */
	private Double purchaseAmount;
    /**
     * 买入净值

     */
	private Double purchaseNetWorth;
    /**
     * 买入份额
     */
	private Double purchaseShare;
    /**
     * 买入手续费
     */
	private Double purchaseCommission;
    /**
     * 买入时间

     */
	private Date purchaseTime;
    /**
     * 卖出金额
     */
	private Double sellAmount;
    /**
     * 卖出净值

     */
	private Double sellNetWorth;
    /**
     * 卖出手续费
     */
	private Double sellCommission;
    /**
     * 卖出份额
     */
	private Double sellShare;
    /**
     * 卖出时间

     */
	private Date sellTime;
    /**
     * 卖出状态: 0 未卖出, 1 已卖出
     */
	private Integer state;
    /**
     * 用户ID

     */
	private Integer userId;
    /**
     * 收益率
     */
	private Double yield;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFundInfoCode() {
		return fundInfoCode;
	}

	public void setFundInfoCode(String fundInfoCode) {
		this.fundInfoCode = fundInfoCode;
	}

	public Double getPurchaseAmount() {
		return purchaseAmount;
	}

	public void setPurchaseAmount(Double purchaseAmount) {
		this.purchaseAmount = purchaseAmount;
	}

	public Double getPurchaseNetWorth() {
		return purchaseNetWorth;
	}

	public void setPurchaseNetWorth(Double purchaseNetWorth) {
		this.purchaseNetWorth = purchaseNetWorth;
	}

	public Double getPurchaseShare() {
		return purchaseShare;
	}

	public void setPurchaseShare(Double purchaseShare) {
		this.purchaseShare = purchaseShare;
	}

	public Double getPurchaseCommission() {
		return purchaseCommission;
	}

	public void setPurchaseCommission(Double purchaseCommission) {
		this.purchaseCommission = purchaseCommission;
	}

	public Date getPurchaseTime() {
		return purchaseTime;
	}

	public void setPurchaseTime(Date purchaseTime) {
		this.purchaseTime = purchaseTime;
	}

	public Double getSellAmount() {
		return sellAmount;
	}

	public void setSellAmount(Double sellAmount) {
		this.sellAmount = sellAmount;
	}

	public Double getSellNetWorth() {
		return sellNetWorth;
	}

	public void setSellNetWorth(Double sellNetWorth) {
		this.sellNetWorth = sellNetWorth;
	}

	public Double getSellCommission() {
		return sellCommission;
	}

	public void setSellCommission(Double sellCommission) {
		this.sellCommission = sellCommission;
	}

	public Double getSellShare() {
		return sellShare;
	}

	public void setSellShare(Double sellShare) {
		this.sellShare = sellShare;
	}

	public Date getSellTime() {
		return sellTime;
	}

	public void setSellTime(Date sellTime) {
		this.sellTime = sellTime;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Double getYield() {
		return yield;
	}

	public void setYield(Double yield) {
		this.yield = yield;
	}
}
