package com.job4kh.tokenizer.data;

import java.util.HashSet;
import java.util.Set;

public class KhmerCharacterSet {

    public final static Set<Character> SPACE_SET = new HashSet<Character>(){
        {add(' ');}
        {add('\u200B'); }
        {add((char)160);}



    };

    public final static Set<Character>DELIMETER = new HashSet<Character>(){
        {add('។');}
        {add('៕');}
        {add('៖');}
        {add('៘');}
        {add('៙');}
        {add('៚');}
        {add('ៜ');}
        {add('«');}
        {add('»');}
        {add('?');}
        {add('(');}
        {add(')');}
        {add('\n');}
        {add('\t');}
        {add('"');}
        {add('\'');}

    };



    public final static Set<Character>DIGIT_SET = new HashSet<Character>()
    {
        {add('០');}
        {add('១');}
        {add('២');}
        {add('៣');}
        {add('៤');}
        {add('៥');}
        {add('៦');}
        {add('៧');}
        {add('៨');}
        {add('៩');}
    };
    public final static Set<Character> CONSONANT_SET = new HashSet<Character>()
    {
        {add('ក');}
        {add('ខ');}
        {add('គ');}
        {add('ឃ');}
        {add('ង');}
        {add('ច');}
        {add('ឆ');}
        {add('ជ');}
        {add('ឈ');}
        {add('ញ');}
        {add('ដ');}
        {add('ឋ');}
        {add('ឌ');}
        {add('ឍ');}
        {add('ណ');}
        {add('ត');}
        {add('ថ');}
        {add('ទ');}
        {add('ធ');}
        {add('ន');}
        {add('ប');}
        {add('ផ');}
        {add('ព');}
        {add('ភ');}
        {add('ម');}
        {add('យ');}
        {add('រ');}
        {add('ល');}
        {add('វ');}
        {add('ឝ');}
        {add('ឞ');}
        {add('ស');}
        {add('ហ');}
        {add('ឡ');}
        {add('អ');}
        {add('ឣ');}

    };

    public final static Set<Character>FULL_CONSONANT_SET = new HashSet<Character>(){
        {add('ឥ');}
        {add('ឦ');}
        {add('ឧ');}
        {add('ឨ');}
        {add('ឩ');}
        {add('ឪ');}
        {add('ឫ');}
        {add('ឬ');}
        {add('ឭ');}
        {add('ឮ');}
        {add('ឯ');}
        {add('ឰ');}
        {add('ឱ');}
        {add('ឲ');}
        {add('ឳ');}
    };

    public final static Set<Character>SYMBOL_SET = new HashSet<Character>() {
        {add('់');}
        {add('៌');}
        {add('៍');}
        {add('៎');}
        {add('៏');}
    };
    public final static Set<Character>VOWEL_SET = new HashSet<Character>(){
        {add('ា');}
        { add('ិ');}
        {add('ី');}
        {add('ឹ');}
        {add('ឺ');}
        {add('ុ');}
        {add('ូ');}
        {add('ួ');}
        {add('ើ');}
        {add('ឿ');}
        {add('ៀ');}
        {add('េ');}
        {add('ែ');}
        {add('ៃ');}
        {add('ោ');}
        {add('ៅ');}
        {add('ំ');}
        {add('ះ');}
    };


}
