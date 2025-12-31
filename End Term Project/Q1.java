// Objective: To design a CPU scheduler for simulating a few CPU Scheduling policies.
import java.util.*;
class Process{
    int pid,arrival,burst,remaining;
    int start=-1,completion,waiting,turnaround,response;
}
// ============FCFS==============
public class Q1{
    static void fcfs(Process[] p, int n){
        int time = 0;
        double awt = 0 , att = 0 , art = 0;
        System.out.println("\nGantt Chart: ");
        System.out.print("|");
        for(int i = 0 ; i<n ; i++){
            if(time<p[i].arrival){
                time = p[i].arrival;
            }
            p[i].start = time;
            p[i].response = p[i].start - p[i].arrival;
            time+=p[i].burst;
            p[i].completion = time;
            p[i].turnaround = p[i].completion-p[i].arrival;
            p[i].waiting = p[i].turnaround-p[i].burst;
            System.out.print("P"+ p[i].pid + "|");
            awt+=p[i].waiting;
            att+=p[i].turnaround;
            art+=p[i].response;
        }
        System.out.println("\nAverage Waiting Time = "+ (awt/n));
        System.out.println("\nAverage Turnaround Time = " + (att/n));;
        System.out.println("\nAverage Response Time = " + (art/n));;
    }
    // ============ Round Robin ==============
    static void roundRobin(Process[] p , int n , int tq){
        int time = 0 , completed = 0;
        double awt = 0 , att = 0 , art = 0;
        System.out.println("\nGantt Chart: ");
        System.out.print("|");
        while(completed<n){
            for(int i = 0 ; i < n ; i++){
                if(p[i].arrival<=time && p[i].remaining > 0){
                    if(p[i].start == -1){
                        p[i].start = time;
                        p[i].response = p[i].start - p[i].arrival;
                    }
                    System.out.print("P" + p[i].pid + "|");
                    if(p[i].remaining > tq){
                        time+=tq;
                        p[i].remaining -= tq;
                    }else{
                        time+=p[i].remaining;
                        p[i].remaining=0;
                        p[i].completion=time;
                        p[i].turnaround=p[i].completion-p[i].arrival;
                        p[i].waiting = p[i].turnaround-p[i].burst;
                        awt += p[i].waiting;
                        att += p[i].turnaround;
                        art += p[i].response;
                        completed++;
                    }
                }
            }
        }
        System.out.println("\nAverage Waiting Time = "+ (awt/n));
        System.out.println("\nAverage Turnaround Time = " + (att/n));;
        System.out.println("\nAverage Response Time = " + (art/n));;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Process[] p = new Process[5];
        int[] arrival = {0,2,4,6,8};
        int[] burst = {3,6,4,5,2};
        for(int i = 0 ; i < 5 ; i++){
            p[i] = new Process();
            p[i].pid = i+1;
            p[i].arrival = arrival[i];
            p[i].burst = burst[i];
            p[i].remaining = burst[i];
        }
        while(true){
            System.out.println("\n1.FCFS");
            System.out.println("2.Round Robin");
            System.out.println("3.Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            switch(choice){
                case 1:
                    fcfs(p,5);
                    break;
                case 2:
                    System.out.print("Enter time quantum: ");
                    int tq = sc.nextInt();
                    roundRobin(p,5,tq);
                        break;
                case 3:
                    System.out.println("Program ended successfully.");
                    sc.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid Choice.");
            }
        }
    }
}