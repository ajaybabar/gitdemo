import java.io.*;
import java.util.*;
class Convert{
	public static void main(String args[]) throws IOException{
		BufferedReader brr=new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter File Name Format : Videoid.json");
		String fileName=brr.readLine();
		String out_file="subs_"+fileName.substring(0,fileName.indexOf('.'))+".srt.sjson";
		System.out.println(out_file);
		FileReader fr=null;
		try{
			fr=new FileReader(fileName);
		}
		catch(Exception e){
			System.out.println(e);
		}
		BufferedReader br=new BufferedReader(fr);
		StringTokenizer st=new StringTokenizer(br.readLine(),":");
		int i=1;
		st.nextToken();
		int size=(int)Math.ceil((st.countTokens()+1)/3.0);
		int[] starts=new int[size];
		int s=0,e=0,t=0;
		int[] ends=new int[size];
		String texts[]=new String[size];
		while(st.hasMoreTokens()){
			String token=st.nextToken();
			StringTokenizer sub=null;
			String str=null;
			if((i%3)!=0)
			{sub=new StringTokenizer(token,",");
			str=sub.nextToken();}
			if(i%3==1) starts[s++]=Integer.parseInt(str);
			else if(i%3==2) ends[e++]=Integer.parseInt(str);
			else {
				StringTokenizer sub3=new StringTokenizer(token,"}");
				str=sub3.nextToken();
				texts[t++]=str;
			}
			i++;
		}
		fr.close();
		FileWriter fw=null;
		try{
			fw=new FileWriter(out_file);
		}
		catch(Exception ex){
			System.out.println(ex);
		}
		int j=0;
		fw.write("{\n  \"start\": [\n");
		for(j=0;j<s-1;j++)
		fw.write("    "+starts[j]+",\n");
		fw.write("    "+starts[j]+"\n");
		fw.write("  ]\n  \"end\": [\n");
		for(j=0;j<e-1;j++)
		fw.write("    "+ends[j]+",\n");
		fw.write("    "+ends[j]+"\n");
		fw.write("  ]\n  \"text\": [\n");
		for(j=0;j<t-1;j++)
		fw.write("    "+texts[j]+",\n");
		fw.write("    "+texts[j]+"\n");
		fw.write("  ]\n}");
		fw.close();
	}
}
