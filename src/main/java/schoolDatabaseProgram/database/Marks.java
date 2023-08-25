package schoolDatabaseProgram.database;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Marks implements Cloneable{

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
    public Marks() {}

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
    public int[] getPolishMark() {
        return polishMark;
    }
    public int[] getEnglishMark() {
        return englishMark;
    }
    public int[] getInformaticsMark() {
        return informaticsMark;
    }
    public int[] getPeMark() {
        return peMark;
    }
    public int[] getMusicMark() {
        return musicMark;
    }
    public int[] getReligionMark() {
        return religionMark;
    }
    public int[] getNatureMark() {
        return natureMark;
    }
    public int[] getBiologyMark() {
        return biologyMark;
    }
    public int[] getPhysicsMark() {
        return physicsMark;
    }
    public int[] getGeographyMark() {
        return geographyMark;
    }
    public int[] getBehaviorMark() {
        return behaviorMark;
    }
    @JsonIgnore
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
    @JsonIgnore
    public boolean getPromotion(int grade) {
        int[] arrayOfMarks = getArrayOfMarks();
        int jumpIndex = (grade>6) ? 27 : 31;
        boolean promotion = true;
        for (int i = 0; i < 48; i++) {
            if (i%4==3) {
                if (!(arrayOfMarks[i]>1 && arrayOfMarks[i]<7)) {return false;}
            } else {
                if (!(arrayOfMarks[i]>0 && arrayOfMarks[i]<7)) {return false;}
            }
            if (i==jumpIndex) {
                if (grade>6) {
                    i+=4;
                } else {
                    i+=12;
                }
            }
        }
        return promotion;
    }
    @JsonIgnore
    public boolean isAwardBar (boolean promotion, int grade) {
        if (promotion) {
            int quantityOfSubjects = (grade>6)?10:8;
            double average;
            if (grade>6) {
                average = (double) (getMathMark()[3] +
                        getPolishMark()[3] +
                        getEnglishMark()[3] +
                        getInformaticsMark()[3] +
                        getPeMark()[3] +
                        getMusicMark()[3] +
                        getReligionMark()[3] +
                        getBiologyMark()[3] +
                        getPhysicsMark()[3] +
                        getGeographyMark()[3]) /
                quantityOfSubjects;
            } else {
                average = (double) (getMathMark()[3] +
                        getPolishMark()[3] +
                        getEnglishMark()[3] +
                        getInformaticsMark()[3] +
                        getPeMark()[3] +
                        getMusicMark()[3] +
                        getReligionMark()[3] +
                        getNatureMark()[3]) /
                        quantityOfSubjects;
            }
            return average > 4.65 && getBehaviorMark()[3] > 4;
        } else {
            return false;
        }
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

    @JsonIgnore
    @Override
    public Marks clone() {
        try {
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            return (Marks) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}


