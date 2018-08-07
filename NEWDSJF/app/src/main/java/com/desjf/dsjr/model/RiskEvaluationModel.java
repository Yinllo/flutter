package com.desjf.dsjr.model;

/**
 * @Author YinL
 * @Date 2018/2/6 0006
 * @Describe 风险测评题目
 */

public class RiskEvaluationModel {
    private String title;
    private String optionOne;
    private String optionTwo;
    private String optionThree;
    private String optionFour;

    private boolean One;
    private boolean Two;
    private boolean Three;
    private boolean Four;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOptionOne() {
        return optionOne;
    }

    public void setOptionOne(String optionOne) {
        this.optionOne = optionOne;
    }

    public String getOptionTwo() {
        return optionTwo;
    }

    public void setOptionTwo(String optionTwo) {
        this.optionTwo = optionTwo;
    }

    public String getOptionThree() {
        return optionThree;
    }

    public void setOptionThree(String optionThree) {
        this.optionThree = optionThree;
    }

    public String getOptionFour() {
        return optionFour;
    }

    public void setOptionFour(String optionFour) {
        this.optionFour = optionFour;
    }

    public boolean isOne() {
        return One;
    }

    public void setOne(boolean one) {
        One = one;
    }

    public boolean isTwo() {
        return Two;
    }

    public void setTwo(boolean two) {
        Two = two;
    }

    public boolean isThree() {
        return Three;
    }

    public void setThree(boolean three) {
        Three = three;
    }

    public boolean isFour() {
        return Four;
    }

    public void setFour(boolean four) {
        Four = four;
    }
}
