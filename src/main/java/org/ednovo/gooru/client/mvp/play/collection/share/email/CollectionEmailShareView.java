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
package org.ednovo.gooru.client.mvp.play.collection.share.email;



import java.util.HashMap;
import java.util.Map;

import org.ednovo.gooru.client.SimpleAsyncCallback;
import org.ednovo.gooru.client.gin.AppClientFactory;
import org.ednovo.gooru.client.uc.PlayerBundle;
import org.ednovo.gooru.client.uc.TextBoxWithPlaceholder;
import org.ednovo.gooru.client.uc.EmailShareUc.CheckProfanityInOnBlur;
import org.ednovo.gooru.client.util.SetStyleForProfanity;
import org.ednovo.gooru.shared.util.MessageProperties;
import org.ednovo.gooru.shared.util.StringUtil;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.BodyElement;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.IFrameElement;
import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.logical.shared.InitializeEvent;
import com.google.gwt.event.logical.shared.InitializeHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RichTextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public abstract class CollectionEmailShareView extends PopupPanel implements MessageProperties {

	@UiField Label fromValidation, toValidation, lblEmailFriend, lblFrom, lblTo, lblSendMeCopy, lblSubject, lblMessage, fromLbl,mandatoryErrorLbl,mandatoryErrorRichTextArea,noteTxt;
	
	@UiField CheckBox checkCopyEmail;
	
	@UiField Button btnSend, cancelLbl;
	
	@UiField TextBoxWithPlaceholder toTxt, fromTxt;
	
	@UiField TextBox subTxt;

	@UiField RichTextArea msgTxa;

	private static boolean isvalid;

	private String cfm = "no";
	
	private static final String AT_SYMBOL = "@";

	String GL_EMAIL_SUBJECT = GL1443;
	String GL0219 = GL1444;
	
	boolean isHavingBadWordsInTextbox=false,isHavingBadWordsInRichText=false;
	
	private static CollectionEmailShareViewUiBinder uiBinder = GWT.create(CollectionEmailShareViewUiBinder.class);

	interface CollectionEmailShareViewUiBinder extends
			UiBinder<Widget, CollectionEmailShareView> {
	}


	public CollectionEmailShareView( String mailSubject, String mailDescription) {
		setWidget(uiBinder.createAndBindUi(this));

		PlayerBundle.INSTANCE.getPlayerStyle().ensureInjected();
		this.getElement().getStyle().setZIndex(999999);
		this.setGlassEnabled(true);
		fromValidation.setText(GL0215);
		toValidation.setText(GL0216);
		lblEmailFriend.setText(GL0222);
		lblFrom.setText(GL0223 + GL_SPL_SEMICOLON);
		lblTo.setText(GL0224 + GL_SPL_SEMICOLON);
		lblSendMeCopy.setText(GL0225);
		lblSubject.setText(GL0226 + GL_SPL_SEMICOLON);
		lblMessage.setText(GL0227 + GL_SPL_SEMICOLON);
		btnSend.setText(GL0228);
		cancelLbl.setText(GL0142);
		
		noteTxt.setText(GL1636);
		
		mandatoryErrorLbl.setVisible(false);
		mandatoryErrorRichTextArea.setVisible(false);
		fromLbl.setVisible(false);
		fromValidation.setVisible(false);
		toValidation.setVisible(false);
		toTxt.getElement().setId("tbTo");
		subTxt.getElement().setId("tbSubject");
		subTxt.setText(mailSubject);
		fromTxt.getElement().setId("tbFrom");
		msgTxa.getElement().setId("taMessage");
		btnSend.getElement().setId("btnSend");
		fromTxt.setMaxLength(50);
		if(AppClientFactory.isAnonymous()){
//			fromLbl.setVisible(false);
			fromTxt.setVisible(true);
//			fromTxt.getElement().setAttribute("placeholder",GL1442);
			fromTxt.setPlaceholder(GL1442_1);
		}else{
//			fromTxt.setText(AppClientFactory.getLoggedInUser().getEmailId());
			if(AppClientFactory.getLoggedInUser().getFirstName()!=null && AppClientFactory.getLoggedInUser().getLastName()!=null){
//				fromLbl.setVisible(true);
				fromTxt.setVisible(true);
				fromTxt.setText(AppClientFactory.getLoggedInUser().getFirstName() + " " +AppClientFactory.getLoggedInUser().getLastName());
			}else{
//				fromLbl.setVisible(false);
				fromTxt.setVisible(true);
				fromTxt.setPlaceholder(GL1442_1);
			}
		}
//		toTxt.getElement().setAttribute("placeholder",GL1184_1);
		toTxt.setPlaceholder(GL1184_1);
		msgTxa.setHTML(mailDescription);
		isvalid = true;
		this.setGlassEnabled(true);
		this.center();
		this.getGlassElement().getStyle().setZIndex(99999);
		
		//this.setGlassStyleName(PlayerBundle.INSTANCE.getPlayerStyle().setGlassPanelStyle());
		msgTxa.addInitializeHandler(new InitializeHandler() {
			@Override
			public void onInitialize(InitializeEvent event) {
				    Document document = IFrameElement.as(msgTxa.getElement()).getContentDocument();
	                BodyElement body = document.getBody();
	                body.setAttribute("style", "font-family: Arial;font-size:12px;");
			}
		});
		fromTxt.addBlurHandler(new CheckProfanityInOnBlur(fromTxt,null, fromValidation));
		subTxt.addBlurHandler(new CheckProfanityInOnBlur(subTxt,null, mandatoryErrorLbl));
		msgTxa.addBlurHandler(new CheckProfanityInOnBlur(null,msgTxa, mandatoryErrorRichTextArea));
	}
	
	@UiHandler("cancelLbl")
	public void onCancelClickEvent(ClickEvent event) {
		closeEmailpanel();
	}

	private void closeEmailpanel() {
		this.hide();
	}
	
	@UiHandler("btnSend")
	public void onSendBtnClickEvent(ClickEvent event) {
		isvalid = true;
/*		if(fromTxt.isVisible()) {
			if (fromTxt.getText().equals("")) {
				fromValidation.setText(GL0215);
				fromValidation.setVisible(true);
				isvalid = false;
			}
			if(fromTxt.getText() != null ||!fromTxt.getText().equals("")){
				String EMAIL_REGEX = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
				String strEmails = fromTxt.getText().trim();
				String emailIds[] = strEmails.split("\\s*,\\s*");
				if (strEmails.contains(",")){
					emailIds = strEmails.split("\\s*,\\s*");
				}else if (strEmails.contains(";")){
					emailIds = strEmails.split("\\s*;\\s*");
				}
				if(emailIds.length > 1 ){
					fromValidation.setText(GL1027_1);
					fromValidation.setVisible(true);
					isvalid = false;
				}
			}
			if ((fromTxt.getText() != null && !fromTxt.getText().isEmpty())
					&& !fromTxt.getText().contains(AT_SYMBOL)) {
				fromValidation.setText(GL1027);
				fromValidation.setVisible(true);
				isvalid = false;
			}
		}*/
		
		if(!toTxt.getText().trim().equals("")){
			String EMAIL_REGEX = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
			String strEmails = toTxt.getText().trim();
			String emailIds[] = strEmails.split("\\s*,\\s*");
			if (strEmails.contains(",")){
				emailIds = strEmails.split("\\s*,\\s*");
			}else if (strEmails.contains(";")){
				emailIds = strEmails.split("\\s*;\\s*");
			}
			
			for (int i=0; i<emailIds.length; i++){
				boolean to = emailIds[i].matches(EMAIL_REGEX);
				if(to){
//					isvalid = true;
				}else{
					toValidation.setText(StringUtil.generateMessage(GL1019, emailIds[i]));
					toValidation.setVisible(true);
					isvalid = false;
					break;
				}
			}
		}

		if (toTxt.getText().equals("")) {
			toValidation.setText(GL0216);
			toValidation.setVisible(true);
			isvalid = false;
		}
		if ((toTxt.getText() != null && !toTxt.getText().isEmpty())
				&& !toTxt.getText().contains(AT_SYMBOL)) {

			toValidation.setText(GL1027);
			toValidation.setVisible(true);
			isvalid = false;
		}
		if(isvalid){
			final Map<String, String> parms = new HashMap<String, String>();
			parms.put("text", subTxt.getValue());
			AppClientFactory.getInjector().getResourceService().checkProfanity(parms, new SimpleAsyncCallback<Boolean>() {
				@Override
				public void onSuccess(Boolean value) {
						isHavingBadWordsInTextbox = value;
						if(value){
							SetStyleForProfanity.SetStyleForProfanityForTextBox(subTxt, mandatoryErrorLbl,value);
						}else{
							parms.put("text", msgTxa.getText());
							AppClientFactory.getInjector().getResourceService().checkProfanity(parms,new SimpleAsyncCallback<Boolean>() {
								
								@Override
								public void onSuccess(Boolean result) {
									isHavingBadWordsInRichText=result;
									if(result){
										SetStyleForProfanity.SetStyleForProfanityForRichTextArea(msgTxa, mandatoryErrorRichTextArea, result);
									}else{
										if (!isHavingBadWordsInRichText && !isHavingBadWordsInTextbox) {
											String fromEmail = "";
					/*						if(fromTxt.isVisible()) {
												fromEmail = fromTxt.getText();
											} else {*/
												fromEmail = fromTxt.getText();
										/*	}*/
											sendEmail(fromEmail, toTxt.getText(), cfm, subTxt.getText(), msgTxa.getHTML());
										}
									}
								}
							});
						}
				}
			});
		}
	}

	@UiHandler("fromTxt")
	public void onKeydownEvent(KeyDownEvent event) {
		fromValidation.setVisible(false);
	}

	@UiHandler("toTxt")
	public void onKeydownClickEvent(KeyDownEvent event) {
		toValidation.setVisible(false);
	}

	@UiHandler("checkCopyEmail")
	public void oncheckCopyEmailEvent(ClickEvent event) {

		if (checkCopyEmail.getValue()) {
			cfm = "yes";
		} else {
			cfm = "no";
		}
	}
	
	public class CheckProfanityInOnBlur implements BlurHandler{
		private TextBox textBox;
		private Label label;
		private RichTextArea richTextArea;
		public CheckProfanityInOnBlur(TextBox textBox,RichTextArea richTextArea,Label label){
			this.textBox=textBox;
			this.label=label;
			this.richTextArea=richTextArea;
		}
		@Override
		public void onBlur(BlurEvent event) {
			Map<String, String> parms = new HashMap<String, String>();
			if(textBox!=null){
				parms.put("text", textBox.getValue());
			}else{
				parms.put("text", richTextArea.getText());
			}
			btnSend.setEnabled(false);
			AppClientFactory.getInjector().getResourceService().checkProfanity(parms, new SimpleAsyncCallback<Boolean>() {

				@Override
				public void onSuccess(Boolean value) {
					btnSend.setEnabled(true);
					if(textBox!=null){
						isHavingBadWordsInTextbox = value;
						SetStyleForProfanity.SetStyleForProfanityForTextBox(textBox, label, value);
					}else{
						isHavingBadWordsInRichText=value;
						SetStyleForProfanity.SetStyleForProfanityForRichTextArea(richTextArea, label, value);
					}
					
				}
			});
		}
	}
	
	@UiHandler("fromTxt")
	public void fromTxtKeyUpEvent(KeyUpEvent event){
		String fromTxtText=fromTxt.getText();
		if(fromTxtText.length()>=50){
			//fromLbl.setVisible(true);
		}else{
			fromValidation.setVisible(false);
		}
	}
	
	public  abstract void sendEmail(String fromEmail,String toEmail,String copyEmail,String subject,String message);
}
