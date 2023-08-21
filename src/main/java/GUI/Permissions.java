package GUI;

public enum Permissions {
    DIRECTOR(9),
    TEACHER(10),
    CLASS_TEACHER_0(0),
    CLASS_TEACHER_1(1),
    CLASS_TEACHER_2(2),
    CLASS_TEACHER_3(3),
    CLASS_TEACHER_4(4),
    CLASS_TEACHER_5(5),
    CLASS_TEACHER_6(6),
    CLASS_TEACHER_7(7),
    CLASS_TEACHER_8(8);

    private  final  int numberPermission;
    Permissions(int numberPermission1) {
        this.numberPermission = numberPermission1;
    }

    public int getNumberPermission() {
        return numberPermission;
    }
}
