package database.marks;

public class MarksTableForSeventhEighthClass extends Marks {
    private final int[] mathMark;
    private final int[] polishMark;
    private final int[] englishMark;
    private final int[] informaticsMark;
    private final int[] peMark;
    private final int[] musicMark;
    private final int[] religionMark;
    private final int[] biologyMark;
    private final int[] physicsMark;
    private final int[] geographyMark;
    private final int[] behaviorMark;

    public MarksTableForSeventhEighthClass(int mathI, int mathII, int mathIII, int mathYearly,
                                          int polishI, int polishII, int polishIII, int polishYearly,
                                          int englishI, int englishII, int englishIII, int englishYearly,
                                          int informaticsI, int informaticsII, int informaticsIII, int informaticsYearly,
                                          int peI, int peII, int peIII, int peYearly,
                                          int musicI, int musicII, int musicIII, int musicYearly,
                                          int religionI, int religionII, int religionIII, int religionYearly,
                                          int biologyI, int biologyII, int biologyIII, int biologyYearly,
                                          int physicsI, int physicsII, int physicsIII, int physicsYearly,
                                          int geographyI, int geographyII, int geographyIII, int geographyYearly,
                                          int behaviorI, int behaviorII, int behaviorIII, int behaviorYearly) {
        this.mathMark = new int[] {mathI,mathII,mathIII,mathYearly};
        this.polishMark = new int[] {polishI, polishII, polishIII, polishYearly};
        this.englishMark = new int[] {englishI, englishII, englishIII, englishYearly};
        this.informaticsMark = new int[] {informaticsI, informaticsII, informaticsIII, informaticsYearly};;
        this.peMark = new int[] {peI, peII, peIII, peYearly};;
        this.musicMark = new int[] {musicI, musicII, musicIII, musicYearly};;
        this.religionMark = new int[] {religionI, religionII, religionIII, religionYearly};;
        this.biologyMark = new int[] {biologyI, biologyII, biologyIII, biologyYearly};;
        this.physicsMark = new int[] {physicsI, physicsII, physicsIII, physicsYearly};;
        this.geographyMark = new int[] {geographyI, geographyII, geographyIII, geographyYearly};;
        this.behaviorMark = new int[] {behaviorI, behaviorII, behaviorIII, behaviorYearly};
    }

    public MarksTableForSeventhEighthClass() {
        this.mathMark = new int[] {0,0,0,0};
        this.polishMark = new int[] {0,0,0,0};
        this.englishMark = new int[] {0,0,0,0};
        this.informaticsMark = new int[] {0,0,0,0};;
        this.peMark = new int[] {0,0,0,0};;
        this.musicMark = new int[] {0,0,0,0};;
        this.religionMark = new int[] {0,0,0,0};;
        this.biologyMark = new int[] {0,0,0,0};;
        this.physicsMark = new int[] {0,0,0,0};;
        this.geographyMark = new int[] {0,0,0,0};;
        this.behaviorMark = new int[] {0,0,0,0};
    }
}
