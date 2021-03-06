/*******************************************************************************
 * Copyright 2013 Ednovo d/b/a Gooru. All rights reserved.
 * 
 *  http://www.goorulearning.org/
 * 
 *  Permission is hereby granted, free of charge, to any person obtaining
 *  a copy of this software and associated documentation files (the
 *  "Software"), to deal in the Software without restriction, including
 *  without limitation the rights to use, copy, modify, merge, publish,
 *  distribute, sublicense, and/or sell copies of the Software, and to
 *  permit persons to whom the Software is furnished to do so, subject to
 *  the following conditions:
 * 
 *  The above copyright notice and this permission notice shall be
 *  included in all copies or substantial portions of the Software.
 * 
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 *  EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 *  MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 *  NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 *  LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 *  OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 *  WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 ******************************************************************************/
package org.ednovo.gooru.client.mvp.play.resource.body;


import org.ednovo.gooru.client.PlaceTokens;
import org.ednovo.gooru.client.gin.AppClientFactory;
import org.ednovo.gooru.client.gin.BaseViewWithHandlers;
import org.ednovo.gooru.client.mvp.home.LoginPopupUc;
import org.ednovo.gooru.client.mvp.play.collection.body.GwtEarthWidget;
import org.ednovo.gooru.client.mvp.play.resource.framebreaker.ResourceFrameBreakerView;
import org.ednovo.gooru.client.ui.HTMLEventPanel;
import org.ednovo.gooru.client.util.PlayerDataLogEvents;
import org.ednovo.gooru.shared.model.content.CollectionItemDo;
import org.ednovo.gooru.shared.model.content.ReactionDo;
import org.ednovo.gooru.shared.util.MessageProperties;
import org.ednovo.gooru.shared.util.ResourceImageUtil;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;

public class ResourcePlayerMetadataView extends BaseViewWithHandlers<ResourcePlayerMetadataUiHandlers> implements IsResourcePlayerMetadataView, MessageProperties{

	@UiField FlowPanel resourceWidgetContainer;
	@UiField
	static FlowPanel wrapperContainerField;
	@UiField Button forwardButton,backwardButton,selectedEmoticButton,canExplainEmoticButton,understandEmoticButton,mehEmoticButton,doNotUnderstandEmoticButton,needHelpButton;
	@UiField HTMLEventPanel emoticsContainer;
	@UiField HTMLPanel allEmoticsContainer,singleEmoticsContainer,collectionContainer;
	@UiField Label resourcePublisher,reactionToolTipOne,reactionToolTipTwo,reactionToolTipThree,reactionToolTipFour,reactionToolTipFive;
	@UiField
	static ResourcePlayerMetadataBundle playerStyle;
	@UiField HTML resourceTitleLbl;

	HandlerRegistration forwardButtonHandler=null, backwardButtonHandler=null;
	
	private static final String REACTION_CAN_UNDERSTAND = "i-can-understand";
	private static final String REACTION_CAN_EXPLAIN = "i-can-explain";
	private static final String REACTION_MEH = "meh";
	private static final String REACTION_DONOT_UNDERSTAND = "i-donot-understand";
	private static final String REACTION_NEED_HELP = "i-need-help";
	private static final String CREATE_PREVIEW_PLAYER_REACTION = "create-reaction-preview";
	private static final String CREATE_STUDY_PLAYER_REACTION = "create-reaction";
	private boolean isCanUnderstandSelected=false;
	private boolean isCanExplainSelected=false;
	private boolean isMehSelected=false;
	private boolean isDoNotUnderstandSelected=false;
	private boolean isNeedHelpSelected=false;
	private CollectionItemDo collectionItemDo=null;
	private String gooruReactionId="";
	private static ResourcePlayerMetadataViewUiBinder uiBinder = GWT.create(ResourcePlayerMetadataViewUiBinder.class);

	interface ResourcePlayerMetadataViewUiBinder extends UiBinder<Widget, ResourcePlayerMetadataView> {
	}

