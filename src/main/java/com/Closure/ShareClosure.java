package com.Closure;

import java.util.ArrayList;
import java.util.List;

interface Action
{
    void Run();
}

public class ShareClosure {

    List<Action> list = new ArrayList<Action>();

    public void Input()
    {
        for(int i=0;i<10;i++)
        {
            int finalI = i;
            list.add(new Action() {
                @Override
                public void Run() {
                    System.out.println(finalI);
                }
            });
        }
    }

    public void Output()
    {
        for(Action a : list){a.Run();}
    }

    public static void main(String[] args) {
        ShareClosure sc = new ShareClosure();
        sc.Input();
        sc.Output();

    }

}
