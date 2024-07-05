package org.Closure;

public class LocalVarTest {

    interface Account {
        void print();
    }

    public Account getAccount() {
        int money = 100;

        Account account = new Account(){
            public void print() {
                //这里会修改money的值，会报错！
                //money=money+200;

                //能访问money的值，不过只能读！
                System.out.println("账户存款为：" + money);
            }
        };
        account.print();

        return account;
    }

    public static void main(String[] args) {
        //getAccount方法访问完后，里面的局部变量会消失，但却返回了内部类Account的对象的引用到这里被持有着，导致Account对象不能被销毁，其引用了局部变量money; 这是不允许的！
        Account account = new LocalVarTest().getAccount();
        System.out.println(account);
    }
}
