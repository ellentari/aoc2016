package com.adventofcode;

import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TokenInfo<TOKEN> {

    private final Pattern pattern;
    private final Function<Matcher, TOKEN> mapping;

    public TokenInfo(String regexp, Function<Matcher, TOKEN> mapping) {
        this.pattern = Pattern.compile("^(" + regexp + ")");
        this.mapping = mapping;
    }

    Pattern getPattern() {
        return pattern;
    }

    public TOKEN map(Matcher matcher) {
        return mapping.apply(matcher);
    }
}
