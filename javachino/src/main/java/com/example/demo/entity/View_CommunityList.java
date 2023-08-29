package com.example.demo.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="view_community_list")
public class View_CommunityList {
	
		@Id
		private int communityNo;
	    private CommunityCategory communityCategory;
	    private CommunitySelect communitySelect;
	    private String communityTitle;
	    private int communityHit;
	    private Date communityDate;
	    private String communityAddr;
	    private String usersName;
	    private int communityLike;
	    private int commentary;
		public int getCommunityNo() {
			return communityNo;
		}
		public void setCommunityNo(int communityNo) {
			this.communityNo = communityNo;
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
		public String getCommunityTitle() {
			return communityTitle;
		}
		public void setCommunityTitle(String communityTitle) {
			this.communityTitle = communityTitle;
		}
		public int getCommunityHit() {
			return communityHit;
		}
		public void setCommunityHit(int communityHit) {
			this.communityHit = communityHit;
		}
		public Date getCommunityDate() {
			return communityDate;
		}
		public void setCommunityDate(Date communityDate) {
			this.communityDate = communityDate;
		}
		public String getCommunityAddr() {
			return communityAddr;
		}
		public void setCommunityAddr(String communityAddr) {
			this.communityAddr = communityAddr;
		}
		public String getUsersName() {
			return usersName;
		}
		public void setUsersName(String usersName) {
			this.usersName = usersName;
		}
		public int getCommunityLike() {
			return communityLike;
		}
		public void setCommunityLike(int communityLike) {
			this.communityLike = communityLike;
		}
		public int getCommentary() {
			return commentary;
		}
		public void setCommentary(int commentary) {
			this.commentary = commentary;
		}
	    
	    
}

