package ci.textanalysis.lucene.impl;

import java.io.Reader;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.LowerCaseFilter;
import org.apache.lucene.analysis.PorterStemFilter;
import org.apache.lucene.analysis.StopFilter;
import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.standard.StandardTokenizer;

import weps.util.TextFile;

public class PorterStemStopWordAnalyzer extends Analyzer {

    public static String[] stopWords =  {"y","de","la","las","los","ellos","ellas","pueden",
    									"yo", "tu", "el","ella", "nosotros", "podemos", "con",
    									"ese", "que", "un", "unas", "esta", "este", "si", "no",
    									"a", "en","haber","hacen","hizo","por","uno", "mi", "muy",
    									"buen", "estos", "sean", "ver", "seamos", "seas", "te"};
      //  = new TextFile("asset/stopwords.txt").toArray(new String[0]);
    
    @Override
    public TokenStream tokenStream(String fieldName, Reader reader) {
        Tokenizer tokenizer = new StandardTokenizer(reader);
        TokenFilter lowerCaseFilter = new LowerCaseFilter(tokenizer);
        TokenFilter stopFilter = new StopFilter(lowerCaseFilter, stopWords);
        TokenFilter stemFilter = new PorterStemFilter(stopFilter);
        return stemFilter;
    }

}
