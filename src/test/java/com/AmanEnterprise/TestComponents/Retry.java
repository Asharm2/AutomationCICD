package com.AmanEnterprise.TestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {

    int count = 0;
    int maxTry = 1;

    @Override
    public boolean retry(ITestResult result) {
        //when this method return true -> that means the failed test method will re-run again
        if(count<maxTry){
            count++;
            return true;
        }
        //if false is returned, the test method won't run
        return false;
    }
}
