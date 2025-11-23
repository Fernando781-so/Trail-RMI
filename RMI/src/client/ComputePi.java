package client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.math.BigDecimal;
import compute.Compute;

public class ComputePi {

    public static void main(String[] args) {
        try {
            if (args.length < 2) {
                System.out.println("Uso: java client.ComputePi <host> <digits>");
                return;
            }

            String host = args[0];
            int digits = Integer.parseInt(args[1]);

            Registry registry = LocateRegistry.getRegistry(host);

            Compute comp = (Compute) registry.lookup("Compute");

            Pi task = new Pi(digits);

            BigDecimal pi = comp.executeTask(task);

            System.out.println("π ≈ " + pi);
        } catch (Exception e) {
            System.err.println("Error en ComputePi:");
            e.printStackTrace();
        }
    }
}
