import java.util.Scanner;

class Process {
    int processId;
    int arrivalTime;
    int burstTime;
    int waitingTime;
    int turnAroundTime;
}

public class SchedulingAlgorithms {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of processes:");
        int numProcesses = scanner.nextInt();

        Process[] processes = new Process[numProcesses];

        for (int i = 0; i < numProcesses; i++) {
            processes[i] = new Process();
            System.out.println("Enter the arrival time and burst time for process " + (i + 1) + ":");
            processes[i].processId = i + 1;
            processes[i].arrivalTime = scanner.nextInt();
            processes[i].burstTime = scanner.nextInt();
        }

        fcfsScheduling(processes);
    }

    public static void fcfsScheduling(Process[] processes) {
        int totalWaitingTime = 0;
        int totalTurnAroundTime = 0;

        for (int i = 0; i < processes.length; i++) {
            if (i == 0) {
                processes[i].waitingTime = 0;
            } else {
                processes[i].waitingTime = processes[i - 1].arrivalTime + processes[i - 1].burstTime + processes[i - 1].waitingTime - processes[i].arrivalTime;
            }
            processes[i].turnAroundTime = processes[i].waitingTime + processes[i].burstTime;
            totalWaitingTime += processes[i].waitingTime;
            totalTurnAroundTime += processes[i].turnAroundTime;
        }

        System.out.println("\nProcess\tArrival Time\tBurst Time\tWaiting Time\tTurnaround Time");
        for (Process process : processes) {
            System.out.println("P" + process.processId + "\t" + process.arrivalTime + "\t\t" + process.burstTime + "\t\t" + process.waitingTime + "\t\t" + process.turnAroundTime);
        }

        System.out.println("\nAverage Waiting Time: " + (float) totalWaitingTime / processes.length);
        System.out.println("Average Turnaround Time: " + (float) totalTurnAroundTime / processes.length);
    }
}
