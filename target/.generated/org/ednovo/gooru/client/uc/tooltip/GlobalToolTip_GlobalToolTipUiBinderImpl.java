package org.ednovo.gooru.client.uc.tooltip;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.safehtml.client.SafeHtmlTemplates;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeUri;
import com.google.gwt.safehtml.shared.UriUtils;
import com.google.gwt.uibinder.client.UiBinderUtil;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiBinderUtil;
import com.google.gwt.user.client.ui.Widget;

public class GlobalToolTip_GlobalToolTipUiBinderImpl implements UiBinder<com.google.gwt.user.client.ui.Widget, org.ednovo.gooru.client.uc.tooltip.GlobalToolTip>, org.ednovo.gooru.client.uc.tooltip.GlobalToolTip.GlobalToolTipUiBinder {

  interface Template extends SafeHtmlTemplates {
    @Template("")
    SafeHtml html1();
     
    @Template("<span id='{0}'></span>")
    SafeHtml html2(String arg0);
     
    @Template("<span id='{0}'></span>")
    SafeHtml html3(String arg0);
     
    @Template("<span id='{0}'></span> <span id='{1}'></span>")
    SafeHtml html4(String arg0, String arg1);
     
  }

  Template template = GWT.create(Template.class);


  public com.google.gwt.user.client.ui.Widget createAndBindUi(final org.ednovo.gooru.client.uc.tooltip.GlobalToolTip owner) {


    return new Widgets(owner).get_confirmationPanel();
  }

  /**
   * Encapsulates the access to all inner widgets
   */
  class Widgets {
    private final org.ednovo.gooru.client.uc.tooltip.GlobalToolTip owner;


    public Widgets(final org.ednovo.gooru.client.uc.tooltip.GlobalToolTip owner) {
      this.owner = owner;
      build_toolTip();  // generated css resource must be always created. Type: GENERATED_CSS. Precedence: 1
      build_domId3();  // more than one getter call detected. Type: DOM_ID_HOLDER, precedence: 4
      build_domId2();  // more than one getter call detected. Type: DOM_ID_HOLDER, precedence: 3
      build_domId0();  // more than one getter call detected. Type: DOM_ID_HOLDER, precedence: 2
      build_domId1();  // more than one getter call detected. Type: DOM_ID_HOLDER, precedence: 2
      build_domId3Element();  // more than one getter call detected. Type: DEFAULT, precedence: 4
      build_domId2Element();  // more than one getter call detected. Type: DEFAULT, precedence: 3
      build_domId0Element();  // more than one getter call detected. Type: DEFAULT, precedence: 2
      build_domId1Element();  // more than one getter call detected. Type: DEFAULT, precedence: 2
    }

    SafeHtml template_html1() {
      return template.html1();
    }
    SafeHtml template_html2() {
      return template.html2(get_domId3());
    }
    SafeHtml template_html3() {
      return template.html3(get_domId2());
    }
    SafeHtml template_html4() {
      return template.html4(get_domId0(), get_domId1());
    }

    /**
     * Getter for clientBundleFieldNameUnlikelyToCollideWithUserSpecifiedFieldOkay called 1 times. Type: GENERATED_BUNDLE. Build precedence: 1.
     */
    private org.ednovo.gooru.client.uc.tooltip.GlobalToolTip_GlobalToolTipUiBinderImpl_GenBundle get_clientBundleFieldNameUnlikelyToCollideWithUserSpecifiedFieldOkay() {
      return build_clientBundleFieldNameUnlikelyToCollideWithUserSpecifiedFieldOkay();
    }
    private org.ednovo.gooru.client.uc.tooltip.GlobalToolTip_GlobalToolTipUiBinderImpl_GenBundle build_clientBundleFieldNameUnlikelyToCollideWithUserSpecifiedFieldOkay() {
      // Creation section.
      final org.ednovo.gooru.client.uc.tooltip.GlobalToolTip_GlobalToolTipUiBinderImpl_GenBundle clientBundleFieldNameUnlikelyToCollideWithUserSpecifiedFieldOkay = (org.ednovo.gooru.client.uc.tooltip.GlobalToolTip_GlobalToolTipUiBinderImpl_GenBundle) GWT.create(org.ednovo.gooru.client.uc.tooltip.GlobalToolTip_GlobalToolTipUiBinderImpl_GenBundle.class);
      // Setup section.


      return clientBundleFieldNameUnlikelyToCollideWithUserSpecifiedFieldOkay;
    }