	@Inject
	public ResourcePlayerMetadataView(){
		setWidget(uiBinder.createAndBindUi(this));
		allEmoticsContainer.getElement().getStyle().setDisplay(Display.NONE);
		singleEmoticsContainer.getElement().getStyle().setDisplay(Display.BLOCK);
		emoticsContainer.addMouseOverHandler(new OnEmoticsMouseOver());
		emoticsContainer.addMouseOutHandler(new OnEmoticsMouseOut());
		reactionToolTipOne.setText(GL0581); 
		reactionToolTipTwo.setText(GL0582); 
		reactionToolTipThree.setText(GL0583); 
		reactionToolTipFour.setText(GL0584); 
		reactionToolTipFive.setText(GL0585); 
		
		if(AppClientFactory.getCurrentPlaceToken().equalsIgnoreCase(PlaceTokens.RESOURCE_PLAY)){
			collectionContainer.getElement().getStyle().setDisplay(Display.NONE);
		}
	}

	public void showResourceWidget(CollectionItemDo collectionItemDo){
		this.collectionItemDo = collectionItemDo;
		if(collectionItemDo.getResource().getTitle()!=null){
			resourceTitleLbl.setHTML(removeHtmlTags(collectionItemDo.getResource().getTitle()));
			
		}else{
			resourceTitleLbl.setHTML("");
		}
		if(collectionItemDo.getResource().getResourceSource()!=null){
			String attributionName=collectionItemDo.getResource().getResourceSource().getAttribution();
			attributionName = attributionName.trim();
			if(attributionName != null && !attributionName.equals("") && !attributionName.equals("null")){
				String resourceSource=collectionItemDo.getResource().getResourceSource().getAttribution();
				if((!collectionItemDo.getResource().getUrl().startsWith("https://docs.google.com"))&&(!collectionItemDo.getResource().getUrl().startsWith("http://docs.google.com"))){
					if(resourceSource.length() > 100){
					resourcePublisher.setText(GL0566+resourceSource.substring(0, 100)+"...");
					}else{
						resourcePublisher.setText(GL0566+resourceSource);
					}
				}else
				{
				resourcePublisher.setText("");	
				}
			}else{
				resourcePublisher.setText("");
			}
		}else{
			resourcePublisher.setText("");
		}

		if(forwardButton!=null){
			forwardButton.removeFromParent();
		}
		if(backwardButton!=null){
			backwardButton.removeFromParent();
		}
		previewResouceWidget(collectionItemDo);
	}

	public void showResourceWidget(PlaceRequest previousResourceRequest){	
		
		collectionContainer.setVisible(false);
		
		if(forwardButtonHandler!=null||backwardButtonHandler!=null){
			forwardButtonHandler.removeHandler();
			backwardButtonHandler.removeHandler();
		}
		forwardButton.getElement().getStyle().setDisplay(Display.NONE);
		forwardButtonHandler=forwardButton.addClickHandler(new ShowResourceView(previousResourceRequest));
		backwardButtonHandler=backwardButton.addClickHandler(new ShowResourceView(previousResourceRequest));
	}

	public void showResourceWidget(CollectionItemDo collectionItemDo,PlaceRequest nextResoruceRequest,PlaceRequest previousResourceRequest){
		if(AppClientFactory.isAnonymous()){
			setDefaultReaction();
		}
		if(collectionItemDo!=null){
			collectionContainer.setVisible(true);
			this.collectionItemDo = collectionItemDo;
			if(collectionItemDo.getResource().getTitle()!=null){
				int sequenceNumber=collectionItemDo.getItemSequence();
				resourceTitleLbl.setHTML(sequenceNumber+". "+removeHtmlTags(collectionItemDo.getResource().getTitle()));
			}else{
				resourceTitleLbl.setHTML("");
			}
			if(collectionItemDo.getResource().getResourceSource()!=null){
				if(collectionItemDo.getResource().getResourceSource().getAttribution()!=null){
					String resourceSource=collectionItemDo.getResource().getResourceSource().getAttribution();
					resourceSource = resourceSource.trim();
					if(resourceSource != null && !resourceSource.equals("") && !resourceSource.equals("null")){
						if((!collectionItemDo.getResource().getUrl().startsWith("https://docs.google.com"))&&(!collectionItemDo.getResource().getUrl().startsWith("http://docs.google.com"))){
							if(resourceSource.length() > 100){
							resourcePublisher.setText(GL0566+resourceSource.substring(0, 100)+"...");
							}else{
								resourcePublisher.setText(GL0566+resourceSource);
							}
						}else{resourcePublisher.setText("");
						}
					}else{
						resourcePublisher.setText("");
					}
				}else{
					resourcePublisher.setText("");
				}
			}else{
				resourcePublisher.setText("");
			}

			if(forwardButtonHandler!=null||backwardButtonHandler!=null){
				forwardButtonHandler.removeHandler();
				backwardButtonHandler.removeHandler();
			}
			forwardButton.getElement().getStyle().clearDisplay();
			previewResouceWidget(collectionItemDo);

			forwardButtonHandler=forwardButton.addClickHandler(new ShowResourceView(nextResoruceRequest));
			backwardButtonHandler=backwardButton.addClickHandler(new ShowResourceView(previousResourceRequest));
		}

	}

