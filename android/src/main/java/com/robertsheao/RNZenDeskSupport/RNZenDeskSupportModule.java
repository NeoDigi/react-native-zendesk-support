package com.robertsheao.RNZenDeskSupport;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;

import zendesk.core.AnonymousIdentity;
import zendesk.core.Zendesk;
import zendesk.support.Support;
import zendesk.support.guide.HelpCenterActivity;
import zendesk.support.guide.HelpCenterUiConfig;

public class RNZenDeskSupportModule extends ReactContextBaseJavaModule {
    public RNZenDeskSupportModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public String getName() {
        return "RNZenDeskSupport";
    }

    @ReactMethod
    public void initialize(ReadableMap config) {
        String appId = config.getString("appId");
        String zendeskUrl = config.getString("zendeskUrl");
        String clientId = config.getString("clientId");
        Zendesk.INSTANCE.init(getReactApplicationContext(), zendeskUrl, appId, clientId);
    }

    @ReactMethod
    public void setupIdentity(ReadableMap identity) {
        AnonymousIdentity.Builder builder = new AnonymousIdentity.Builder();

        if (identity != null && identity.hasKey("customerEmail")) {
            builder.withEmailIdentifier(identity.getString("customerEmail"));
        }

        if (identity != null && identity.hasKey("customerName")) {
            builder.withNameIdentifier(identity.getString("customerName"));
        }

        Zendesk.INSTANCE.setIdentity(builder.build());

        Support.INSTANCE.init(Zendesk.INSTANCE);
    }

    @ReactMethod
    public void showHelpCenterWithOptions(ReadableMap options) {
        HelpCenterUiConfig.Builder builder = HelpCenterActivity.builder();
        if (options != null) {

            if (options.hasKey("showConversationsMenuButton")) {
                builder.withShowConversationsMenuButton(options.getBoolean("showConversationsMenuButton"));
            }
            if (options.hasKey("withContactUsButtonVisibility")) {
                builder.withContactUsButtonVisible(options.getBoolean("withContactUsButtonVisibility"));
            }
        }
        builder.show(getReactApplicationContext());
    }

    @ReactMethod
    public void showHelpCenter() {
        showHelpCenterWithOptions(null);
    }
}