    /**
     * Getter for toolTip called 3 times. Type: GENERATED_CSS. Build precedence: 1.
     */
    private org.ednovo.gooru.client.uc.tooltip.GlobalToolTip_GlobalToolTipUiBinderImpl_GenCss_toolTip toolTip;
    private org.ednovo.gooru.client.uc.tooltip.GlobalToolTip_GlobalToolTipUiBinderImpl_GenCss_toolTip get_toolTip() {
      return toolTip;
    }
    private org.ednovo.gooru.client.uc.tooltip.GlobalToolTip_GlobalToolTipUiBinderImpl_GenCss_toolTip build_toolTip() {
      // Creation section.
      toolTip = get_clientBundleFieldNameUnlikelyToCollideWithUserSpecifiedFieldOkay().toolTip();
      // Setup section.
      toolTip.ensureInjected();


      return toolTip;
    }

    /**
     * Getter for confirmationPanel called 1 times. Type: DEFAULT. Build precedence: 1.
     */
    private org.ednovo.gooru.client.ui.HTMLEventPanel get_confirmationPanel() {
      return build_confirmationPanel();
    }
    private org.ednovo.gooru.client.ui.HTMLEventPanel build_confirmationPanel() {
      // Creation section.
      final org.ednovo.gooru.client.ui.HTMLEventPanel confirmationPanel = new org.ednovo.gooru.client.ui.HTMLEventPanel(template_html4().asString());
      // Setup section.
      confirmationPanel.setStyleName("" + get_toolTip().resourceRightsPopupContainer() + "");

      // Attach section.
      UiBinderUtil.TempAttachment attachRecord1326 = UiBinderUtil.attachToDom(confirmationPanel.getElement());
      get_domId0Element().get();
      get_domId1Element().get();

      // Detach section.
      attachRecord1326.detach();
      confirmationPanel.addAndReplaceElement(get_panelArrow(), get_domId0Element().get());
      confirmationPanel.addAndReplaceElement(get_f_HTMLPanel1(), get_domId1Element().get());

      owner.confirmationPanel = confirmationPanel;

      return confirmationPanel;
    }

    /**
     * Getter for domId0 called 2 times. Type: DOM_ID_HOLDER. Build precedence: 2.
     */
    private java.lang.String domId0;
    private java.lang.String get_domId0() {
      return domId0;
    }
    private java.lang.String build_domId0() {
      // Creation section.
      domId0 = com.google.gwt.dom.client.Document.get().createUniqueId();
      // Setup section.


      return domId0;
    }

    /**
     * Getter for panelArrow called 1 times. Type: DEFAULT. Build precedence: 2.
     */
    private com.google.gwt.user.client.ui.HTMLPanel get_panelArrow() {
      return build_panelArrow();
    }
    private com.google.gwt.user.client.ui.HTMLPanel build_panelArrow() {
      // Creation section.
      final com.google.gwt.user.client.ui.HTMLPanel panelArrow = new com.google.gwt.user.client.ui.HTMLPanel(template_html1().asString());
      // Setup section.
      panelArrow.setStyleName("" + get_toolTip().arrow() + "");


      owner.panelArrow = panelArrow;

      return panelArrow;
    }

    /**
     * Getter for domId0Element called 2 times. Type: DEFAULT. Build precedence: 2.
     */
    private com.google.gwt.uibinder.client.LazyDomElement domId0Element;
    private com.google.gwt.uibinder.client.LazyDomElement get_domId0Element() {
      return domId0Element;
    }
    private com.google.gwt.uibinder.client.LazyDomElement build_domId0Element() {
      // Creation section.
      domId0Element = new com.google.gwt.uibinder.client.LazyDomElement<Element>(get_domId0());
      // Setup section.


      return domId0Element;
    }

    /**
     * Getter for domId1 called 2 times. Type: DOM_ID_HOLDER. Build precedence: 2.
     */
    private java.lang.String domId1;
    private java.lang.String get_domId1() {
      return domId1;
    }
    private java.lang.String build_domId1() {
      // Creation section.
      domId1 = com.google.gwt.dom.client.Document.get().createUniqueId();
      // Setup section.


      return domId1;
    }

    /**
     * Getter for f_HTMLPanel1 called 1 times. Type: DEFAULT. Build precedence: 2.
     */
    private com.google.gwt.user.client.ui.HTMLPanel get_f_HTMLPanel1() {
      return build_f_HTMLPanel1();
    }
    private com.google.gwt.user.client.ui.HTMLPanel build_f_HTMLPanel1() {
      // Creation section.
      final com.google.gwt.user.client.ui.HTMLPanel f_HTMLPanel1 = new com.google.gwt.user.client.ui.HTMLPanel(template_html3().asString());
      // Setup section.
      f_HTMLPanel1.setStyleName("" + get_toolTip().resourceRightsInnerPopup() + "");

      // Attach section.
      UiBinderUtil.TempAttachment attachRecord1327 = UiBinderUtil.attachToDom(f_HTMLPanel1.getElement());
      get_domId2Element().get();

      // Detach section.
      attachRecord1327.detach();
      f_HTMLPanel1.addAndReplaceElement(get_f_HTMLPanel2(), get_domId2Element().get());

      return f_HTMLPanel1;
    }

