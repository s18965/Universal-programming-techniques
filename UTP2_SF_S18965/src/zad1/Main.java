/**
 *
 *  @author Stachurski Filip S18965
 *
 */

package zad1;



import java.util.*;

public class Main {
  public Main() {
    List<Integer> src1 = Arrays.asList(1,2,3,4,52,6,7,8,8);
    System.out.println(test1(src1));

    List<String> src2 = Arrays.asList("s","s ss","zzzz","szszszsz");
    System.out.println(test2(src2));
  }

  public List<Integer> test1(List<Integer> src) {
    Selector<Integer> sel= (i) -> i<10;
    Mapper<Integer,Integer> map = new Mapper<Integer, Integer>() {
      @Override
      public Integer map(Integer arg) {
        return arg+10;
      }
    };
    return ListCreator.collectFrom(src).when(sel).mapEvery(map);
  }

  public List<Integer> test2(List<String> src) {
    Selector<String> sel=new Selector <String>(){
      @Override
      public boolean select(String s) {
        return s.length()>3;
      }
    };
    Mapper<String,Integer> map = new Mapper<String, Integer>() {

      @Override
      public Integer map(String s) {
        return s.length()+10;
      }
    };
    return ListCreator.collectFrom(src).when(sel).mapEvery(map);
  }

  public static void main(String[] args) {
    new Main();
  }
}
