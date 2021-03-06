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
package org.ednovo.gooru.server.service;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.ednovo.gooru.client.PlaceTokens;
import org.ednovo.gooru.client.service.UserService;
import org.ednovo.gooru.server.annotation.ServiceURL;
import org.ednovo.gooru.server.deserializer.ResourceDeserializer;
import org.ednovo.gooru.server.form.ResourceFormFactory;
import org.ednovo.gooru.server.request.JsonResponseRepresentation;
import org.ednovo.gooru.server.request.ServiceProcessor;
import org.ednovo.gooru.server.request.UrlToken;
import org.ednovo.gooru.server.serializer.JsonDeserializer;
import org.ednovo.gooru.shared.exception.GwtException;
import org.ednovo.gooru.shared.model.user.BiographyDo;
import org.ednovo.gooru.shared.model.user.GenderDo;
import org.ednovo.gooru.shared.model.user.ProfileDo;
import org.ednovo.gooru.shared.model.user.ProfilePageDo;
import org.ednovo.gooru.shared.model.user.ProfileV2Do;
import org.ednovo.gooru.shared.model.user.SettingDo;
import org.ednovo.gooru.shared.model.user.UserDo;
import org.ednovo.gooru.shared.model.user.V2UserDo;
import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.ext.json.JsonRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Search Team
 * 
 */
@Service("userService")
@ServiceURL("/userService")
public class UserServiceImpl extends BaseServiceImpl implements UserService {

	@Autowired
	ResourceDeserializer resourceDeserializer;

	private static final long serialVersionUID = -7268580337769405632L;
	
	private static final String BY_EMAIL_ID = "emailId";
	
	private static final String GOORU_CLASSIC_URL = "gooruClassicUrl";
	
	private static final String REGISTRATION_TYPE = "registrationType";
	
	private static final String GOORU_BASE_URL = "gooruBaseUrl";
	
	private static final String CHILD = "Child";
	private static final String OPTIONALVALUE="optionalValue";
	private static final String PARTY_CUSTOM_FIELD = "partyCustomField";
	private static final String PROFILE = "profile";
	
	private static final String USER_META_ACTIVE_FLAG = "&userMetaActiveFlag=1";

	@Override
	public UserDo getEmailId(String emailId) {
		return getEmailId(emailId, BY_EMAIL_ID);
	}

