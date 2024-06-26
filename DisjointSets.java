import java.io.*;
import java.util.*;
 
public class Main
{
    public static void main(String[] args)throws IOException
    {
        Scanner ss=new Scanner(System.in);
        System.out.println("enter no of rows and columns:");
        int r=ss.nextInt();
        int q=ss.nextInt();
        int[][] a = new int[r][q];
        System.out.println("enter the adjoint matrix");
        for(int i=0;i<r;i++){
            for(int j=0;j<q;j++){
                a[i][j]=ss.nextInt();
            }
        }

        System.out.println("Number of Islands is: " +
                           countIslands(a));
                           ss.close();
     }
 
  
     static int countIslands(int a[][])
     {
        int n = a.length;
        int m = a[0].length;
 
        DisjointUnionSets dus = new DisjointUnionSets(n*m);
 
 
        for (int j=0; j<n; j++)
        {
            for (int k=0; k<m; k++)
            {
             
                if (a[j][k] == 0)
                    continue;
 
             
                if (j+1 < n && a[j+1][k]==1)
                    dus.union(j*(m)+k, (j+1)*(m)+k);
                if (j-1 >= 0 && a[j-1][k]==1)
                    dus.union(j*(m)+k, (j-1)*(m)+k);
                if (k+1 < m && a[j][k+1]==1)
                    dus.union(j*(m)+k, (j)*(m)+k+1);
                if (k-1 >= 0 && a[j][k-1]==1)
                    dus.union(j*(m)+k, (j)*(m)+k-1);
                if (j+1<n && k+1<m && a[j+1][k+1]==1)
                    dus.union(j*(m)+k, (j+1)*(m)+k+1);
                if (j+1<n && k-1>=0 && a[j+1][k-1]==1)
                    dus.union(j*m+k, (j+1)*(m)+k-1);
                if (j-1>=0 && k+1<m && a[j-1][k+1]==1)
                    dus.union(j*m+k, (j-1)*m+k+1);
                if (j-1>=0 && k-1>=0 && a[j-1][k-1]==1)
                    dus.union(j*m+k, (j-1)*m+k-1);
            }
        }
 
      
        int[] c = new int[n*m];
        int numberOfIslands = 0;
        for (int j=0; j<n; j++)
        {
            for (int k=0; k<m; k++)
            {
                if (a[j][k]==1)
                {
 
                    int x = dus.find(j*m+k);
 
                  
                    if (c[x]==0)
                    {
                        numberOfIslands++;
                        c[x]++;
                    }
 
                    else
                        c[x]++;
                }
            }
        }
        return numberOfIslands;
    }
}
 

class DisjointUnionSets
{
    int[] rank, parent;
    int n;
 
    public DisjointUnionSets(int n)
    {
        rank = new int[n];
        parent = new int[n];
        this.n = n;
        makeSet();
    }
 
    void makeSet()
    {
      
        for (int i=0; i<n; i++)
            parent[i] = i;
    }
 
  
    int find(int x)
    {
        if (parent[x] != x)
        {
            
            parent[x]=find(parent[x]);
        }
 
        return parent[x];
    }
 

    void union(int x, int y)
    {
       
        int xRoot = find(x);
        int yRoot = find(y);
 
        
        if (xRoot == yRoot)
            return;
 
      
        if (rank[xRoot] < rank[yRoot])
            parent[xRoot] = yRoot;
 
      
        else if(rank[yRoot]<rank[xRoot])
            parent[yRoot] = xRoot;
 
        else  
        {
            
            parent[yRoot] = xRoot;
 
            
            rank[xRoot] = rank[xRoot] + 1;
        }
    }
}
