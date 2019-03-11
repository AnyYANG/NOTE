package CollectionCompartor;

public class kuaisu {

    public static void main(String args[]) {

    }
   public int[] quick(int[] array){
       int length= array.length;
       int right;
       int left;
       int middle = array[0];
       for(int i=length-1;i>0;i--){
           right=array[i];
           if(right<middle){
               for(int j=1;j<i;j++){
                   left=array[j];
                   if(left>middle){
                       int temp2= left;
                       left=right;
                       right=temp2;
                   }
               }
           }
       }
        return array;
   }
}

