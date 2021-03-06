package org.ednovo.gooru.client.mvp.home.library.customize;

import java.util.HashMap;
import java.util.Map;

import org.ednovo.gooru.client.PlaceTokens;
import org.ednovo.gooru.client.SimpleAsyncCallback;
import org.ednovo.gooru.client.gin.AppClientFactory;
import org.ednovo.gooru.client.mvp.home.library.customize.LoginPluginView;
import org.ednovo.gooru.client.mvp.play.collection.preview.home.assign.AssignPopUpCBundle;
import org.ednovo.gooru.client.mvp.search.event.SetHeaderZIndexEvent;
import org.ednovo.gooru.client.mvp.shelf.event.RefreshUserShelfCollectionsEvent;
import org.ednovo.gooru.client.service.ResourceServiceAsync;
import org.ednovo.gooru.client.util.MixpanelUtil;
import org.ednovo.gooru.client.util.SetStyleForProfanity;
import org.ednovo.gooru.shared.model.content.CollectionDo;
import org.ednovo.gooru.shared.util.MessageProperties;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.storage.client.Storage;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

/**
 * @author BLR Team
 * 
 */
public abstract class RenameAndCustomizeLibraryPopUp extends PopupPanel implements MessageProperties {

	@UiField
	HTMLPanel loginCustom, copyCollectionSuccess, panelAssign,loadingImageLabel,popupcontentCustomize,customizeText;

	@UiField(provided = true)
	AssignPopUpCBundle res;

	@UiField
	Label cancelButton, assignDes,lblpopupTitle,lbltxtBoxTitle, errorLabel;

	@UiField
	Button backtoLibrary, editCollection;
	
	@UiField
	TextBox copycollectionTextbox;

	@Inject
	private ResourceServiceAsync resourceService;

	private SimpleAsyncCallback<CollectionDo> saveCollectionAsyncCallback;
	
	CollectionDo collectionDo = new CollectionDo();

	@UiTemplate("RenameAndCustomizeLibraryPopUp.ui.xml")
	interface Binder extends UiBinder<Widget, RenameAndCustomizeLibraryPopUp> {

	}

	private static final Binder binder = GWT.create(Binder.class);
	
	private boolean isCustomizePopup = false,isHavingBadWords;
	

	/**
	 * 
	 */
	public RenameAndCustomizeLibraryPopUp(String collectionId, final Boolean loginFlag, String collectionTitle) {
		super(false);
		this.res = AssignPopUpCBundle.INSTANCE;
		res.css().ensureInjected();
		add(binder.createAndBindUi(this));
		
		copycollectionTextbox.setText(collectionTitle);
		
		AppClientFactory.getInjector().getClasspageService().getSCollIdClasspageById(collectionId, new AsyncCallback<CollectionDo>(){

			@Override
			public void onFailure(Throwable caught) {
				
			}

			@Override
			public void onSuccess(CollectionDo result) {
				collectionDo = result;
				MixpanelUtil.Preview_Click_Customize_successful();

			

				if (loginFlag) {
					loginCustom.setVisible(true);
					copyCollectionSuccess.setVisible(false);
				LoginPluginView assignWidget = new LoginPluginView(result) {
						
						@Override
						public void closePoupfromChild() {
							closePoup();
						}

						@Override
						public void showSuccessMsgfromChild(String collectionId) {
							showSuccessMsg(collectionId);
							
						}
					};
					loginCustom.add(assignWidget);
				} else {
					loginCustom.setVisible(false);
					copyCollectionSuccess.setVisible(true);
			

				}
				popupcontentCustomize.setVisible(true);
				loadingImageLabel.setVisible(false);
			}
		});
		
		errorLabel.setVisible(false);
		this.setGlassEnabled(true);
		customizeText.getElement().setInnerHTML(GL0743);
		backtoLibrary.setText(GL0142);
		editCollection.setText(GL0636);
		panelAssign.getElement().getStyle().setMarginBottom(10, Unit.PX);
		loginCustom.getElement().getStyle().setMarginBottom(15, Unit.PX);
		copycollectionTextbox.setMaxLength(50);
		Window.enableScrolling(false);
		this.getElement().setAttribute("style", "z-index:99999;");
		this.getGlassElement().setAttribute("style", "z-index:99999; position:absolute; left:0px; top:0px;");
		AppClientFactory.fireEvent(new SetHeaderZIndexEvent(99, false));
		
		popupcontentCustomize.setVisible(false);
		loadingImageLabel.setVisible(true);
		
		editCollection.getElement().setAttribute("collectionId", collectionId);
		
		assignDes.setText(GL0744);
		lblpopupTitle.setText(GL0743);
		lbltxtBoxTitle.setText(GL0553);
		
		copycollectionTextbox.addBlurHandler(new BlurHandler() {
			
			@Override
			public void onBlur(BlurEvent event) {
				Map<String, String> parms = new HashMap<String, String>();
				parms.put("text", copycollectionTextbox.getValue());
				AppClientFactory.getInjector().getResourceService().checkProfanity(parms, new SimpleAsyncCallback<Boolean>() {

					@Override
					public void onSuccess(Boolean value) {
						isHavingBadWords=value;
						SetStyleForProfanity.SetStyleForProfanityForTextBox(copycollectionTextbox, errorLabel, value);
					}
				});
			}
		});

		copycollectionTextbox.addKeyUpHandler(new OnkeyUp());
		MixpanelUtil.mixpanelEvent("CoursePage_customize_collection");
		this.center();

	}


