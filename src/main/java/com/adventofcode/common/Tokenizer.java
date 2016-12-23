package com.adventofcode.common;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;

public class Tokenizer<TOKEN> {

    private final List<TokenInfo<TOKEN>> tokenInfos = new ArrayList<>();

    public Tokenizer(List<TokenInfo<TOKEN>> tokenInfos) {
        this.tokenInfos.addAll(tokenInfos);
    }

    public List<TOKEN> parse(String input) {
        List<TOKEN> tokens = new ArrayList<>();

        while (!"".equals(input)) {
            boolean matchFound = false;

            for (TokenInfo<TOKEN> tokenInfo : tokenInfos) {
                Matcher matcher = tokenInfo.getPattern().matcher(input);
                if (matcher.find()) {
                    matchFound = true;
                    tokens.add(tokenInfo.map(matcher, tokens));
                    input = matcher.replaceFirst("").trim();
                }
            }

            if (!matchFound) throw new IllegalArgumentException("Unexpected token: " + input);
        }

        return tokens;
    }

    public void parse(String input, Collection<TOKEN> to) {
        while (!"".equals(input)) {
            boolean matchFound = false;

            for (TokenInfo<TOKEN> tokenInfo : tokenInfos) {
                Matcher matcher = tokenInfo.getPattern().matcher(input);
                if (matcher.find()) {
                    matchFound = true;
                    to.add(tokenInfo.map(matcher));
                    input = matcher.replaceFirst("").trim();
                }
            }

            if (!matchFound) throw new IllegalArgumentException("Unexpected token: " + input);
        }
    }
}
