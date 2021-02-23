package com.github.dev.muzi.base.concurrent.knowledge.core.exercise.sync.wn;


/**
 * create by muzi 2019-06-29
 *
 * wait和notifyAll的使用问题：快递场景
 */
public class Express {

    public static final String CITY = "上海";
    private int km;
    private String site;

    public Express() {
    }

    public Express(int km, String site) {
        this.km = km;
        this.site = site;
    }

    synchronized public void changeKm(){
        this.km = 101;
        notifyAll();
    }

    synchronized public void changeSite(){
        this.site = "北京";
        notifyAll();
    }

    synchronized public void waitKm(){
        while (this.km < 100){
            try {
                this.wait();
                System.out.println("Check Km thread[" + Thread.currentThread().getId() + "] is be notified ");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("the Km is " + this.km + " ,I will change db");
    }

    synchronized public void waitSite(){
        while(this.site.equals(CITY)){
            try {
                this.wait();
                System.out.println("Check Site thread[" + Thread.currentThread().getId() + "] is be notified ");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("the site is " + this.site + " ,I will call user");
    }
}
