package com.job4kh.indexmap;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

public class NumericComputedIndexer {
    private char start;
    private int length;
    private BigInteger sum = new BigInteger("0");
    private Set<Character> charSet;
    private String word;
    public NumericComputedIndexer(){
        charSet  = new HashSet<>();
    }
    public NumericComputedIndexer(String word) {
        this();
        start = word.charAt(0);
        this.length = word.length();
        this.word = word;
        index( word);

    }
    public void index(String word) {

        BigInteger multiple =  new BigInteger("1");
        int xor = 0;
        int and = 1;
        int or = 0;
        int total = 0;
        int sub = word.length();
        double distance = 0d;
        for(int i=0;i<word.length();i++ ) {
            charSet.add( word.charAt(i));
            int num =  word.charAt(i);
            BigInteger val = new BigInteger( String.valueOf(num));

            sum = sum.add( val );
            sub  = sub %num;
            //xor +=1;
            xor = xor ^ num;
            total = total + ~num;
            //and = and &num;
            or = or | num;
            or +=1;
            multiple = multiple.multiply( val);
            if( num == NumericComputedIndexChecker.SUBSCRIPT_IDENTIFIER ) {
                int next = i +1;
                if( next < word.length())
                {

                    xor = xor ^ (int)word.charAt( next);

                    BigInteger nextVal = new BigInteger( String.valueOf((int) word.charAt( next)));
                    sum = sum.add( nextVal );
                    multiple = multiple.multiply( nextVal );
                }
                int prev = i -1;
                if( prev > 0) {
                    if( word.charAt( prev) == NumericComputedIndexChecker.SUBSCRIPT_IDENTIFIER || NumericComputedIndexChecker.notConsonant( word.charAt( prev))) {
                        prev--;
                    }
                    if(prev> 0) {
                        xor = xor ^ (int)word.charAt( prev);
                        sum = sum.subtract( new BigInteger( String.valueOf((int) word.charAt( prev))));
                    }

                }

            }
            else {

            }
            if( i+1 < word.length())
            {
                int nextNum = word.charAt( i+1);
                distance = distance +  Math.abs( num -nextNum);
            }
            else if( i+1 == word.length())
            {
                int nextNum = word.charAt(0);
                distance = distance + Math.abs( num - nextNum);
            }

        }
        xor++;
        sum = sum.multiply( new BigInteger( String.valueOf( xor) ));
        sum = sum.multiply( multiple);
    }

    public BigInteger getSum() {
        return sum;
    }

    public int getLength() {
        return length;
    }

    public char getStart() {
        return start;
    }

    public Set<Character> getCharSet() {
        return charSet;
    }
    public boolean isSimilar(String word)
    {
        if( word.length() == length)
        {
            char[] arr = word.toCharArray();
            int sum = 0;
            for( char ch : arr) {
                if(!charSet.contains( ch)) {
                    return false;
                }
                sum += ch;
            }
            if( this.sum.equals( sum)) {
                return true;
            }

        }
        return false;
    }
    public boolean isSimilar( NumericComputedIndexer target){
        if( target.getLength() == this.length) {
            return target.sum.equals(this.sum);
        }
        return false;
    }

    public String getWord() {
        return word;
    }

    @Override
    public int hashCode() {
        return word.hashCode();
    }
}
