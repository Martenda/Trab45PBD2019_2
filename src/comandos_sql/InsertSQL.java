/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comandos_sql;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 *
 * @author Lucas Martendal
 */
public abstract class InsertSQL {
    
    public static boolean Insert(String nomeBase, String nomeTabela) throws FileNotFoundException, IOException {
        //Insert - Verificando se o Banco de Dados existe, caso tenha sido digitado o nome do banco
        if (!nomeBase.isEmpty()) {
            File diretorio = new File(nomeBase);
            if (!diretorio.exists()) {
                System.out.println("Esse Banco de Dados não existe");
                //Exibir mensagem de erro ("Esse Banco de Dados não existe")
                return false;
            }
        }
        
        //Insert - Verificando se a Tabela existe
        File diretorio = new File(nomeTabela);
        if (!diretorio.exists()) {
            System.out.println("Essa Tabela não existe");
            //Mensagem de erro ("Essa Tabela não existe")
            return false;
        }
        
        //Insert - Criando o raf do arquivo da Tabela
        RandomAccessFile raf = new RandomAccessFile(nomeBase + nomeTabela + ".dat", "rw");
        
        //Insert - Pegando o Tamanho do Registro (TAMREG) nos MetaDados do arquivo
        int TAMREG = 7;
        
//        System.out.println(view.list().get(0));
//        
//        ByteBuffer buf = ByteBuffer.allocateDirect(view.size("Tes"));
//        view.read("Tes", buf);
//        buf.flip();
//        System.out.println(Charset.defaultCharset().decode(buf).toString());
        
        //Insert - Pulando com o ponteiro pelo arquivo da Tabela para pegar o Registro desejado
        raf.seek(3 * TAMREG); //3° registro - vai gravar como último - length/size
        
//        byte[] reg = Arrays.copyOf("XYZABCD".getBytes(), 3);
//        raf.write(reg);
        
        //Insert - Escrevendo os dados no arquivo da Tabela
        raf.write("XYZABCD".getBytes(), 0, 3);
        raf.writeUTF("XYZABC");
        raf.writeInt(1);
        raf.writeInt(12345);
        raf.writeFloat(12345);
        raf.writeBoolean(true);
        raf.writeDouble(123456.789012);
        
        //Fechando o raf
        raf.close();
        
        return true;
    }
    
}
