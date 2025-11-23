package engine;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import compute.Compute;
import compute.Task;

public class ComputeEngine implements Compute{

    public ComputeEngine() {
        super();
    }

    public <T> T executeTask(Task<T> t) {
        return t.execute();
    }

    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(1099);
            System.out.println("Registro RMI iniciado en puerto 1099");

            Compute engine = new ComputeEngine();

            Compute stub = (Compute)
                    UnicastRemoteObject.exportObject(engine, 0);

            Registry registry = LocateRegistry.getRegistry();
            registry.rebind("Compute", stub);

            System.out.println("ComputeEngine listo");
        } catch (Exception e) {
            System.err.println("Error en ComputeEngine:");
            e.printStackTrace();
        }
    }
}
