package database.marks;

public class MarksTableForSeventhEighthClass extends Marks {
    private int[] mathMark;
    private int[] polishMark;
    private int[] englishMark;
    private int[] informaticsMark;
    private int[] peMark;
    private int[] musicMark;
    private int[] religionMark;
    private int[] biologyMark;
    private int[] physicsMark;
    private int[] geographyMark;
    private int[] behaviorMark;

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

    public int[] getMathMark() {
        return mathMark;
    }

    public void setMathMark(int[] mathMark) {
        this.mathMark = mathMark;
    }

    public int[] getPolishMark() {
        return polishMark;
    }

    public void setPolishMark(int[] polishMark) {
        this.polishMark = polishMark;
    }

    public int[] getEnglishMark() {
        return englishMark;
    }

    public void setEnglishMark(int[] englishMark) {
        this.englishMark = englishMark;
    }

    public int[] getInformaticsMark() {
        return informaticsMark;
    }

    public void setInformaticsMark(int[] informaticsMark) {
        this.informaticsMark = informaticsMark;
    }

    public int[] getPeMark() {
        return peMark;
    }

    public void setPeMark(int[] peMark) {
        this.peMark = peMark;
    }

    public int[] getMusicMark() {
        return musicMark;
    }

    public void setMusicMark(int[] musicMark) {
        this.musicMark = musicMark;
    }

    public int[] getReligionMark() {
        return religionMark;
    }

    public void setReligionMark(int[] religionMark) {
        this.religionMark = religionMark;
    }

    public int[] getBiologyMark() {
        return biologyMark;
    }

    public void setBiologyMark(int[] biologyMark) {
        this.biologyMark = biologyMark;
    }

    public int[] getPhysicsMark() {
        return physicsMark;
    }

    public void setPhysicsMark(int[] physicsMark) {
        this.physicsMark = physicsMark;
    }

    public int[] getGeographyMark() {
        return geographyMark;
    }

    public void setGeographyMark(int[] geographyMark) {
        this.geographyMark = geographyMark;
    }

    public int[] getBehaviorMark() {
        return behaviorMark;
    }

    public void setBehaviorMark(int[] behaviorMark) {
        this.behaviorMark = behaviorMark;
    }
}
