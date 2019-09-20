package com.job4kh.indexmap;

import com.job4kh.tokenizer.data.EnglishLanguage;
import com.job4kh.tokenizer.data.Language;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CleanTextUtil {

    private Language englishLanguage;
    private Set<Character> spaceAndEnd;
    private Set<Character> eliminateCharacter;
    private Set<Character> numbers;
    private Set<String>wordSet;
   public static int startCh = 0x1780;
   public static int endCh =0x17FF;

    public CleanTextUtil(Set<Character> spaceAndEnd, Set<Character> eliminateCharacter, Set<Character> numbers) {
        this.spaceAndEnd = spaceAndEnd;
        this.eliminateCharacter = eliminateCharacter;
        this.numbers = numbers;
        this.englishLanguage = new EnglishLanguage();
    }

    public enum  TextType {
        Text,
        Number,
        English,
        SpaceOrEnd,
        Delimiter
    }


    public static class CleanText{
        private String phrase;
        private TextType type;
        private StringBuilder builder;
        public CleanText(){
            this(TextType.Text);
        }
        public CleanText(TextType textType) {
            this.type = textType;
            builder = new StringBuilder();

        }
        public CleanText(TextType textType,String phrase) {
            this.phrase = phrase;
            this.type = textType;
            this.builder = new StringBuilder();
        }
        public void add(char ch) {
            builder.append(ch);
        }

        public int getCurrentLength()
        {
            return builder.length();
        }

        public CleanText flush()
        {
            phrase = builder.toString();
            return this;
        }

        public CleanText(String phrase)
        {
            this(phrase,TextType.Text);
        }
        public CleanText(String phrase,TextType type) {
            this.phrase = phrase;
            this.type = type;
            builder = new StringBuilder( phrase);

        }

        public String getPhrase() {
            return phrase;
        }

        public TextType getType() {
            return type;
        }

        public void setType(TextType type) {
            this.type = type;
        }
        public static final CleanText SPACE = new CleanText(" ",TextType.SpaceOrEnd);
        public static CleanText instanceOf( Set<Character> eliminateCharacter, Set<Character> spaceAndEnd, char ch) {
            if( spaceAndEnd.contains(ch) ) {
                return SPACE;
            }
            else if( eliminateCharacter.contains( ch)) {
                return new CleanText( String.valueOf(ch), TextType.Delimiter );
            }
            throw  new RuntimeException("Invalid character");
        }

    }

    boolean NonKhmerLang(char ch) {
        int code = (int)ch;
        return code <startCh || code > endCh;
    }

    public List<CleanText> cleanText(String text)
    {

        CleanText cleanText = new CleanText();
        List<CleanText> result = new ArrayList<>();
        for(int i=0;i<text.length();i++)
        {
            char ch = text.charAt(i);
            switch ( cleanText.getType())
            {
                case Text:
                    if( spaceAndEnd.contains(ch) ||eliminateCharacter.contains( ch)) {
                        if(cleanText.getCurrentLength() > 0) {
                            result.add( cleanText.flush());
                            result.add( CleanText.instanceOf( spaceAndEnd, eliminateCharacter, ch));
                            cleanText = new CleanText();
                        }
                    }
                    else if (numbers.contains( ch)) {
                        if( cleanText.getCurrentLength() > 0) {
                            result.add( cleanText.flush());
                            cleanText = new CleanText();
                        }
                        cleanText.setType(  TextType.Number );
                        cleanText.add( ch);

                    }
                    else if( NonKhmerLang( ch)) {
                        if( cleanText.getCurrentLength() > 0) {
                            result.add( cleanText.flush() );
                            cleanText  = new CleanText();
                        }
                        cleanText.setType( TextType.English);
                        cleanText.add( ch);
                    }
                    else {
                        cleanText.add( ch );
                    }
                    break;
                case Number:
                    if( spaceAndEnd.contains(ch) || eliminateCharacter.contains(ch)) {
                        if(cleanText.getCurrentLength() > 0) {
                            result.add( cleanText.flush());
                            result.add( CleanText.instanceOf( spaceAndEnd, eliminateCharacter, ch));
                            cleanText = new CleanText();
                        }

                    }
                    else if(numbers.contains( ch)) {
                        cleanText.add( ch);

                    }
                    else if( NonKhmerLang( ch)) {
                        if( cleanText.getCurrentLength() >0 ) {
                            result.add( cleanText.flush() );
                            cleanText = new CleanText();

                        }
                        cleanText.add( ch );
                        cleanText.setType( TextType.English);

                    }
                    else {
                        if( cleanText.getCurrentLength() > 0) {
                            result.add( cleanText.flush());
                            cleanText = new CleanText();
                        }
                        cleanText.add( ch );
                        cleanText.setType( TextType.Text);
                    }
                    break;
                case English:
                    if( spaceAndEnd.contains(ch) || eliminateCharacter.contains(ch)) {
                        if(cleanText.getCurrentLength() > 0) {
                            result.add( cleanText.flush());
                            result.add( CleanText.instanceOf( spaceAndEnd, eliminateCharacter, ch));
                            cleanText = new CleanText();
                        }

                    }
                    else if(numbers.contains( ch)) {
                        if( cleanText.getCurrentLength() > 0)
                        {
                            result.add( cleanText.flush());
                            cleanText = new CleanText();
                        }
                        cleanText.add( ch);
                        cleanText.setType(  TextType.Number );

                    }
                    else if( NonKhmerLang( ch)) {
                        cleanText.add( ch );
                    }
                    else {
                        if( cleanText.getCurrentLength() > 0)
                        {
                            result.add( cleanText.flush() );
                            cleanText = new CleanText();
                        }
                        cleanText.add( ch );
                        cleanText.setType( TextType.Text);
                    }
                    break;
            }
        }
        if( cleanText !=null && cleanText.getCurrentLength() > 0) {
            result.add( cleanText.flush());
        }
        return result;
    }
}
