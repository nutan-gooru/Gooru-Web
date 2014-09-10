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
package org.ednovo.gooru.client.mvp.classpages.unitdetails;
import org.ednovo.gooru.client.mvp.classpages.unitdetails.personalize.PersonalizeUnitPresenter;
import org.ednovo.gooru.shared.model.content.ClasspageDo;

import com.google.gwt.event.shared.EventBus;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.PresenterWidget;
public class UnitAssignmentPresenter extends PresenterWidget<IsUnitAssignmentView> implements UnitAssignmentUiHandlers{

	
	
	public static final  Object _SLOT = new Object();
	
	private PersonalizeUnitPresenter studentPersonalizePresenter = null;
	
	@Inject
	public UnitAssignmentPresenter(EventBus eventBus, IsUnitAssignmentView view, PersonalizeUnitPresenter studentPersonalizePresenter) {
		super(eventBus, view);
		getView().setUiHandlers(this);
		this.studentPersonalizePresenter = studentPersonalizePresenter;
		
	}
	
	@Override
	public void setClasspageData(ClasspageDo classpageDo){
		System.out.println("in set classpage ... ");
		studentPersonalizePresenter.setClasspageData(classpageDo);
		setInSlot(_SLOT, studentPersonalizePresenter,false);
	}
	
}