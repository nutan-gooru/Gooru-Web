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
import org.ednovo.gooru.client.SimpleAsyncCallback;
import org.ednovo.gooru.client.gin.AppClientFactory;
import org.ednovo.gooru.client.mvp.home.register.RegisterCBundle;
import org.ednovo.gooru.shared.model.user.UserDo;
import org.ednovo.gooru.shared.util.MessageProperties;
import org.ednovo.gooru.shared.util.StringUtil;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DeckPanel;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
/**
 * @fileName : SettingEmailEditLabelUc.java
 * 
 * @description :This class is used to change email in settingPage.
 * 
 * 
 * @version : 5.9
 * 
 * @date: Sep 20, 2013
 * 
 * @Author Gooru Team
 * 
 * @Reviewer:
 */
public class SettingEmailEditLabelUc extends Composite implements MessageProperties,HasValue<String> {

	private static SettingEmailEditLabelUcUiBinder uiBinder = GWT
			.create(SettingEmailEditLabelUcUiBinder.class);

	interface SettingEmailEditLabelUcUiBinder extends
			UiBinder<Widget, SettingEmailEditLabelUc> {
	}
	
	String EMAIL_REGEX = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
	
	@UiField
	protected Label editLabel,errorLabel;

	@UiField
	protected DeckPanel deckPanel;

	@UiField
	protected TextBox editTextBox;

	@UiField
	protected FocusPanel focusPanel;
	boolean emailAvailable;
	protected String text;
	private static final String EMAIL = GL0212.toLowerCase();
	@UiField(provided = true)
	UcCBundle res;
	public SettingEmailEditLabelUc() {
		this.res = UcCBundle.INSTANCE;
		initWidget(uiBinder.createAndBindUi(this));
		deckPanel.showWidget(0);
		
		editTextBox.getElement().setAttribute("maxlength", "500");
		editTextBox.addKeyUpHandler(new ValidateConfirmText());
		editTextBox.getElement().setId("txtEmail");
	}
	private class ValidateConfirmText implements KeyUpHandler {

		@Override
		public void onKeyUp(KeyUpEvent event) {
			checkCharacterLimit(editTextBox.getText());
			errorLabel.setVisible(false);
		}
	}

	/**
	 * Change to editable view
	 */
	public void switchToEdit() {

		if (deckPanel.getVisibleWidget() == 1)
			return;
		editTextBox.setText(getValue());	
		deckPanel.showWidget(1);
		editTextBox.setFocus(true);
		editTextBox.addStyleName(res.css().SettingEditEmail());
		

	}

	/**
	 * Change to label
	 */
	public void switchToLabel() {

		if (hasValidateData()) {
			try {
				AppClientFactory
						.getInjector()
						.getUserService()
						.getEmailId(editTextBox.getText(),
								new SimpleAsyncCallback<UserDo>() {
									@Override
									public void onSuccess(UserDo user) {
										emailAvailable=user.isAvailability();
										if (deckPanel.getVisibleWidget() == 0)
											return;

										if (editTextBox.getText().trim().length() > 0) {
												if(!emailAvailable){
											setValue(editTextBox.getText(), true); // fires events, too
											}
										}else {
											
											errorLabel.setVisible(true);
											errorLabel.setText(StringUtil.generateMessage(GL0082, EMAIL));
												return;
											
											
										}
										if ((getText() != null && text.trim().length() > 0)) {
											if(emailAvailable){
											errorLabel.setText(StringUtil.generateMessage(GL0428, EMAIL));	
											errorLabel.setVisible(true);
											}
											if(!emailAvailable){
											errorLabel.setVisible(false);
											deckPanel.showWidget(0);
											setText(editTextBox.getText());
											onEditDisabled(editTextBox.getText());
										}
																				
										}
									}
								});
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		//String text = getValue();

	/*	if ((getText() != null && text.trim().length() > 0)) {
			deckPanel.showWidget(0);
			setText(editTextBox.getText());
			onEditDisabled(editTextBox.getText());
			errorLabel.setVisible(false);
		}*/

	}

	public void cancel() {
		deckPanel.showWidget(0);
		editTextBox.setText(editLabel.getText());
		errorLabel.setVisible(false);
	}

	// Override this method to catch on blur
	/**
	 * @param text
	 */
	public void onEditDisabled(String text) {

	}

	// Override this method to catch on blur
	/**
	 * @param text
	 */
	public void checkCharacterLimit(String text) {

	}
	
	@Override
	public HandlerRegistration addValueChangeHandler(
			ValueChangeHandler<String> handler) {
		return addHandler(handler, ValueChangeEvent.getType());
	}

	@Override
	public String getValue() {
		return editTextBox.getText();
	}

	@Override
	public void setValue(String value) {

		/*editLabel.setText(value.length() > 50 ? value.substring(0, 50) + "..."
				: value);
	*/	editLabel.setText(value);
		editTextBox.setText(value);
	}

	/**
	 * @return placeholder
	 */
	

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
		setValue(text);
	}

	@Override
	public void setValue(String value, boolean fireEvents) {

		if (fireEvents)
			ValueChangeEvent.fireIfNotEqual(this, getValue(),value);
		setValue(value);
	}

	public TextBox getTextBoxSource() {
		return editTextBox;
	}
	public boolean hasValidateData() {
		boolean isValid = true;
		
		Boolean hasvalidData = editTextBox.getText().matches(EMAIL_REGEX);
		
		if ((editTextBox.getText() != null && !editTextBox.getText().isEmpty()) && !hasvalidData) {
			errorLabel.setText(StringUtil.generateMessage(GL0067, EMAIL));
			errorLabel.setVisible(true);
			isValid = false;
		}	
		if (editTextBox.getText() == null || editTextBox.getText().isEmpty() || editTextBox.getText().trim().equals(""))
		 { 
			errorLabel.setText(StringUtil.generateMessage(GL0082, EMAIL));
			errorLabel.setVisible(true);
			editTextBox.addStyleName(RegisterCBundle.INSTANCE.css().errorBoxStyle());
			isValid = false;
		 }
		return isValid;
	}
	
	

}

