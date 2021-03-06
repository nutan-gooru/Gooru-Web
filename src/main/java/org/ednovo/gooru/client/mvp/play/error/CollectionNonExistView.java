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
package org.ednovo.gooru.client.mvp.play.error;


import org.ednovo.gooru.client.PlaceTokens;
import org.ednovo.gooru.client.gin.AppClientFactory;
import org.ednovo.gooru.shared.util.MessageProperties;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;
/**
* @fileName : CollectionNonExistView.java
*
* @description : If collection doesn't exist this view will be called and displayed.
* 
* @version : 1.0
*
* @date:  December, 2013.
*
* @Author: Gooru Team.
* 
* @Reviewer: Gooru Team.
*/
public class CollectionNonExistView extends Composite implements MessageProperties{
	
	public interface CollectionNonExistViewUiBinder extends UiBinder<Widget,CollectionNonExistView>{
		
	}
	
	public static CollectionNonExistViewUiBinder collectionNonExistViewUiBinder=GWT.create(CollectionNonExistViewUiBinder.class);
	
	@UiField public FlowPanel feautredCollectionContainer;
	@UiField Button btnLibrary;
	@UiField HTMLPanel noLongerText,madePrivateOrDeletedText,meanTimetext;
	@UiField Image recycleImage;
	
	/**
	 * Class constructor.
	 */
	public CollectionNonExistView(){
		initWidget(collectionNonExistViewUiBinder.createAndBindUi(this));
		btnLibrary.setText(GL0506);
		noLongerText.getElement().setInnerHTML(GL0655);
		recycleImage.setUrl("images/recycle.png");
		madePrivateOrDeletedText.getElement().setInnerHTML(GL0656);
		meanTimetext.getElement().setInnerHTML(GL0657);
		btnLibrary.setText(GL0506);
		btnLibrary.getElement().getStyle().setMarginLeft(50, Unit.PX);
//		getFeaturedCollections();
	}
	
	/**
	 * On click of this button navigates user to library page.
	 * @param clickEvent {@link ClickEvent}
	 */
	@UiHandler("btnLibrary")
	public void onClickOnLibrary(ClickEvent clickEvent){
		AppClientFactory.getPlaceManager().revealPlace(PlaceTokens.HOME);
		
	}
	
	
	/*
	*//**
	 * Gets all featured collections from API.s
	 *//*
	private void getFeaturedCollections(){
		AppClientFactory.getInjector().getPlayerAppService().getFeaturedContent(new SimpleAsyncCallback<ArrayList<FeaturedContentDo>>() {
			@Override
			public void onSuccess(ArrayList<FeaturedContentDo> featuredContentList) {
				showFeaturedContent(featuredContentList);
			}
		});
	}
	*//**
	 * Sets all list featured collection, received as response from an API.
	 * @param featuredContentList {@link List}
	 *//*
	 private void showFeaturedContent(List<FeaturedContentDo> featuredContentList){
	    	if(featuredContentList.size()>0){
	    		FlowPanel featuredContentContainer=new FlowPanel();
	    		for(int i=0;i<featuredContentList.size();i++){
	    			if(i<4){
		    			FeaturedContentDo featuredContentDo=featuredContentList.get(i);
		    			featuredContentContainer.add(new FeaturedCollectionView(featuredContentDo.getCollectionTitle(), featuredContentDo.getCollectionThumbnailUrl(),featuredContentDo.getCollectionGooruOid()));
	    			}
	    		}
	    		feautredCollectionContainer.clear();
	    		feautredCollectionContainer.add(featuredContentContainer);
	    	}
	    }
	*/
}