	public abstract void closePoup();

	public ResourceServiceAsync getResourceService() {
		return resourceService;
	}

	public void setResourceService(ResourceServiceAsync resourceService) {
		this.resourceService = resourceService;
	}

	public void showSuccessMsg(String collectionId) {
		loginCustom.setVisible(false);
		copyCollectionSuccess.setVisible(true);
		editCollection.getElement().setAttribute("collectionId", collectionId);
		customizeText.getElement().setInnerHTML(GL0743);
	}

	/**
	 * Added click handler to hide the login popup.
	 * 
	 * @param clickEvent
	 *            instance of {@link ClickEvent}
	 */
	@UiHandler("cancelButton")
	public void onCancelClicked(ClickEvent clickEvent) {
		closePoup();
	}

	@UiHandler("backtoLibrary")
	public void onbacktoLibrarybuttonClicked(ClickEvent clickEvent) {
		closePoup();
	}

	@UiHandler("editCollection")
	public void onEditcollectionbuttonClicked(ClickEvent clickEvent) {
		
		//String collectionId = clickEvent.getRelativeElement().getAttribute("collectionId");
		//String collectionId = clickEvent.getRelativeElement().getAttribute("collectionId");
		
		Storage stockStore = Storage.getLocalStorageIfSupported();

		if (stockStore != null) {
			stockStore.setItem("tabKey", "resourceTab");
		}
				Map<String, String> parms = new HashMap<String, String>();
				parms.put("text", copycollectionTextbox.getValue());
				AppClientFactory.getInjector().getResourceService().checkProfanity(parms, new SimpleAsyncCallback<Boolean>() {

					@Override
					public void onSuccess(Boolean value) {
						isHavingBadWords=value;
						if(value){
							SetStyleForProfanity.SetStyleForProfanityForTextBox(copycollectionTextbox, errorLabel, value);
						}else{	
								String collectionTitle = copycollectionTextbox.getText();
								
								if(!collectionTitle.isEmpty())
								{
									closePoup();
								if(!isCustomizePopup){
									isCustomizePopup=true;
								Boolean loginFlag = false;
								if (AppClientFactory.isAnonymous()){
									loginFlag = true;
								}
								else
								{
									loginFlag = false;
								}
								collectionDo.setTitle(collectionTitle);
								AppClientFactory
								.getInjector()
								.getResourceService()
								.copyCollection(collectionDo, "true", null,
										getSaveCollectionAsyncCallback());
								}
						
							}
							else
							{
								errorLabel.setText(GL0693);
								errorLabel.setVisible(true);	
							}
						}
					}
				});
	}
	

	/**
	 * @return instance of collectionDo after collection save
	 */
	public SimpleAsyncCallback<CollectionDo> getSaveCollectionAsyncCallback() {
		if (saveCollectionAsyncCallback == null) {
			saveCollectionAsyncCallback = new SimpleAsyncCallback<CollectionDo>() {

				@Override
				public void onSuccess(CollectionDo result) {
						AppClientFactory.getPlaceManager().revealPlace(
							PlaceTokens.SHELF,
							new String[] {
									"id",
									result.getGooruOid()});
						AppClientFactory.fireEvent(new RefreshUserShelfCollectionsEvent());
					closePoup();
				}
			};
		}
		return saveCollectionAsyncCallback;
	}
	public class OnkeyPress implements KeyPressHandler
	{
		@Override
		public void onKeyPress(KeyPressEvent event) {
			String collTitle=copycollectionTextbox.getText().trim();
			if(collTitle.length()>=50){
				errorLabel.setText(GL0143);
				errorLabel.setVisible(true);
			}else{
				errorLabel.setText("");
				errorLabel.setVisible(false);
			}
			
		}
	}
	public class OnkeyUp implements KeyUpHandler{
			
			@Override
			public void onKeyUp(KeyUpEvent event) {
				String collTitle=copycollectionTextbox.getText().trim();
				if(collTitle.length()>=50){
					errorLabel.setText(GL0143);
					errorLabel.setVisible(true);
				}else{
					errorLabel.setText("");
					errorLabel.setVisible(false);
				}
				
			}
		}
}