	public void previewResouceWidget(CollectionItemDo collectionItemDo){
		resourceWidgetContainer.clear();
		String resourceTypeName=collectionItemDo.getResource().getResourceType().getName();
		wrapperContainerField.getElement().getStyle().clearHeight();
		if(resourceTypeName.equalsIgnoreCase("video/youtube")){
			wrapperContainerField.getElement().getStyle().setHeight(525	, Unit.PX);
			resourceWidgetContainer.add(new FlashAndVideoPlayerWidget(ResourceImageUtil.getYoutubeVideoId(collectionItemDo.getResource().getUrl()), collectionItemDo.getStart(), collectionItemDo.getStop()));
		}else if(resourceTypeName.equalsIgnoreCase("animation/kmz")){
			resourceWidgetContainer.add(new GwtEarthWidget(collectionItemDo.getResource().getUrl()));
		}else if(resourceTypeName.equalsIgnoreCase("assessment-question")){
			getUiHandlers().showQuestionView(collectionItemDo);
		}else if(resourceTypeName.equalsIgnoreCase("resource/url")||resourceTypeName.equalsIgnoreCase("image/png")){
			if(collectionItemDo.getResource().getHasFrameBreaker()!=null&&collectionItemDo.getResource().getHasFrameBreaker().equals(true)||isProtocolMatched(collectionItemDo.getResource().getUrl())){
				resourceWidgetContainer.add(new ResourceFrameBreakerView(collectionItemDo));
			}else{
				if(resourceTypeName.equalsIgnoreCase("image/png")){
					final WebResourceWidget webResourceWidget=new WebResourceWidget(collectionItemDo.getResource().getAssetURI()+collectionItemDo.getResource().getFolder()+collectionItemDo.getResource().getUrl());
					resourceWidgetContainer.add(webResourceWidget);
				}
				else{
					if(!collectionItemDo.getResource().getUrl().startsWith("http"))
					{
						collectionItemDo.getResource().setUrl(collectionItemDo.getResource().getAssetURI()+collectionItemDo.getResource().getFolder()+collectionItemDo.getResource().getUrl());
					}
					final WebResourceWidget webResourceWidget=new WebResourceWidget(collectionItemDo.getResource().getUrl());
					resourceWidgetContainer.add(webResourceWidget);
				}
			}
		}else {
			String[] urlFormat = collectionItemDo.getResource().getUrl().split("\\.");
			String urlExtension = urlFormat[urlFormat.length - 1];
			if(urlExtension.equalsIgnoreCase("pdf")){
				String resourceSourceUrl="";
				if(!collectionItemDo.getResource().getUrl().substring(0, 4).equalsIgnoreCase("http")){
//					resourceSourceUrl = URL.encode(collectionItemDo.getResource().getUrl()).replaceAll("\\+", "%20");
					resourceSourceUrl = collectionItemDo.getResource().getUrl();
					resourceSourceUrl=collectionItemDo.getResource().getAssetURI()+collectionItemDo.getResource().getFolder()+resourceSourceUrl;
				}else{
					resourceSourceUrl=collectionItemDo.getResource().getUrl();
				}
				String signedFlag=resourceSourceUrl.contains("http")||resourceSourceUrl.contains("https")?"0":"1";
				String startPage=collectionItemDo.getStart()!=null?collectionItemDo.getStart():"1";
				resourceWidgetContainer.add(new WebResourceWidget(AppClientFactory.getLoggedInUser().getSettings().getDocViewerHome()+"?startPage="+startPage+"&endPage=&signedFlag="+signedFlag+"&oid="+collectionItemDo.getResource().getGooruOid()+"&appKey="+AppClientFactory.getLoggedInUser().getSettings().getDocViewerPoint()+"&url="+resourceSourceUrl));
			}
		}
	}
	
