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
package org.ednovo.gooru.client.uc;



import org.ednovo.gooru.client.gin.AppClientFactory;
import org.ednovo.gooru.client.mvp.play.collection.preview.PreviewPlayerPresenter;
import org.ednovo.gooru.shared.util.MessageProperties;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ErrorEvent;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;

public class TocCollectionEndView extends Composite implements HasClickHandlers,MessageProperties{

	@UiField Image resourceThumbnail;
	
	@UiField HTMLPanel resourceTitle;
	
	@UiField FlowPanel endImageContainer,endContainer;
	
	private String thumbnailUrl;
	
	private static TocCollectionEndViewUiBinder uiBinder = GWT.create(TocCollectionEndViewUiBinder.class);

	interface TocCollectionEndViewUiBinder extends UiBinder<Widget, TocCollectionEndView> {
	}
	
	public TocCollectionEndView(){
		initWidget(uiBinder.createAndBindUi(this));
		resourceTitle.getElement().setInnerHTML(GL1051);
	}
	
	@UiConstructor
	public TocCollectionEndView(String thumbnailUrl){
		initWidget(uiBinder.createAndBindUi(this));
		this.thumbnailUrl=thumbnailUrl;
		resourceTitle.getElement().setInnerHTML(GL1051);
		setResourcePlayLink();
	}
	
	public void onLoad(){
		resourceThumbnail.setUrl(thumbnailUrl);
	}
	
	@UiHandler("resourceThumbnail")
	public void onErrorImageLoad(ErrorEvent event){
		resourceThumbnail.setUrl("images/default-collection-image-160x120.png");
	}
	public void setResourcePlayLink(){
		Anchor resourceAnchor=new Anchor();
		resourceAnchor.setHref(getResourceLink());
		resourceAnchor.setStyleName("");
		resourceAnchor.getElement().appendChild(endImageContainer.getElement());
		endContainer.add(resourceAnchor);
	}
	public String getResourceLink(){
		String collectionId=AppClientFactory.getPlaceManager().getRequestParameter("id", null);
		String viewToken=AppClientFactory.getPlaceManager().getCurrentPlaceRequest().getNameToken();
		String resourceLink="#"+viewToken+"&id="+collectionId+"&view=end";
		resourceLink += PreviewPlayerPresenter.setConceptPlayerParameters();
		return resourceLink;
	}
	
	public HandlerRegistration addClickHandler(ClickHandler handler) {
		return addDomHandler(handler, ClickEvent.getType());
	}
	
}
