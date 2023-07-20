package database.marks;

public class MarksTableForthFifthSixthClass extends Marks {

    private int[] mathMark;
    private int[] polishMark;
    private int[] englishMark;
    private int[] informaticsMark;
    private int[] peMark;
    private int[] musicMark;
    private int[] religionMark;
    private int[] natureMark;
    private int[] behaviorMark;

    public MarksTableForthFifthSixthClass(int mathI, int mathII, int mathIII, int mathYearly,
                                          int polishI, int polishII, int polishIII, int polishYearly,
                                          int englishI, int englishII, int englishIII, int englishYearly,
                                          int informaticsI, int informaticsII, int informaticsIII, int informaticsYearly,
                                          int peI, int peII, int peIII, int peYearly,
                                          int musicI, int musicII, int musicIII, int musicYearly,
                                          int religionI, int religionII, int religionIII, int religionYearly,
                                          int natureI, int natureII, int natureIII, int natureYearly,
                                          int behaviorI, int behaviorII, int behaviorIII, int behaviorYearly) {
        this.mathMark = new int[] {mathI,mathII,mathIII,mathYearly};
        this.polishMark = new int[] {polishI, polishII, polishIII, polishYearly};
        this.englishMark = new int[] {englishI, englishII, englishIII, englishYearly};
        this.informaticsMark = new int[] {informaticsI, informaticsII, informaticsIII, informaticsYearly};;
        this.peMark = new int[] {peI, peII, peIII, peYearly};;
        this.musicMark = new int[] {musicI, musicII, musicIII, musicYearly};;
        this.religionMark = new int[] {religionI, religionII, religionIII, religionYearly};;
        this.natureMark = new int[] {natureI, natureII, natureIII, natureYearly};;
        this.behaviorMark = new int[] {behaviorI, behaviorII, behaviorIII, behaviorYearly};;
    }
}
