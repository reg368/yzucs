package com.question_level.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class Question_levelVO implements Serializable {
	Integer l_id;
	String l_level;
	Timestamp l_joindate;
	Integer l_group_id;
	Integer isVisible; //�O�_�}�� 0:���}�� 1:�}��
	Integer totalQNumber; //�`�@�n�ҴX�D
	Integer isRandom; //�X�D���ǬO�_�H�� 0 : ���H��  1: �H��
	Integer correctQNumber; //����X�D�L��
	Integer awardMoney; //�L����o������
	Integer awardExperience; //�L����o���g���
	Integer fromQuestion; //�X�D�d��q�� ..�D
	Integer toQuestion; //  �X�D�d����..�D
	
	
	public Integer getL_id() {
		return l_id;
	}
	public void setL_id(Integer l_id) {
		this.l_id = l_id;
	}
	public String getL_level() {
		return l_level;
	}
	public void setL_level(String l_level) {
		this.l_level = l_level;
	}
	public Timestamp getL_joindate() {
		return l_joindate;
	}
	public void setL_joindate(Timestamp l_joindate) {
		this.l_joindate = l_joindate;
	}
	public Integer getL_group_id() {
		return l_group_id;
	}
	public void setL_group_id(Integer l_group_id) {
		this.l_group_id = l_group_id;
	}
	public Integer getIsVisible() {
		return isVisible;
	}
	public void setIsVisible(Integer isVisible) {
		this.isVisible = isVisible;
	}
	public Integer getTotalQNumber() {
		return totalQNumber;
	}
	public void setTotalQNumber(Integer totalQNumber) {
		this.totalQNumber = totalQNumber;
	}
	public Integer getIsRandom() {
		return isRandom;
	}
	public void setIsRandom(Integer isRandom) {
		this.isRandom = isRandom;
	}
	public Integer getCorrectQNumber() {
		return correctQNumber;
	}
	public void setCorrectQNumber(Integer correctQNumber) {
		this.correctQNumber = correctQNumber;
	}
	public Integer getAwardMoney() {
		return awardMoney;
	}
	public void setAwardMoney(Integer awardMoney) {
		this.awardMoney = awardMoney;
	}
	public Integer getAwardExperience() {
		return awardExperience;
	}
	public void setAwardExperience(Integer awardExperience) {
		this.awardExperience = awardExperience;
	}
	public Integer getFromQuestion() {
		return fromQuestion;
	}
	public void setFromQuestion(Integer fromQuestion) {
		this.fromQuestion = fromQuestion;
	}
	public Integer getToQuestion() {
		return toQuestion;
	}
	public void setToQuestion(Integer toQuestion) {
		this.toQuestion = toQuestion;
	}
	
	
}