	/**
	 * On click, "canExplain" emotic will be set for a resource, provided user should be logged in or it demands for log in.
	 * @param clickEvent {@link ClickEvent}
	 */
	@UiHandler("canExplainEmoticButton")
	public void clickOnCanExplainEmoticButtonEmotic(ClickEvent clickEvent){
		if(AppClientFactory.isAnonymous()){
			showLoginPopupWidget();
		}else{
			if(isCanExplainSelected){
				canExplainEmoticButton.setStyleName(playerStyle.spriteType());
				canExplainEmoticButton.addStyleName(playerStyle.emoticon_i());
				getUiHandlers().deleteReaction(gooruReactionId);
				deleteReactionTriggerEvent();
				setDefaultReaction();
			}else{
				canExplainEmoticButton.setStyleName(playerStyle.spriteType());
				canExplainEmoticButton.addStyleName(playerStyle.emoticon_vi());
				deleteReactionTriggerEvent();
				createReaction(collectionItemDo.getResource().getGooruOid(),REACTION_CAN_EXPLAIN,gooruReactionId,AppClientFactory.getPlaceManager().getRequestParameter("id"),AppClientFactory.getCurrentPlaceToken());
				getUiHandlers().triggerCreateReactionEvent(collectionItemDo.getResource().getGooruOid(), REACTION_CAN_EXPLAIN,PlayerDataLogEvents.REACTION_CREATE);
			}
			 
		}
		
	}
	
	/**
	 * On click, "understand" emotic will be set for a resource, provided user should be logged in or it demands for log in.
	 * @param clickEvent {@link ClickEvent}
	 */
	@UiHandler("understandEmoticButton")
	public void clickOnUnderstandEmotic(ClickEvent clickEvent){
		if(AppClientFactory.isAnonymous()){
			showLoginPopupWidget();
		}else{
			/**
			 * If user clicks on selected emotic again, it will de-select and will call delete API, else it will highlight the selected emotic 
			 * and call Create API.
			 */
			if(isCanUnderstandSelected){
				understandEmoticButton.setStyleName(playerStyle.spriteType());
				understandEmoticButton.addStyleName(playerStyle.emoticon_ii());
				getUiHandlers().deleteReaction(gooruReactionId);
				deleteReactionTriggerEvent();
				setDefaultReaction();
			}else{
				understandEmoticButton.setStyleName(playerStyle.spriteType());
				understandEmoticButton.addStyleName(playerStyle.emoticon_vii());
				deleteReactionTriggerEvent();
				createReaction(collectionItemDo.getResource().getGooruOid(),REACTION_CAN_UNDERSTAND,gooruReactionId,AppClientFactory.getPlaceManager().getRequestParameter("id"),AppClientFactory.getCurrentPlaceToken());
				getUiHandlers().triggerCreateReactionEvent(collectionItemDo.getResource().getGooruOid(), REACTION_CAN_UNDERSTAND,PlayerDataLogEvents.REACTION_CREATE);
			}
		}
		
	}
	
	/**
	 * On click,"meh" emotic will be set for a resource, provided user should be logged in or it demands for log in.
	 * @param clickEvent {@link ClickEvent}
	 */
	@UiHandler("mehEmoticButton")
	public void clickOnMehEmoticButton(ClickEvent clickEvent){
		if(AppClientFactory.isAnonymous()){
			showLoginPopupWidget();
		}else{
			/**
			 * If user clicks on selected emotic again, it will de-select and will call delete API, else it will highlight the selected emotic 
			 * and call Create API.
			 */
			if(isMehSelected){
				mehEmoticButton.setStyleName(playerStyle.spriteType());
				mehEmoticButton.addStyleName(playerStyle.emoticon_viii());
				getUiHandlers().deleteReaction(gooruReactionId);
				deleteReactionTriggerEvent();
				setDefaultReaction();
			}else{
				mehEmoticButton.setStyleName(playerStyle.spriteType());
				mehEmoticButton.addStyleName(playerStyle.emoticon_v());
				deleteReactionTriggerEvent();
				createReaction(collectionItemDo.getResource().getGooruOid(),REACTION_MEH,gooruReactionId,AppClientFactory.getPlaceManager().getRequestParameter("id"),AppClientFactory.getCurrentPlaceToken());
				getUiHandlers().triggerCreateReactionEvent(collectionItemDo.getResource().getGooruOid(), REACTION_MEH,PlayerDataLogEvents.REACTION_CREATE);
			}
			
		}
		
	}
	
