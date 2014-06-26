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
package org.ednovo.gooru.client.mvp.classpages.home;



import java.util.HashMap;
import java.util.Map;

import org.ednovo.gooru.client.PlaceTokens;
import org.ednovo.gooru.client.gin.AppClientFactory;
import org.ednovo.gooru.client.uc.HTMLEventPanel;
import org.ednovo.gooru.client.uc.PlayerBundle;
import org.ednovo.gooru.shared.model.content.CollectionDo;
import org.ednovo.gooru.shared.util.MessageProperties;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ErrorEvent;
import com.google.gwt.event.dom.client.ErrorHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;

public class ClasspageWidgetView extends Composite implements MessageProperties{

/*	@UiField InlineLabel ratingCountLabel,star_1,star_2,star_3,star_4,star_5;*/
	
	@UiField Image classImage,imgUserProfile;
	
	@UiField HTMLPanel classTitle,assignmentsCount,ownerName,assignmentsCounter;
	
	@UiField HTMLEventPanel classpageContainer;
	
	private String DEFAULT_CLASSPAGE_IMAGE = "images/Classpage/default-classpage.png";
	
	private static String DEFAULT_PROFILE_IMAGE="images/settings/setting-user-image.png";


	private static ClasspageWidgetViewUiBinder uiBinder = GWT.create(ClasspageWidgetViewUiBinder.class);

	interface ClasspageWidgetViewUiBinder extends UiBinder<Widget, ClasspageWidgetView> {

	}

	public ClasspageWidgetView(){
		initWidget(uiBinder.createAndBindUi(this));
		PlayerBundle.INSTANCE.getPlayerStyle().ensureInjected();
		
		classImage.setWidth("422"+Unit.PX);
		classImage.setHeight("77"+Unit.PX);
		classImage.getElement().setId("imgClassImage");
		classpageContainer.getElement().setId("pnlClasspageContainer");
		classTitle.getElement().setId("pnlClassTitle");
		imgUserProfile.getElement().setId("imgUserProfile");
		ownerName.getElement().setId("pnlOwnerName");
		assignmentsCounter.getElement().setId("pnlAssignmentsCounter");
		assignmentsCount.getElement().setId("pnlAssignmentsCount");
	}

/*	public InlineLabel getRatingCountLabel(){
		return ratingCountLabel;
	}*/

	public void setClassPageImage(final CollectionDo collectionDoObj,String pageMode) {
		

		classTitle.getElement().setInnerHTML(collectionDoObj.getTitle());
		classTitle.getElement().setAttribute("alt",collectionDoObj.getTitle());
		classTitle.getElement().setAttribute("title",collectionDoObj.getTitle());
		
		assignmentsCounter.getElement().setAttribute("style", "margin-left:31%;");
		
		if(pageMode.equalsIgnoreCase("Teach"))
		{
		if(collectionDoObj.getMemberCount() == 1)
		{
			assignmentsCounter.getElement().setInnerHTML(collectionDoObj.getMemberCount()+" "+GL1932);
		}
		else
		{
			assignmentsCounter.getElement().setInnerHTML(collectionDoObj.getMemberCount()+" "+GL1931);
		}
		if(collectionDoObj.getItemCount() == 1)
		{
			assignmentsCount.getElement().setInnerHTML(collectionDoObj.getItemCount()+" "+GL1934);
		}
		else
		{
			assignmentsCount.getElement().setInnerHTML(collectionDoObj.getItemCount()+" "+GL1933);
		}
		
		imgUserProfile.setVisible(false);
		assignmentsCounter.setVisible(true);
		ownerName.setVisible(false);
		classpageContainer.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				Map<String, String> params=new HashMap<String, String>();
				params.put("pageSize", "5");
				params.put("classpageid", collectionDoObj.getGooruOid());
				params.put("pageNum", "0");
				params.put("pos", "1");
				
				AppClientFactory.getPlaceManager().revealPlace(PlaceTokens.EDIT_CLASSPAGE,params);
				
			}
		});
		}
		else
		{
		if(collectionDoObj.getItemCount() == 1)
		{
			assignmentsCount.getElement().setInnerHTML(collectionDoObj.getItemCount()+" "+GL1934);
		}
		else
		{
			assignmentsCount.getElement().setInnerHTML(collectionDoObj.getItemCount()+" "+GL1933);	
		}
		ownerName.getElement().setInnerHTML(collectionDoObj.getUser().getUserName()+"'s"+" "+"class");
		imgUserProfile.setVisible(true);
		assignmentsCounter.setVisible(false);
		ownerName.setVisible(true);
		if(collectionDoObj.getUser().getProfileImageUrl() != null)
		{
		imgUserProfile.setUrl(collectionDoObj.getUser().getProfileImageUrl());
		imgUserProfile.addErrorHandler(new ErrorHandler() {
				
				@Override
				public void onError(ErrorEvent event) {
					imgUserProfile.setUrl(DEFAULT_PROFILE_IMAGE);
					
				}
			});
		}
		else
		{
			imgUserProfile.setUrl(DEFAULT_PROFILE_IMAGE);
		}
		classpageContainer.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				Map<String, String> params=new HashMap<String, String>();
				params.put("pageSize", "5");
				params.put("id", collectionDoObj.getGooruOid());
				params.put("pageNum", "0");
				params.put("pos", "1");

				AppClientFactory.getPlaceManager().revealPlace(PlaceTokens.STUDENT, params);
				
			}
		});
		}
		classImage.setUrl(collectionDoObj.getThumbnailUrl());
		
		classImage.addErrorHandler(new ErrorHandler() {
			
			@Override
			public void onError(ErrorEvent event) {
				classImage.setUrl(DEFAULT_CLASSPAGE_IMAGE);
				
			}
		});
		
		
		
		
	}
}