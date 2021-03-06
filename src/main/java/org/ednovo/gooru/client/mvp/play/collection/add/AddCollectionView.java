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
package org.ednovo.gooru.client.mvp.play.collection.add;

import org.ednovo.gooru.client.PlaceTokens;
import org.ednovo.gooru.client.gin.AppClientFactory;
import org.ednovo.gooru.client.gin.BaseViewWithHandlers;
import org.ednovo.gooru.shared.util.MessageProperties;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ErrorEvent;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;

public class AddCollectionView extends BaseViewWithHandlers<AddCollectionUiHandlers> implements IsAddCollectionView , MessageProperties{


	private static ResourceShareViewUiBinder uiBinder = GWT.create(ResourceShareViewUiBinder.class);

	interface ResourceShareViewUiBinder extends UiBinder<Widget, AddCollectionView> {

	}
	
	public String ERROR_MSG=GL1425;
	public String URL_CHAR=GL0323;
	public String SPEC_CHAR=GL1426;
	public String specialCh = "!@#$%^&*?";
	public String MAX_ERROR_MESSAGE=GL1427;
	public String thumbnailUrl="";
	public String collectionGooruOid=null;

    //@UiField Image collImage;
	
	@UiField TextBox collectionTitleInCoverPage;
	
	@UiField HTMLPanel addToCollectionWidgetContainer,addCollectionInsteadLabelContainer,collectionAddImageContainer,addResourceInsteadLabelContainerInCollectionImage,addcollectionText;
	
	@UiField Label addingLabel,addResourceInsteadLabel,addErrorLabel,successMessageLabelText,hideText,renameText;
	
	@UiField Button addToShelfCollectionButton;
	
	@UiField FlowPanel collectionAddedSuccessMessageContainer;
	
	@UiField Anchor workSpaceLink;

	
	@Inject
	public AddCollectionView(){
		setWidget(uiBinder.createAndBindUi(this));
		hideText.setText(GL0592);
		addcollectionText.getElement().setInnerHTML(GL0690);
		renameText.setText(GL0593);
		addToShelfCollectionButton.setText(GL0590);
		collectionAddedSuccessMessageContainer.setVisible(false);
		getAddToShelfCollectionButton().addClickHandler(new OnAddCollectionClick());
		collectionTitleInCoverPage.addKeyUpHandler(new onKeyErrorMsg());
		workSpaceLink.setText(GL0589);
	}
	
	@UiHandler("workSpaceLink")
	public void workspaceLinkClickEvent(ClickEvent event){
		AppClientFactory.getPlaceManager().setRefreshPlace(true);
	}
	
//	public Label getSuccessMessageAddedCollection() {
//		return successMessageAddedCollection;
//	}
//	public void setSuccessMessageAddedCollection(Label successMessageAddedCollection) {
//		this.successMessageAddedCollection = successMessageAddedCollection;
//	}
	public Label getAddingLabel() {
		return addingLabel;
	}
	public void setAddingLabel(Label addingLabel) {
		this.addingLabel = addingLabel;
	}
	public Label getAddResourceViewButton() {
		return addResourceInsteadLabel;
	}
	public void setAddResourceInsteadLabel(Label addResourceInsteadLabel) {
		this.addResourceInsteadLabel = addResourceInsteadLabel;
	}
	public Label getAddErrorLabel() {
		return addErrorLabel;
	}
	public void setAddErrorLabel(Label addErrorLabel) {
		this.addErrorLabel = addErrorLabel;
	}
	public Button getAddToShelfCollectionButton() {
		return addToShelfCollectionButton;
	}
	public void setAddToShelfCollectionButton(
			Button addToShelfCollectionButton) {
		this.addToShelfCollectionButton = addToShelfCollectionButton;
	}
//	public Image getCollImage() {
//		return collImage;
//	}
//	public void setCollImage(Image collImage) {
//		this.collImage = collImage;
//	}
	public TextBox getCollectionTitleInCoverPage() {
		return collectionTitleInCoverPage;
	}
	public void setCollectionTitleInCoverPage(TextBox collectionTitleInCoverPage) {
		this.collectionTitleInCoverPage = collectionTitleInCoverPage;
	}
//	public HTMLPanel getCollImageUrlHolderPanel() {
//		return collImageUrlHolderPanel;
//	}
//	public void setCollImageUrlHolderPanel(HTMLPanel collImageUrlHolderPanel) {
//		this.collImageUrlHolderPanel = collImageUrlHolderPanel;
//	}
//	public void displaySuccessLabel(){
//		successMessageAddedCollection.getElement().getStyle().setDisplay(Display.BLOCK);
//	}
	public HTMLPanel getAddToCollectionWidgetContainer() {
		return addToCollectionWidgetContainer;
	}
	public void setAddToCollectionWidgetContainer(HTMLPanel addToCollectionWidgetContainer) {
		this.addToCollectionWidgetContainer = addToCollectionWidgetContainer;
	}
	