	/**
	 * On click, "doNotUnderstand" emotic will be set for a resource, provided user should be logged in or it demands for log in.
	 * @param clickEvent {@link ClickEvent}
	 */
	@UiHandler("doNotUnderstandEmoticButton")
	public void clickOnDoNotUnderstandEmoticButton(ClickEvent clickEvent){
		if(AppClientFactory.isAnonymous()){
			showLoginPopupWidget();
		}else{
			/**
			 * If user clicks on selected emotic again, it will de-select and will call delete API, else it will highlight the selected emotic 
			 * and call Create API.
			 */
			if(isDoNotUnderstandSelected){
				doNotUnderstandEmoticButton.setStyleName(playerStyle.spriteType());
				doNotUnderstandEmoticButton.addStyleName(playerStyle.emoticon_iii());
				getUiHandlers().deleteReaction(gooruReactionId);
				deleteReactionTriggerEvent();
				setDefaultReaction();
			}else{
				doNotUnderstandEmoticButton.setStyleName(playerStyle.spriteType());
				doNotUnderstandEmoticButton.addStyleName(playerStyle.emoticon_ix());
				deleteReactionTriggerEvent();
				createReaction(collectionItemDo.getResource().getGooruOid(),REACTION_DONOT_UNDERSTAND,gooruReactionId,AppClientFactory.getPlaceManager().getRequestParameter("id"),AppClientFactory.getCurrentPlaceToken());
				getUiHandlers().triggerCreateReactionEvent(collectionItemDo.getResource().getGooruOid(), REACTION_DONOT_UNDERSTAND,PlayerDataLogEvents.REACTION_CREATE);
			}
		}
	}
	
	/**
	 * On click, "needHelp" emotic will be set for a resource, provided user should be logged in or it demands for log in.
	 * @param clickEvent {@link ClickEvent}
	 */
	@UiHandler("needHelpButton")
	public void clickOnNeedHelpButtonEmoticButton(ClickEvent clickEvent){
		if(AppClientFactory.isAnonymous()){
			showLoginPopupWidget();
		}else{
			/**
			 * If user clicks on selected emotic again, it will de-select and will call delete API, else it will highlight the selected emotic 
			 * and call Create API.
			 */
			if(isNeedHelpSelected){
				needHelpButton.setStyleName(playerStyle.spriteType());
				needHelpButton.addStyleName(playerStyle.emoticon_iv());
				deleteReactionTriggerEvent();
				getUiHandlers().deleteReaction(gooruReactionId);
				setDefaultReaction();
			}else{
				needHelpButton.setStyleName(playerStyle.spriteType());
				needHelpButton.addStyleName(playerStyle.emoticon_x());
				deleteReactionTriggerEvent();
				createReaction(collectionItemDo.getResource().getGooruOid(),REACTION_NEED_HELP,gooruReactionId,AppClientFactory.getPlaceManager().getRequestParameter("id"),AppClientFactory.getCurrentPlaceToken());
				getUiHandlers().triggerCreateReactionEvent(collectionItemDo.getResource().getGooruOid(), REACTION_NEED_HELP,PlayerDataLogEvents.REACTION_CREATE);
			}
		}
	}

	public void deleteReactionTriggerEvent(){
		if(isCanUnderstandSelected){
			getUiHandlers().triggerCreateReactionEvent(collectionItemDo.getResource().getGooruOid(), REACTION_CAN_UNDERSTAND,PlayerDataLogEvents.REACTION_DELETE);
		}else if(isCanExplainSelected){
			getUiHandlers().triggerCreateReactionEvent(collectionItemDo.getResource().getGooruOid(), REACTION_CAN_EXPLAIN,PlayerDataLogEvents.REACTION_DELETE);
		}else if(isDoNotUnderstandSelected){
			getUiHandlers().triggerCreateReactionEvent(collectionItemDo.getResource().getGooruOid(), REACTION_DONOT_UNDERSTAND,PlayerDataLogEvents.REACTION_DELETE);
		}else if(isMehSelected){
			getUiHandlers().triggerCreateReactionEvent(collectionItemDo.getResource().getGooruOid(), REACTION_MEH,PlayerDataLogEvents.REACTION_DELETE);
		}else if(isNeedHelpSelected){
			getUiHandlers().triggerCreateReactionEvent(collectionItemDo.getResource().getGooruOid(), REACTION_NEED_HELP,PlayerDataLogEvents.REACTION_DELETE);
		}
	}
	private void showLoginPopupWidget() { 
		LoginPopupUc popup =new LoginPopupUc();
		popup.setWidgetMode("reactionWidget");
		popup.setGlassEnabled(true);
	}

	@Override
	public FlowPanel getResourceWidgetContainer(){
		return resourceWidgetContainer;
	}

