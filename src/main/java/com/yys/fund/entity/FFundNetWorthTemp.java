package com.yys.fund.entity;

import java.util.Date;

/**
 * <p>
 * 基金最新净值
 * </p>
 *
 * @author yys
 * @since 2022-12-03
 */
public class FFundNetWorthTemp {

    private static final long serialVersionUID = 1L;


	private Integer id;

	private String fundInfoCode;
    /**
     * 当天
     */

	private Date fundDay;
	/**
	 * 当前等级次数
	 */
	private Integer levelNumber;
	/**
	 * 当前等级前净值
	 */
	private Double levelFront;
	/**
	 * 当前等级后净值
	 */
	private Double levelBehind;

    /**
     * 当天净值
     */

	private Double fundNetWorth;
	/**
	 * 涨跌
	 */
	private Double riseFall;

	public Integer getLevelNumber() {
		return levelNumber;
	}

	public void setLevelNumber(Integer levelNumber) {
		this.levelNumber = levelNumber;
	}

	public Double getLevelFront() {
		return levelFront;
	}

	public void setLevelFront(Double levelFront) {
		this.levelFront = levelFront;
	}

	public Double getLevelBehind() {
		return levelBehind;
	}

	public void setLevelBehind(Double levelBehind) {
		this.levelBehind = levelBehind;
	}

	public Double getRiseFall() {
		return riseFall;
	}

	public void setRiseFall(Double riseFall) {
		this.riseFall = riseFall;
	}

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

	public Date getFundDay() {
		return fundDay;
	}

	public void setFundDay(Date fundDay) {
		this.fundDay = fundDay;
	}

	public Double getFundNetWorth() {
		return fundNetWorth;
	}

	public void setFundNetWorth(Double fundNetWorth) {
		this.fundNetWorth = fundNetWorth;
	}
}
