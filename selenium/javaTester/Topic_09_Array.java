package javaTester;

public class Topic_09_Array {

    int[] studentAge = {18, 19, 20, 21, 22};
    static String[] studentName = {"Nguyen Van A", "Ho Thi B", "Le Tuan C",};

    public static void main(String[] args) {
        String[] studentAddress = new String[3];

        studentAddress[0] = "NgoDucKe";
        studentAddress[1] = "HungVuong";
        studentAddress[2] = "HoThiKy";

        System.out.println(studentName[2]);

        Topic_09_Array intAge = new Topic_09_Array();
        System.out.println(intAge.studentAge[1]);
        System.out.println(intAge.studentAge[4]);

        for(int i = 0; i < studentAddress.length; i++){
            System.out.println(studentAddress[i]);
        }

        for(int i = 0; i < intAge.studentAge.length; i++){
            System.out.println(intAge.studentAge[i]);
        }






    }

}