	public class ShowResourceView implements ClickHandler{
		private PlaceRequest resourceRequest;
		public ShowResourceView(PlaceRequest resourceRequest){
			this.resourceRequest=resourceRequest;
		}
		@Override
		public void onClick(ClickEvent event) {
			AppClientFactory.getPlaceManager().revealPlace(false, resourceRequest,true);
		}
	}

	/*
	 * Inner Class
	 */
	public class OnEmoticsMouseOver implements MouseOverHandler{

		/**
		 * On mouse over emotics options will be shown for the user.  
		 */
		
		@Override
		public void onMouseOver(MouseOverEvent event) {
			allEmoticsContainer.getElement().getStyle().setDisplay(Display.BLOCK);
			singleEmoticsContainer.getElement().getStyle().setDisplay(Display.NONE);
			
			/**
			 * On mouse over if user selected any emotic, selected emotic will be highlighted.
			 * Note: selected emotic will be differntiated based on respective emotic flag.
			 */
			
			if(isCanExplainSelected){
				canExplainEmoticButton.setStyleName(playerStyle.spriteType());
				canExplainEmoticButton.addStyleName(playerStyle.emoticon_vi());
			}else if(isCanUnderstandSelected){
				understandEmoticButton.setStyleName(playerStyle.spriteType());
				understandEmoticButton.addStyleName(playerStyle.emoticon_vii());
			}else if(isMehSelected){
				mehEmoticButton.setStyleName(playerStyle.spriteType());
				mehEmoticButton.addStyleName(playerStyle.emoticon_v());
			}else if(isDoNotUnderstandSelected){
				doNotUnderstandEmoticButton.setStyleName(playerStyle.spriteType());
				doNotUnderstandEmoticButton.addStyleName(playerStyle.emoticon_ix());
			}else if(isNeedHelpSelected){
				needHelpButton.setStyleName(playerStyle.spriteType());
				needHelpButton.addStyleName(playerStyle.emoticon_x());
			}
		}

	}

	/*
	 * Inner Class
	 */
	public class OnEmoticsMouseOut implements MouseOutHandler{
		
		/**
		 * On mouse out only selected emotic will be visible.
		 */
		@Override
		public void onMouseOut(MouseOutEvent event) {
			allEmoticsContainer.getElement().getStyle().setDisplay(Display.NONE);
			singleEmoticsContainer.getElement().getStyle().setDisplay(Display.BLOCK);
		}
	}

	/**
	 * Sets the emotics for respective resource.
	 * 
	 * @param reactionDo {@link ReactionDo}
	 * @param gooruReactionId {@link String}
	 */
	
