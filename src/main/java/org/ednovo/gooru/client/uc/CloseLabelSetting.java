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

import org.ednovo.gooru.shared.util.MessageProperties;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;

public abstract class CloseLabelSetting  extends FlowPanel implements ClickHandler,MessageProperties{
	private Label label;

	private Label removeLabel;
	/**
	 * Class constructor
	 * @param text for label
	 */
	public CloseLabelSetting(String text) {
		removeLabel = new Label();
		removeLabel.setStyleName(UcCBundle.INSTANCE.css().closeLabelRemoveInSetting());
		removeLabel.setText(GL_GRR_Close+" ");
		label = new Label();
		label.setStyleName(UcCBundle.INSTANCE.css().closeLabelText());
		label.setText(text);
		setStyleName(UcCBundle.INSTANCE.css().closeLabel());
		add(label);
		add(removeLabel);
		removeLabel.addClickHandler(this);
	}

	public abstract void onCloseLabelClick(ClickEvent event);

	public Label getLabelSource() {
		return removeLabel;
	}

	public String getSourceText() {
		return label.getText();
	}

	@Override
	public final void onClick(ClickEvent event) {
		if (event.getSource().equals(removeLabel)) {
			onCloseLabelClick(event);
			this.removeFromParent();
		}
	}
	
	/*Unused Method
	 * public void disableParentRemoveFunction() {
		removeLabel.getElement().getStyle().setDisplay(Display.NONE);
	}*/

}
