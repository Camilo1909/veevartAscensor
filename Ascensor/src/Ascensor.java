import java.util.*;

public class Ascensor {

    static int actualFloor;
    static ArrayList<Integer> stopFloors = new ArrayList<Integer>();
    static int[] buildingFloors;
    static HashMap<Integer,Integer> newFloors;



    public static void main(String[] args) {
        //Conocemos el numero de pisos que tendra el edificio
        Scanner scan = new Scanner(System.in);
        System.out.println("Por favor ingrese el numero de pisos que tendra el edificio");
        String numFloors = scan.nextLine();
        buildingFloors = new int[Integer.parseInt(numFloors)];
        //Conocemos los pisos inciales en los que debera parar el ascensor
        System.out.println("Por favor digite ingrese el numero de los pisos a los que ira el ascensor separados por una coma (,)");
        String pisosString = scan.nextLine();
        String[] pisos = pisosString.split(",");
        System.out.println(pisos);
        int[] pisosIn = new int[pisos.length];
        for(int i = 0; i < pisos.length; i++){
            if(Integer.parseInt(pisos[i])>Integer.parseInt(numFloors)){
                System.out.println("El piso " + Integer.parseInt(pisos[i]) + " no existe en el edificio. Proceso finalizado");
                return;
            }else{
                pisosIn[i] = Integer.parseInt(pisos[i]);
            }
        }

        //Conocemos el piso inicial en el que se encuentra el ascensor
        System.out.println("Ingrese el piso incial de ejecucion");
        int inicial = scan.nextInt();
        newFloors = new HashMap<>();

        //Conocemos los nuevos pisos que se ingresaran en cada parada
        System.out.println("Ingrese el valor de los nuevos pisos");
        for(int i = 0; i < pisos.length; i++){
            System.out.print("En el piso "+ pisosIn[i] + " ingresara el piso : ");
            int newPiso = scan.nextInt();
            newFloors.put(pisosIn[i],newPiso);
        }
        fun(pisosIn,inicial, newFloors);
        scan.close();
    }

    public static void createStopFloors(int[] floors, HashMap<Integer,Integer> newsFloors){
        Integer[] newStopFloors = newsFloors.values().toArray(new Integer[0]);
        //stopFloors = new int[newStopFloors.length+floors.length];
        int pos=0;
        for (int i = 0; i<floors.length;i++){
            stopFloors.add(floors[i]);
            pos = i;
        }

        for (int i=(pos+1), j = 0; i<newStopFloors.length + floors.length ;i++,j++){
            stopFloors.add(newStopFloors[j]);
        }
    }

    public static void fun (int[] floors, int startFloor, HashMap<Integer,Integer> newsFloors  ) {

        //createStopFloors(floors,newsFloors);
        System.out.println(stopFloors.toString());
        actualFloor = startFloor;
        Collections.sort(stopFloors);
        Arrays.sort(floors);
        System.out.println("Mira el recorrido del ascensor");

        if (actualFloor > 0 && actualFloor < buildingFloors.length && actualFloor < floors[0] ){
            ascensorSubiendo(stopFloors,startFloor);
        }

        if (actualFloor > 0 && actualFloor <= buildingFloors.length && actualFloor >= floors[0]){
            ascensorBajando(stopFloors,startFloor);
        }
    }

    public static void ascensorBajando(ArrayList<Integer> newStopFloors, int startFloor){

        System.out.println("Elevador descendiendo");

        for (int i = actualFloor; i > 0; i--) {
            System.out.println("El elevador se encuentra en el piso: " + actualFloor);


            for(int j = 0; j<newStopFloors.size();j++){
                if (newStopFloors.get(j) == actualFloor){
                    System.out.println("Elevador se detiene");
                }
            }

            if (actualFloor  > 0){
                System.out.println("Elevador descendiendo");
                actualFloor--;
            }


            if (actualFloor == 1){
                System.out.println("El elevador se encuentra en el piso: " + actualFloor);
                System.out.println("Elevador se detiene");
                break;
            }
        }
        actualFloor = actualFloor + startFloor;
    }

    public static void ascensorSubiendo(ArrayList<Integer>  newStopFloors, int startFloor){

        System.out.println("El elevador se encuentra en el piso: " + actualFloor);
        System.out.println("Elevador subiendo");
        actualFloor += 1;

        for (int i = startFloor; i < buildingFloors.length; i++) {
            System.out.println("El elevador se encuentra en el piso: " + actualFloor);
            for(int j = 0; j<newStopFloors.size();j++){
                if (newStopFloors.get(j) == actualFloor){
                    System.out.println("Elevador se detiene");
                    if(j+1<stopFloors.size()){
                        if(newStopFloors.get(j) == newStopFloors.get(j+1)){
                            System.out.println("Piso ingresado: "+ newFloors.get(newStopFloors.get(j)));
                            newStopFloors.remove(j);
                            newStopFloors.remove(j+1);

                        }else{
                            System.out.println("Piso ingresado: "+ newFloors.get(newStopFloors.get(j)));
                            newStopFloors.remove(j);

                        }
                    }else {
                        System.out.println("Piso ingresado: "+ newFloors.get(newStopFloors.get(j)));
                        newStopFloors.remove(j);
                    }
                }
            }
            if (actualFloor < buildingFloors.length){
                System.out.println("Elevador subiendo");
                actualFloor++;
            }
        }
        actualFloor = actualFloor - 1;
    }
}