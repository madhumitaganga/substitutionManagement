    import java.io.*;
    import java.util.*;
    class Substitution
    {
        public static InputStreamReader reader=new InputStreamReader(System.in);
        public static BufferedReader input=new BufferedReader(reader);
        public static int num123=0;
        public void title()
        {
            System.out.print("\f");
            System.out.println("-----------------------------------------------------------------");
            System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
            System.out.println();
            System.out.println("\t                        VITA                              ");
            System.out.println("\t          Dayananda Sagar College Of Engineering          ");
            System.out.println();
            System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
            System.out.println("-----------------------------------------------------------------");
        }
    
        public int countLines(String s)throws IOException
        {
            File f=new File(s+".txt");
            Scanner scf = new Scanner(f);
            int num=0;
            while(scf.hasNextLine())
            {
                scf.nextLine();
                num++;
            }
            scf.close();
            return num;
        }
    
        public int countTeacherOnLeave()throws IOException
        {
            Calendar cal=Calendar.getInstance();
            String today=cal.get(Calendar.DATE)+"/"+(cal.get(Calendar.MONTH)+1)+"/"+cal.get(Calendar.YEAR);
    
            File f=new File("teacheronleave.txt");
            Scanner scf = new Scanner(f);
            int num=0;
            while(scf.hasNextLine())
            {
                String s=scf.nextLine();
                if(s.startsWith(today))
                    num++;
            }
            scf.close();
            return num;
        }
    
        public String returnName(int id)throws IOException
        {
            File f=new File("Staff.txt");
            Scanner scf = new Scanner(f);
            String name="";
            while(scf.hasNextLine())
            {
                String line=scf.nextLine();
                Scanner sc1=new Scanner(line);
                sc1.useDelimiter("~");
                int id1=sc1.nextInt();
                name=sc1.next();
                if(id1==id)
                    break;
            }
            scf.close();
            return name;
        }
    
        public void copyTodaysTimeTable()throws IOException
        {
            FileWriter fw = new FileWriter("today.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
    
            int n=countLines("Staff");
            for(int i=1 ; i<=n ; i++)
            {
                Calendar cal=Calendar.getInstance();
                int ln=0,day=(cal.get(Calendar.DAY_OF_WEEK))-1;
                File f=new File(i+".txt");
                Scanner s=new Scanner(f);
                while(s.hasNextLine())
                {
                    ln++;
                    String tt=s.nextLine();
                    Scanner st = new Scanner(tt);
                    st.useDelimiter("~");
                    if(ln==day)
                    {
                        pw.print(i);
                        for(int j=0 ; j<10 ; j++)
                        {
                            String t=st.next();
                            if(t.equals("F"))
                                pw.print("~F");
                            else
                                pw.print("~"+t);
                        }
                        pw.println();
                    }
                    st.close();
                }
                s.close();
            }
            pw.close();
            bw.close();
            fw.close();
        }
    
        public void addTeacher()throws IOException
        {
            String name,gen,quali,dept,tt,cno,house;
            int code=countLines("Staff")+1;
            title();
            System.out.println("\tSubstitution Management System - Add Teacher");
            System.out.println("\t--------------------------------------------");
            System.out.print("\n\tEnter the following details to Add Teacher :\n");
            System.out.print("\tName : ");
            name=input.readLine().toUpperCase();
            while(true)
            {
                System.out.print("\tGender ('F' for Female, 'M' for Male) : ");
                char g1=input.readLine().charAt(0);
                g1=Character.toUpperCase(g1);
                if(g1=='F') 
                {
                    gen="FEMALE";
                    break;
                }
                else if(g1=='M')
                {
                    gen="MALE";
                    break;
                }
                else
                    System.out.println("\tWrong Input. Please Re-enter");
            }
            System.out.print("\tContact Number : ");
            cno=input.readLine();
            System.out.print("\tDate of Birth (dd/mm/yyyy) : ");
            String dob=input.readLine();
            System.out.print("\tQualification : ");
            quali=input.readLine().toUpperCase();
            System.out.print("\tDepartment : ");
            dept=input.readLine().toUpperCase();
    
            //Writing the details to staff.txt
            FileWriter fw = new FileWriter("Staff.txt",true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            pw.println(code+"~"+name+"~"+gen+"~"+cno+"~"+dob+"~"+quali+"~"+dept);
            pw.close();
            bw.close();
            fw.close();
    
            //Writing the details to idOfSubstitute.txt
            fw = new FileWriter("idOfSubstitute.txt",true);
            bw = new BufferedWriter(fw);
            pw = new PrintWriter(bw);
            pw.println(code+"~"+0);
            pw.close();
            bw.close();
            fw.close();
     
            title();
            System.out.println("\tSubstitution Management System - Add Teacher");
            System.out.println("\t--------------------------------------------");
            System.out.println();
            System.out.println("\tThe following details are added:\n ");
            System.out.println("\tTeacher ID :    "+code);
            System.out.println("\tName :          "+name);
            System.out.println("\tGender :        "+gen);
            System.out.println("\tContact No. :   "+cno);
            System.out.println("\tDate of Birth : "+dob);
            System.out.println("\tQualification : "+quali);
            System.out.println("\tDepartment :    "+dept);
            System.out.println("\n\tPress ENTER key to continue...");
            input.readLine();
    
            title();
            System.out.println("\tSubstitution Management System - Add Teacher's Time Table");
            System.out.println("\t---------------------------------------------------------");
            System.out.println();
            
            FileWriter fout=new FileWriter(code+".txt");
            BufferedWriter bout=new BufferedWriter(fout);
            PrintWriter pout=new PrintWriter(bout); 
            for(int i=1;i<=5;i++)
            {
                switch(i)
                {
                    case 1:
                    System.out.println("\n\tEnter Monday's Time Table: \n");
                    break;
                    case 2:
                    System.out.println("\n\tEnter Tuesday's Time Table: \n");
                    break;
                    case 3:
                    System.out.println("\n\tEnter Wednesday's Time Table: \n");
                    break;
                    case 4:
                    System.out.println("\n\tEnter Thursday's Time Table: \n");
                    break;      
                    case 5:
                    System.out.println("\n\tEnter Friday's Time Table: \n");
                    break;
                }   
                for(int j=1;j<=10;j++)
                {
                    System.out.print("\tPeriod "+j+" : ");
                    tt=input.readLine();
                    if(tt.equals(""))
                        tt="F";
                    pout.print(tt.toUpperCase());
                    if(j<10)
                        pout.print("~");
                }
                pout.println();
            }
            pout.close();
            bout.close();
            fout.close();
     
            File f=new File(code+".txt");
            Scanner sc=new Scanner(f);
            title();
            System.out.println("\tSubstitution Management System - Add Teacher");
            System.out.println("\t--------------------------------------------");
            System.out.println();
            System.out.println("\tThe following Time Table is added:\n ");
            System.out.println("\t"+name+"'S Time Table\n");
            System.out.println("\tP 1\tP 2\tP 3\tP 4\tP 5\tP 6\tP 7\tP 8\tP 9\tP 10\n");
            while(sc.hasNextLine())
            {
                System.out.print("\t");
                Scanner st=new Scanner(sc.nextLine());
                st.useDelimiter("~");
                for(int i=0 ; i<10 ; i++)
                    System.out.print(st.next()+"\t");
                System.out.println();
            }
            sc.close();
            System.out.println("");
            System.out.println("\tPress ENTER key to continue...");
            input.readLine();
        }
    
    
        public void teacherOnLeave()throws IOException
        {
            String name="";
            boolean found=false;
            int code,c=0;
            System.out.print("\n\tEnter the ID of the Teacher on Leave : ");
            code=Integer.parseInt(input.readLine());
            int id=countLines("Staff");
            if(code<=id)
            {
                name=returnName(code);
                System.out.println("\n\tToday's Time Table of "+name+"\n");
                System.out.print(/*\tID*/"\tP 1\tP 2\tP 3\tP 4\tP 5\tP 6\tP 7\tP 8\tP 9\tP 10\n");
                displayTodaysTimeTable(code);
                System.out.println("\n\tPress ENTER key to continue...");
                input.readLine();
                System.out.println("\f");
                FileWriter fw = new FileWriter("teacheronleave.txt",true);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter pw = new PrintWriter(bw);
                Calendar cal=Calendar.getInstance();
                pw.print(cal.get(Calendar.DATE)+"/"+(cal.get(Calendar.MONTH)+1)+"/"+cal.get(Calendar.YEAR));
                pw.print("~"+code);
                pw.println();
                pw.close();
                bw.close();
                fw.close();
            }
            else
            {
                System.out.println("\n\tID Does Not Exist...");
                System.out.println("\tPress ENTER key to continue...");
                input.readLine();
                System.out.println("\f");
            }
        }   
    
        public void displayTodaysTimeTable(int id)throws IOException
        {
            int n=countLines("Staff");
            File f=new File("today.txt");
            Scanner s=new Scanner(f);
            while(s.hasNextLine())
            {
                String line = s.nextLine();
                Scanner ss = new Scanner(line);
                ss.useDelimiter("~");
                int c=Integer.parseInt(ss.next());
                if(c==id)
                {
                    System.out.print(/*"\t"+c+*/"\t");
                    for(int j=1 ; j<10 ; j++)
                    {
                        String t=ss.next();
                        if(t.equals("F"))
                            System.out.print("F\t");
                        else
                            System.out.print(""+t+"\t");
                    }
                    System.out.println();
                }
                else
                    continue;
            }
        }
    
        public boolean checkTeacherOnLeave(int id)throws IOException
        {
            String s="";
            boolean status=false;
            Calendar cal=Calendar.getInstance();
            String today1=cal.get(Calendar.DATE)+"/"+(cal.get(Calendar.MONTH)+1)+"/"+cal.get(Calendar.YEAR);
            File f = new File("teacheronleave.txt");
            Scanner sf = new Scanner(f);
            while(sf.hasNextLine())
            {
                String t = sf.nextLine();
                Scanner sft = new Scanner(t);
                sft.useDelimiter("~");
                String today2=sft.next();
                int code=sft.nextInt();
                if(today1.equals(today2) && id==code)
                {
                    status=true;
                    break;
                }
            }
            return status;
        }
    
        public void sort(int ct[][])
        {
            int small=0,pos=0;
            for(int i=0;i<ct.length;i++)
            {
                small=ct[i][1];
                pos=i;
                for(int j=i+1;j<ct.length;j++)
                {
                    if(ct[j][1]<small)
                    {
                        small=ct[j][1];
                        pos=j;
                    }
                }
                int temp1=ct[i][0];
                ct[i][0]=ct[pos][0];
                ct[pos][0]=temp1;
                int temp2=ct[i][1];
                ct[i][1]=ct[pos][1];
                ct[pos][1]=temp2;
            }
        }
    
        public void arrangeSubstitute()throws IOException
        {
            int n1=countLines("Staff")-countTeacherOnLeave();
            String tt1[][] = new String[n1][11];
            int i=0;
            File f1 = new File("today.txt");
            Scanner sf1 = new Scanner(f1);
            while(sf1.hasNextLine())
            {
                String temp=sf1.nextLine();
                Scanner sft=new Scanner(temp);
                sft.useDelimiter("~");
                int tid=sft.nextInt();
                if(!(checkTeacherOnLeave(tid)))
                {
                    tt1[i][0]=""+tid;
                    for(int j=1 ; j<11 ; j++)
                        tt1[i][j]=sft.next();
                    i++;
                }
                sft.close();
            }
            sf1.close();
    
            int n2=countTeacherOnLeave();
            String tt2[][] = new String[n2][11];
            i=0;
            File f2 = new File("today.txt");
            Scanner sf2 = new Scanner(f2);
            while(sf2.hasNextLine())
            {
                String temp=sf2.nextLine();
                Scanner sft=new Scanner(temp);
                sft.useDelimiter("~");
                int tid=sft.nextInt();
                if(checkTeacherOnLeave(tid))
                {
                    tt2[i][0]=""+tid;
                    for(int j=1 ; j<11 ; j++)
                        tt2[i][j]=sft.next();
                    i++;
                }
                sft.close();
            }
            sf2.close();
    
            int n3=countLines("Staff");
            int ct[][] = new int[n3][2]; 
            File f3 = new File("idOfSubstitute.txt");
            Scanner sf3 = new Scanner(f3);
            i=0;
            while(sf3.hasNextLine())
            {
                String temp=sf3.nextLine();
                Scanner cs=new Scanner(temp);
                cs.useDelimiter("~");
                ct[i][0]=cs.nextInt();
                ct[i][1]=cs.nextInt();
                i++;
                cs.close();
            }
            sf3.close();
            sort(ct);
    
            Calendar cal=Calendar.getInstance();
            String today=(cal.get(Calendar.DATE)+"/"+(cal.get(Calendar.MONTH)+1)+"/"+cal.get(Calendar.YEAR));
            String teacher="";
            title();
            System.out.println("\n\tSubstitution Arranged Successfully.");
            System.out.println("\n\tPress ENTER Key to see the report...");
            input.readLine();
    
            title();
            System.out.println("\tSUBSTITUTE REPORT");
            System.out.println("\n\tDate: "+today);
            FileWriter fw1 = new FileWriter("teacheronsubstitute.txt",true);
            BufferedWriter bw1 = new BufferedWriter(fw1);
            PrintWriter pw1 = new PrintWriter(bw1);
            for(int x=0;x<tt2.length;x++)
            {
                pw1.print(today);
                System.out.println("\n\tTeacher On Leave: "+returnName(Integer.parseInt(tt2[x][0]))+"\n");
                pw1.print("~"+tt2[x][0]);
                System.out.println("\tPeriod\tClass\tTeacher on Substitute\n");
                abc:for(int y=1;y<11;y++)
                {
                    if(!(tt2[x][y].equals("F")))
                    {
                        Substitution.num123++;
                        System.out.print("\t"+y+"\t"+tt2[x][y]);
                        pw1.print("~"+y+"~"+tt2[x][y]);
                        int w=0;
                        xyz:for(int z=0;z<ct.length;z++)
                        {
                            for(w=0;w<tt1.length;w++)
                            {
                                if(tt1[w][0].equals(""+ct[z][0]))
                                {
                                    if(tt1[w][y].equals("F"))
                                        break xyz;
                                }
                            }
                        }
                        System.out.print("\t"+returnName(Integer.parseInt(tt1[w][0]))+"\n");
                        pw1.print("~"+tt1[w][0]);
    
                        tt1[w][y]="S";
                        for(int v=0;v<ct.length;v++)
                        {
                            if(tt1[w][0].equals(""+ct[v][0]))
                                ct[v][1]+=1;
                        }
                        sort(ct);
                        continue abc;
                    }
                }
                pw1.println();
            }
            pw1.close();
            bw1.close();
            fw1.close();
    
            FileWriter fw = new FileWriter("idOfSubstitute.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            for(int m=0;m<ct.length;m++)
            {
                pw.println(ct[m][0]+"~"+ct[m][1]);
            }
            pw.close();
            bw.close();
            fw.close();
            System.out.println("\n\tPress ENTER Key to continue...");
            input.readLine();
        }
    
        public void displayTeacherOnSubstitute()throws IOException
        {
            Calendar cal=Calendar.getInstance();
            String today=(cal.get(Calendar.DATE)+"/"+(cal.get(Calendar.MONTH)+1)+"/"+cal.get(Calendar.YEAR));
            String today1="";
            title();
            System.out.println("\tSUBSTITUTE REPORT");
            System.out.println("\n\tDate: "+today);
            File f = new File("teacheronsubstitute.txt");
            Scanner sf = new Scanner(f);
            while(sf.hasNextLine())
            {
                String s = sf.nextLine();
                Scanner st = new Scanner(s);
                st.useDelimiter("~");
                today1=st.next();
                if(today.equals(today1))
                {
                    int onLeave=st.nextInt();
                    String teacherOnLeave=returnName(onLeave);
                    System.out.println("\n\tTeacher On Leave: "+teacherOnLeave+"\n");
                    System.out.println("\tPeriod\tClass\tTeacher on Substitute");
                    try
                    {
                        if(st.hasNext())
                        {
                            for(int i=1;i<=Substitution.num123;i++)
                            {
                                System.out.print("\t"+st.next()+"\t"+st.next()+"\t");
                                int c=st.nextInt();
                                String subTeacher=returnName(c);
                                System.out.print(subTeacher+"\n");
                            }
                        }
                    }
                    catch(NoSuchElementException e)
                    {
                    }
                }
                st.close();
            }
            System.out.println("\n\tPress ENTER Key to continue...");
            char ch=(char)input.read();
            System.out.println("\f");
        }
    
        public static void main(String args[])throws IOException
        {
            Substitution obj = new Substitution();
            obj.copyTodaysTimeTable();
            int ans=0;
            do
            {
                System.out.println("\f");
                obj.title();
                System.out.println("\tWelcome to Substitution Management System");
                System.out.println("\t-----------------------------------------");
                System.out.println();
                System.out.println("\t1. Add Teacher"); 
                System.out.println("\t2. Teacher On Leave"); 
                System.out.println("\t3. Arrange Substitute"); 
                System.out.println("\t4. Substitute Report");
                System.out.println("\t5. Exit");
                System.out.print("\n\tEnter your choice: ");
                ans = Integer.parseInt(input.readLine());
                switch(ans)
                {
                    case 1:
                    obj.addTeacher();
                    break;
                    case 2:
                    obj.teacherOnLeave();
                    break;
                    case 3:
                    obj.copyTodaysTimeTable();
                    obj.arrangeSubstitute();
                    break;
                    case 4:
                    obj.displayTeacherOnSubstitute();
                    break;
                    case 5: 
                    System.out.println("\f");
                    System.out.println("-------------------------------------------------------------------");
                    System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
                    System.out.println();
                    System.out.println("\t        Thank You and Have A Nice Day!         ");
                    System.out.println("\t           By Vikas and Madhumita                ");
                    System.out.println("\t                  Team VITA                ");
                    System.out.println();
                    System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
                    System.out.println("-------------------------------------------------------------------");
                    System.out.println("\n\n\nPress ENTER key to terminate...");
                    input.readLine();
                    System.exit(0);
                    break;
                    default:
                    {
                        System.out.println("\n\tWrong choice! Try again!");
                        System.out.println("\n\tPress ENTER key to continue...");
                        input.readLine();
                        System.out.println("\f");
                    }
                }
            }while(ans!=5);
        }
    }