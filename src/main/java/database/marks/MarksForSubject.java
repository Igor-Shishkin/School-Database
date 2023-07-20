package database.marks;

import database.marks.Subject;

public class MarksForSubject {
    private Subject subject;
    private byte firstTrimesterMark;
    private byte secondTrimesterMark;
    private byte thirdTrimesterMark;
    private byte yearlyEstimate;

    public MarksForSubject(Subject subject, byte firstTrimesterMark, byte secondTrimesterMark, byte thirdTrimesterMark,
                           byte yearlyEstimate) {
        this.subject = subject;
        this.firstTrimesterMark = firstTrimesterMark;
        this.secondTrimesterMark = secondTrimesterMark;
        this.thirdTrimesterMark = thirdTrimesterMark;
        this.yearlyEstimate = yearlyEstimate;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public byte getFirstTrimesterMark() {
        return firstTrimesterMark;
    }

    public void setFirstTrimesterMark(byte firstTrimesterMark) {
        this.firstTrimesterMark = firstTrimesterMark;
    }

    public byte getSecondTrimesterMark() {
        return secondTrimesterMark;
    }

    public void setSecondTrimesterMark(byte secondTrimesterMark) {
        this.secondTrimesterMark = secondTrimesterMark;
    }

    public byte getThirdTrimesterMark() {
        return thirdTrimesterMark;
    }

    public void setThirdTrimesterMark(byte thirdTrimesterMark) {
        this.thirdTrimesterMark = thirdTrimesterMark;
    }

    public byte getYearlyEstimate() {
        return yearlyEstimate;
    }

    public void setYearlyEstimate(byte yearlyEstimate) {
        this.yearlyEstimate = yearlyEstimate;
    }
}
