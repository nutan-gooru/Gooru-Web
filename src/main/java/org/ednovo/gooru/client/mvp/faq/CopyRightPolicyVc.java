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
package org.ednovo.gooru.client.mvp.faq;

import org.ednovo.gooru.client.gin.AppClientFactory;
import org.ednovo.gooru.client.mvp.search.event.SetHeaderZIndexEvent;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Search Team
 *
 */
public abstract class CopyRightPolicyVc extends PopupPanel {

	@UiField
	Button termsCloseBtn;

	private static CopyRightPolicyUiBinder uiBinder = GWT.create(CopyRightPolicyUiBinder.class);

	interface CopyRightPolicyUiBinder extends UiBinder<Widget, CopyRightPolicyVc> {
	}

	/**
	 * Class constructor
	 */
	public CopyRightPolicyVc() {
		super(false);
		setWidget(uiBinder.createAndBindUi(this));
		setGlassEnabled(true);
		termsCloseBtn.getElement().setId("btnOk");
	}

	/**
	 * @param clickEvent instance of {@link ClickEvent} , hide CopyRightPolicyVc popup
	 */
	@UiHandler("termsCloseBtn")
	public void privacyCloseButtonClick(ClickEvent clickEvent) {
		this.hide();
		Window.enableScrolling(true);
		AppClientFactory.fireEvent(new SetHeaderZIndexEvent(0, true));
		openParentPopup();
	}
	
	public abstract void openParentPopup();

}
