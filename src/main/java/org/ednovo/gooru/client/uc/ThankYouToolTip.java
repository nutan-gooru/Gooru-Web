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


import org.ednovo.gooru.player.resource.client.view.resourceplayer.flag.FlagBundle;
import org.ednovo.gooru.shared.util.MessageProperties;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.Widget;

public class ThankYouToolTip extends PopupPanel implements MessageProperties {

	private static ThankYouToolTipUiBinder uiBinder = GWT
			.create(ThankYouToolTipUiBinder.class);

	interface ThankYouToolTipUiBinder extends UiBinder<Widget, ThankYouToolTip> {
	}
	@UiField
	HTMLEventPanel closeButton;
	
	@UiField Image popUpCloseButton;
	
	@UiField Button okButton;
	
	@UiField Label successPopupTitle,emailIdLbl,emailConfirmationText;
	@UiField Label successPopupMessage;
	@UiField HTMLPanel emailPopupThanksmsg;
	
	public ThankYouToolTip() {
		super(false);
		setWidget(uiBinder.createAndBindUi(this));
		this.getElement().getStyle().setZIndex(999999);
		this.setGlassStyleName(FlagBundle.IMAGEBUNDLEINSTANCE.flagstyle().glassStyle());
		setGlassEnabled(true);
		FlagBundle.IMAGEBUNDLEINSTANCE.flagstyle().ensureInjected();
		successPopupTitle.setText(GL0600);
		popUpCloseButton.setAltText(GL1050);
		successPopupMessage.setText(GL0615);
		emailConfirmationText.setText(GL0648);
		okButton.setText(GL0190);
		popUpCloseButton.setResource(FlagBundle.IMAGEBUNDLEINSTANCE.closeFlagPopUpImages());
		okButton.getElement().setAttribute("id","okButton");
		successPopupMessage.setVisible(true);
		emailPopupThanksmsg.setVisible(false);
	}
	
	
	public void setTitleData(String title, String emailId) {
		successPopupTitle.setText(title);
		successPopupMessage.setVisible(false);
		emailPopupThanksmsg.setVisible(true);
		emailIdLbl.setText(emailId);

	}
	
	@UiHandler("okButton")
	public void onClickOfOkButton(ClickEvent event) {
		Window.enableScrolling(true);
		this.hide();
				
	}

	@UiHandler("closeButton")
	public void onClickOfcloseButton(ClickEvent event) {
		Window.enableScrolling(true);
		this.hide();
	}
	
}
