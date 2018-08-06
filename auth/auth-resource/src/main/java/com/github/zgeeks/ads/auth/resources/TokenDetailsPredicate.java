package com.github.zgeeks.ads.auth.resources;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;
import java.util.function.Predicate;

public class TokenDetailsPredicate implements Predicate<HttpServletRequest> {

    private final String name;
    private final Object value;

    protected TokenDetailsPredicate(String name, String value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public boolean test(HttpServletRequest request) {
        return Objects.equals(value, OAuthContext.details().get(name));
    }

    public static TokenDetailsPredicate tokenHas(String name, String value) {
        return new TokenDetailsPredicate(name, value);
    }
}
