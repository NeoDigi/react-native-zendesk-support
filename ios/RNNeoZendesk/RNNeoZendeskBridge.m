//
//  RNNeoZendeskBridge.m
//  RNNeoZendesk
//
//  Created by Julien Tielemans on 2018/11/06.
//  Copyright © 2018 Julien Tielemans. All rights reserved.
//

#import "RNNeoZendeskBridge.h"
#import <React/RCTBridgeModule.h>

@interface RCT_EXTERN_REMAP_MODULE(RNNeoZendesk, RNNeoZendesk, NSObject)

RCT_EXTERN_METHOD(initialize:(NSDictionary *)config);

RCT_EXTERN_METHOD(identifyJWT:(NSString *)token);

RCT_EXTERN_METHOD(showHelpCenter:(NSDictionary *)options);

RCT_EXTERN_METHOD(showNewTicket:(NSDictionary *)options);

@end
