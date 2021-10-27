// graph with adjustency list
package Graph;

public class LinkedGraph {
    private int size;
    private List a[];
    
    public LinkedGraph(String[] args){
        size=args.length;
        a= new List[size];
        for(int i=0; i<size; i++){
            a[i]= new List(args[i]);
        }       
    }
    
    private class List{
        String vertex;
        Node edge;
        
        public List(String vertex){
            this.vertex=vertex;
        }
        
        private class Node{
            int to;
            Node next;
            Node(int to, Node next){
                this.to=to;
                this.next=next;
            }
        }
        public void add(int j){
            edge=new Node(j,edge);
        }
        public String toString(){
            StringBuffer sb = new StringBuffer(vertex);
            if(edge!=null)
             sb.append(":");
            for(Node p=edge; p!=null; p=p.next){
                sb.append(a[p.to].vertex);
            }
            return sb+"";
        }
    }
    public void add(String x, String y){
        a[index(x)].add(index(y));
        a[index(y)].add(index(x));
    }
    private int index(String x){
        for(int i=0; i<size; i++)
            if(a[i].vertex.equals(x))
                return i;
        return -1;
    }
    public String toString(){
        if(size==0)
            return "{}";
        StringBuffer sb = new StringBuffer("{"+a[0].toString());
        for(int i=1; i<size; i++)
            sb.append(","+a[i]);
        return sb+"}";
        
    }
    
    public static void main(String args[]){
        LinkedGraph g = new LinkedGraph(new String[]{"A","B","C","D","E"});
        
        g.add("A", "B");
        g.add("A", "D");
        g.add("A", "C");
        g.add("B", "C");
        g.add("C","D");
        System.out.println(g);
    }
}
