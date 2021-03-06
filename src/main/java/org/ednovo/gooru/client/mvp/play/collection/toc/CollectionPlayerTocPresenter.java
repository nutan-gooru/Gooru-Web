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
package org.ednovo.gooru.client.mvp.play.collection.toc;


import org.ednovo.gooru.shared.model.content.CollectionDo;

import com.google.inject.Inject;
import com.google.gwt.event.shared.EventBus;
import com.gwtplatform.mvp.client.PresenterWidget;

public class CollectionPlayerTocPresenter extends PresenterWidget<IsCollectionPlayerTocView> implements CollectionPlayerTocUiHandlers{

	
	@Inject
	public CollectionPlayerTocPresenter(EventBus eventBus, IsCollectionPlayerTocView view) {
		super(eventBus, view);
	}
	
	public void setNavigationResources(CollectionDo collectionDo){
		getView().setNavigationResources(collectionDo);
	}
	
	public void setResourceActive(String collectionId,String collectionItemid,boolean isCollectionHome){
		getView().setResourceActive(collectionId,collectionItemid,isCollectionHome);
	}
	public void clearNavigationPanel(){
		getView().clearNavigationPanel();
	}

}
