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
package org.ednovo.gooru.client.mvp.shelf.collection;

import org.ednovo.gooru.client.mvp.search.NoSearchResultBundle;
import org.ednovo.gooru.client.uc.UcCBundle;
import org.ednovo.gooru.shared.model.content.CollectionItemDo;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ErrorEvent;
import com.google.gwt.event.dom.client.ErrorHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class CollectionSharePrivateResource extends Composite {

	@UiField Label resourceCategory;
	
	@UiField HTML resourceTitle,resourceDescription;
	
	@UiField Image resourceImageUc;
	
	@UiField NoSearchResultBundle noResultStyle;
	
	@UiField HTMLPanel resourceCategoryIcon;
	
	private static final String DEFULT_IMAGE_PREFIX = "images/default-";
	
	private static final String PNG = ".png";
	
	private static final String SMALL = "Small";
	private String category;
	
	private static CollectionSharePrivateResourceUiBinder uiBinder = GWT
			.create(CollectionSharePrivateResourceUiBinder.class);

	interface CollectionSharePrivateResourceUiBinder extends
			UiBinder<Widget, CollectionSharePrivateResource> {
	}

	public CollectionSharePrivateResource(CollectionItemDo collectionItemDo) {
		initWidget(uiBinder.createAndBindUi(this));
		String resourceHtmlTitle=collectionItemDo.getResourceTitle();
		resourceHtmlTitle=resourceHtmlTitle.replaceAll("</p>", " ").replaceAll("<p>", "").replaceAll("<br data-mce-bogus=\"1\">", "").replaceAll("<br>", "").replaceAll("</br>", "");
		resourceTitle.setHTML(resourceHtmlTitle);
		resourceTitle.setStyleName("resourceTitleShare");
		category = collectionItemDo.getResource().getCategory();
		if(category.equalsIgnoreCase("question")){
			resourceCategory.setText("Question");
		}else if(category.equalsIgnoreCase("text")){
			resourceCategory.setText("Text");
		}
		else if(category.equalsIgnoreCase("slide")){
			resourceCategory.setText("Slide");
		}
		else if(category.equalsIgnoreCase("webpage")){
			resourceCategory.setText("Webpage");	
		}
		else if(category.equalsIgnoreCase("interactive")){
			resourceCategory.setText("Interactive");	
		}
		else if(category.equalsIgnoreCase("audio")){
			resourceCategory.setText("Audio");	
		}
		else if(category.equalsIgnoreCase("video")){
			resourceCategory.setText("Video");	
		}
		else if(category.equalsIgnoreCase("handout")){
			resourceCategory.setText("Text");	
		}
		else{
			resourceCategory.setText(category);
		}
		resourceCategoryIcon.addStyleName(UcCBundle.INSTANCE.css().resourceName());
		resourceCategoryIcon.addStyleName(category.toLowerCase() + SMALL);
		if(!category.equalsIgnoreCase("Question")){
			String descriptionTxt = collectionItemDo.getResource().getDescription();
			descriptionTxt = descriptionTxt.replaceAll("</p>", " ").replaceAll("<p>", "").replaceAll("<br data-mce-bogus=\"1\">", "").replaceAll("<br>", "").replaceAll("</br>", "");
			resourceDescription.setHTML(descriptionTxt);
		}
		if(category!=null){
			if(category.equalsIgnoreCase("Lesson")||category.equalsIgnoreCase("Textbook")||category.equalsIgnoreCase("Handout"))
			{
				category=category.replaceAll("Lesson", "Text").replaceAll("Textbook", "Text").replaceAll("Handout", "Text").replaceAll("lesson", "Text").replaceAll("textbook", "Text").replaceAll("handout", "Text");
			}
			if(category.equalsIgnoreCase("Slide"))
			{
				category=category.replaceAll("Slide","Image").replaceAll("slide","Image");
			}
			if(category.equalsIgnoreCase("Exam") || category.equalsIgnoreCase("Challenge")||category.equalsIgnoreCase("Website"))
			{
				category=category.replaceAll("Exam","Webpage").replaceAll("Challenge", "Webpage").replaceAll("exam","Webpage").replaceAll("challenge", "Webpage");
			}
		}
		final String url = collectionItemDo.getResource().getThumbnails().getUrl();
		if(url!=null) {
			resourceImageUc.setUrl(collectionItemDo.getResource().getThumbnails().getUrl());
		} else {
			resourceImageUc.setUrl(DEFULT_IMAGE_PREFIX + category.toLowerCase() + PNG);
		}
		
		resourceImageUc.addErrorHandler(new ErrorHandler() {
			@Override
			public void onError(ErrorEvent event) {
				resourceImageUc.setUrl(DEFULT_IMAGE_PREFIX + category.toLowerCase() + PNG);
			}
		});
	}
}