	@Override
	public UserDo getEmailId(String emailId, String type) {
	  	UserDo userDo = null;
		JsonRepresentation jsonRep = null;
		String url = UrlGenerator.generateUrl(getRestEndPoint(), UrlToken.V2_USER_AVAILABILITY, type,getLoggedInSessionToken(), emailId);
		JsonResponseRepresentation jsonResponseRep = ServiceProcessor.get(url, getRestUsername(), getRestPassword());
		jsonRep = jsonResponseRep.getJsonRepresentation();
		try {
			userDo = JsonDeserializer.deserialize(jsonRep.getJsonObject().toString(), UserDo.class);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return userDo;
	}

	@Override
	public void registerUser(Map<String, String> params) {
		params.put(GOORU_CLASSIC_URL, URLEncoder.encode(getHomeEndPoint() + "#" + PlaceTokens.HOME));
		JsonRepresentation jsonRep = null;
		String url = UrlGenerator.generateUrl(getRestEndPoint(), UrlToken.REGISTER_USER, params, getLoggedInSessionToken());
		JsonResponseRepresentation jsonResponseRep = ServiceProcessor.post(url, getRestUsername(), getRestPassword());
		jsonRep = jsonResponseRep.getJsonRepresentation();
		try {
			JsonDeserializer.deserialize(jsonRep.getJsonObject().toString(), UserDo.class);
		} catch (JSONException e) {
			e.printStackTrace();
		} 
	}

	@Override
	public UserDo getRegistredUserDetails(String gooruUid) {
		UserDo userDo = null;
		String url = UrlGenerator.generateUrl(getRestEndPoint(), UrlToken.GET_REGISTERED_USER_DETAILS, gooruUid, getLoggedInSessionToken());
		JsonRepresentation jsonRep = null;
		JsonResponseRepresentation jsonResponseRep = ServiceProcessor.get(url, getRestUsername(), getRestPassword());
		jsonRep = jsonResponseRep.getJsonRepresentation();
		try {
			userDo = JsonDeserializer.deserialize(jsonRep.getJsonObject().toString(), UserDo.class);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return userDo;
	}

	@Override
	public SettingDo getUserProfileDetails(String gooruUid) {
		SettingDo settingeDo = null;
		String userUid = getLoggedInUserUid();
		String url = UrlGenerator.generateUrl(getRestEndPoint(), UrlToken.GET_USER_PROFILE_DETAILS, userUid, getLoggedInSessionToken());
		JsonRepresentation jsonRep = null;
		JsonResponseRepresentation jsonResponseRep = ServiceProcessor.get(url, getRestUsername(), getRestPassword());
		jsonRep = jsonResponseRep.getJsonRepresentation();
		try {
			settingeDo = JsonDeserializer.deserialize(jsonRep.getJsonObject().toString(), SettingDo.class);
		} catch (JSONException e) {
			e.printStackTrace();
		} 
		return settingeDo;
	}
	
	@Override
	public V2UserDo getV2UserProfileDetails(String gooruUid) {
		V2UserDo settingeDo = null;
		String userUid = getLoggedInUserUid();
		String url = UrlGenerator.generateUrl(getRestEndPoint(), UrlToken.GET_USER_PROFILE_DETAILS, userUid, getLoggedInSessionToken());
		JsonRepresentation jsonRep = null;
		JsonResponseRepresentation jsonResponseRep = ServiceProcessor.get(url, getRestUsername(), getRestPassword());
		jsonRep = jsonResponseRep.getJsonRepresentation();
		try {
			settingeDo = JsonDeserializer.deserialize(jsonRep.getJsonObject().toString(), V2UserDo.class);
		} catch (JSONException e) {
			e.printStackTrace();
		} 
		return settingeDo;
	}

	@Override
	public ProfileDo updateUserDetails(String gooruUid, String token, Map<String, String> params) {
		ProfileDo profileDo = null;
		JsonRepresentation jsonRep = null;
		String url = UrlGenerator.generateUrl(getRestEndPoint(), UrlToken.UPDATE_REGISTER_USER, gooruUid, getLoggedInSessionToken());
		JsonResponseRepresentation jsonResponseRep = ServiceProcessor.put(url, getRestUsername(), getRestPassword(), generateFormFromParamters(params));
		jsonRep = jsonResponseRep.getJsonRepresentation();
		String text = null;
		try {
			text = jsonRep.getText();
			profileDo = JsonDeserializer.deserialize(jsonRep.getJsonObject().toString(), ProfileDo.class);
			setUserFilterProperties(profileDo.getUser());
			if (params.containsKey(REGISTRATION_TYPE) && !params.get(REGISTRATION_TYPE).equalsIgnoreCase(CHILD)) {
				setLoggedInSessionToken(token);
				setLoggedInUserUid(profileDo.getUser().getGooruUId());
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new GwtException(text != null ? text : e.getMessage());
		}
		return profileDo;
	}

	@Override
	public void resendConfirmationMail(Map<String, String> params) {
		params.put(GOORU_CLASSIC_URL, URLEncoder.encode(getHomeEndPoint() + "#" + PlaceTokens.HOME));
		String url = UrlGenerator.generateUrl(getRestEndPoint(), UrlToken.SEND_CONFIRMATION_MAIL, params, getLoggedInSessionToken());
		ServiceProcessor.post(url, getRestUsername(), getRestPassword());
	}

	/*@Override
	public UserDo getUser(String userUid) {
		return getUserInfo(userUid);
	}*/

	@Override
	public Map<String, Object> forgotPassword(String emailId) {
		JsonRepresentation jsonRep = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put(GOORU_CLASSIC_URL, URLEncoder.encode(getHomeEndPoint() + "#" + PlaceTokens.HOME));
		String url = UrlGenerator.generateUrl(getRestEndPoint(), UrlToken.FORGOT_PASSWORD, params, getLoggedInSessionToken(), emailId);
		JsonResponseRepresentation jsonResponseRep = ServiceProcessor.post(url, getRestUsername(), getRestPassword());
		jsonRep = jsonResponseRep.getJsonRepresentation();
		return resourceDeserializer.forgotPassword(jsonRep);
	}

	@Override
	public Map<String, Object> resetCredential(String password, String token) {
		JsonRepresentation jsonRep = null;
		String url = UrlGenerator.generateUrl(getRestEndPoint(), UrlToken.RESET_CREDENTIAL, getLoggedInSessionToken(), password, token);
		JsonResponseRepresentation jsonResponseRep = ServiceProcessor.post(url, getRestUsername(), getRestPassword());
		jsonRep = jsonResponseRep.getJsonRepresentation();
		if(jsonResponseRep.getStatusCode()==400){
			return resourceDeserializer.resetPassword(jsonRep,400); 
		}else{
			return resourceDeserializer.resetPassword(jsonRep,0);
		}
		
	}

	@Override
	public UserDo updateUserViewFlag(String gooruUid, Integer viewFlag) throws GwtException {
		UserDo userDo = null;
		String url = UrlGenerator.generateUrl(getRestEndPoint(), UrlToken.UPDATE_USER_VIEW, gooruUid,getLoggedInSessionToken(), String.valueOf(viewFlag));
		JsonRepresentation jsonRep = null;
		JsonResponseRepresentation jsonResponseRep = ServiceProcessor.get(url, getRestUsername(), getRestPassword());
		jsonRep = jsonResponseRep.getJsonRepresentation();
		try {
			userDo = JsonDeserializer.deserialize(jsonRep.getJsonObject().toString(), UserDo.class);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return userDo;
	}

	@Override
	public SettingDo updateProfileSettings(String gooruUid,	 Map<String, String> params) throws GwtException {
		SettingDo settingDo = null;
		String userUid = getLoggedInUserUid();
		JsonRepresentation jsonRep = null;
		String url = UrlGenerator.generateUrl(getRestEndPoint(), UrlToken.UPDATE_USER_PROFILE_DETAILS, userUid, getLoggedInSessionToken());
		
		JsonResponseRepresentation jsonResponseRep = ServiceProcessor.put(url, getRestUsername(), getRestPassword(), generateFormFromParamters(params));
		jsonRep = jsonResponseRep.getJsonRepresentation();
		String text="";
		try {
			text = jsonRep.getText();
			settingDo = JsonDeserializer.deserialize(jsonRep.getJsonObject().toString(), SettingDo.class);
		} catch (Exception e) {
			throw new GwtException(text != null ? text : e.getMessage());
		}
		return settingDo;
	}

	
	public ProfilePageDo deserializeProfilePage(JsonRepresentation jsonRep) {
		if (jsonRep != null && jsonRep.getSize() != -1) {
			try {
				
				ProfilePageDo profilePageDo = JsonDeserializer.deserialize(jsonRep.getJsonObject().toString(), ProfilePageDo.class);
				
				return profilePageDo;
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return new ProfilePageDo();
	}
	
	
	@Override
	public ProfilePageDo updateUserProfileVisibility(String gooruUid,
		String optionalValue) throws GwtException {
		JsonRepresentation jsonRep = null;
		ProfilePageDo profilePageDo = new ProfilePageDo();
		profilePageDo.setOptionalValue(optionalValue);
		profilePageDo.setCategory("user_meta");
		profilePageDo.setOptionalKey("show_profile_page");
		String url = UrlGenerator.generateUrl(getRestEndPoint(), UrlToken.UPDATE_USER_PROFILE_VISIBILTY, getLoggedInUserUid(), getLoggedInSessionToken());
		String formData = ResourceFormFactory.generateStringDataForm(profilePageDo,PARTY_CUSTOM_FIELD);
		
		JsonResponseRepresentation jsonResponseRep = ServiceProcessor.put(url, getRestUsername(), getRestPassword(), formData);
		jsonRep = jsonResponseRep.getJsonRepresentation();
		//return deserializeProfilePage(jsonRep);
		return profilePageDo;
		
	}

	@Override
	public ProfilePageDo getUserProfilePage(String gooruUid) throws GwtException {
		ProfilePageDo profilePageDo = null;
		String userUid = getLoggedInUserUid();
		String url = UrlGenerator.generateUrl(getRestEndPoint(), UrlToken.GET_USER_PROFILE_PAGE, userUid, getLoggedInSessionToken());
		JsonRepresentation jsonRep = null;
		JsonResponseRepresentation jsonResponseRep = ServiceProcessor.get(url, getRestUsername(), getRestPassword());
		jsonRep = jsonResponseRep.getJsonRepresentation();
		try {
			profilePageDo = JsonDeserializer.deserialize(jsonRep.getJsonObject().toString(), ProfilePageDo.class);
		} catch (JSONException e) {
			e.printStackTrace();
		} 
		return profilePageDo;
	}

	@Override
	public ProfilePageDo getUserPublicProfilePage(String gooruUid) throws GwtException {
		ProfilePageDo profilePageDo = null;
		String url = UrlGenerator.generateUrl(getRestEndPoint(), UrlToken.GET_USER_PROFILE_PAGE, gooruUid, getLoggedInSessionToken());
		JsonRepresentation jsonRep = null;
		JsonResponseRepresentation jsonResponseRep = ServiceProcessor.get(url, getRestUsername(), getRestPassword());
		jsonRep = jsonResponseRep.getJsonRepresentation();
		try {
			if(jsonRep.getText()!=null&&!jsonRep.getText().equals("null")&&!jsonRep.getText().equals("")){
				profilePageDo = JsonDeserializer.deserialize(jsonRep.getJsonObject().toString(), ProfilePageDo.class);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return profilePageDo;
	}

	@Override
	public BiographyDo updateProfileBiography(String gooruUid,String biography,String role,String firstName,String lastName,String gender) throws GwtException {
		JsonRepresentation jsonRep = null;
		String url = UrlGenerator.generateUrl(getRestEndPoint(), UrlToken.UPDATE_USER_BIOGRAPHY, getLoggedInUserUid(), getLoggedInSessionToken());
		BiographyDo biographyDo = new BiographyDo();
		biographyDo.setAboutMe(biography);
		biographyDo.setSubject(role);
		biographyDo.setFirstName(firstName);
		biographyDo.setLastName(lastName);
		biographyDo.setGenderId(gender);
		String formData = ResourceFormFactory.generateStringDataForm(biographyDo,PROFILE);
		JsonResponseRepresentation jsonResponseRep = ServiceProcessor.put(url, getRestUsername(), getRestPassword(), formData);
		jsonRep = jsonResponseRep.getJsonRepresentation();
		return biographyDo;
	}


	@Override
	public ProfileDo getUserProfileV2Details(String gooruUid, String userMetaActiveFlag)
			throws GwtException {
		ProfileDo profileDo = null;
		String userUid = getLoggedInUserUid();
		String url = UrlGenerator.generateUrl(getRestEndPoint(), UrlToken.GET_USER_PROFILE_V2_DETAILS, gooruUid, getLoggedInSessionToken());
		if(userMetaActiveFlag.equalsIgnoreCase("1")) {
			url+=USER_META_ACTIVE_FLAG;
		}
		
		JsonRepresentation jsonRep = null;
		JsonResponseRepresentation jsonResponseRep = ServiceProcessor.get(url, getRestUsername(), getRestPassword());
		jsonRep = jsonResponseRep.getJsonRepresentation();
		try {
			profileDo = JsonDeserializer.deserialize(jsonRep.getJsonObject().toString(), ProfileDo.class);
		} catch (JSONException e) {
			e.printStackTrace();
		} 
		return profileDo;
	}

	@Override
	public UserDo createUser(String postData) throws GwtException {
		UserDo userDo = null;
		String url = UrlGenerator.generateUrl(getRestEndPoint(), UrlToken.V2_CREATE_USER, getLoggedInSessionToken());
		JsonRepresentation jsonRep = null;
		JsonResponseRepresentation jsonResponseRep = ServiceProcessor.post(url, getRestUsername(), getRestPassword(),postData);
		jsonRep = jsonResponseRep.getJsonRepresentation();
		try {
			userDo = JsonDeserializer.deserialize(jsonRep.getJsonObject().toString(), UserDo.class);
		} catch (JSONException e) {
			e.printStackTrace();
		} 
		return userDo;
	}

	public void updateNewEmailStatus(String emailId, boolean isEmailConfirmation) {
		JsonRepresentation jsonRep = null;
		String url = UrlGenerator.generateUrl(getRestEndPoint(), UrlToken.UPDATE_USER_BIOGRAPHY, getLoggedInUserUid(), getLoggedInSessionToken());
		String formData = "";
		formData = "{\"profile\": {\"user\": {\"emailId\":\""+emailId+"\"}},\"emailConfirmStatus\":"+isEmailConfirmation+"}";
		JsonResponseRepresentation jsonResponseRep = ServiceProcessor.put(url, getRestUsername(), getRestPassword(), formData);
		jsonRep = jsonResponseRep.getJsonRepresentation();
	}

	@Override
	public V2UserDo updateV2ProfileDo(String EmailId,String accountType,String firstName,String lastName,String biography,String password, String userName, String gender, boolean isSendConfirmEmail) {
		JsonRepresentation jsonRep = null;
		String url = UrlGenerator.generateUrl(getRestEndPoint(), UrlToken.V2_UPDATE_USER_PROFILE, getLoggedInUserUid(), getLoggedInSessionToken());
		V2UserDo userv2Do = new V2UserDo();
		ProfileV2Do profileV2Do = new ProfileV2Do();
		UserDo user = new UserDo();
		
		if (!userName.equalsIgnoreCase("")){
			user.setUsername(userName);
		}
		if(!firstName.equalsIgnoreCase("")){
			user.setFirstName(firstName);
		}
		if(!lastName.equalsIgnoreCase("")){
			user.setLastName(lastName);
		}
		if(!EmailId.equalsIgnoreCase("")){
			user.setEmailId(EmailId);
		}
		if (accountType.equalsIgnoreCase("")){
			user.setAccountTypeId(3);
		}
		profileV2Do.setUser(user);
		if(!biography.equalsIgnoreCase("")){
			profileV2Do.setAboutMe(biography);
		}
		userv2Do.setProfile(profileV2Do);
		userv2Do.setAccountType(accountType);
		
		if (!gender.equalsIgnoreCase("")){
			GenderDo genderDo = new GenderDo();
			genderDo.setGenderId(gender);
			profileV2Do.setGender(genderDo);
		}
		
		if(!password.equalsIgnoreCase("")){
			userv2Do.setPassword(password);
		}
		if (isSendConfirmEmail){
			userv2Do.setEmailConfirmStatus("true");
			user.setConfirmStatus(0);
		}else{
			userv2Do.setEmailConfirmStatus("false");
			user.setConfirmStatus(1);
		}
	
		String formData = ResourceFormFactory.generateStringDataForm(userv2Do,null);
		JsonResponseRepresentation jsonResponseRep = ServiceProcessor.put(url, getRestUsername(), getRestPassword(), formData);
		jsonRep = jsonResponseRep.getJsonRepresentation();
		try{
			userv2Do = JsonDeserializer.deserialize(jsonRep.getJsonObject().toString(), V2UserDo.class);
		}
		catch(JSONException ex){
			
		}
		return userv2Do;
		
	}
	
	/*@Override
	public ProfilePageDo updateProfileBiography(String gooruUid,String biography) throws GwtException {
		JsonRepresentation jsonRep = null;
		String url = UrlGenerator.generateUrl(getRestEndPoint(), UrlToken.UPDATE_USER_BIOGRAPHY, getLoggedInUserUid(), getLoggedInSessionToken());
		ProfilePageDo profilePageDo = new ProfilePageDo();
		profilePageDo.setAboutMe(biography);
		String formData = ResourceFormFactory.generateStringDataForm(profilePageDo,PROFILE);
		jsonRep = ServiceProcessor.put(url, getRestUsername(), getRestPassword(), formData);
		return profilePageDo;
	}
	*/
	
	@Override
	public void sendWelcomeMail(String gooruUId, String emailType) {
		Map<String, String> params = new HashMap<String, String>();
		params.put(GOORU_BASE_URL, getHomeEndPoint() + "/#discover");
		params.put("type", emailType);
		String formData = ResourceFormFactory.generateStringDataForm(params,null);
		String url = UrlGenerator.generateUrl(getRestEndPoint(), UrlToken.V2_SEND_WELCOME_MAIL, gooruUId, getLoggedInSessionToken());
		ServiceProcessor.post(url, getRestUsername(), getRestPassword(), formData);
	}

	@Override
	public void updatePartyCustomField(String gooruUid, String optionKey,
			String optionValue) throws GwtException {
		JsonRepresentation jsonRep = null;
		String url = UrlGenerator.generateUrl(getRestEndPoint(), UrlToken.V2_UPDATE_PARTY_CUSTOM_FIELD, getLoggedInUserUid(), getLoggedInSessionToken());
		try{
			JSONObject updateCustomFieldObj=new JSONObject();
			JSONObject optionObject=new JSONObject();
			optionObject.put("optionalValue", optionValue);
			optionObject.put("optionalKey", optionKey);
			updateCustomFieldObj.put("partyCustomField", optionObject);
			JsonResponseRepresentation jsonResponseRep = ServiceProcessor.put(url, getRestUsername(), getRestPassword(), updateCustomFieldObj.toString());
			jsonRep = jsonResponseRep.getJsonRepresentation();
		}
		catch(Exception ex){}
		
	}

}