	public void showSuccessMessageWidget(String collectionId){
		getAddingLabel().setVisible(false);
		getAddToShelfCollectionButton().setVisible(true);
		if(getAddToShelfCollectionButton().getText().equalsIgnoreCase("Add")){
			getAddToShelfCollectionButton().setText(GL0691);
			successMessageLabelText.setText(GL0547);
		}else{
			successMessageLabelText.setText(GL0692);
		}
		addCollectionInsteadLabelContainer.clear();
		addResourceInsteadLabel.setText(GL0685);
		addCollectionInsteadLabelContainer.add(addResourceInsteadLabel);
		addResourceInsteadLabel.getElement().getStyle().setMarginRight(60,Unit.PX);
		//addResourceInsteadLabel.getElement().getStyle().setMarginTop(-15,Unit.PX);
		collectionAddImageContainer.setVisible(false);
		collectionAddedSuccessMessageContainer.setVisible(true);
		workSpaceLink.setHref("#organize&id="+collectionId+"&eventType=refresh");
	}
	public void showCollectionAddImageWidget(){
		getAddToShelfCollectionButton().setText(GL0590);
		successMessageLabelText.setText(GL0547);
		addResourceInsteadLabelContainerInCollectionImage.clear();
		addResourceInsteadLabelContainerInCollectionImage.add(addResourceInsteadLabel);
		
		addResourceInsteadLabel.getElement().getStyle().clearMarginLeft();
		addResourceInsteadLabel.getElement().getStyle().clearMarginTop();
		collectionAddImageContainer.setVisible(true);
		collectionAddedSuccessMessageContainer.setVisible(false);
	}
	
	public class OnAddCollectionClick implements ClickHandler {
		public void onClick(ClickEvent event) {
			addingLabel.setText(GL0591);
			String title = getCollectionTitleInCoverPage().getText();
			if(title.trim().length()==0){
				getAddErrorLabel().setVisible(true);
				getAddErrorLabel().setText(ERROR_MSG);
			} else if(title.trim().length()>50){
				getAddErrorLabel().setVisible(true);
				getAddErrorLabel().setText(MAX_ERROR_MESSAGE);
			}else if (title.toLowerCase().contains("www.") || title.toLowerCase().contains("http://") || title.toLowerCase().contains("https://") || title.toLowerCase().contains("ftp://")){
				getAddErrorLabel().setVisible(true);
				getAddErrorLabel().setText(URL_CHAR);
			}else if(title.contains(specialCh)){
				getAddErrorLabel().setVisible(true);
				getAddErrorLabel().setText(SPEC_CHAR);
			}
			else{
			getAddingLabel().setVisible(true);
			getAddToShelfCollectionButton().setVisible(false);
			getAddErrorLabel().setVisible(false);
			copyCollection(collectionGooruOid,title);
		 }
	  }		
	}
	public class onKeyErrorMsg implements KeyUpHandler{
		@Override
		public void onKeyUp(KeyUpEvent event) {
			String text=getCollectionTitleInCoverPage().getText();
			if(text.trim().length()>0){
				getAddErrorLabel().setVisible(false);
			}
			if(text.length()>50){
				getAddErrorLabel().setVisible(true);
				getAddErrorLabel().setText(MAX_ERROR_MESSAGE);
			}	
		}	
	}
	
