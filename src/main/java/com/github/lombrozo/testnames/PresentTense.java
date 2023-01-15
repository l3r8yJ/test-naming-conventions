package com.github.lombrozo.testnames;

public class PresentTense implements Rule{
    private final TestCase test;

    public PresentTense(final TestCase test) {
        this.test = test;
    }

    @Override
    public void validate() throws WrongTestName {
        if(!presentTense()){
            throw new WrongTestName(test, "the test name has to be written using present tense");
        }
    }

    private boolean presentTense() {
        final char[] chars = test.name().toCharArray();
        char prev = '!';
        for (final char c : chars) {
            if (Character.isUpperCase(c)) {
                return prev == 's';
            } else {
                prev = c;
            }
        }
        return prev == 's';
    }
}
