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
package org.ednovo.gooru.client.mvp.home;

import java.util.Map;

import org.ednovo.gooru.client.PlaceTokens;
import org.ednovo.gooru.client.SimpleAsyncCallback;
import org.ednovo.gooru.client.gin.AppClientFactory;
import org.ednovo.gooru.client.mvp.classpages.event.ClearClasspageListEvent;
import org.ednovo.gooru.client.mvp.home.event.SetTexasAccountEvent;
import org.ednovo.gooru.client.mvp.home.event.SetTexasPlaceHolderEvent;
import org.ednovo.gooru.client.mvp.home.library.events.StandardPreferenceSettingEvent;
import org.ednovo.gooru.client.mvp.search.event.SetHeaderZIndexEvent;
import org.ednovo.gooru.client.uc.AppPopUp;
import org.ednovo.gooru.client.uc.BlueButtonUc;
import org.ednovo.gooru.shared.model.user.UserDo;
import org.ednovo.gooru.shared.util.MessageProperties;
import org.ednovo.gooru.shared.util.StringUtil;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.http.client.URL;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Search Team
 *
 */
public class LogoutPopupVc extends Composite implements MessageProperties{

	public AppPopUp appPopUp;

	@UiField
	Anchor cancelAnr;

	@UiField
	BlueButtonUc okBtnUc;
	
	@UiField Label lblClassDismissed;
	
	private static final String HEAR_THE_BELL_TEXT = GL0188;

	private static LogoutPopupVcUiBinder uiBinder = GWT.create(LogoutPopupVcUiBinder.class);

	interface LogoutPopupVcUiBinder extends UiBinder<Widget, LogoutPopupVc> {
	}

	/**
	 * Class constructor , get confirm logout popup
	 */
	public LogoutPopupVc() {
		initWidget(uiBinder.createAndBindUi(this));
		appPopUp = new AppPopUp();
		appPopUp.setStyleName("removeResourcePopup");
		appPopUp.setContent(HEAR_THE_BELL_TEXT, uiBinder.createAndBindUi(this));
		
		lblClassDismissed.setText(GL0189);
		okBtnUc.setText(GL0190);
		cancelAnr.setText(GL0142);
		
		appPopUp.show();
		appPopUp.center();
		okBtnUc.getElement().setId("btnOk");
		cancelAnr.getElement().setId("lnkCancel");
	}

	/**
	 * Cancel the logout and hide logout popup
	 * @param clickEvent instance of {@link ClickEvent}
	 */
	@UiHandler("cancelAnr")
	public void onCancelClick(ClickEvent clickEvent) {
		appPopUp.hide();
		
		Window.enableScrolling(true);
        AppClientFactory.fireEvent(new SetHeaderZIndexEvent(0, true));
	}

	/**
	 * Logout from signed user and makes as a anonymous user 
	 * @param clickEvent instance of {@link ClickEvent}
	 */
	@UiHandler("okBtnUc")
	public void userLogout(ClickEvent clickEvent) {
		AppClientFactory.getInjector().getAppService().v2Signout(new SimpleAsyncCallback<UserDo>() {

			@Override
			public void onSuccess(UserDo result) {
				
			    String premiumAccountUserName = AppClientFactory.getLoggedInUser().getUsername();
				
				AppClientFactory.fireEvent(new ClearClasspageListEvent());
				
				Window.enableScrolling(true);
		        AppClientFactory.fireEvent(new SetHeaderZIndexEvent(0, true));
				AppClientFactory.setLoggedInUser(result);
				AppClientFactory.getInjector().getWrapPresenter().get().setLoginData(result);
				//AppClientFactory.resetPlace();
				if(premiumAccountUserName.equalsIgnoreCase("TexasTeacher")) {
					AppClientFactory.fireEvent(new SetTexasAccountEvent("failure"));
					AppClientFactory.fireEvent(new SetTexasPlaceHolderEvent(false));
				}else{
					AppClientFactory.fireEvent(new SetTexasAccountEvent("success"));
					AppClientFactory.fireEvent(new SetTexasPlaceHolderEvent(true));
				}
				AppClientFactory.fireEvent(new StandardPreferenceSettingEvent(null));

				if (AppClientFactory.getPlaceManager().getCurrentPlaceRequest().getNameToken().equalsIgnoreCase(PlaceTokens.HOME) || AppClientFactory.getPlaceManager().getCurrentPlaceRequest().getNameToken().equalsIgnoreCase(PlaceTokens.RESOURCE_SEARCH) || AppClientFactory.getPlaceManager().getCurrentPlaceRequest().getNameToken().equalsIgnoreCase(PlaceTokens.COLLECTION_SEARCH)){
					Map<String, String> map = StringUtil.splitQuery(Window.Location.getHref());
					if(map.containsKey("query"))
					{
						String queryVal = map.get("query");
						queryVal = queryVal.replaceAll("%5C1", "&");
						try
						{
						queryVal = URL.decodeQueryString(queryVal);
						}
						catch(Exception ex)
						{
							
						}
						map.put("query", queryVal);
					}
					if(map.containsKey("flt.subjectName"))
					{
						String subjectNameVal = map.get("flt.subjectName");
						subjectNameVal = subjectNameVal.replaceAll("%5C1", "&");
						try
						{
							subjectNameVal = URL.decodeQueryString(subjectNameVal);
						}
						catch(Exception ex)
						{
							
						}
						subjectNameVal = subjectNameVal.replace("+", " ");
						map.put("flt.subjectName", subjectNameVal);
					}
					map.remove("callback");
					map.remove("type");
					map.remove("userName");
					map.remove("account");
					AppClientFactory.getPlaceManager().revealPlace(AppClientFactory.getCurrentPlaceToken(), map);
				}else{
					AppClientFactory.getPlaceManager().revealPlace(PlaceTokens.HOME);
				}
				appPopUp.hide();
			}

		});

	}

}
