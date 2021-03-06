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
/**
 * 
 */
package org.ednovo.gooru.server.form;

import org.ednovo.gooru.server.serializer.JsonSerializer;
import org.restlet.data.Form;

/**
 * @author Search Team
 *
 */
public class ResourceFormFactory {
	
	/**
	 * Generate form object, respect to individual object 
	 * @param object instance of the {@link Object} 
	 * @param objectName name of the object
	 * @return form
	 */
	public static Form generateDataForm(Object object, String objectName) {
		Form form = new Form();
		
		String data = "{\""+objectName+"\" : " + JsonSerializer.serialize(object) +"}";
		form.add("data", data);
		return form;
	}
	/**
	 * Generate form object, respect to individual object 
	 * @param object instance of the {@link Object} 
	 * @param objectName name of the object
	 * @return form
	 */
	public static String generateStringDataForm(Object object, String objectName) {
		
		String data = null;
		
		if (objectName != null){
			data = "{\""+objectName+"\" : " + JsonSerializer.serialize(object) +"}";
		}else{
			data = JsonSerializer.serialize(object);
		}
		return data;
	}

	/**
	 * Generate form object, respect to individual object 
	 * @param object instance of the {@link Object} 
	 * @param objectName name of the object
	 * @return form
	 */
	public static String generateStringMultiDataForm(Object object, String objectName) {
		
		String data = null;
		
		if (objectName != null){
			data = "{\""+objectName+"\" : [" + JsonSerializer.serialize(object) +"]}";
		}else{
			data = JsonSerializer.serialize(object);
		}
		return data;
	}

	/**
	 * Set collection meta info to {@link Form}
	 * @param title of the collection
	 * @param description of the collection
	 * @param grade of the collection
	 * @param sharing of the collection
	 * @param vocabulary of the collection
	 * @param taxonomyCode of the collection
	 * @param updateTaxonomyByCode that codeId or code
	 * @param action add or delete
	 * @return form
	 */
	public static Form updateCollection(String title, String description, String grade, String sharing, String vocabulary, String taxonomyCode, String updateTaxonomyByCode, String mediaType, String action) {
		Form form = new Form();
		if(title != null){
			form.add("title", title);
		}
		if(description != null){
			form.add("description", description);
		}
		if(grade != null){
			form.add("grade", grade);
		}
		if(sharing != null){
			form.add("sharing", sharing);
		}
		if(vocabulary != null){
			form.add("vocabulary", vocabulary);
		}
		if(taxonomyCode != null){
			form.add("taxonomyCode", taxonomyCode);
		}
		if(updateTaxonomyByCode != null){
			form.add("updateTaxonomyByCode", updateTaxonomyByCode);
		}
		if(mediaType != null){
			form.add("mediaType", mediaType);
		}
		
		if(action != null){
			form.add("action", action);
		}
		return form;
	}
	
	/**
	 * Set collection meta info to {@link Form}
	 * @param title of the collection
	 * @param description of the collection
	 * @param grade of the collection
	 * @param sharing of the collection
	 * @param vocabulary of the collection
	 * @param taxonomyCode of the collection
	 * @param updateTaxonomyByCode that codeId or code
	 * @param action add or delete
	 * @return form
	 */
	public static Form updateCollectionInfo(String title, String keyPointsVal) {
		Form form = new Form();

		
		String titleName = "title";
		String keyPointsKey = "keyPoints";
		String dataObj = "{\""+titleName+"\" : " + title +",\""+keyPointsKey+"\" : " + keyPointsVal +"}";
		
		String objectName = "collection";
		String data = "{\""+objectName+"\" : " + JsonSerializer.serialize(dataObj) +"}";
		
		form.add("data", data);
		return form;
	}
	
	/**
	 * Set collection item meta info to {@link Form}
	 * @param narration of the collectionItem
	 * @param narrationType of the collectionItem
	 * @param start of the collectionItem
	 * @param stop of the collectionItem
	 * @return form
	 */
	public static Form updateCollectionItem(String narration, String narrationType, String start, String stop){
		Form form = new Form();
		if(narration != null){
			form.add("narration", narration);
		}
		if(narrationType != null){
			form.add("narrationType", narrationType);
		}
		if(start != null){
			form.add("start", start);
		}
		if(stop != null){
			form.add("stop", stop);
		}
		return form;
		
		
	}
	public static Form updateNarrationItem(String narration, String narrationType){
		Form form = new Form();
		if(narration != null){
			form.add("narration", narration);
		}
		if(narrationType != null){
			form.add("narrationType", narrationType);
		}
		return form;
		
		
	}
}
