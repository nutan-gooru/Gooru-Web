package org.ednovo.gooru.client.mvp.library.partner.autodesk;

import com.google.gwt.core.client.GWT;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.gwtplatform.mvp.client.proxy.NotifyingAsyncCallback;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.common.client.StandardProvider;
import com.gwtplatform.common.client.CodeSplitProvider;
import com.gwtplatform.common.client.CodeSplitBundleProvider;
import com.gwtplatform.mvp.client.proxy.ProxyImpl;
import com.gwtplatform.mvp.client.proxy.ProxyPlaceImpl;
import com.gwtplatform.mvp.client.proxy.RevealContentHandler;
import com.gwtplatform.mvp.client.DelayedBind;
import com.gwtplatform.mvp.client.proxy.TabContentProxyPlaceImpl;
import com.gwtplatform.mvp.client.DelayedBindRegistry;
import com.google.gwt.inject.client.Ginjector;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.gwtplatform.mvp.client.TabData;
import com.gwtplatform.mvp.client.TabDataBasic;

public class AutodeskLibraryPresenterIsAutodeskLibraryProxyImpl extends com.gwtplatform.mvp.client.proxy.ProxyPlaceImpl<AutodeskLibraryPresenter> implements org.ednovo.gooru.client.mvp.library.partner.autodesk.AutodeskLibraryPresenter.IsAutodeskLibraryProxy, com.gwtplatform.mvp.client.DelayedBind {
  
  private org.ednovo.gooru.client.gin.AppInjector ginjector;
  
  public static class WrappedProxy
  extends com.gwtplatform.mvp.client.proxy.ProxyImpl<AutodeskLibraryPresenter> implements com.gwtplatform.mvp.client.DelayedBind {
    
    private org.ednovo.gooru.client.gin.AppInjector ginjector;
    
    public WrappedProxy() {
    }
    
    @Override
    public void delayedBind(Ginjector baseGinjector) {
      ginjector = (org.ednovo.gooru.client.gin.AppInjector)baseGinjector;
      bind(ginjector.getPlaceManager(),
          ginjector.getEventBus());
      presenter = new CodeSplitProvider<AutodeskLibraryPresenter>( ginjector.getAutodeskLibraryPresenter() );
      
      RevealContentHandler<AutodeskLibraryPresenter> revealContentHandler = new RevealContentHandler<AutodeskLibraryPresenter>( eventBus, this );
      getEventBus().addHandler( AutodeskLibraryPresenter.TYPE_FOLDERS_SLOT, revealContentHandler );
    }
  }
  
  public AutodeskLibraryPresenterIsAutodeskLibraryProxyImpl() {
    DelayedBindRegistry.register(this);
  }
  
  @Override
  public void delayedBind(Ginjector baseGinjector) {
    ginjector = (org.ednovo.gooru.client.gin.AppInjector)baseGinjector;
    bind(ginjector.getPlaceManager(),
        ginjector.getEventBus());
    WrappedProxy wrappedProxy = GWT.create(WrappedProxy.class);
    wrappedProxy.delayedBind( ginjector ); 
    proxy = wrappedProxy; 
    String nameToken = "Autodesk"; 
    place = new com.gwtplatform.mvp.client.proxy.PlaceWithGatekeeper( nameToken, ginjector.getAppPlaceKeeper() );
  }
}