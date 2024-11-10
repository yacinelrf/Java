public class StringIntCouple{
private final String str;
private int ent;

public StringIntCouple(String s,int e){
   str=s;
   ent=e;
}
public StringIntCouple(String s){
    this(s,0); 
}

public String fst(){
    return str;
}
public int snd(){
    return ent;
}
public void setSnd(int e){
    this.ent=e;
}
public void incrSnd(){
    this.ent++;
}
public void decrSnd(){
    this.ent--;
}
public boolean equals(String s2){
  return equals(s2);
}
public String toString(){
    return toString();
}
public int hashCode(String s){
    return s.hashCode();

}
public class TryStringIntCouple{
    public static void main(String[] args){
        StringIntCouple sic=new StringIntCouple("bounjour");
        System.out.printf("the string is : %s\n",sic.fst());
        System.out.printf("the number is : %d\n",sic.snd());
        sic.setSnd(5);
        System.out.printf("the number is : %d\n",sic.snd());
        sic.incrSnd();
        System.out.printf("the number is : %d\n",sic.snd());
        sic.decrSnd();
        System.out.printf("the number is : %d\n",sic.snd());


        System.out.printf("is the string equals to bonjour? %b\n",sic.fst().equals("bonjour"));
        System.out.printf("the hashcode is : %d\n",sic.hashCode());



    }
}
}