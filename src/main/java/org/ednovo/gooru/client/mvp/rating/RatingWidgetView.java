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
package org.ednovo.gooru.client.mvp.rating;



import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.Widget;

public class RatingWidgetView extends Composite{

	@UiField InlineLabel ratingCountLabel,star_1,star_2,star_3,star_4,star_5;


	private static RatingWidgetViewUiBinder uiBinder = GWT.create(RatingWidgetViewUiBinder.class);

	interface RatingWidgetViewUiBinder extends UiBinder<Widget, RatingWidgetView> {

	}

	public RatingWidgetView(){
		initWidget(uiBinder.createAndBindUi(this));
	}

	public InlineLabel getRatingCountLabel(){
		return ratingCountLabel;
	}

	public void setAvgStarRating(double averageRating) {
		Integer roundOffAvg = (int) Math.round(averageRating);
		if(roundOffAvg==1){
			star_1.addStyleName("filled");
		}else if(roundOffAvg==2){
			star_1.addStyleName("filled");
			star_2.addStyleName("filled");
		}else if(roundOffAvg==3){
			star_1.addStyleName("filled");
			star_2.addStyleName("filled");
			star_3.addStyleName("filled");
		}else if(roundOffAvg==4){
			star_1.addStyleName("filled");
			star_2.addStyleName("filled");
			star_3.addStyleName("filled");
			star_4.addStyleName("filled");
		}else if(roundOffAvg==5){
			star_1.addStyleName("filled");
			star_2.addStyleName("filled");
			star_3.addStyleName("filled");
			star_4.addStyleName("filled");
			star_5.addStyleName("filled");
		}
	}
}