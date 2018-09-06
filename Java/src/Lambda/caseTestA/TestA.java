package Lambda.caseTestA;

import java.nio.file.DirectoryStream;
import java.util.List;

class TestA {
    public static void main(String args[]) {
        DirectoryStream.Filter<List<String>> listFilter = (List<String> list) -> list.isEmpty();

        System.out.println("helloworld");
    }
}