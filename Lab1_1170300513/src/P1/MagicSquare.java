package P1;
import java.io.*;
import java.util.*;
public class MagicSquare 
{
	
			public static void main(String[] args)
			{
				boolean s1 =  isLegalMagicSquare("1.txt");
				if(s1)
				{
					System.out.println("1.txt contains a MagicSquare.");
				}
				else
				{
					System.out.println("1.txt does not contains a MagicSquare.");
				}
				System.out.println();
				boolean s2 =  isLegalMagicSquare("2.txt");
				if(s2)
				{
					System.out.println("2.txt contains a MagicSquare.");
				}
				else
				{
					System.out.println("2.txt does not contains a MagicSquare.");
				}
				System.out.println();
				boolean s3 =  isLegalMagicSquare("3.txt");
				if(s3)
				{
					System.out.println("3.txt contains a MagicSquare.");
				}
				else
				{
					System.out.println("3.txt does not contains a MagicSquare.");
				}
				System.out.println();
				boolean s4 =  isLegalMagicSquare("4.txt");
				if(s4)
				{
					System.out.println("4.txt contains a MagicSquare.");
				}
				else
				{
					System.out.println("4.txt does not contains a MagicSquare.");
				}
				System.out.println();
				boolean s5 =  isLegalMagicSquare("5.txt");
				if(s5)
				{
					System.out.println("5.txt contains a MagicSquare.");
				}
				else
				{
					System.out.println("5.txt does not contains a MagicSquare.");
				}
				System.out.println();
				System.out.println("请输入n: ");
				Scanner sc = new Scanner(System.in);
				int n = sc.nextInt();
				boolean s6 = generateMagicSquare(n);
			    s6 =  isLegalMagicSquare("6.txt");
				if(s6)
				{
					System.out.println("6.txt contains a MagicSquare.");
				}
				else
				{
					System.out.println("6.txt does not contains a MagicSquare.");
				}
				
			}
			
			public static boolean isLegalMagicSquare(String fileName)
			{
				int mycounter1 = 0;
				String [] line = new String[10000];
				//文件的读写。
			    try {
			    	
			    	String pathname = "src\\P1\\txt\\"+fileName;
			    	File filename = new File(pathname);
			    	InputStreamReader reader = new InputStreamReader(  new  FileInputStream(filename));
			    	BufferedReader br = new BufferedReader(reader);
				
			    	while((line[mycounter1]=br.readLine())!= null)
			    	{
			    		mycounter1 += 1;
			    	}
			    	}catch (Exception e) 
			    	{
			    		e.printStackTrace(); 
			    	}
			    
			    	int i , j , num ;
			    	int sum1 = 0;
			    	int sum2 = 0;
			    	int [][] square = new int[mycounter1][mycounter1];
			    	for(i = 0; i < mycounter1 ; i++)
			    	{
			    		
			    	  String[] sArray = line[i].split("\t"); //将txt中每一行字符串分割为数字构成的字符串。
			    		num = sArray.length;
			    		if(num != mycounter1) //行数不等于列数则必然不是矩阵，不符合Magic Square的定义，并返回false。。
			    		{
			    			System.out.println("It's not a matrix!");
			    			return false;
			    		}
			    		for(j = 0 ; j < num ; j++)
			    		{
			    		try {
			    			square[i][j] = Integer.valueOf(sArray[j]); //将字符串转化为整数值。
			    			if(square[i][j] < 0) //矩阵中含有负数，则不符合Magic Square的定义，并返回false。。
			    			{
			    				System.out.println("Number problems (non-positive integer)!");
			    				return false;
			    			}
			    		}catch (Exception e) { /**Integer.valueOf()方法中如遇到空格与小数则会触发异常，利用此性质用try/catch机制
			    								 * 来发现小数或间隔非"\t"的情况，并返回false。*/
							System.out.println("Incorrect data format (with decimal or numeric spacing errors)!");
							return false;
						}
			    		//sum1存储第一行的值，sum2通过循环记录每行每列对角线的和，利用等式的传递性，只有每次都相等最终才能符合Magic Square的定义，返回true；否则返回false。	
			    		square[i][j] = Integer.valueOf(sArray[j]);
			    			sum2 += square[i][j];
			    			if(i == 0)
			    				sum1 = sum2;
			    		}
			    		if(sum2 != sum1)
			    			return false;	
			    		sum2 = 0;
			    	}
			    	for(i = 0; i < mycounter1 ; i++)
			    	{
			    		sum2 += square[i][i];
			    	}
			    	if(sum2 != sum1)
			    		return false;
			    	sum2 = 0;
			    	for(i = 0 ; i < mycounter1 ; i++)
			    	{
			    		sum2 += square[i][mycounter1-1-i];
			    	}
			    	if(sum2 != sum1)
			    		return false;
			   	

			    return true;
			}
			public static boolean generateMagicSquare(int n)
			{
				if(n < 0) //判断n是否为负数，如若为则提示出错并返回。
				{
					System.out.println("n is negative！");
					System.out.println("false");
					System.exit(0);
				}
				try {
				int row = 0, col = n / 2, i, j, square = n * n;
				int magic[][] = new int[n][n];
				String[] liner = new String[n];
				for (i = 1; i <= square; i++) { //罗伯法造奇数阶幻方。
					magic[row][col] = i; //一居上行正中央。
					if (i % n == 0) //依次斜填切莫忘。
						row++;
					else {
						if (row == 0) //上出框时向下放。
							row = n - 1;
						else
							row--;
						if (col == (n - 1)) //右出框时向左放。
							col = 0;
						else
							col++;
					}
				}

				for (i = 0; i < n; i++) { //按格式输出幻方。
					for (j = 0; j < n; j++)
					{
						System.out.print(magic[i][j] + "\t");
						if(j < n - 1 && liner[i] != null)
							liner[i] = liner[i] + magic[i][j] + "\t";
						else if(j < n - 1)
							liner[i] = magic[i][j] + "\t";
						else
							liner[i] = liner[i] + magic[i][j];

					}
					System.out.println();
				}
				File writename = new File("src\\P1\\txt\\6.txt");//创建6.txt并将幻方按行写入txt文件中。
				writename.createNewFile();
				BufferedWriter out = new BufferedWriter(new FileWriter(writename));
				for(i = 0 ; i < n ; i++)
				{
					out.write(liner[i] + "\r\n");
				}
				out.close();
				}catch (Exception e) {
					System.out.println("n is even number！");
					System.out.println("false");
					System.exit(0);
				}
				return true;
			}
}
