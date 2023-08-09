package database;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Marks {

    private int[] mathMark;
    private int[] polishMark;
    private int[] englishMark;
    private int[] informaticsMark;
    private int[] peMark;
    private int[] musicMark;
    private int[] religionMark;
    private int[] natureMark;
    private int[] biologyMark;
    private int[] physicsMark;
    private int[] geographyMark;
    private int[] behaviorMark;
    public Marks() {};

    public Marks(int mathI, int mathII, int mathIII, int mathYearly,
                 int polishI, int polishII, int polishIII, int polishYearly,
                 int englishI, int englishII, int englishIII, int englishYearly,
                 int informaticsI, int informaticsII, int informaticsIII, int informaticsYearly,
                 int peI, int peII, int peIII, int peYearly,
                 int musicI, int musicII, int musicIII, int musicYearly,
                 int religionI, int religionII, int religionIII, int religionYearly,
                 int natureI, int natureII, int natureIII, int natureYearly,
                 int biologyI, int biologyII, int biologyIII, int biologyYearly,
                 int physicsI, int physicsII, int physicsIII, int physicsYearly,
                 int geographyI, int geographyII, int geographyIII, int geographyYearly,
                 int behaviorI, int behaviorII, int behaviorIII, int behaviorYearly) {
        this.mathMark = new int[] {mathI,mathII,mathIII,mathYearly};
        this.polishMark = new int[] {polishI, polishII, polishIII, polishYearly};
        this.englishMark = new int[] {englishI, englishII, englishIII, englishYearly};
        this.informaticsMark = new int[] {informaticsI, informaticsII, informaticsIII, informaticsYearly};
        this.peMark = new int[] {peI, peII, peIII, peYearly};
        this.musicMark = new int[] {musicI, musicII, musicIII, musicYearly};
        this.religionMark = new int[] {religionI, religionII, religionIII, religionYearly};
        this.natureMark = new int[] {natureI, natureII, natureIII, natureYearly};
        this.biologyMark = new int[] {biologyI, biologyII, biologyIII, biologyYearly};
        this.physicsMark = new int[] {physicsI, physicsII, physicsIII, physicsYearly};
        this.geographyMark = new int[] {geographyI, geographyII, geographyIII, geographyYearly};
        this.behaviorMark = new int[] {behaviorI, behaviorII, behaviorIII, behaviorYearly};
    }
    public Marks(int[] a) {
        this.mathMark = new int[] {a[0],a[1],a[2],a[3]};
        this.polishMark = new int[] {a[4],a[5],a[6],a[7]};
        this.englishMark = new int[] {a[8],a[9],a[10],a[11]};
        this.informaticsMark = new int[] {a[12],a[13],a[14],a[15]};
        this.peMark = new int[] {a[16],a[17],a[18],a[19]};
        this.musicMark = new int[] {a[20],a[21],a[22],a[23]};
        this.religionMark = new int[] {a[24],a[25],a[26],a[27]};
        this.natureMark = new int[] {a[28],a[29],a[30],a[31]};
        this.biologyMark = new int[] {a[32],a[33],a[34],a[35]};
        this.physicsMark = new int[] {a[36],a[37],a[38],a[39]};
        this.geographyMark = new int[] {a[40],a[41],a[42],a[43]};
        this.behaviorMark = new int[] {a[44],a[45],a[46],a[47]};
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

    public int[] getNatureMark() {
        return natureMark;
    }

    public void setNatureMark(int[] natureMark) {
        this.natureMark = natureMark;
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

    public int[] getArrayOfMarks() {
        List<int[]> listOfArrays = new ArrayList<>();
        listOfArrays.add(getMathMark());
        listOfArrays.add(getPolishMark());
        listOfArrays.add(getEnglishMark());
        listOfArrays.add(getInformaticsMark());
        listOfArrays.add(getPeMark());
        listOfArrays.add(getMusicMark());
        listOfArrays.add(getReligionMark());
        listOfArrays.add(getNatureMark());
        listOfArrays.add(getBiologyMark());
        listOfArrays.add(getPhysicsMark());
        listOfArrays.add(getGeographyMark());
        listOfArrays.add(getBehaviorMark());

        int i = 0;
        int[] arrayOfMarks = new int[48];
        for (int[] marks : listOfArrays) {
            for (int element : marks) {
                arrayOfMarks[i] = element;
                i++;
            }
        }
        return arrayOfMarks;
    }
    public Marks setMarksFromArray(int[] array) {

        return null;
    }

    @Override
    public String toString() {
        return "Marks{" +
                "mathMark=" + Arrays.toString(mathMark) +
                ", polishMark=" + Arrays.toString(polishMark) +
                ", englishMark=" + Arrays.toString(englishMark) +
                ", informaticsMark=" + Arrays.toString(informaticsMark) +
                ", peMark=" + Arrays.toString(peMark) +
                ", musicMark=" + Arrays.toString(musicMark) +
                ", religionMark=" + Arrays.toString(religionMark) +
                ", natureMark=" + Arrays.toString(natureMark) +
                ", biologyMark=" + Arrays.toString(biologyMark) +
                ", physicsMark=" + Arrays.toString(physicsMark) +
                ", geographyMark=" + Arrays.toString(geographyMark) +
                ", behaviorMark=" + Arrays.toString(behaviorMark) +
                '}';
    }
}


