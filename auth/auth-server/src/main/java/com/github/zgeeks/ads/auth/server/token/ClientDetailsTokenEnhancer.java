package com.github.zgeeks.ads.auth.server.token;


public class ClientDetailsTokenEnhancer {

//    private final ClientRepository repository;
//
//    public ClientDetailsTokenEnhancer(ClientRepository repository) {
//        this.repository = repository;
//    }
//
//    @Override
//    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
//
//        String clientId = Optional.ofNullable(authentication)
//                .map(OAuth2Authentication::getOAuth2Request)
//                .map(request -> request.getClientId())
//                .orElseThrow(() -> new IllegalArgumentException("Missing getOAuth2Request().getClientId()"));
//
//        Client client = repository.byClientId(clientId)
//            .orElseThrow(() -> new IllegalArgumentException("No client with requested id: " + clientId));
//
//        DefaultOAuth2AccessToken result = new DefaultOAuth2AccessToken(accessToken);
//        result.getAdditionalInformation().putAll(client.getAdditionalInformation());
//        result.setScope(new HashSet<>(Arrays.asList(client.getScopes())));
//        return result;
//    }
}
