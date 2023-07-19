package database.marks;

import database.marks.Subject;

public class MarksForSubject {
    private final Subject subject;
    private final byte firstTrimesterMark;
    private final byte secondTrimesterMark;
    private final byte thirdTrimesterMark;
    private final byte yearliEstimate;

    public MarksForSubject(Subject subject, byte firstTrimesterMark, byte secondTrimesterMark, byte thirdTrimesterMark, byte yearliEstimate) {
        this.subject = subject;
        this.firstTrimesterMark = firstTrimesterMark;
        this.secondTrimesterMark = secondTrimesterMark;
        this.thirdTrimesterMark = thirdTrimesterMark;
        this.yearliEstimate = yearliEstimate;
    }


}
