package com.adventofcode.common;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TokenInfo<TOKEN> {

    private final Pattern pattern;
    private final Function<Matcher, TOKEN> mapping;
    private final BiFunction<Matcher, List<TOKEN>, TOKEN> mapping2;

    public TokenInfo(String regexp, Function<Matcher, TOKEN> mapping) {
        this.pattern = Pattern.compile("^" + regexp);
        this.mapping = mapping;
        this.mapping2 = null;
    }

    public TokenInfo(String regexp, BiFunction<Matcher, List<TOKEN>, TOKEN> mapping2) {
        this.pattern = Pattern.compile("^" + regexp);
        this.mapping = null;
        this.mapping2 = mapping2;
    }

    Pattern getPattern() {
        return pattern;
    }

    public TOKEN map(Matcher matcher, List<TOKEN> tokens) {
        if (mapping != null) {
            return mapping.apply(matcher);
        } else {
            return mapping2.apply(matcher, tokens);
        }
    }

    public TOKEN map(Matcher matcher) {
        return mapping.apply(matcher);
    }
}
