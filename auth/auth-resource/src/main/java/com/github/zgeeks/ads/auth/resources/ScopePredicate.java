package com.github.zgeeks.ads.auth.resources;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static java.util.Arrays.*;

public class ScopePredicate implements Predicate<HttpServletRequest> {

    private final Predicate<Stream<String>> check;

    protected ScopePredicate(Predicate<Stream<String>> check) {
        this.check = check;
    }

    public static ScopePredicate scopeMatchesAny(String... scopes) {
        final List<String> scopeList = asList(scopes);
        return new ScopePredicate(s -> s.anyMatch(scopeList::contains));
    }

    public static ScopePredicate scopeMatchesAll(String... scopes) {
        final List<String> scopeList = asList(scopes);
        return new ScopePredicate(s -> s.allMatch(scopeList::contains));
    }

    @Override
    public boolean test(HttpServletRequest request) {
        return OAuthContext.auth()
            .map(x -> check.test(x.getOAuth2Request().getScope().stream()))
            .orElse(false);
    }
}
