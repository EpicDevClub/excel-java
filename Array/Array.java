package Array;
import java.util.Scanner;
public class Array {
    
    public static int linearSearch(int a[],int value){
        for(int i=0; i<a.length; i++){
            if(a[i]==value){
                return i;
            }
        }
        return -1;
    }
    public static int BinarySearch(int a[],int value){
        int first=0;
        int last=a.length-1;
        while(first<=last){
              int mid=first+last/2;
            if(a[mid]<value){
                first=mid+1;
            }
            else if(a[mid]==value)
                return mid;
            else
                last=mid-1;
        }
        return -1;
    }
    
    
    public static void main(String args[]){
        Scanner s = new Scanner(System.in);
        int a[]=new int[5];
       
        System.out.println("input values");
      /*  for(int i=0; i<a.length;i++)
            a[i]=s.nextInt();*/
       int index=linearSearch(a,34);
       System.out.println(index);
        
    }
    
}
