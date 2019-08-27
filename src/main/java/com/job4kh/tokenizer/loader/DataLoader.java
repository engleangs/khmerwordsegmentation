package com.job4kh.tokenizer.loader;

import java.util.Set;

public interface DataLoader {
    void load(Set<String> wordSet) throws Exception;
    int getMaxLength();
}