    /**
     * Getter for domId2 called 2 times. Type: DOM_ID_HOLDER. Build precedence: 3.
     */
    private java.lang.String domId2;
    private java.lang.String get_domId2() {
      return domId2;
    }
    private java.lang.String build_domId2() {
      // Creation section.
      domId2 = com.google.gwt.dom.client.Document.get().createUniqueId();
      // Setup section.


      return domId2;
    }

    /**
     * Getter for f_HTMLPanel2 called 1 times. Type: DEFAULT. Build precedence: 3.
     */
    private com.google.gwt.user.client.ui.HTMLPanel get_f_HTMLPanel2() {
      return build_f_HTMLPanel2();
    }
    private com.google.gwt.user.client.ui.HTMLPanel build_f_HTMLPanel2() {
      // Creation section.
      final com.google.gwt.user.client.ui.HTMLPanel f_HTMLPanel2 = new com.google.gwt.user.client.ui.HTMLPanel(template_html2().asString());
      // Setup section.

      // Attach section.
      UiBinderUtil.TempAttachment attachRecord1328 = UiBinderUtil.attachToDom(f_HTMLPanel2.getElement());
      get_domId3Element().get();

      // Detach section.
      attachRecord1328.detach();
      f_HTMLPanel2.addAndReplaceElement(get_desLbl(), get_domId3Element().get());

      return f_HTMLPanel2;
    }

    /**
     * Getter for domId3 called 2 times. Type: DOM_ID_HOLDER. Build precedence: 4.
     */
    private java.lang.String domId3;
    private java.lang.String get_domId3() {
      return domId3;
    }
    private java.lang.String build_domId3() {
      // Creation section.
      domId3 = com.google.gwt.dom.client.Document.get().createUniqueId();
      // Setup section.


      return domId3;
    }

    /**
     * Getter for desLbl called 1 times. Type: DEFAULT. Build precedence: 4.
     */
    private com.google.gwt.user.client.ui.Label get_desLbl() {
      return build_desLbl();
    }
    private com.google.gwt.user.client.ui.Label build_desLbl() {
      // Creation section.
      final com.google.gwt.user.client.ui.Label desLbl = (com.google.gwt.user.client.ui.Label) GWT.create(com.google.gwt.user.client.ui.Label.class);
      // Setup section.


      owner.desLbl = desLbl;

      return desLbl;
    }

    /**
     * Getter for domId3Element called 2 times. Type: DEFAULT. Build precedence: 4.
     */
    private com.google.gwt.uibinder.client.LazyDomElement domId3Element;
    private com.google.gwt.uibinder.client.LazyDomElement get_domId3Element() {
      return domId3Element;
    }
    private com.google.gwt.uibinder.client.LazyDomElement build_domId3Element() {
      // Creation section.
      domId3Element = new com.google.gwt.uibinder.client.LazyDomElement<Element>(get_domId3());
      // Setup section.


      return domId3Element;
    }

    /**
     * Getter for domId2Element called 2 times. Type: DEFAULT. Build precedence: 3.
     */
    private com.google.gwt.uibinder.client.LazyDomElement domId2Element;
    private com.google.gwt.uibinder.client.LazyDomElement get_domId2Element() {
      return domId2Element;
    }
    private com.google.gwt.uibinder.client.LazyDomElement build_domId2Element() {
      // Creation section.
      domId2Element = new com.google.gwt.uibinder.client.LazyDomElement<Element>(get_domId2());
      // Setup section.


      return domId2Element;
    }

    /**
     * Getter for domId1Element called 2 times. Type: DEFAULT. Build precedence: 2.
     */
    private com.google.gwt.uibinder.client.LazyDomElement domId1Element;
    private com.google.gwt.uibinder.client.LazyDomElement get_domId1Element() {
      return domId1Element;
    }
    private com.google.gwt.uibinder.client.LazyDomElement build_domId1Element() {
      // Creation section.
      domId1Element = new com.google.gwt.uibinder.client.LazyDomElement<Element>(get_domId1());
      // Setup section.


      return domId1Element;
    }
  }
}