package com.job4kh.indexmap;

import com.job4kh.tokenizer.data.KhmerCharacterSet;

public class NumericComputedIndexChecker {
    static final char SUBSCRIPT_IDENTIFIER = 6098 ;
    static final char startConsonant = 6016;
    static final char endConsonant =6064;
    public static boolean notConsonant(char ch)
    {
        return ch < startConsonant || ch > endConsonant;
    }

    public static boolean isEligible(String word) {

        int startPos;
        int endPos;
        for(int i =0;i < word.length();i++) {
            if( word.charAt(i) == SUBSCRIPT_IDENTIFIER) {
                startPos = i ;
                //look for vowel
                int vowelPos = startPos -1;
                if( vowelPos > 0 )
                {
                      char expectedVowel=   word.charAt( vowelPos);
                      if(KhmerCharacterSet.VOWEL_SET.contains( expectedVowel)) {
                          return true;
                      }
                }
                int consonantPos  = startPos+1;
                vowelPos = startPos+2;
                if( vowelPos < word.length()) {
                    char expectedConsonant = word.charAt( consonantPos);
                    char expectedVowel = word.charAt( vowelPos);
                    if( KhmerCharacterSet.VOWEL_SET.contains( expectedVowel) && KhmerCharacterSet.CONSONANT_SET.contains( expectedConsonant)) {
                        return true;
                    }
                }
                int lookup = startPos+2;
                if( lookup <word.length()) {
                    if( word.charAt( lookup) == SUBSCRIPT_IDENTIFIER) {
                        endPos = lookup;
                        lookup = endPos+1;
                        if( lookup <word.length())
                        {
                            if( word.charAt(lookup) >= startConsonant && word.charAt(lookup) <= endConsonant) {
                                return true;
                            }
                        }
                        else {
                            break;
                        }
                    }
                }
                else {
                    break;
                }
            }
        }
        return false;
    }
}
