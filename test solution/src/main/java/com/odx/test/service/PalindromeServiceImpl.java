package com.odx.test.service;

import com.odx.test.entities.DemoEntity;
import com.odx.test.repository.DemoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PalindromeServiceImpl implements PalindromeService {

    private DemoRepository demoRepository;
    int resultStart;
    int resultLength;

    @Autowired
    PalindromeServiceImpl(DemoRepository demoRepository) {
        this.demoRepository = demoRepository;
    }

    @Override
    public void storeLongestPalindrome(String inputString) {
        int strLength = inputString.length();
        String longestPalindrome;
        /*all strings of length 1 is an palindrome*/
        if (strLength < 2) {
            longestPalindrome = inputString;
        } else {
            for (int i = 0; i < strLength - 1; i++) {
                checkPalindrome(inputString, i, i);
                checkPalindrome(inputString, i, i + 1);
            }
            longestPalindrome = inputString.substring(resultStart, resultStart + resultLength);
        }
        DemoEntity demoEntity = new DemoEntity();
        demoEntity.setName(longestPalindrome);
        demoRepository.save(demoEntity);
    }

    private void checkPalindrome(String start, int begin, int end) {
        while (begin >= 0 && end < start.length() && start.charAt(begin) == start.charAt(end)) {
            begin--;
            end++;
        }
        if (resultLength < end - begin - 1) {
            resultStart = begin + 1;
            resultLength = end - begin - 1;
        }
    }

    @Override
    public String getLongestPalindrome() {
        List<DemoEntity> demoEntities = demoRepository.findAll();
        int totalCount = demoEntities.size();
        if (totalCount > 0) {
            /* retrieving last stored palindrome value*/
            return demoEntities.get(totalCount - 1).getName();
        }
        return null;
    }
}
