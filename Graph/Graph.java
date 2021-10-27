package Graph;
public class Graph {           //graph with adjustency matrix
    int size;
    String vertices[];
    boolean[][] adjustencyMatrix;
    
    public Graph(String args[]){
        size=args.length;
        vertices= new String[size];
        System.arraycopy(args, 0, vertices, 0, size);
        adjustencyMatrix=new boolean[size][size]; 
    }
    public void add(String x, String y){
        int i,j;
        i=index(x);
        j=index(y);
        adjustencyMatrix[i][j]=adjustencyMatrix[j][i]=true;
    }
    public int index(String s){
        for(int i=0; i<size; i++)
            if(vertices[i].equals(s))
                return i;
        return -1;
    }
    public String toString(){
        StringBuffer sb = new StringBuffer("{"+vertex(0));
        for(int i=1; i<size; i++)
            sb.append(" "+vertex(i));
        return sb+"";
    }
    public String vertex(int i){
        StringBuffer sb = new StringBuffer(vertices[i]+":");
        for(int j=0; j<size; j++)
            if(adjustencyMatrix[i][j])
                sb.append(vertices[j]);
        return sb+"}";
    }
    
    public static void main(String args[]){
        Graph g = new Graph(new String[]{"A","B","C","D","E"});
        
        g.add("A", "B");
        g.add("A","C");
        g.add("A", "E");
        g.add("B", "E");
        g.add("C", "D");
        g.add("D","E");
        System.out.println(g);
    }
}
