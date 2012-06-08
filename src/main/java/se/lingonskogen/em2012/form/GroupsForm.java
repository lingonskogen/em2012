package se.lingonskogen.em2012.form;

import java.util.ArrayList;
import java.util.List;

public class GroupsForm
{
    private final GroupForm groupA = new GroupForm("A");
    private final GroupForm groupB = new GroupForm("B");
    private final GroupForm groupC = new GroupForm("C");
    private final GroupForm groupD = new GroupForm("D");
    
    public GroupForm getGroupA()
    {
        return groupA;
    }

    public GroupForm getGroupB()
    {
        return groupB;
    }

    public GroupForm getGroupC()
    {
        return groupC;
    }

    public GroupForm getGroupD()
    {
        return groupD;
    }

    public static final class GroupForm
    {
        private final String name;
        private final List<TeamForm> teams = new ArrayList<TeamForm>(); 
        public GroupForm(String name)
        {
            this.name = name;
        }
        public String getName()
        {
            return name;
        }
        public List<TeamForm> getTeams()
        {
            return teams;
        }
        
    }
    
    public static final class TeamForm
    {
        private int s = 0; // spelade
        private int v = 0; // vunna
        private int o = 0; // oavgjorda
        private int f = 0; // forlorade
        //private int p = 0; // poang
        private final String name;
        
        public TeamForm(String name)
        {
            this.name = name;
        }
        public String getName()
        {
            return name;
        }
        public int getP()
        {
            return (3*v)+o;
        }
        public int getS()
        {
            return s;
        }
        public void setS(int s)
        {
            this.s = s;
        }
        public int getV()
        {
            return v;
        }
        public void setV(int v)
        {
            this.v = v;
        }
        public int getO()
        {
            return o;
        }
        public void setO(int o)
        {
            this.o = o;
        }
        public int getF()
        {
            return f;
        }
        public void setF(int f)
        {
            this.f = f;
        }
        
    }
}
