public class QN5b {

    int[][] capacityDistance;
    int capacity;
    int remCapacity;
    int recharging;
    int distance;
    int distancetravelled;
    int prevDistance=0;
    int nextDistance;

    QN5b(int[][] capacityDistance){
        this.capacityDistance=capacityDistance;
        this.remCapacity=10;
        this.prevDistance=0;

        for(int i=0;i< capacityDistance.length;i++){
            for(int j=1;j>=0;j--){
                if(j==1){
                    capacity=capacityDistance[i][j];
                }else{

                    if(i!=0){
                        prevDistance=capacityDistance[i-1][j];

                    }
                    System.out.println("previous distance is === "+prevDistance);

                    if(i!=capacityDistance.length-1) {
                        nextDistance = capacityDistance[i + 1][j];
//                        System.out.println("next distance "+nextDistance);
                    }
                    distance=capacityDistance[i][j]-prevDistance;
                    System.out.println("distance from on to another is  "+distance);
                    if(remCapacity>=distance){
                        distancetravelled+=distance;
                        remCapacity=remCapacity-distance;
                        System.out.println("distance travelled "+distancetravelled);
                        System.out.println("remaining capacity is"+remCapacity);
                    }else{
                        recharging=recharge(capacity);
                        System.out.println("recharged at"+distancetravelled);
                        System.out.println("new capacity after recharging "+recharging);
                        remCapacity=remCapacity+recharging;
                        if(remCapacity>=distance){
                            distancetravelled+=distance;
                            remCapacity=remCapacity-distance;
                        }
                    }
                }

            }
        }


    }
    int recharge(int serviceCentercharge){
        this.capacity=capacity+serviceCentercharge;
        return capacity;
    }

    public static void main(String[] args) {
        int[][] arr={{10,60},{20,30},{30,30},{60,40},{100,0}};
        //giving the destination distance as 100 to make it travel till the end

        new ElectricCar(arr);
    }
}