package org.ednovo.gooru.client.uc.tooltip;

public class DiscoverToolTip_DiscoverToolTipUiBinderImpl_TemplateImpl implements org.ednovo.gooru.client.uc.tooltip.DiscoverToolTip_DiscoverToolTipUiBinderImpl.Template {
  
  public com.google.gwt.safehtml.shared.SafeHtml html1(java.lang.String arg0,java.lang.String arg1) {
    StringBuilder sb = new java.lang.StringBuilder();
    sb.append("<span id='");
    sb.append(com.google.gwt.safehtml.shared.SafeHtmlUtils.htmlEscape(arg0));
    sb.append("'></span> <span id='");
    sb.append(com.google.gwt.safehtml.shared.SafeHtmlUtils.htmlEscape(arg1));
    sb.append("'></span>");
return new com.google.gwt.safehtml.shared.OnlyToBeUsedInGeneratedCodeStringBlessedAsSafeHtml(sb.toString());
}

public com.google.gwt.safehtml.shared.SafeHtml html2(java.lang.String arg0) {
StringBuilder sb = new java.lang.StringBuilder();
sb.append("<span id='");
sb.append(com.google.gwt.safehtml.shared.SafeHtmlUtils.htmlEscape(arg0));
sb.append("'></span>");
return new com.google.gwt.safehtml.shared.OnlyToBeUsedInGeneratedCodeStringBlessedAsSafeHtml(sb.toString());
}
}