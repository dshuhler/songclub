package com.shuhler.negadelphia.domain.classify;

import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableSet;

import java.util.List;
import java.util.Set;

public class CussClassifier {

    private Set<String> cussWords = ImmutableSet.of("fuck", "fucking", "fucker", "motherfucker", "ass",
            "asshole", "shit", "bullshit", "shithead", "bitch", "damn", "goddamn");


    // raw count of curse occurances
    public int classify(String text) {

        String noPunctuationText = text.replaceAll("\\p{Punct}", "");

        List<String> words = Splitter.on(' ').splitToList(noPunctuationText.toLowerCase());

        int occurances = 0;
        for (String word: words) {
            if (cussWords.contains(word)) {
                occurances++;
            }
        }
        return occurances;
    }





}