	@Override
	public void setReaction(ReactionDo reactionDo,String gooruReactionId) {
		this.gooruReactionId=gooruReactionId;
		if(reactionDo.getReactionText().equalsIgnoreCase(REACTION_CAN_EXPLAIN)){
			selectedEmoticButton.setStyleName(playerStyle.spriteType());
			selectedEmoticButton.addStyleName(playerStyle.emoticon_vi());
			isCanExplainSelected=true;
			
			/**
			 * Except "can explain" emotic, all other will be changed to normal emotics.
			 * Note: this changes is made to achieve when user tries to select multiple emotic, previously selected emotic shld be removed.
			 */
			understandEmoticButton.setStyleName(playerStyle.spriteType());
			understandEmoticButton.addStyleName(playerStyle.emoticon_ii());
			
			mehEmoticButton.setStyleName(playerStyle.spriteType());
			mehEmoticButton.addStyleName(playerStyle.emoticon_viii());
			
			doNotUnderstandEmoticButton.setStyleName(playerStyle.spriteType());
			doNotUnderstandEmoticButton.addStyleName(playerStyle.emoticon_iii());
			
			needHelpButton.setStyleName(playerStyle.spriteType());
			needHelpButton.addStyleName(playerStyle.emoticon_iv());
			
			/**
			 * Except can explain emotic flag, all other emotic flag set to false, based on this again on mouse over previously selected will be removed.
			 * 
			 */
			isCanUnderstandSelected=false;
			isMehSelected=false;
			isDoNotUnderstandSelected=false;
			isNeedHelpSelected=false;
		}else if(reactionDo.getReactionText().equalsIgnoreCase(REACTION_CAN_UNDERSTAND)){
			selectedEmoticButton.setStyleName(playerStyle.spriteType());
			selectedEmoticButton.addStyleName(playerStyle.emoticon_vii());
			isCanUnderstandSelected = true;
			
			/**
			 * Except "can understand" emotic, all other will be changed to normal emotics.
			 * Note: this changes is made to achieve when user tries to select multiple emotic, previously selected emotic shld be removed.
			 */
			canExplainEmoticButton.setStyleName(playerStyle.spriteType());
			canExplainEmoticButton.addStyleName(playerStyle.emoticon_i());
			
			mehEmoticButton.setStyleName(playerStyle.spriteType());
			mehEmoticButton.addStyleName(playerStyle.emoticon_viii());
			
			doNotUnderstandEmoticButton.setStyleName(playerStyle.spriteType());
			doNotUnderstandEmoticButton.addStyleName(playerStyle.emoticon_iii());
			
			needHelpButton.setStyleName(playerStyle.spriteType());
			needHelpButton.addStyleName(playerStyle.emoticon_iv());
			
			/**
			 * Except "can understand" emotic flag, all other emotic flag set to false, based on this again on mouse over previously selected will be removed.
			 * 
			 */
			isCanExplainSelected=false;
			isMehSelected=false;
			isDoNotUnderstandSelected=false;
			isNeedHelpSelected=false;
		}else if(reactionDo.getReactionText().equalsIgnoreCase(REACTION_MEH)){
			selectedEmoticButton.setStyleName(playerStyle.spriteType());
			selectedEmoticButton.addStyleName(playerStyle.emoticon_v());
			isMehSelected = true;
			
			/**
			 * Except "meh" emotic, all other will be changed to normal emotics.
			 * Note: this changes is made to achieve when user tries to select multiple emotic, previously selected emotic shld be removed.
			 */
			canExplainEmoticButton.setStyleName(playerStyle.spriteType());
			canExplainEmoticButton.addStyleName(playerStyle.emoticon_i());
			
			understandEmoticButton.setStyleName(playerStyle.spriteType());
			understandEmoticButton.addStyleName(playerStyle.emoticon_ii());
			
			doNotUnderstandEmoticButton.setStyleName(playerStyle.spriteType());
			doNotUnderstandEmoticButton.addStyleName(playerStyle.emoticon_iii());
			
			needHelpButton.setStyleName(playerStyle.spriteType());
			needHelpButton.addStyleName(playerStyle.emoticon_iv());
			
			/**
			 * Except "meh" emotic flag, all other emotic flag set to false, based on this again on mouse over previously selected will be removed.
			 * 
			 */
			isCanExplainSelected=false;
			isCanUnderstandSelected=false;
			isDoNotUnderstandSelected=false;
			isNeedHelpSelected=false;
		}else if(reactionDo.getReactionText().equalsIgnoreCase(REACTION_DONOT_UNDERSTAND)){
			selectedEmoticButton.setStyleName(playerStyle.spriteType());
			selectedEmoticButton.addStyleName(playerStyle.emoticon_ix());
			isDoNotUnderstandSelected = true;
			
			/**
			 * Except "do not understand" emotic, all other will be changed to normal emotics.
			 * Note: this changes is made to achieve when user tries to select multiple emotic, previously selected emotic shld be removed.
			 */
			canExplainEmoticButton.setStyleName(playerStyle.spriteType());
			canExplainEmoticButton.addStyleName(playerStyle.emoticon_i());
			
			understandEmoticButton.setStyleName(playerStyle.spriteType());
			understandEmoticButton.addStyleName(playerStyle.emoticon_ii());
			
			mehEmoticButton.setStyleName(playerStyle.spriteType());
			mehEmoticButton.addStyleName(playerStyle.emoticon_viii());
			
			needHelpButton.setStyleName(playerStyle.spriteType());
			needHelpButton.addStyleName(playerStyle.emoticon_iv());
			
			/**
			 * Except "do not understand" emotic flag, all other emotic flag set to false, based on this again on mouse over previously selected will be removed.
			 * 
			 */
			isCanExplainSelected=false;
			isCanUnderstandSelected=false;
			isMehSelected=false;
			isNeedHelpSelected=false;
		}else if(reactionDo.getReactionText().equalsIgnoreCase(REACTION_NEED_HELP)){
			selectedEmoticButton.setStyleName(playerStyle.spriteType());
			selectedEmoticButton.addStyleName(playerStyle.emoticon_x());
			isNeedHelpSelected = true;
			
			/**
			 * Except "need help" emotic, all other will be changed to normal emotics.
			 * Note: this changes is made to achieve when user tries to select multiple emotic, previously selected emotic shld be removed.
			 */
			canExplainEmoticButton.setStyleName(playerStyle.spriteType());
			canExplainEmoticButton.addStyleName(playerStyle.emoticon_i());
			
			understandEmoticButton.setStyleName(playerStyle.spriteType());
			understandEmoticButton.addStyleName(playerStyle.emoticon_ii());
			
			mehEmoticButton.setStyleName(playerStyle.spriteType());
			mehEmoticButton.addStyleName(playerStyle.emoticon_viii());
			
			doNotUnderstandEmoticButton.setStyleName(playerStyle.spriteType());
			doNotUnderstandEmoticButton.addStyleName(playerStyle.emoticon_iii());
			
			/**
			 * Except "need help" emotic flag, all other emotic flag set to false, based on this again on mouse over previously selected will be removed.
			 * 
			 */
			isCanExplainSelected=false;
			isCanUnderstandSelected=false;
			isMehSelected=false;
			isDoNotUnderstandSelected=false;
			
		}
		
	}
	public static void addPadding(){
		wrapperContainerField.setStyleName(playerStyle.collectionPlayerWrapperPadding());
	}
	
