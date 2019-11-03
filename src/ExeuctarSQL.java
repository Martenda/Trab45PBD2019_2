/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CodePointCharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import br.udesc.udescdb.SQLiteBaseListener;
import br.udesc.udescdb.SQLiteLexer;
import br.udesc.udescdb.SQLiteParser;
import org.antlr.v4.runtime.RecognitionException;

/**
 *
 * @author Lucas Martendal
 */
public abstract class ExeuctarSQL {
    
    public static boolean Executar(String sql) {
//        sql = "create table xpto (col1 int, col2 char(20), col3 float)";
//        sql = "insert into xpto (col1, col2) values (1, 'abc')";
//        sql = "select * from xpto";

        CodePointCharStream inputStream = CharStreams.fromString(sql);
        SQLiteLexer lexer = new SQLiteLexer(inputStream);
        CommonTokenStream cts = new CommonTokenStream(lexer);
        SQLiteParser parser = new SQLiteParser(cts);
        parser.setBuildParseTree(true);
        
        try {
            ParseTree tree = parser.parse();

            ParseTreeWalker ptw = new ParseTreeWalker();        
            ptw.walk(new SQLiteBaseListener(), tree);
            
        } catch (RecognitionException e) {
            return false;
        };

        // agora vamos pegar as informacoes que o listener capturou e processar o comando
    
        
        
        return true;
    }
    
}