	@Override
	public void setCollectionData(String collectionTitle, String collectionId,
			String collectionImageUrl) {
		collectionTitleInCoverPage.setValue(collectionTitle);
		this.collectionGooruOid=collectionId;
		this.thumbnailUrl=collectionImageUrl;
		//setCollectionThumbnail();
		getAddErrorLabel().setVisible(false);
		getAddingLabel().setVisible(false);
		getCollectionTitleInCoverPage().setVisible(true);
		showCollectionAddImageWidget();
	}
//	@UiHandler("collImage")
//	public void setDefaultThumbnail(ErrorEvent event){
//		collImage.setUrl("");
//	}
//	
//	public void setCollectionThumbnail(){
//		collImage.setUrl(thumbnailUrl);
//	}
	public void copyCollection(String collectionId,String collectionTile){
		getUiHandlers().copyCollection( collectionId, collectionTile);
	}
	
	/**
	 * 
	 * @function onhideBtnClicked 
	 * 
	 * @created_date : 11-Dec-2013
	 * 
	 * @description
	 * 
	 * 
	 * @parm(s) : @param clickEvent
	 * 
	 * @return : void
	 *
	 * @throws : <Mentioned if any exceptions>
	 *
	 */
	@UiHandler("hideButton")
	public void onhideBtnClicked(ClickEvent clickEvent) 
	{
		PlaceRequest collectionRequest = AppClientFactory.getPlaceManager().getCurrentPlaceRequest();
		String collectionId = collectionRequest.getParameter("id", null);
		String collectionItemId = collectionRequest.getParameter("rid", null);
		String chkViewParam = collectionRequest.getParameter("view", null);
	if(AppClientFactory.getCurrentPlaceToken().contains(PlaceTokens.RESOURCE_PLAY))
	{
		PlaceRequest request=new PlaceRequest(PlaceTokens.RESOURCE_PLAY).
				with("id", collectionId);
		AppClientFactory.getPlaceManager().revealPlace(false,request,true);
	}
	else if(AppClientFactory.getCurrentPlaceToken().contains(PlaceTokens.COLLECTION_PLAY) && chkViewParam == null && collectionItemId != null)
	{
		PlaceRequest request=new PlaceRequest(PlaceTokens.COLLECTION_PLAY).
				with("id", collectionId).with("rid", collectionItemId);
		AppClientFactory.getPlaceManager().revealPlace(false,request,true);
	}
	else if(AppClientFactory.getCurrentPlaceToken().contains(PlaceTokens.PREVIEW_PLAY) && chkViewParam == null && collectionItemId != null)
	{
		PlaceRequest request=new PlaceRequest(PlaceTokens.PREVIEW_PLAY).
				with("id", collectionId).with("rid", collectionItemId);
		AppClientFactory.getPlaceManager().revealPlace(false,request,true);
	}
	else if(AppClientFactory.getCurrentPlaceToken().contains(PlaceTokens.COLLECTION_PLAY) && chkViewParam == null && collectionItemId == null)
	{
		PlaceRequest request=new PlaceRequest(PlaceTokens.COLLECTION_PLAY).
				with("id", collectionId);
		AppClientFactory.getPlaceManager().revealPlace(false,request,true);
	}
	else if(AppClientFactory.getCurrentPlaceToken().contains(PlaceTokens.PREVIEW_PLAY) && chkViewParam == null && collectionItemId == null)
	{
		PlaceRequest request=new PlaceRequest(PlaceTokens.PREVIEW_PLAY).
				with("id", collectionId);
		AppClientFactory.getPlaceManager().revealPlace(false,request,true);
	}
	else if(AppClientFactory.getCurrentPlaceToken().contains(PlaceTokens.COLLECTION_PLAY) && chkViewParam.equalsIgnoreCase("end"))
	{
		PlaceRequest request=new PlaceRequest(PlaceTokens.COLLECTION_PLAY).
				with("id", collectionId).with("view", "end");
		AppClientFactory.getPlaceManager().revealPlace(false,request,true);
	}
	else if(AppClientFactory.getCurrentPlaceToken().contains(PlaceTokens.PREVIEW_PLAY) && chkViewParam.equalsIgnoreCase("end"))
	{
		PlaceRequest request=new PlaceRequest(PlaceTokens.PREVIEW_PLAY).
				with("id", collectionId).with("view", "end");
		AppClientFactory.getPlaceManager().revealPlace(false,request,true);
	}
	}

}
