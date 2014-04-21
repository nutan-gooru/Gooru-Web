package org.ednovo.gooru.shared.model.content;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class ReactionDo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String assocGooruOid;
	private String deleteReactionGooruOid=null;
	private String reactionText;
	
	public ReactionDo(){}
	/**
	 * @return the assocGooruOid
	 */
	public String getAssocGooruOid() {
		return assocGooruOid;
	}
	/**
	 * @param assocGooruOid the assocGooruOid to set
	 */
	public void setAssocGooruOid(String assocGooruOid) {
		this.assocGooruOid = assocGooruOid;
	}
	
	/**
	 * @return the reactionText
	 */
	public String getReactionText() {
		return reactionText;
	}
	/**
	 * @param reactionText the reactionText to set
	 */
	public void setReactionText(String reactionText) {
		this.reactionText = reactionText;
	}
	/**
	 * @return the deleteReactionGooruOid
	 */
	public String getDeleteReactionGooruOid() {
		return deleteReactionGooruOid;
	}
	/**
	 * @param deleteReactionGooruOid the deleteReactionGooruOid to set
	 */
	public void setDeleteReactionGooruOid(String deleteReactionGooruOid) {
		this.deleteReactionGooruOid = deleteReactionGooruOid;
	}
	
	
	
	
}