	public static void removePadding(){
		wrapperContainerField.setStyleName(playerStyle.collectionPlayerWrapper());
	}

	/**
	 * If no reaction available, sets the default reaction for all the resources.
	 */
	@Override
	public void setDefaultReaction() {
		gooruReactionId="";
		isCanUnderstandSelected=false;
		isCanExplainSelected = false;
		isDoNotUnderstandSelected=false;
		isMehSelected=false;
		isNeedHelpSelected=false;
		selectedEmoticButton.setStyleName(playerStyle.spriteType());
		selectedEmoticButton.addStyleName(playerStyle.emoticon_ii());
		
		/**
		 * Resets all emotics to normal in allemoticContainer,
		 */
		canExplainEmoticButton.setStyleName(playerStyle.spriteType());
		canExplainEmoticButton.addStyleName(playerStyle.emoticon_i());
		
		understandEmoticButton.setStyleName(playerStyle.spriteType());
		understandEmoticButton.addStyleName(playerStyle.emoticon_ii());
		
		mehEmoticButton.setStyleName(playerStyle.spriteType());
		mehEmoticButton.addStyleName(playerStyle.emoticon_viii());
		
		doNotUnderstandEmoticButton.setStyleName(playerStyle.spriteType());
		doNotUnderstandEmoticButton.addStyleName(playerStyle.emoticon_iii());
		
		needHelpButton.setStyleName(playerStyle.spriteType());
		needHelpButton.addStyleName(playerStyle.emoticon_iv());
	}
	
	/**
	 * On select of any reaction based on the player type API call is done.
	 * 
	 * @param resourceGooruId {@link String}
	 * @param reactionText {@link String}
	 * @param gooruReactionId {@link String}
	 * @param collectionId {@link String}
	 * @param playerName {@link String}
	 */
	private void createReaction(String resourceGooruId,String reactionText,String gooruReactionId,String collectionId,String playerName){
		if(playerName.trim().equalsIgnoreCase(PlaceTokens.COLLECTION_PLAY)){ 
			getUiHandlers().createReaction(resourceGooruId,reactionText,gooruReactionId,collectionId,CREATE_STUDY_PLAYER_REACTION);
		}else if(playerName.trim().equalsIgnoreCase(PlaceTokens.PREVIEW_PLAY)){
			getUiHandlers().createReaction(resourceGooruId,reactionText,gooruReactionId,collectionId,CREATE_PREVIEW_PLAYER_REACTION);
		}
		
	}
	
	private String removeHtmlTags(String html){
        html = html.replaceAll("</p>", " ").replaceAll("<p>", "").replaceAll("<br data-mce-bogus=\"1\">", "").replaceAll("<br>", "").replaceAll("</br>", "");
        return html;
	}
	public boolean isProtocolMatched(String sourceUrl){
		boolean isProtocolsMatched=false;
		if(FlashAndVideoPlayerWidget.getProtocal().equalsIgnoreCase("https:")){
				isProtocolsMatched=!sourceUrl.contains("https");
		}
		else{
			if(!sourceUrl.contains("docs.google.com")){
				isProtocolsMatched=sourceUrl.contains("https");
			}
		}
		return isProtocolsMatched;
	}
	
	
}
