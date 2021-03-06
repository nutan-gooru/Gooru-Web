package org.ednovo.gooru.shared.model.content;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class ClasspageItemDo implements Serializable{
	
	private static final long serialVersionUID = 1L;

	public ClasspageItemDo(){}
	
	private String plannedEndDate;
	private Integer totalHitCount;
	private String collectionTitle;
	private String direction; // narration from API
	private String goal;
	private String thumbnailUrl;
	private String collectionId;
	private String collectionItemId;
	private String title;
	private String userNameDispaly;
	private String classpageId;
	
	
	public String getUserNameDispaly() {
		return userNameDispaly;
	}
	public void setUserNameDispaly(String userNameDispaly) {
		this.userNameDispaly = userNameDispaly;
	}
	public String getProfileImageUrl() {
		return profileImageUrl;
	}
	public void setProfileImageUrl(String profileImageUrl) {
		this.profileImageUrl = profileImageUrl;
	}

	private String profileImageUrl;
	
	/**
	 * @return the plannedEndDate
	 */
	public String getPlannedEndDate() {
		return plannedEndDate;
	}
	/**
	 * @param plannedEndDate the plannedEndDate to set
	 */
	public void setPlannedEndDate(String plannedEndDate) {
		this.plannedEndDate = plannedEndDate;
	}
	/**
	 * @return the totalHitCount
	 */
	public Integer getTotalHitCount() {
		return totalHitCount;
	}
	/**
	 * @param totalHitCount the totalHitCount to set
	 */
	public void setTotalHitCount(Integer totalHitCount) {
		this.totalHitCount = totalHitCount;
	}
	/**
	 * @return the collectionTitle
	 */
	public String getCollectionTitle() {
		return collectionTitle;
	}
	/**
	 * @param collectionTitle the collectionTitle to set
	 */
	public void setCollectionTitle(String collectionTitle) {
		this.collectionTitle = collectionTitle;
	}
	/**
	 * @return the direction
	 */
	public String getDirection() {
		return direction;
	}
	/**
	 * @param direction the direction to set
	 */
	public void setDirection(String direction) {
		this.direction = direction;
	}
	/**
	 * @return the goal
	 */
	public String getGoal() {
		return goal;
	}
	/**
	 * @param goal the goal to set
	 */
	public void setGoal(String goal) {
		this.goal = goal;
	}
	/**
	 * @return the thumbnailUrl
	 */
	public String getThumbnailUrl() {
		return thumbnailUrl;
	}
	/**
	 * @param thumbnailUrl the thumbnailUrl to set
	 */
	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}
	/**
	 * @return the collectionId
	 */
	public String getCollectionId() {
		return collectionId;
	}
	/**
	 * @param collectionId the collectionId to set
	 */
	public void setCollectionId(String collectionId) {
		this.collectionId = collectionId;
	}
	public String getCollectionItemId() {
		return collectionItemId;
	}
	public void setCollectionItemId(String collectionItemId) {
		this.collectionItemId = collectionItemId;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	public String getClasspageId() {
		return classpageId;
	}
	public void setClasspageId(String classpageId) {
		this.classpageId = classpageId;
	}
	

}
