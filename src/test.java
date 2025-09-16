import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class test {
    public static void main(String[] args) throws IOException {
        test test9= new test();
        test9.resolverParte1();
        test9.resolverParte2();
    }
    public void resolverParte1() throws IOException {
        List<LinkedList<Integer>> informe = this.cargaDatos();
        this.sumaValoresParte1(informe);
    }
    public void resolverParte2() throws IOException {
        List<LinkedList<Integer>> informe = this.cargaDatos();
        this.sumaValoresParte2(informe);
    }

    private  List<LinkedList<Integer>> cargaDatos() throws IOException
    {
        List<LinkedList<Integer>> informe = new LinkedList<>();

        try(BufferedReader in = new BufferedReader(new FileReader("ressources/mirageMaintenance.txt")))
        {
            String line;
            while((line=in.readLine())!=null)
            {
                LinkedList<Integer> lineInforme = new LinkedList<>();
                String[] numerosHistorial = line.trim().split(" ");
                for(int n=0; n<numerosHistorial.length;n++)
                {
                    lineInforme.add(Integer.parseInt(numerosHistorial[n]));
                }
                informe.add(lineInforme);
            }
        }
        return informe;
    }

    private void sumaValoresParte1(List<LinkedList<Integer>> informe)
    {
        long valorFinal=0;
        for (LinkedList<Integer> lineInforme: informe)
        {
            valorFinal = valorFinal + this.valorHistorialParte1(lineInforme);
        }
        System.out.println(valorFinal);
    }

    private long valorHistorialParte1(LinkedList<Integer> lineInforme)
    {
        long valorFinal=0;
        int cantidadValores = lineInforme.size();
        int condicion=0;

        do {
            for(int valor=1;valor<cantidadValores-condicion;valor++)
            {

                int diferenciaValor = lineInforme.get(valor) - lineInforme.get(valor-1);
                lineInforme.set(valor-1,diferenciaValor);

            }
            condicion++;
        }while(condicion!= cantidadValores);
        for(int valor: lineInforme)
        {
            valorFinal = valorFinal+valor;
        }

        return valorFinal ;
    }

    private void sumaValoresParte2(List<LinkedList<Integer>> informe)
    {
        long valorFinal=0;
        for (LinkedList<Integer> lineInforme: informe)
        {
            valorFinal = valorFinal + this.valorHistorialParte2(lineInforme);
        }
        System.out.println(valorFinal);
    }

    private long valorHistorialParte2(LinkedList<Integer> lineInforme)
    {
        int cantidadValores = lineInforme.size();
        int condicion=0;

        do {
            for(int valor=lineInforme.size()-1;valor>0+condicion;valor--)
            {

                int diferenciaValor = lineInforme.get(valor) - lineInforme.get(valor-1);
                lineInforme.set(valor,diferenciaValor);

            }
            condicion++;
        }while(condicion!= cantidadValores);

        for(int valor=lineInforme.size()-1;valor>0; valor--)
        {
            lineInforme.set(valor-1,lineInforme.get(valor-1)- lineInforme.get(valor));
        }

        return lineInforme.get(0);
    }
}
