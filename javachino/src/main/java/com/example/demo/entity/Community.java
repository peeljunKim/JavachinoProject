package com.example.demo.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "community")
@SequenceGenerator(
        name="COMMUNITY_SEQ_GEN",
        sequenceName = "COMMUNITY_SEQ",
		initialValue = 1,
		allocationSize = 1
) 
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Community {
	@Id
	@GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "COMMUNITY_SEQ_GEN"
    )
	@Column(name = "community_no")
	private int communityNo;
	@Column(name = "community_date")
	private Date communityDate;
	@Column(name = "community_title")
	private String communityTitle;
	@Column(name = "community_content", length = 4000)
	private String communityContent;
	@Column(name = "community_addr")
	private String communityAddr;
	@Column(name = "community_hit")
	private int communityHit;
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "community_category")
	private CommunityCategory communityCategory;
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "community_select")
	private CommunitySelect communitySelect;
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_no", foreignKey = @ForeignKey(name = "fk_community_to_users"))
	private Users users;
	
   @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "activity_rv_no", foreignKey = @ForeignKey(name = "fk_community_to_activity_rv"))
   private ActivityRv activityRv;
   @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accomodation_rv_no", foreignKey = @ForeignKey(name = "fk_community_to_accomodation_rv"))
   private AccomodationRV accomodationRv;
public int getCommunityNo() {
	return communityNo;
}
public void setCommunityNo(int communityNo) {
	this.communityNo = communityNo;
}
public Date getCommunityDate() {
	return communityDate;
}
public void setCommunityDate(Date communityDate) {
	this.communityDate = communityDate;
}
public String getCommunityTitle() {
	return communityTitle;
}
public void setCommunityTitle(String communityTitle) {
	this.communityTitle = communityTitle;
}
public String getCommunityContent() {
	return communityContent;
}
public void setCommunityContent(String communityContent) {
	this.communityContent = communityContent;
}
public String getCommunityAddr() {
	return communityAddr;
}
public void setCommunityAddr(String communityAddr) {
	this.communityAddr = communityAddr;
}
public int getCommunityHit() {
	return communityHit;
}
public void setCommunityHit(int communityHit) {
	this.communityHit = communityHit;
}
public CommunityCategory getCommunityCategory() {
	return communityCategory;
}
public void setCommunityCategory(CommunityCategory communityCategory) {
	this.communityCategory = communityCategory;
}
public CommunitySelect getCommunitySelect() {
	return communitySelect;
}
public void setCommunitySelect(CommunitySelect communitySelect) {
	this.communitySelect = communitySelect;
}
public Users getUsers() {
	return users;
}
public void setUsers(Users users) {
	this.users = users;
}
public ActivityRv getActivityRv() {
	return activityRv;
}
public void setActivityRv(ActivityRv activityRv) {
	this.activityRv = activityRv;
}
public AccomodationRV getAccomodationRv() {
	return accomodationRv;
}
public void setAccomodationRv(AccomodationRV accomodationRv) {
	this.accomodationRv = accomodationRv;
}
   
   
}
